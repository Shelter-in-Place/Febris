package com.project.febris.persistence;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.project.febris.API.Details;
import com.project.febris.API.ResultModel;
import com.project.febris.API.ResultsAPI;
import com.project.febris.async.DeleteAllFavouritesAsyncTask;
import com.project.febris.async.DeleteAsyncTask;
import com.project.febris.async.DeleteFavouriteAsyncTask;
import com.project.febris.async.InsertAsyncTask;
import com.project.febris.async.InsertFavouriteAsyncTask;
import com.project.febris.models.FavouritesPlace;
import com.project.febris.models.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static final String TAG = "Repository";

    private Database mDatabase;
    private Retrofit retrofit;
    private ResultsAPI resultsAPI;


    public Repository(Context context) {

        mDatabase = Database.getInstance(context);
        initRetrofit();
        callRetrofit();
    }

    // RETROFIT

    public void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pomber.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        resultsAPI = retrofit.create(ResultsAPI.class);
    }

    public void callRetrofit(){

        Call<ResultModel> call = resultsAPI.getResult();
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                Log.d(TAG, "Repository: Success -->" + response.code());
                deleteAll();
                List<List<Details>> countries = response.body().getCountries();
                List<String> countryNames = response.body().getCountriesList();
                for(int i=0; i < countries.size() - 1; i++){
                    String currentCountryName = countryNames.get(i);
                    Details currentCountry = countries.get(i).get(countries.get(i).size()-1);
                    Place place = new Place(i+1, currentCountryName, "", currentCountry.getConfirmed(), currentCountry.getDeaths(), currentCountry.getRecovered(),false);
                    insertPlaceTask(place);
                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                Log.d(TAG, "Repository: Failure --> " + t);
            }
        });

    }

    public void cacheData(ResultModel resultModel){
//        deleteAll();
//        Details det = resultModel.getAfghanistan().get(2);
//        int confirmed = det.getConfirmed();
//        Log.d(TAG, "THIS" + confirmed);
//        Place place = new Place(1, "Afghanistan", "address", confirmed, 1, 1);
//        insertPlaceTask(place);

    }

    // DATABASE METHODS

    //Places
    public void insertPlaceTask(Place place){
        new InsertAsyncTask(mDatabase.getNoteDao()).execute(place);
    }

    public void updatePlaces(Place place){

    }

    public LiveData<List<Place>> retrievePlacesTask(){
        return mDatabase.getNoteDao().getPlaces();
    }

    public void deletePlace(Place place){

    }

    public void deleteAll() {
        new DeleteAsyncTask(mDatabase.getNoteDao()).execute();
    }

    public int isFavourite(int id){return mDatabase.getNoteDao().isFavorite(id);}



    //Favourites
    public void insertFavouriteTask(FavouritesPlace favourite){
        new InsertFavouriteAsyncTask(mDatabase.getFavouritesDao()).execute(favourite);
    }

    public void updateFavourites(FavouritesPlace favourite){

    }

    public LiveData<List<FavouritesPlace>> retrieveFavouritesTask(){
        return mDatabase.getFavouritesDao().getFavourites();
    }

    public void deleteFavourite(FavouritesPlace favourite){
        new DeleteFavouriteAsyncTask(mDatabase.getFavouritesDao()).execute();
    }

    public void deleteAllFavourites() {
        new DeleteAllFavouritesAsyncTask(mDatabase.getFavouritesDao()).execute();
    }



}

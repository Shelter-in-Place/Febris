package com.project.febris.persistence;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.project.febris.API.Details;
import com.project.febris.API.ResultModel;
import com.project.febris.API.ResultsAPI;
import com.project.febris.async.DeleteAsyncTask;
import com.project.febris.async.InsertAsyncTask;
import com.project.febris.async.UpdateAsyncTask;
import com.project.febris.models.Place;

import java.io.IOException;
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
//        testDataFList();
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

    // DATABASE METHODS

    //Places
    public void insertPlaceTask(Place place){
        new InsertAsyncTask(mDatabase.getNoteDao()).execute(place);
    }

    public void updatePlaces(Place place){
        new UpdateAsyncTask(mDatabase.getNoteDao()).execute(place);
    }

    public LiveData<List<Place>> retrievePlacesTask(){
        return mDatabase.getNoteDao().getPlaces();
    }

    public List<Place> retrievePlacesTaskNonLiveData(){
        return mDatabase.getNoteDao().getPlacesNonLiveData();
    }

    public LiveData<Place> getSpecificPlace(String placeName){
        return mDatabase.getNoteDao().getSpecificPlace(placeName);
    }

    public void deleteAll() {
        new DeleteAsyncTask(mDatabase.getNoteDao()).execute();
    }


    public LiveData<List<Place>> getFavPlaces(){
        return mDatabase.getNoteDao().getFavPlaces();
    }




}

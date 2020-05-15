package com.project.febris.persistence;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.febris.API.SummaryResponse;
//import com.project.febris.models.Details;
//import com.project.febris.models.ResultModel;
import com.project.febris.API.ResultsAPI;
import com.project.febris.API.ServiceGenerator;
import com.project.febris.async.DeleteAsyncTask;
import com.project.febris.async.InsertAsyncTask;
import com.project.febris.async.UpdateAsyncTask;
import com.project.febris.models.Place;
import com.project.febris.models.SummaryDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository {

    private static final String TAG = "Repository";

    private Database mDatabase;
    private Retrofit retrofit;

    private ResultsAPI mResultsAPI;

    private Retrofit retrofitJSON;
    private ResultsAPI resultsAPIJSON;
    private ServiceGenerator mServiceGenerator;
    private static Repository instance;

    public Repository(Context context) {

        mDatabase = Database.getInstance(context);
        initRetrofit();
        callRetrofit();
//        callRetrofitJSON();
    }

    // RETROFIT

    public void initRetrofit(){
        mServiceGenerator = new ServiceGenerator();
        mResultsAPI = mServiceGenerator.getResultsAPI();

//        retrofit = new Retrofit.Builder()
//                .baseUrl("https://pomber.github.io/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        resultsAPI = retrofit.create(ResultsAPI.class);
//
//        retrofitJSON = new Retrofit.Builder()
//                .baseUrl("https://pomber.github.io/")
//                .build();
//        resultsAPIJSON = retrofitJSON.create(ResultsAPI.class);
    }

    public void callRetrofit(){

        Call<SummaryResponse> call = mResultsAPI.getCountryData();
        call.enqueue(new Callback<SummaryResponse>() {
            @Override
            public void onResponse(Call<SummaryResponse> call, Response<SummaryResponse> response) {
                Log.d(TAG, "onResponse: this is happening on thread: "+Thread.currentThread().getName());

                deleteAll();
//                Log.d(TAG, "onResponse: qqq" + response.body().hashCode());
                List<SummaryDetails> countries = response.body().getCountries();

                for(int i=0; i < countries.size() - 1; i++){
                    String currentCountryName = countries.get(i).getCountry();
                    int totConfirmedCases = countries.get(i).getTotalConfirmed();
                    int totDeaths = countries.get(i).getTotalDeaths();
                    int totRecovered = countries.get(i).getTotalRecovered();
                    String latestUpdate = countries.get(i).getDate();

//                    Details currentCountry = countries.get(i).get(countries.get(i).size()-1);
                    Place place = new Place(
                            i+1,
                            currentCountryName,
                            "",
                            totConfirmedCases,
                            totDeaths,
                            totRecovered,
                            false,
                            latestUpdate);
                    place.setPresent(true);
                    insertPlaceTask(place);
                }
            }

            @Override
            public void onFailure(Call<SummaryResponse> call, Throwable t) {
                Log.d(TAG, "Repository: Failure --> " + t);
            }
        });

    }

//    private void callRetrofitJSON(){
//        Call<ResultModel> call = resultsAPIJSON.getSeparateResult();
//        call.enqueue(new Callback<ResultModel>() {
//            @Override
//            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
//                Log.d(TAG, "onResponse: qqqq" + response.raw());
//            }
//
//            @Override
//            public void onFailure(Call<ResultModel> call, Throwable t) {
//                Log.d(TAG, "onFailure: ");
//            }
//        });
//    }

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

    public LiveData<List<Place>> getSelectedCountry(){
        return mDatabase.getNoteDao().getSelectedCountry();
    }


}

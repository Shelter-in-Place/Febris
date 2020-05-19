package com.project.febris.persistence;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.project.febris.API.CountryResponse;
import com.project.febris.API.SummaryAPIClient;
import com.project.febris.API.SummaryResponse;
import com.project.febris.API.ResultsAPI;
import com.project.febris.API.ServiceGenerator;
import com.project.febris.async.DeleteAsyncTask;
import com.project.febris.async.InsertAsyncTask;
import com.project.febris.async.UpdateAsyncTask;
import com.project.febris.models.Place;
import com.project.febris.API.SummaryDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static final String TAG = "Repository";
    private Database mDatabase;
    private ResultsAPI mResultsAPI;
    private ServiceGenerator mServiceGenerator;
    private SummaryAPIClient mSummaryAPIClient;


    public Repository(Context context) {
        mDatabase = Database.getInstance(context);
        mSummaryAPIClient = SummaryAPIClient.getInstance();
        initRetrofit();
        callRetrofit();
    }

    // RETROFIT
    public void initRetrofit(){
        mServiceGenerator = new ServiceGenerator();
        mResultsAPI = mServiceGenerator.getResultsAPI();
    }

    public void callRetrofit(){
        deleteAll();
        mSummaryAPIClient.setSummaryCountries();


//        Log.d(TAG, "callRetrofit: called");
//        Call<SummaryResponse> call = mResultsAPI.getSummaryData();
//        call.enqueue(new Callback<SummaryResponse>() {
//            @Override
//            public void onResponse(Call<SummaryResponse> call, Response<SummaryResponse> response) {
//                Log.d(TAG, "onResponse: this is happening on thread: "+Thread.currentThread().getName());
//
//                deleteAll();
//                if(response.body() != null){
//                    List<SummaryDetails> countries = response.body().getCountries();
//                    for(int i=0; i < countries.size() - 1; i++){
//                        // 1. Setting local variables for loop
//                        String currentCountryName = countries.get(i).getCountry();
//                        int totConfirmedCases = countries.get(i).getTotalConfirmed();
//                        int totDeaths = countries.get(i).getTotalDeaths();
//                        int totRecovered = countries.get(i).getTotalRecovered();
//                        String latestUpdate = countries.get(i).getDate();
//
//                        // 2. Creating new Place using local variables on loop
//                        Place place = new Place(null,
//                                currentCountryName,
//                                "",
//                                totConfirmedCases,
//                                totDeaths,
//                                totRecovered,
//                                false,
//                                latestUpdate);
//                        place.setPresent(true);
//
//                        // 3. Inserting new Place into the DB
//                        insertPlaceTask(place);
//                    }
//                }
//                else{
//                    Log.d(TAG, "onResponse: init call - response was null");
//                    callRetrofit(); // Fix this to avoid an infinite loop!!!
//                }

//            }


//            @Override
//            public void onFailure(Call<SummaryResponse> call, Throwable t) {
//                Log.d(TAG, "Repository: Failure --> " + t);
//                callRetrofit();
//            }
//        });

    }

    public void callRetrofitSpecificCountryData(String place){
//        Log.d(TAG, "callRetrofitSpecificCountryData: qqq called");
//        Call<List<CountryResponse>> call = mResultsAPI.getCountryData(place);
//        call.enqueue(new Callback<List<CountryResponse>>() {
//            @Override
//            public void onResponse(Call<List<CountryResponse>> call, Response<List<CountryResponse>> response) {
//                Log.d(TAG, "onResponse: this is happening on thread: qqq "+Thread.currentThread().getName());
//
//                if(response.body() != null){
//                    Log.d(TAG, "onResponse: qqq Success");
//                    for(int i = 0; i< response.body().size(); i++){
//                        Place place = new Place(null,
//                                response.body().get(i).getCountry(),
//                                "",
//                                Integer.parseInt(response.body().get(i).getConfirmed()),
//                                Integer.parseInt(response.body().get(i).getDeaths()),
//                                Integer.parseInt(response.body().get(i).getRecovered()),
//                                false,
//                                response.body().get(i).getDate());
//                        place.setSelected(true);
//                        insertPlaceTask(place);
//                    }
//
//                }
//                else{
//                    Log.d(TAG, "onResponse: qqq response was null");
////                    callRetrofitSpecificCountryData();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<CountryResponse>> call, Throwable t) {
//                Log.d(TAG, "Repository: Failure --> qqq " + t);
////                callRetrofitSpecificCountryData();
//            }
//        });

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
        Log.d(TAG, "retrievePlacesTask: called");
        return mDatabase.getNoteDao().getPlaces();
    }

    public List<Place> retrievePlacesTaskNonLiveData(){
        return mDatabase.getNoteDao().getPlacesNonLiveData();
    }

    public LiveData<Place> getSpecificPlace(String placeName){
        return mDatabase.getNoteDao().getSpecificPlace(placeName);
    }

    public void deleteAll() {
        Log.d(TAG, "deleteAll: called");
        new DeleteAsyncTask(mDatabase.getNoteDao()).execute();
    }


    public LiveData<List<Place>> getFavPlaces(){
        return mDatabase.getNoteDao().getFavPlaces();
    }

    public LiveData<List<Place>> getSelectedCountry(){
        return mDatabase.getNoteDao().getSelectedCountry();
    }


}

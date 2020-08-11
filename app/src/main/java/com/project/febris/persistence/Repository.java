package com.project.febris.persistence;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

//import com.project.febris.API.CountryAPIClient;
//import com.project.febris.API.CountryAPIClient;
//import com.project.febris.API.CountryResponse;
import com.project.febris.API.SummaryAPIClient;
import com.project.febris.API.SummaryResponse;
import com.project.febris.API.ResultsAPI;
import com.project.febris.API.ServiceGenerator;
import com.project.febris.async.DeleteAsyncTask;
import com.project.febris.async.DeleteDatasetsAsyncTask;
import com.project.febris.async.InsertAsyncTask;
import com.project.febris.async.UpdateAsyncTask;
import com.project.febris.models.Place;
import com.project.febris.API.SummaryDetails;
import com.project.febris.models.PlaceWithDatasets;

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
//    private CountryAPIClient mCountryAPIClient;


    public Repository(Context context) {
        mDatabase = Database.getInstance(context);
        mSummaryAPIClient = SummaryAPIClient.getInstance(mDatabase);

        if (retrievePlacesTask().getValue() == null) {
            callRetrofit();
        }
//        deleteAll();
//        deleteAllDatasets();
    }


    // RETROFIT METHODS
    public void callRetrofit(){
        mSummaryAPIClient.setSummaryCountries(1, "noCountry");
    }

    public void callRetrofitSpecificCountryData(String countryKeyID){
        mSummaryAPIClient.setSummaryCountries(2, countryKeyID);
    }

//    public void callRetrofitSpecificCountryData(String place){
//        Log.d(TAG, "callRetrofitSpecificCountryData: qqq called");
//        mCountryAPIClient.setSpecificCountries(place);
//    }


    // DATABASE METHODS
    public void insertPlaceTask(Place place){
        new InsertAsyncTask(mDatabase.getNoteDao()).execute(place);
    }

    public void updatePlaces(Place place){
        new UpdateAsyncTask(mDatabase.getNoteDao()).execute(place);
    }

    public LiveData<List<PlaceWithDatasets>> retrievePlacesTask(){
        Log.d(TAG, "retrievePlacesTask: called");
        return mDatabase.getNoteDao().getPlaces();
    }

    public void deleteAll() {
        Log.d(TAG, "deleteAll: called");
        new DeleteAsyncTask(mDatabase.getNoteDao()).execute();
    }
    public void deleteAllDatasets() {
        Log.d(TAG, "deleteAllDatasets: called");
        new DeleteDatasetsAsyncTask(mDatabase.getNoteDao()).execute();
    }


    public LiveData<List<PlaceWithDatasets>> getFavPlaces(){
        return mDatabase.getNoteDao().getFavPlaces();
    }

    public LiveData<List<PlaceWithDatasets>> getPlacesWithDatasets(){
        return mDatabase.getNoteDao().getPlacesWithDatasets();
    }

//    public LiveData<List<Place>> getSelectedCountry(){
//        return mDatabase.getNoteDao().getSelectedCountry();
//    }

}

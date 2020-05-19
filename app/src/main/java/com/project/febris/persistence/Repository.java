package com.project.febris.persistence;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

//import com.project.febris.API.CountryAPIClient;
import com.project.febris.API.CountryAPIClient;
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
    private CountryAPIClient mCountryAPIClient;


    public Repository(Context context) {
        mDatabase = Database.getInstance(context);
        mSummaryAPIClient = SummaryAPIClient.getInstance(mDatabase);
        mCountryAPIClient = CountryAPIClient.getInstance(mDatabase);
//        initRetrofit();
        callRetrofit();
    }

    // RETROFIT
//    public void initRetrofit(){
//        mServiceGenerator = new ServiceGenerator();
//        mResultsAPI = mServiceGenerator.getResultsAPI();
//    }

    public void callRetrofit(){
        deleteAll();
        mSummaryAPIClient.setSummaryCountries();

    }

    public void callRetrofitSpecificCountryData(String place){
        Log.d(TAG, "callRetrofitSpecificCountryData: qqq called");
        mCountryAPIClient.setSpecificCountries(place);
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
//                                response.body().get(i).getConfirmed(),
//                                response.body().get(i).getDeaths(),
//                                response.body().get(i).getRecovered(),
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

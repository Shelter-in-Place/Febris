package com.project.febris.API;

import android.provider.ContactsContract;
import android.util.Log;

import com.project.febris.models.Dataset;
import com.project.febris.models.Place;
import com.project.febris.persistence.Dao;
import com.project.febris.persistence.Database;
import com.project.febris.ui.main.AppExecutors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.project.febris.util.Constants.NETWORK_TIMEOUT;

public class SummaryAPIClient {
    private static final String TAG = "SummaryAPIClient";

    private static SummaryAPIClient instance;
    private GetSummaryCountriesRunnable mGetSummaryCountriesRunnable;
    private Database mDatabase;

    public static SummaryAPIClient getInstance(Database database){
        Log.d(TAG, "getInstance: called");
        if(instance==null){
            instance = new SummaryAPIClient(database);
        }
        return instance;
    }

    public SummaryAPIClient(Database database) {
        mDatabase = database;
    }

    public void setSummaryCountries(int option, String countryKeyID){
        if(mGetSummaryCountriesRunnable!=null){
            mGetSummaryCountriesRunnable=null;
        }
        Log.d(TAG, "setSummaryCountries: runnable created");
        mGetSummaryCountriesRunnable = new GetSummaryCountriesRunnable(option, countryKeyID);

        final Future handler = AppExecutors.getInstance().networkIO().submit(mGetSummaryCountriesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
        @Override
        public void run() {
            //let user know ts timed out
            Log.d(TAG, "run: timed out!");
                handler.cancel(true);
        }
        },NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class GetSummaryCountriesRunnable implements Runnable{

        int mOption;
        String mCountryKeyID;
        boolean cancelRequest;

        public GetSummaryCountriesRunnable(int option, String countryKeyID) {
            mOption = option;
            mCountryKeyID = countryKeyID;
            Log.d(TAG, "GetSummaryCountriesRunnable: called: cancel request is false");
            cancelRequest = false;
        }

        @Override
        public void run() {
            Log.d(TAG, "run: thread: this is happening on thread: getCountries to execute"+ Thread.currentThread().getName());
            Response<List<SummaryResponse>> response;
            try{
                Log.d(TAG, "run: getCountries executed");
                if (mOption == 1){
                    response = getCountries().execute();
                    Log.d(TAG, "run: getCountries executed11 " + response.code());
                }
                else{
                    response = getSpecificCountry(mCountryKeyID).execute();
                    Log.d(TAG, "run: getCountries executed112 " + response.code());
                }

                if(cancelRequest){
                    Log.d(TAG, "run: cancelRequest = true");
                    return;
                }
                if(response.code()==200){

                    Log.d(TAG, "run: response code is 200" + " " + mOption + " " + response.body().get(0).getData().size() + " countries size: " + response.body().size());
                    List<SummaryResponse> countries = response.body();
                    for(int i=0; i <= countries.size() - 1; i++){
                        Log.d(TAG, "for loop run " + i);
                        Place place = new Place(
                                countries.get(i).getCountry_key_id(),
                                countries.get(i).getContinent(),
                                countries.get(i).getLocation(),
                                countries.get(i).getPopulation(),
                                "",
                                false);

                        try {
                            // 2. Inserting new Place into the DB
                            if(mDatabase.getNoteDao().getSpecificPlace(countries.get(i).getCountry_key_id()) != null){
                                Log.d(TAG, "made it here");
                                mDatabase.getNoteDao().updatePlaces(place);
                                Log.d(TAG, "run: updated -->" + place.getLocation());
                            }
                            else{
                                Log.d(TAG, "made it here");
                                mDatabase.getNoteDao().insertPlaces(place);
                                Log.d(TAG, "run: inserted --> " + place.getLocation());
                            }
                        }
                        catch (Exception e){
                            Log.d(TAG, "exceeepetion 111" + e);
                            e.printStackTrace();
                        }



                        // 3. Setting local variables for datasets loop
                        List<SummaryDetails> datasets = countries.get(i).getData();
                        try{
                            Log.d(TAG, "datasets size: " + datasets.size());
                        }
                        catch (Exception e){
                            Log.d(TAG, "exceeepetion 111" + e);
                            e.printStackTrace();
                        }

                        for (int x=0; x <= datasets.size() - 1; x++){

                            Log.d(TAG, "run: --> dataset " + datasets.get(x).getDate());
                            Dataset dataset = new Dataset(null, datasets.get(x).getDate(), datasets.get(x).getTotal_cases(), datasets.get(x).getNew_cases(), datasets.get(x).getTotal_deaths(), datasets.get(x).getNew_deaths(),
                                    datasets.get(x).getTotal_cases_per_million(), datasets.get(x).getNew_cases_per_million(), datasets.get(x).getTotal_deaths_per_million(), datasets.get(x).getNew_deaths_per_million(), datasets.get(x).getCountry_key());

                            // 4. Inserting new datasets into the DB
                            if(mDatabase.getNoteDao().getSpecificDataset(datasets.get(x).getDate(), datasets.get(x).getCountry_key()) != null){
                                mDatabase.getNoteDao().updateDatasets(dataset);
                                Log.d(TAG, "run: updated -->" + dataset.getCountry_key() + " " + dataset.getDate());
                            }
                            else{
                                mDatabase.getNoteDao().insertDatasets(dataset);
                                Log.d(TAG, "run: inserted --> " + dataset.getDate());
                            }
                        }

                    }
                }

            }
            catch(IOException e){
                Log.d(TAG, "exceeepetion " + e);
                e.printStackTrace();
            }
        }

        private Call<List<SummaryResponse>> getCountries(){
            Log.d(TAG, "getCountries: called");
            return ServiceGenerator.getResultsAPI().getSummaryData();
        }

        private Call<List<SummaryResponse>> getSpecificCountry(String countryKeyID){
            Log.d(TAG, "getCountries: called");
            return ServiceGenerator.getResultsAPI().getCountryData(countryKeyID);
        }

    }

    public void processData(List<SummaryResponse> countries){
        // 1. Setting local variables for places loop
        for(int i=0; i <= countries.size() - 1; i++){
            Place place = new Place(
                    countries.get(i).getCountry_key_id(),
                    countries.get(i).getContinent(),
                    countries.get(i).getLocation(),
                    countries.get(i).getPopulation(),
                    "",
                    false);

            // 2. Inserting new Place into the DB
            if(mDatabase.getNoteDao().getSpecificPlace(countries.get(i).getCountry_key_id()) != null){
                mDatabase.getNoteDao().updatePlaces(place);
                Log.d(TAG, "run: updated -->" + place.getLocation());
            }
            else{
                mDatabase.getNoteDao().insertPlaces(place);
                Log.d(TAG, "run: inserted --> " + place.getLocation());
            }

            // 3. Setting local variables for datasets loop
            List<SummaryDetails> datasets = countries.get(i).getData();
            for (int x=0; x <= datasets.size() - 1; x++){

                Log.d(TAG, "run: --> dataset " + datasets.get(x).getDate());
                Dataset dataset = new Dataset(null, datasets.get(x).getDate(), datasets.get(x).getTotal_cases(), datasets.get(x).getNew_cases(), datasets.get(x).getTotal_deaths(), datasets.get(x).getNew_deaths(),
                        datasets.get(x).getTotal_cases_per_million(), datasets.get(x).getNew_cases_per_million(), datasets.get(x).getTotal_deaths_per_million(), datasets.get(x).getNew_deaths_per_million(), datasets.get(x).getCountry_key());

                // 4. Inserting new datasets into the DB
                if(mDatabase.getNoteDao().getSpecificDataset(datasets.get(x).getDate(), datasets.get(x).getCountry_key()) != null){
                    mDatabase.getNoteDao().updateDatasets(dataset);
                    Log.d(TAG, "run: updated -->" + dataset.getCountry_key() + " " + dataset.getDate());
                }
                else{
                    mDatabase.getNoteDao().insertDatasets(dataset);
                    Log.d(TAG, "run: inserted --> " + dataset.getDate());
                }
            }

        }
    }
}

package com.project.febris.API;

import android.provider.ContactsContract;
import android.util.Log;

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

    public void setSummaryCountries(){
        if(mGetSummaryCountriesRunnable!=null){
            mGetSummaryCountriesRunnable=null;
        }
        Log.d(TAG, "setSummaryCountries: runnable created");
        mGetSummaryCountriesRunnable = new GetSummaryCountriesRunnable();
        
        final Future handler = AppExecutors.getInstance().networkIO().submit(mGetSummaryCountriesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let user know ts timed out
                Log.d(TAG, "run: timed out!");
//                handler.cancel(true);
            }
        },NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class GetSummaryCountriesRunnable implements Runnable{


        boolean cancelRequest;

        public GetSummaryCountriesRunnable() {
            Log.d(TAG, "GetSummaryCountriesRunnable: called: cancel request is false");
            cancelRequest = false;
        }

        @Override
        public void run() {
            Log.d(TAG, "run: thread: this is happening on thread: "+ Thread.currentThread().getName());

            try{
                Response response = getCountries().execute();
                Log.d(TAG, "run: getCountries executed");
                if(cancelRequest){
                    Log.d(TAG, "run: cancelRequest = true");
                    return;
                }
                if(response.code()==200){
                    Log.d(TAG, "run: response code is 200");
                    List<SummaryDetails> countries = new ArrayList<>(((SummaryResponse)response.body()).getCountries());
                    Log.d(TAG, "run: countries list size = "+countries.size());
                    Log.d(TAG, "run: country 10 "+countries.get(10).getCountry());
                    for(int i=0; i < countries.size() - 1; i++){
                        // 1. Setting local variables for loop
                        String currentCountryName = countries.get(i).getCountry();
                        int totConfirmedCases = countries.get(i).getTotalConfirmed();
                        int totDeaths = countries.get(i).getTotalDeaths();
                        int totRecovered = countries.get(i).getTotalRecovered();
                        String latestUpdate = countries.get(i).getDate();

                        // 2. Creating new Place using local variables on loop
                        Place place = new Place(null,
                                currentCountryName,
                                "",
                                totConfirmedCases,
                                totDeaths,
                                totRecovered,
                                false,
                                latestUpdate);
                        place.setPresent(true);
                        Log.d(TAG, "run: print details: ");

                        // 3. Inserting new Place into the DB
                        mDatabase.getNoteDao().insertPlaces(place);

                    }
                }

            }
            catch(IOException e){
                e.printStackTrace();
            }

        }

        private Call<SummaryResponse> getCountries(){
            Log.d(TAG, "getCountries: called");
            return ServiceGenerator.getResultsAPI().getSummaryData();
        }
    }
}

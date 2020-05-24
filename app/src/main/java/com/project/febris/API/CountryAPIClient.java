package com.project.febris.API;

import android.util.Log;

import com.project.febris.models.Place;
import com.project.febris.persistence.Database;
import com.project.febris.ui.main.AppExecutors;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.project.febris.util.Constants.NETWORK_TIMEOUT;

public class CountryAPIClient {
    private static final String TAG = "CountryAPIClient";

    private static CountryAPIClient instance;
    private GetSpecificCountryRunnable mGetSpecificCountryRunnable;
    private Database mDatabase;

    private List<Place> placeslist = new ArrayList<>();


    public static CountryAPIClient getInstance(Database database){
        Log.d(TAG, "getInstance: called");
        if(instance==null){
            instance = new CountryAPIClient(database);
        }
        return instance;
    }

    public CountryAPIClient(Database database) {
        mDatabase = database;
    }

    public void setSpecificCountries(String place){
        if(mGetSpecificCountryRunnable!=null){
            mGetSpecificCountryRunnable=null;
        }
        Log.d(TAG, "setSpecificCountrz: runnable created");
        mGetSpecificCountryRunnable = new GetSpecificCountryRunnable(place);

        AppExecutors.getInstance().networkIO().submit(mGetSpecificCountryRunnable);

//        final Future handler = AppExecutors.getInstance().networkIO().submit(mGetSpecificCountryRunnable);
//        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
//            @Override
//            public void run() {
//                //let user know ts timed out
//                Log.d(TAG, "run: timed out!");
////                handler.cancel(true);
//            }
//        },NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class GetSpecificCountryRunnable implements Runnable{

        String mPlace;
        boolean cancelRequest;

        public GetSpecificCountryRunnable(String place) {
            Log.d(TAG, "GetSpecificCountryRunnable: called: cancel request is false");
            cancelRequest = false;
            mPlace = place;
        }

        @Override
        public void run() {
            Log.d(TAG, "run: thread: this is happening on thread: "+ Thread.currentThread().getName());

            try{
                Response<List<CountryResponse>> response = getCountry(mPlace).execute();
                List<CountryResponse> country = response.body();



                Log.d(TAG, "run: getCountries executed");
                if(cancelRequest){
                    Log.d(TAG, "run: cancelRequest = true");
                    return;
                }
                if(response.code() == 200){
                    Log.d(TAG, "onResponse: qqq Success");
                    Log.d(TAG, "run: country array size = " + country.size());



                    for(int i = 0; i< country.size(); i++){
                        Place place = new Place(null,
                                country.get(i).getCountry(),
                                "",
                                country.get(i).getConfirmed(),
                                country.get(i).getDeaths(),
                                country.get(i).getRecovered(),
                                false,
                                country.get(i).getDate());
                        place.setPresent(false);
                        place.setSelected(true);

                        Log.d(TAG, "run: place "+toString());

                        placeslist.add(place);

                    }
                    //Inserts the whole list rather than place by place in the for loop above
//                    mDatabase.getNoteDao().deleteSpecificPlaceList();
                    mDatabase.getNoteDao().insertPlacesList(placeslist);
                }
                else{
                    Log.d(TAG, "onResponse: qqq response was null");
//                    callRetrofitSpecificCountryData();
                }

            }
            catch(IOException e){
                e.printStackTrace();
            }

        }

        private Call<List<CountryResponse>> getCountry(String place){
            Log.d(TAG, "getCountry: called");
            return ServiceGenerator.getResultsAPI().getCountryData(place);
        }
    }
}

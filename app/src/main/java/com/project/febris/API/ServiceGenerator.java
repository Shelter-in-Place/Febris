package com.project.febris.API;

import android.nfc.Tag;
import android.util.Log;

import com.project.febris.util.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String TAG = "ServiceGenerator";

//    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//            .build();

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static ResultsAPI resultsAPI = retrofit.create(ResultsAPI.class);


    public static ResultsAPI getResultsAPI(){
        Log.d(TAG, "getResultsAPI: called " + resultsAPI.toString());
        return resultsAPI;
    };
}

package com.project.febris.API;

import com.project.febris.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static ResultsAPI resultsAPI = retrofit.create(ResultsAPI.class);


    public static ResultsAPI getResultsAPI(){
        return resultsAPI;
    };
}

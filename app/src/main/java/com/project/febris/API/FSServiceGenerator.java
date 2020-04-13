package com.project.febris.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FSServiceGenerator {

    static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create());

    static Retrofit retrofit = builder.build();

}

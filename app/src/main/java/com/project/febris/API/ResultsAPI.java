package com.project.febris.API;

//import com.project.febris.models.ResultModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResultsAPI {

//    @GET("covid19/timeseries.json")
//    Call<ResultModel> getResult();
//
//    @GET("covid19/timeseries.json")
//    Call<ResultModel> getSeparateResult();


    //Summary Data
    @GET("summary")
    Call<SummaryResponse> getCountryData();
}


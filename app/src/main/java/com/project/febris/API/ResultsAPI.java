package com.project.febris.API;

//import com.project.febris.models.ResultModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ResultsAPI {

//    @GET("covid19/timeseries.json")
//    Call<ResultModel> getResult();

    //Summary Data
    @GET("latest_data_all_countries/")
    Call<List<SummaryResponse>> getSummaryData();

    @GET("countries/{place}")
    Call<List<SummaryResponse>> getCountryData(@Path("place") String place);
}


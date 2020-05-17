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
    @GET("summary")
    Call<SummaryResponse> getSummaryData();

    @GET("total/dayone/country/{place}")
    Call<List<CountryResponse>> getCountryData(@Path("place") String place);
}


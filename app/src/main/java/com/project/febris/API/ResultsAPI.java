package com.project.febris.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResultsAPI {

    @GET("covid19/timeseries.json")
    Call<ResultModel> getResult();

    @GET("covid19/timeseries.json")
    Call<ResultModel> getSeparateResult();

}

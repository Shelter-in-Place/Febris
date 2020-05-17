package com.project.febris.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SummaryResponse {

    //Variables for getSummaryData()
    @SerializedName("Countries")
    @Expose
    private List<SummaryDetails> countries;
    public List<SummaryDetails> getCountries(){
        return countries;
    }


    private List<SummaryDetails> specificCountries;

    public List<SummaryDetails> getSpecificCountries() {
        return specificCountries;
    }

    @Override
    public String toString() {
        return "SummaryResponse{" +
                "countries=" + countries +
                '}';
    }
}

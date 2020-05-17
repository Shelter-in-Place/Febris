package com.project.febris.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.febris.models.SummaryDetails;

import java.util.List;

public class SummaryResponse {

    @SerializedName("Countries")
    @Expose
    private List<SummaryDetails> countries;

    public List<SummaryDetails> getCountries(){
        return countries;
    }

    @Override
    public String toString() {
        return "SummaryResponse{" +
                "countries=" + countries +
                '}';
    }
}

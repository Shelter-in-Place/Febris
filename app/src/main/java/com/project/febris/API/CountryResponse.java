package com.project.febris.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryResponse {

    @SerializedName("Country")
    @Expose
    private String country;

    @SerializedName("Confirmed")
    @Expose
    private String confirmed;

    @SerializedName("Deaths")
    @Expose
    private String deaths;

    @SerializedName("Recovered")
    @Expose
    private String recovered;


    @SerializedName("Date")
    @Expose
    private String date;

    public String getCountry() {
        return country;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDate() {
        return date;
    }
}

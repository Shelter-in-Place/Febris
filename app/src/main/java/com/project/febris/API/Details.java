package com.project.febris.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("Country")
    @Expose
    private String Country;

    @SerializedName("Date")
    @Expose
    private String date;

    @SerializedName("TotalConfirmed")
    private int confirmed;

    @SerializedName("TotalDeaths")
    private int deaths;

    @SerializedName("TotalRecovered")
    private int recovered;

    // Getters:


    public String getCountry() {
        return Country;
    }

    public String getDate() {
        return date;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

}

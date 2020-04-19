package com.project.febris.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("confirmed")
    private int confirmed;

    @SerializedName("deaths")
    private int deaths;

    @SerializedName("recovered")
    private int recovered;

    // Getters:

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

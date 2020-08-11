package com.project.febris.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SummaryResponse {

    //Variables for getSummaryData()
    @SerializedName("data")
    @Expose
    private List<SummaryDetails> data;

    @SerializedName("country_key_id")
    @Expose
    private String country_key_id;

    @SerializedName("continent")
    @Expose
    private String continent;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("population")
    @Expose
    private float population;

    public List<SummaryDetails> getData() {
        return data;
    }

    public String getCountry_key_id() {
        return country_key_id;
    }

    public String getContinent() {
        return continent;
    }

    public String getLocation() {
        return location;
    }

    public float getPopulation() {
        return population;
    }

//    //Variables for getSummaryData()
//    @SerializedName("Countries")
//    @Expose
//    private List<SummaryDetails> countries;
//    public List<SummaryDetails> getCountries(){
//        return countries;
//    }

//
////    private List<SummaryDetails> specificCountries;
//
//    public List<SummaryDetails> getSpecificCountries() {
//        return specificCountries;
//    }
//
//    @Override
//    public String toString() {
//        return "SummaryResponse{" +
//                "countries=" + countries +
//                '}';
//    }
}

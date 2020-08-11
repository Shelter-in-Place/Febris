package com.project.febris.API;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SummaryDetails {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("total_cases")
    @Expose
    private float total_cases;

    @SerializedName("new_cases")
    @Expose
    private float new_cases;

    @SerializedName("total_deaths")
    @Expose
    private float total_deaths;

    @SerializedName("new_deaths")
    @Expose
    private float new_deaths;

    @SerializedName("total_cases_per_million")
    @Expose
    private float total_cases_per_million;

    @SerializedName("new_cases_per_million")
    @Expose
    private float new_cases_per_million;

    @SerializedName("total_deaths_per_million")
    @Expose
    private float total_deaths_per_million;

    @SerializedName("new_deaths_per_million")
    @Expose
    private float new_deaths_per_million;

    @SerializedName("country_key")
    @Expose
    private String country_key;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(float total_cases) {
        this.total_cases = total_cases;
    }

    public float getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(float new_cases) {
        this.new_cases = new_cases;
    }

    public float getTotal_deaths() {
        return total_deaths;
    }

    public void setTotal_deaths(float total_deaths) {
        this.total_deaths = total_deaths;
    }

    public float getNew_deaths() {
        return new_deaths;
    }

    public void setNew_deaths(float new_deaths) {
        this.new_deaths = new_deaths;
    }

    public float getTotal_cases_per_million() {
        return total_cases_per_million;
    }

    public void setTotal_cases_per_million(float total_cases_per_million) {
        this.total_cases_per_million = total_cases_per_million;
    }

    public float getNew_cases_per_million() {
        return new_cases_per_million;
    }

    public void setNew_cases_per_million(float new_cases_per_million) {
        this.new_cases_per_million = new_cases_per_million;
    }

    public float getTotal_deaths_per_million() {
        return total_deaths_per_million;
    }

    public void setTotal_deaths_per_million(float total_deaths_per_million) {
        this.total_deaths_per_million = total_deaths_per_million;
    }

    public float getNew_deaths_per_million() {
        return new_deaths_per_million;
    }

    public void setNew_deaths_per_million(float new_deaths_per_million) {
        this.new_deaths_per_million = new_deaths_per_million;
    }

    public String getCountry_key() {
        return country_key;
    }

    public void setCountry_key(String country_key) {
        this.country_key = country_key;
    }

    //    @Override
//    public String toString() {
//        return "SummaryDetails{" +
//                "Country='" + Country + '\'' +
//                ", Slug='" + Slug + '\'' +
//                ", NewConfirmed=" + NewConfirmed +
//                ", TotalConfirmed=" + TotalConfirmed +
//                ", NewDeaths=" + NewDeaths +
//                ", TotalDeaths=" + TotalDeaths +
//                ", NewRecovered=" + NewRecovered +
//                ", TotalRecovered=" + TotalRecovered +
//                ", Date='" + Date + '\'' +
//                '}';
//    }
}

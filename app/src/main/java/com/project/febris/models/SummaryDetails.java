package com.project.febris.models;

import androidx.room.ColumnInfo;

public class SummaryDetails {

    private String Country;
    private String Slug;
    private int NewConfirmed;
    private int TotalConfirmed;
    private int NewDeaths;
    private int TotalDeaths;
    private int NewRecovered;
    private int TotalRecovered;

    @ColumnInfo(name="latest_update")
    private String Date;

    public String getCountry() {
        return Country;
    }

    public String getSlug() {
        return Slug;
    }

    public int getNewConfirmed() {
        return NewConfirmed;
    }

    public int getTotalConfirmed() {
        return TotalConfirmed;
    }

    public int getNewDeaths() {
        return NewDeaths;
    }

    public int getTotalDeaths() {
        return TotalDeaths;
    }

    public int getNewRecovered() {
        return NewRecovered;
    }

    public int getTotalRecovered() {
        return TotalRecovered;
    }

    public String getDate() {
        return Date;
    }

    @Override
    public String toString() {
        return "SummaryDetails{" +
                "Country='" + Country + '\'' +
                ", Slug='" + Slug + '\'' +
                ", NewConfirmed=" + NewConfirmed +
                ", TotalConfirmed=" + TotalConfirmed +
                ", NewDeaths=" + NewDeaths +
                ", TotalDeaths=" + TotalDeaths +
                ", NewRecovered=" + NewRecovered +
                ", TotalRecovered=" + TotalRecovered +
                ", Date='" + Date + '\'' +
                '}';
    }
}

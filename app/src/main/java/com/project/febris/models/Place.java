package com.project.febris.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "places")
public class Place {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "place")
    private String place;

    @ColumnInfo(name = "image_address")
    private String image_address;

    @ColumnInfo(name = "infections")
    private int infections;

    @ColumnInfo(name = "currentinfections")
    private int currentInfections;

    @ColumnInfo(name = "deaths")
    private int deaths;

    @ColumnInfo(name = "recovered")
    private int recovered;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "isFav")
    private boolean isFavourite;

    @ColumnInfo(name = "present")
    private boolean isPresent;


    public Place(int ID, String place, String image_address, int infections, int deaths, int recovered, boolean isFavourite){
        this.ID = ID;
        this.place = place;
        this.image_address = image_address;
        this.infections = infections;
        this.deaths = deaths;
        this.recovered = recovered;
        this.isFavourite = isFavourite;
        this.currentInfections = infections - recovered - deaths;
    }

    @Ignore
    public Place() {
    }

    // Get / Set Methods:

    public boolean is_favourite() {
        return isFavourite;
    }

    public void set_favourite(boolean is_favourite) {
        this.isFavourite = is_favourite;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public int getID() { return ID; }

    public void setID(int ID) { this.ID = ID; }

    public String getPlace() {
        return place;
    }

    public void setRegion(String region) {
        this.place = region;
    }

    public String getImage_address() {
        return image_address;
    }

    public void setImage_address(String image_address) {
        this.image_address = image_address;
    }

    public int getInfections() { return infections;}

    public void setInfections(int infections) {
        this.infections = infections;
    }

    public int getCurrentInfections() { return currentInfections; }

    public void setCurrentInfections(int currentInfections) { this.currentInfections = currentInfections; }

    public int getDeaths() { return deaths; }

    public void setDeaths(int deaths) { this.deaths = deaths; }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) { this.recovered = recovered; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}


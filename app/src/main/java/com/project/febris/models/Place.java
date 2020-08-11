package com.project.febris.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "places")
public class Place {

//    @PrimaryKey(autoGenerate = true)
////    private int ID;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "country_key_id")
    private String country_key_id;

    @ColumnInfo(name = "continent")
    private String continent;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "population")
    private float population;

    @ColumnInfo(name = "image_address")
    private String image_address;

//    @ColumnInfo(name = "infections")
//    private int infections;
//
//    @ColumnInfo(name = "currentinfections")
//    private int currentInfections;

//    @ColumnInfo(name = "deaths")
//    private int deaths;
//
//    @ColumnInfo(name = "recovered")
//    private int recovered;
//
//    @ColumnInfo(name = "date")
//    private String date;

    @ColumnInfo(name = "isFav")
    private boolean isFavourite;

    @ColumnInfo(name = "present")
    private boolean isPresent;

    @ColumnInfo(name = "selected")
    private boolean isSelected;


    public Place(String country_key_id, String continent, String location, float population, String image_address, boolean isFavourite){
//        if(ID != null){
//            this.ID = ID;
//        }
        this.country_key_id = country_key_id;
        this.continent = continent;
        this.location = location;
        this.population = population;
        this.image_address = image_address;
        this.isFavourite = isFavourite;
    }

    @Ignore
    public Place() {
    }

    // Get / Set Methods:

//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }

    public String getCountry_key_id() {
        return country_key_id;
    }

    public void setCountry_key_id(String country_key_id) {
        this.country_key_id = country_key_id;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getPopulation() {
        return population;
    }

    public void setPopulation(float population) {
        this.population = population;
    }

    public String getImage_address() {
        return image_address;
    }

    public void setImage_address(String image_address) {
        this.image_address = image_address;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


//    @Override
//    public String toString() {
//        return "Place{" +
//                "ID=" + ID +
//                ", place='" + place + '\'' +
//                ", image_address='" + image_address + '\'' +
//                ", infections=" + infections +
//                ", currentInfections=" + currentInfections +
//                ", deaths=" + deaths +
//                ", recovered=" + recovered +
//                ", date='" + date + '\'' +
//                ", isFavourite=" + isFavourite +
//                ", isPresent=" + isPresent +
//                ", isSelected=" + isSelected +
//                '}';
//    }
}


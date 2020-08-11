package com.project.febris.models;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "datasets")
public class Dataset {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "total_cases")
    private float total_cases;

    @ColumnInfo(name = "new_cases")
    private float new_cases;

    @ColumnInfo(name = "total_deaths")
    private float total_deaths;

    @ColumnInfo(name = "new_deaths")
    private float new_deaths;

    @ColumnInfo(name = "total_cases_per_million")
    private float total_cases_per_million;

    @ColumnInfo(name = "new_cases_per_million")
    private float new_cases_per_million;

    @ColumnInfo(name = "total_deaths_per_million")
    private float total_deaths_per_million;

    @ColumnInfo(name = "new_deaths_per_million")
    private float new_deaths_per_million;

    @ColumnInfo(name = "country_key")
    private String country_key;


    @ColumnInfo(name = "isFav")
    private boolean isFavourite;

    @ColumnInfo(name = "present")
    private boolean isPresent;

    @ColumnInfo(name = "selected")
    private boolean isSelected;


    public Dataset(@Nullable Integer ID, String date, float total_cases, float new_cases, float total_deaths, float new_deaths,
                   float total_cases_per_million, float new_cases_per_million, float total_deaths_per_million, float new_deaths_per_million, String country_key){
        if(ID != null){
            this.ID = ID;
        }
        this.date = date;
        this.total_cases = total_cases;
        this.new_cases = new_cases;
        this.total_deaths = total_deaths;
        this.new_deaths = new_deaths;
        this.total_cases_per_million = total_cases_per_million;
        this.new_cases_per_million = new_cases_per_million;
        this.total_deaths_per_million = total_deaths_per_million;
        this.new_deaths_per_million = new_deaths_per_million;
        this.country_key = country_key;
    }

    @Ignore
    public Dataset() {
    }

    // Get / Set Methods:

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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


package com.project.febris.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "places")
public class Place{

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "place")
    private String place;

    @ColumnInfo(name = "image_address")
    private String image_address;

    @ColumnInfo(name = "infections")
    private String infections ;

    @ColumnInfo(name = "deaths")
    private String deaths;

    public Place(int ID, String place, String image_address, String infections, String deaths){
        this.ID = ID;
        this.place = place;
        this.image_address = image_address;
        this.infections = infections;
        this.deaths = deaths;
    }

    @Ignore
    public Place() {
    }

    // Get / Set Methods:
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

    public String getInfections() { return infections;}

    public void setInfections(String region) {
        this.infections = region;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String image_address) {
        this.deaths = image_address;
    }
}


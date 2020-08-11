package com.project.febris.persistence;

import androidx.lifecycle.LiveData;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.project.febris.models.Dataset;
import com.project.febris.models.Place;
import com.project.febris.models.PlaceWithDatasets;

import java.util.List;

@androidx.room.Dao
public abstract class Dao {


    //^^^^^^^^^^^^^^^^^^^
    // INSERTS / UPDATES:
    // Insert a single place
    @Insert
    public abstract long[] insertPlaces(Place... places);

    @Insert
    public abstract long[] insertDatasets(Dataset... datasets);

    // Insert an arraylist of places
    @Insert
    public abstract long[] insertPlacesList(List<Place> places);

    // Update places
    @Update
    public abstract void updatePlaces(Place... places);

    @Update
    public abstract void updateDatasets(Dataset... datasets);

    //^^^^^^
    // GETS:
    // Retrieve the latest (i.e. present) places for the summary list
    @Query("SELECT * FROM places ORDER BY location")
    public abstract LiveData<List<PlaceWithDatasets>> getPlaces();


    // Retrieve the latest (i.e. present) favourited places for the summary list
//    @Query("SELECT * FROM places WHERE isFav = 1 and present = 1")INNER JOIN datasets ON places.country_key_id = datasets.country_key
    @Query("SELECT * FROM places  WHERE places.isFav = 1")
    public abstract LiveData<List<PlaceWithDatasets>> getFavPlaces();



    // Retrieve all entries (i.e. for all dates) for a selected country
    @Query("SELECT * FROM datasets WHERE selected = 1 ORDER BY date(date)")
    public abstract LiveData<List<Dataset>> getSelectedCountry();

    @Query("SELECT * FROM places WHERE location = :place and present = 1")
    public abstract Place getSpecificPlace(String place);


    @Query("SELECT * FROM datasets WHERE date = :date and country_key = :country_key")
    public abstract Dataset getSpecificDataset(String date, String country_key);



    //^^^^^^^^
    // DELETES:
    // Delete specific places
    @Delete
    public abstract int delete(Place... places);

    // Delete all places
    @Query("DELETE FROM places")
    public abstract void deleteAll();

    // Delete all datasets
    @Query("DELETE FROM datasets")
    public abstract void deleteAllDatasets();

    // Delete all non-present entries for a specific place
    @Query("DELETE FROM places WHERE location = :place AND present = 0")
    public abstract void deleteSpecificPlaceList(String place);


    //^^^^^^^^^^^^^
    // TRANSACTIONS:
    // Transaction to delete non-current places and insert API call for specific country (i.e. for the data screen)
    @Transaction
    public void singleTransaction(String place, List<Place> placeslist){
        deleteSpecificPlaceList(place);
        insertPlacesList(placeslist);
    }

    @Transaction
    @Query("SELECT * FROM places")
    public abstract LiveData<List<PlaceWithDatasets>> getPlacesWithDatasets();
}

package com.project.febris.persistence;

import androidx.lifecycle.LiveData;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.project.febris.models.Place;

import java.util.List;

@androidx.room.Dao
public abstract class Dao {


    //^^^^^^^^^^^^^^^^^^^
    // INSERTS / UPDATES:
    // Insert a single place
    @Insert
    public abstract long[] insertPlaces(Place... places);

    // Insert an arraylist of places
    @Insert
    public abstract long[] insertPlacesList(List<Place> places);

    // Update places
    @Update
    public abstract void updatePlaces(Place... places);


    //^^^^^^
    // GETS:
    // Retrieve the latest (i.e. present) places for the summary list
    @Query("SELECT * FROM places WHERE present = 1")
    public abstract LiveData<List<Place>> getPlaces();

    // Retrieve the latest (i.e. present) favourited places for the summary list
    @Query("SELECT * FROM places WHERE isFav = 1 and present = 1")
    public abstract LiveData<List<Place>> getFavPlaces();

    // Retrieve all entries (i.e. for all dates) for a selected country
    @Query("SELECT * FROM places WHERE selected = 1 ORDER BY date(date)")
    public abstract LiveData<List<Place>> getSelectedCountry();

    @Query("SELECT * FROM places WHERE place = :place and present = 1")
    public abstract Place getSpecificPlace(String place);


    //^^^^^^^^
    // DELETES:
    // Delete specific places
    @Delete
    public abstract int delete(Place... places);

    // Delete all places
    @Query("DELETE FROM places")
    public abstract void deleteAll();

    // Delete all non-present entries for a specific place
    @Query("DELETE FROM places WHERE place = :place AND present = 0")
    public abstract void deleteSpecificPlaceList(String place);


    //^^^^^^^^^^^^^
    // TRANSACTIONS:
    // Transaction to delete non-current places and insert API call for specific country (i.e. for the data screen)
    @Transaction
    public void singleTransaction(String place, List<Place> placeslist){
        deleteSpecificPlaceList(place);
        insertPlacesList(placeslist);
    }
}

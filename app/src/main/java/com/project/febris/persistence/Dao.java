package com.project.febris.persistence;

import androidx.lifecycle.LiveData;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.project.febris.models.Place;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    long[] insertPlaces(Place... places);

    @Query("SELECT * FROM places")
    LiveData<List<Place>> getPlaces();

    @Query("SELECT * FROM places")
    List<Place> getPlacesNonLiveData();

    @Query("SELECT * FROM places WHERE place = :place")
    LiveData<Place> getSpecificPlace(String place);

    @Query("SELECT * FROM places WHERE isFav = 1")
    LiveData<List<Place>> getFavPlaces();

    @Query("SELECT * FROM places WHERE selected = 1")
    LiveData<List<Place>> getSelectedCountry();

    @Delete
    int delete(Place... places);

    @Query("DELETE FROM places")
    void deleteAll();

    @Update
    void updatePlaces(Place... places);

    @Query("SELECT EXISTS (SELECT 1 FROM places WHERE ID == :id AND deaths >= 2000)")
    public int isFavorite(int id);
}

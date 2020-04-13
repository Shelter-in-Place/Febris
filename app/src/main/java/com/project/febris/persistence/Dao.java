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

    @Delete
    int delete(Place... places);

    @Update
    int update(Place... places);
}

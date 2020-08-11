package com.project.febris.persistence;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.project.febris.models.Dataset;
//import com.project.febris.models.OrderedDataset;
import com.project.febris.models.Place;

@androidx.room.Database(entities = {Place.class, Dataset.class}, version = 11)
public abstract class Database extends RoomDatabase {

    public static final String DATABASE_NAME = "db";

    private static Database instance;

    static Database getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    Database.class,
                    DATABASE_NAME
            ).fallbackToDestructiveMigration().
                    build();
        }
        return instance;
    }

    public abstract Dao getNoteDao();

}

package com.project.febris.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.project.febris.async.InsertAsyncTask;
import com.project.febris.models.Place;

import java.util.List;

public class Repository {

    private Database mDatabase;

    public Repository(Context context) {
        mDatabase = Database.getInstance(context);
    }

    public void insertPlaceTask(Place place){
        new InsertAsyncTask(mDatabase.getNoteDao()).execute(place);
    }

    public void updatePlaces(Place place){

    }

    public LiveData<List<Place>> retrievePlacesTask(){
        return mDatabase.getNoteDao().getPlaces();
    }

    public void deletePlace(Place place){

    }

}

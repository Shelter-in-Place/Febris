package com.project.febris;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.febris.models.Place;
import com.project.febris.persistence.Repository;

import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Place>> allPlaces;

    public ListViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        allPlaces = mRepository.retrievePlacesTask();
        insertTestData();
    }

    private void insertTestData(){
        Place place = new Place(0, "New Jersey", "www.newyork.com", "1000", "1000");
        mRepository.insertPlaceTask(place);
    }

    //DB METHODS:
    public void insert(Place place){
        mRepository.insertPlaceTask(place);
    }

    public void update(Place place){
        mRepository.updatePlaces(place);
    }

    public void delete(Place place){
        mRepository.deletePlace(place);
    }

    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }
}
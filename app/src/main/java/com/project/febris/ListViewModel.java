package com.project.febris;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project.febris.models.FavouritesPlace;
import com.project.febris.models.Place;
import com.project.febris.persistence.Repository;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends AndroidViewModel {
    private static final String TAG = "ListViewModel";
    private Repository mRepository;
    private LiveData<List<Place>> allPlaces;
    private LiveData<List<Place>> allFavourites;
    public Place selectedCountry;
    public List<Place> placesLocal = new ArrayList<>();

    public ListViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        allPlaces = mRepository.retrievePlacesTask();
        allFavourites = mRepository.getFavPlaces();
    }

    //DB METHODS:
    public void insert(Place place){
        mRepository.insertPlaceTask(place);
    }

    public void update(Place place){
        Log.d(TAG, "update: ");
        mRepository.updatePlaces(place);
    }

    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    public LiveData<List<Place>> getFavPlaces(){
        return allFavourites;
    }

    public Place getSelectedCountry() {
        return this.selectedCountry;
    }

    public void setSelectedCountry(Place country) {
        this.selectedCountry = country;
        Log.d(TAG, "setSelectedCountry: " + selectedCountry.getPlace());
    }

    public List<Place> getPlacesLocal() {
        return placesLocal;
    }

    public void setPlacesLocal(List<Place> placesLocal) {
        this.placesLocal = placesLocal;
    }
}
package com.project.febris;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

//import com.project.febris.models.FavouritesPlace;
import com.project.febris.models.Place;
import com.project.febris.models.PlaceWithDatasets;
import com.project.febris.persistence.Repository;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends AndroidViewModel {
    private static final String TAG = "ListViewModel";
    private Repository mRepository;
    private LiveData<List<PlaceWithDatasets>> allPlaces;
    private LiveData<List<PlaceWithDatasets>> allFavourites;
    private LiveData<List<PlaceWithDatasets>> selectedCountry;
    private LiveData<List<PlaceWithDatasets>> placesWithDatasets;

    public List<Place> placesLocal = new ArrayList<>();

    public ListViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        allPlaces = mRepository.retrievePlacesTask();
        allFavourites = mRepository.getFavPlaces();
        placesWithDatasets = mRepository.getPlacesWithDatasets();
//        selectedCountry = mRepository.getSelectedCountry();
    }

    //Network Calls
//    public void callRetrofitSpecificCountryData(String place){
//        Log.d(TAG, "callRetrofitSpecificCountryData: called");
//        mRepository.callRetrofitSpecificCountryData(place);
//    }

    //DB METHODS:
    public void insert(Place place){
        mRepository.insertPlaceTask(place);
    }

//    public void update(Place place){
//        Log.d(TAG, "update: ");
//        mRepository.updatePlaces(place);
//    }

    public void update(Place place){
        Log.d(TAG, "update: ");
        mRepository.updatePlaces(place);
    }

    public void callSpecificCountryData(String countryKeyID){
        mRepository.callRetrofitSpecificCountryData(countryKeyID);
    }

    public LiveData<List<PlaceWithDatasets>> getAllPlaces() {
        return allPlaces;
    }

    public LiveData<List<PlaceWithDatasets>> getFavPlaces(){
        return allFavourites;
    }

    public LiveData<List<PlaceWithDatasets>> getSelectedCountry(){
        return selectedCountry;
    }

    public void clearSelected() {
        for(int i = 0; i < selectedCountry.getValue().size(); i++){
            PlaceWithDatasets place = selectedCountry.getValue().get(i);
            place.getPlace().setSelected(false);
            update(place.getPlace());
        }
        Log.d(TAG, "ClearSelected: ");
    }


    public List<Place> getPlacesLocal() {
        return placesLocal;
    }

    public void setPlacesLocal(List<Place> placesLocal) {
        this.placesLocal = placesLocal;
    }

    public LiveData<List<PlaceWithDatasets>> getPlacesWithDatasets() {
        return placesWithDatasets;
    }
}
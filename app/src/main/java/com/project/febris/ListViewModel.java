package com.project.febris;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project.febris.models.FavouritesPlace;
import com.project.febris.models.Place;
import com.project.febris.persistence.Repository;

import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Place>> allPlaces;
    private LiveData<List<FavouritesPlace>> allFavourites;

    public ListViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        allPlaces = mRepository.retrievePlacesTask();
        allFavourites = mRepository.retrieveFavouritesTask();

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

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    public LiveData<List<FavouritesPlace>> getAllFavourites(){
        return allFavourites;
    }

    public void insertFavourite(String countryName){
        mRepository.addFav(countryName);
    }

    public void deleteFavourite(FavouritesPlace favourite){mRepository.deleteFavourite(favourite);}
}
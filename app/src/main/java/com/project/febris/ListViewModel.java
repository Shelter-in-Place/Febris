package com.project.febris;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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
        deleteAll();
        insertTestData();
    }

    private void insertTestData(){
        Place place = new Place(0, "New York", "", "190,288", "9,385");
        Place place1 = new Place(0, "New Jersey", "", "61,850", "2,350");
        Place place2 = new Place(0, "Massachusetts", "", "25,475", "756");
        Place place3 = new Place(0, "Michigan", "", "24,244", "1,479");
        Place place4 = new Place(0, "California", "", "23,324", "682");
        Place place5 = new Place(0, "Pennsylvania", "", "23,036", "561");
        Place place6 = new Place(0, "Illinois", "", "20,852", "720");
        Place place7 = new Place(0, "Louisiana", "", "20,595", "840");
        Place place8 = new Place(0, "Florida", "", "19,895", "461");
        Place place9 = new Place(0, "Texas", "", "13,886", "296");
        Place place10 = new Place(0, "Georgia", "", "12,550", "442");

        mRepository.insertPlaceTask(place);
        mRepository.insertPlaceTask(place1);
        mRepository.insertPlaceTask(place2);
        mRepository.insertPlaceTask(place3);
        mRepository.insertPlaceTask(place4);
        mRepository.insertPlaceTask(place5);
        mRepository.insertPlaceTask(place6);
        mRepository.insertPlaceTask(place7);
        mRepository.insertPlaceTask(place8);
        mRepository.insertPlaceTask(place9);
        mRepository.insertPlaceTask(place10);
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
}
package com.project.febris.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.project.febris.ListViewModel;
import com.project.febris.R;
import com.project.febris.adapters.PlacesRecyclerAdapter;
import com.project.febris.models.PlaceWithDatasets;
import com.project.febris.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements PlacesRecyclerAdapter.OnClickboxListener {
    private static final String TAG = "LIST ACTIVITY";
    private RecyclerView mRecyclerView;
    private PlacesRecyclerAdapter adapter;

    private ListViewModel mListViewModel;
    private List<PlaceWithDatasets> mPlaces = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListViewModel = new ViewModelProvider(this).get(ListViewModel.class);

        initViewModel();
        initRecyclerView();
        initSearchView();
    }

    public void initRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(0);
        mRecyclerView.addItemDecoration(itemDecorator);
        adapter = new PlacesRecyclerAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }

    private void initViewModel(){
        mListViewModel.getAllPlaces().observe(this, new Observer<List<PlaceWithDatasets>>() {
            @Override
            public void onChanged(List<PlaceWithDatasets> places) {
                Log.d(TAG, "onChanged: ");
                mPlaces = places;
                adapter.setPlaces(places);
                adapter.notifyItemRangeChanged(0, places.size());
                Log.d(TAG, "onChanged: ");
            }
        });
    }

    private void initSearchView(){
        final SearchView searchView = findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.setQuery("", true);
                searchView.clearFocus();
                Log.d(TAG, "Search Text Submitted");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {
                adapter.getFilter().filter(searchText);
                Log.d(TAG, "Search Text Typed");

                return false;
            }
        });
    }



    @Override
    public void onClickboxclick(int position) {
        PlaceWithDatasets place = mPlaces.get(position);
        if(place.getPlace().isFavourite()){
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") was favourited");
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") is currently set to\n"+ place.getPlace().isFavourite());

            place.getPlace().setFavourite(false);
            mListViewModel.update(place.getPlace());

            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") is no longer favourited");
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") is currently set to\n"+ place.getPlace().isFavourite());
        }
        else{
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") was not favourited");
            place.getPlace().setFavourite(true);
            mListViewModel.update(place.getPlace());
            mListViewModel.callSpecificCountryData(place.getPlace().getCountry_key_id());
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") is now favourited");
        }
    }

    @Override
    public void onChecked(boolean checked) {
        Log.d(TAG, "onChecked: ");
    }

    @Override
    public void dataScreen(int position) {

    }
}

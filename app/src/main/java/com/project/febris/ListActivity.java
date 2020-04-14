// LATEST CHANGES: (i) implementing a recyclerview for the main screen, (ii) implementing a ViewModel
package com.project.febris;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

// Recyclerview imports
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Febris package imports
import com.project.febris.adapters.PlacesRecyclerAdapter;
import com.project.febris.models.Place;
import com.project.febris.util.VerticalSpacingItemDecorator;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    //UI components
    private RecyclerView mRecyclerView;

    //variables
    private PlacesRecyclerAdapter adapter;
    private ListViewModel mListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        initViewModel();
    }

    private void initRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        adapter = new PlacesRecyclerAdapter();
        mRecyclerView.setAdapter(adapter);
    }

    public void initViewModel(){
        mListViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        mListViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                adapter.setPlaces(places);
            }
        });
    }

}


package com.project.febris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.widget.SearchView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


// Febris package imports

import com.google.android.material.tabs.TabLayout;
import com.project.febris.adapters.PlacesRecyclerAdapter;
import com.project.febris.models.Place;
import com.project.febris.ui.main.*;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "List Activity";

    //UI components

    //variables
    private PlacesRecyclerAdapter adapter;
    private ListViewModel mListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(fragmentAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


//        initToolBar();

        initViewModel();
        initSearchView();
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
            public boolean onQueryTextChange(String newText) {
                // Keith your search methods go here ya ya ya!
                Log.d(TAG, "Search Text Typed");
                return false;
            }
        });
    }

    public void initViewModel(){
        mListViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        mListViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
//                adapter.setPlaces(places);
            }
        });
    }

}

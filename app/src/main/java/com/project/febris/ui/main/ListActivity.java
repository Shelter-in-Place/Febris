
package com.project.febris.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

// Febris package imports
import com.google.android.material.tabs.TabLayout;
import com.project.febris.ListViewModel;
import com.project.febris.R;
import com.project.febris.adapters.PlacesRecyclerAdapter;
import com.project.febris.models.Place;

import java.util.List;

public class ListActivity extends AppCompatActivity implements Fragment2.DataTransfertoActivity{

    private static final String TAG = "List Activity";

    //UI components
    List<Fragment> allFragments;
    private CustomViewPager viewPager;

    //variables
    private PlacesRecyclerAdapter adapter;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    public Fragment3 fragment3;
    public Place selectedPlace;
    public FragmentAdapter fragmentAdapter;

    public ListViewModel mListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSearchView();
        initFragmentAdapter();
        mListViewModel = new ViewModelProvider(this).get(ListViewModel.class);
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
                searchViaFragment(newText);
                return false;
            }
        });
    }

    public void initFragmentAdapter(){
        fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager());
        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(fragmentAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPagingEnabled(false);
    }

    public void searchViaFragment(String searchText){
        allFragments = getSupportFragmentManager().getFragments();
        fragment1 = (Fragment1) allFragments.get(0);
        fragment2 = (Fragment2) allFragments.get(1);
        fragment3 = (Fragment3) allFragments.get(2);
        Log.d(TAG, "searchViaFragment: " + allFragments.size());

        fragment1.search(searchText);
        fragment2.search(searchText);
        fragment3.search(searchText);

        Log.d(TAG, "TEST METHOD TRIGGERED");
    }

    public CustomViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof Fragment2){
            Fragment2 fragment2 = (Fragment2) fragment;
            fragment2.setDataTransfertoActivity(this);
        }

    }

    @Override
    public void sendInfo(int position) {
        Log.d(TAG, "sendInfo: Initiated");

        try{
            Log.d(TAG, "sendInfo: try");
        }
        catch(Exception err){
            Log.d(TAG, "sendInfo: catch " + err);
        }
    }

}

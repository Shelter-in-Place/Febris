
package com.project.febris.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

// Febris package imports
import com.google.android.material.tabs.TabLayout;
import com.project.febris.R;
import com.project.febris.adapters.PlacesRecyclerAdapter;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "List Activity";

    //UI components
    List<Fragment> allFragments;

    //variables
    private PlacesRecyclerAdapter adapter;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSearchView();
        initFragmentAdapter();
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
        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager());
        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.view_pager);
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

}

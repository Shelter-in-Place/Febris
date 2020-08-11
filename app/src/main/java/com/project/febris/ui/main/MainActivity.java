
package com.project.febris.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

// Febris package imports
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.project.febris.ListViewModel;
import com.project.febris.R;
import com.project.febris.adapters.PlacesRecyclerAdapter;
import com.project.febris.models.Place;

import java.util.List;
//implements Fragment2.DataTransfertoActivity
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "List Activity";

    //UI components
    List<Fragment> allFragments;
    private CustomViewPager viewPager;
    private CustomViewPager viewPagerTop;

    //variables
    public FragmentAdapterBottom fragmentAdapter;
    public FragmentAdapterTop fragmentAdapterTop;
    public FloatingActionButton FAB;
    public ListViewModel mListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FAB = (FloatingActionButton) findViewById(R.id.fab);;
//        initSearchView();
        initFragmentAdapter();
        mListViewModel = new ViewModelProvider(this).get(ListViewModel.class);
    }


    public void initFragmentAdapter(){

        fragmentAdapterTop = new FragmentAdapterTop(this, getSupportFragmentManager());
        viewPagerTop = (CustomViewPager) findViewById(R.id.view_pager_top);
        viewPagerTop.setAdapter(fragmentAdapterTop);;
        viewPagerTop.setOffscreenPageLimit(2);
        viewPagerTop.setPagingEnabled(false);
        viewPagerTop.setCurrentItem(1);

        fragmentAdapter = new FragmentAdapterBottom(this, getSupportFragmentManager());
        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(fragmentAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
//        tabs.getTabAt(0).setIcon(R.drawable.ic_list_icon);
//        tabs.getTabAt(1).setIcon(R.drawable.ic_world_icon);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        FAB.show();
                        break;
                    case 1:
                        FAB.hide();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        FAB.show();
                        break;
                    case 1:
                        FAB.hide();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        FAB.show();
                        break;
                    case 1:
                        FAB.hide();
                }
            }
        });
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPagingEnabled(false);


    }

    public CustomViewPager getViewPager() {
        return viewPager;
    }

    public void onClick(View view){
        Intent i = new Intent(MainActivity.this, ListActivity.class);
        MainActivity.this.startActivity(i);
    }

//    @Override
//    public void onAttachFragment(@NonNull Fragment fragment) {
//        if (fragment instanceof Fragment2){
//            Fragment2 fragment2 = (Fragment2) fragment;
//            fragment2.setDataTransfertoActivity(this);
//        }

//    }

    //    @Override
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

package com.project.febris.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.febris.ListViewModel;
import com.project.febris.R;
import com.project.febris.adapters.FavouritesRecyclerAdapter;
import com.project.febris.models.Place;

import com.project.febris.models.PlaceWithDatasets;
import com.project.febris.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment implements FavouritesRecyclerAdapter.FavOnClickboxListener {

    private static final String TAG = "FRAGMENT 1";

    private FavouritesRecyclerAdapter favouritesRecyclerAdapter;
    private List<PlaceWithDatasets> mPlaces = new ArrayList<>();
    private ListViewModel mListViewModel;
//    private CustomViewPager viewPager;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_screen_1, container, false);
//        viewPager = getActivity().findViewById(R.id.view_pager);
        initRecyclerView(root);
        initViewModel();
        return root;
    }

    public void initRecyclerView(View root){
        RecyclerView mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(0);
        mRecyclerView.addItemDecoration(itemDecorator);
        favouritesRecyclerAdapter = new FavouritesRecyclerAdapter(this);
        mRecyclerView.setAdapter(favouritesRecyclerAdapter);
    }

    private void initViewModel(){
        mListViewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);
        mListViewModel.getFavPlaces().observe(this, new Observer<List<PlaceWithDatasets>>() {
            @Override
            public void onChanged(List<PlaceWithDatasets> places) {
                Log.d(TAG, "onChanged: ");
                mPlaces = places;
                favouritesRecyclerAdapter.setFavourites(places);
//                favouritesRecyclerAdapter.notifyItemRangeChanged(0, places.size());

            }
        });
    }

    public void search(String searchText){
        Log.d(TAG, "TEST METHOD TRIGGERED");
        favouritesRecyclerAdapter.getFilter().filter(searchText);
    }

    @Override
    public void favOnClickboxclick(int position) {
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

            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") is now favourited");
        }
    }

    @Override
    public void onChecked(boolean checked){
        Log.d(TAG, "onChecked: ");
    }

    @Override
    public void dataScreen(int position) {
        Log.d(TAG, "dataScreen: clicked " + mPlaces.get(position).getPlace().getLocation());

//        mListViewModel.callRetrofitSpecificCountryData(mPlaces.get(position).getLocation());

        mListViewModel.clearSelected();
        PlaceWithDatasets place = mPlaces.get(position);
        place.getPlace().setSelected(true);
        mListViewModel.update(place.getPlace());

//        dataTransfertoActivity.sendInfo(position);
//        viewPager.setCurrentItem(3);
    }


}

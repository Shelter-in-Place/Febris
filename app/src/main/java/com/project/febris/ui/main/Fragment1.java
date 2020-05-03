package com.project.febris.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.febris.ListViewModel;
import com.project.febris.R;
import com.project.febris.adapters.FavouritesRecyclerAdapter;
import com.project.febris.models.Place;

import com.project.febris.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment implements FavouritesRecyclerAdapter.FavOnClickboxListener {

    private static final String TAG = "FRAGMENT 1";

    private FavouritesRecyclerAdapter favouritesRecyclerAdapter;
    private List<Place> mPlaces = new ArrayList<>();
    private ListViewModel mListViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_screen_1, container, false);
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

    public void initViewModel(){
        mListViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        mListViewModel.getFavPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                Log.d(TAG, "onChanged: ");
                mPlaces = places;
                favouritesRecyclerAdapter.setFavourites(places);
                favouritesRecyclerAdapter.notifyDataSetChanged();

            }
        });
    }

    public void search(String searchText){
        Log.d(TAG, "TEST METHOD TRIGGERED");
        favouritesRecyclerAdapter.getFilter().filter(searchText);
    }

    @Override
    public void favOnClickboxclick(int position) {
        Place place = mPlaces.get(position);
        if(place.is_favourite()){
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") was favourited");
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is currently set to\n"+ place.is_favourite());

            place.set_favourite(false);
            mListViewModel.update(place);
            favouritesRecyclerAdapter.notifyDataSetChanged();

            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is no longer favourited");
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is currently set to\n"+ place.is_favourite());
        }
        else{
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") was not favourited");
            place.set_favourite(true);
            mListViewModel.update(place);
            favouritesRecyclerAdapter.notifyDataSetChanged();
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is now favourited");
        }
    }
    
    @Override
    public void onChecked(boolean checked){
        Log.d(TAG, "onChecked: ");
    }
    
    
}

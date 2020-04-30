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
import com.project.febris.models.FavouritesPlace;
import com.project.febris.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment implements FavouritesRecyclerAdapter.FavOnClickboxListener {

    private static final String TAG = "FRAGMENT 1";

    private FavouritesRecyclerAdapter favouritesRecyclerAdapter;
    private List<FavouritesPlace> mFavouritesList = new ArrayList<>();
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
//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        favouritesRecyclerAdapter = new FavouritesRecyclerAdapter(this);
        mRecyclerView.setAdapter(favouritesRecyclerAdapter);
    }

    public void initViewModel(){
        mListViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        mListViewModel.getAllFavourites().observe(this, new Observer<List<FavouritesPlace>>() {
            @Override
            public void onChanged(List<FavouritesPlace> favourites) {
                favouritesRecyclerAdapter.setFavourites(favourites);
                mFavouritesList.addAll(favourites);
            }
        });
    }

    public void search(String searchText){
        Log.d(TAG, "TEST METHOD TRIGGERED");
        favouritesRecyclerAdapter.getFilter().filter(searchText);
    }

    private void deleteFavourite (FavouritesPlace favouritesPlace){
        mFavouritesList.remove(favouritesPlace);
        favouritesRecyclerAdapter.notifyDataSetChanged();
//        mListViewModel.deleteFavourite(favouritesPlace);
    }

    @Override
    public void favOnClickboxclick(int position) {
        deleteFavourite(mFavouritesList.get(position));
        Log.d(TAG, "favOnClickboxclick");
    }
}

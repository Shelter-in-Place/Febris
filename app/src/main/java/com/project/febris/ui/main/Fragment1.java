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
import com.project.febris.models.Place;
import com.project.febris.util.VerticalSpacingItemDecorator;

import java.util.List;

public class Fragment1 extends Fragment {

    private static final String TAG = "FRAGMENT 1";

    private FavouritesRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

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
        adapter = new FavouritesRecyclerAdapter();
        mRecyclerView.setAdapter(adapter);
    }

    public void initViewModel(){
        ListViewModel mListViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        mListViewModel.getAllFavourites().observe(this, new Observer<List<FavouritesPlace>>() {
            @Override
            public void onChanged(List<FavouritesPlace> favourites) {
                adapter.setFavourites(favourites);
            }
        });
    }

    public void search(String newText){
        Log.d(TAG, "TEST METHOD TRIGGERED");
        adapter.getFilter().filter(newText);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        Log.d(TAG, "onresume");
    }
}

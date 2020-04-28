package com.project.febris.ui.main;

import android.os.Bundle;
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
import com.project.febris.adapters.PlacesRecyclerAdapter;
import com.project.febris.models.Place;
import com.project.febris.util.VerticalSpacingItemDecorator;

import java.util.List;

public class Fragment1 extends Fragment {

    private PlacesRecyclerAdapter adapter;

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

    private void initRecyclerView(View root){
        RecyclerView mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(0);
        mRecyclerView.addItemDecoration(itemDecorator);
//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        adapter = new PlacesRecyclerAdapter();
        mRecyclerView.setAdapter(adapter);
    }

    public void initViewModel(){
        ListViewModel mListViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        mListViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                adapter.setPlaces(places);
            }
        });
    }
}

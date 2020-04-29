package com.project.febris.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.project.febris.R;
import com.project.febris.adapters.PlacesRecyclerAdapter;
import com.project.febris.models.Place;

public class Fragment2 extends BaseFragment {
    private static final String TAG = "FRAGMENT 2";
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

}

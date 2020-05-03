package com.project.febris.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

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
import com.project.febris.adapters.PlacesRecyclerAdapter;
import com.project.febris.models.FavouritesPlace;
import com.project.febris.models.Place;
import com.project.febris.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment implements PlacesRecyclerAdapter.OnClickboxListener {
    private static final String TAG = "FRAGMENT 2";
    private PlacesRecyclerAdapter adapter;
    private FavouritesRecyclerAdapter mFavAdapter;
    private List<Place> mPlaces = new ArrayList<>();
    private ListViewModel mListViewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_screen_2, container, false);
        mListViewModel = new ViewModelProvider(this).get(ListViewModel.class);

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
        adapter = new PlacesRecyclerAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }

    public void initViewModel(){
        mListViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                Log.d(TAG, "onChanged: ");
                mPlaces = places;
                adapter.setPlaces(places);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void search(String newText){
        Log.d(TAG, "TEST METHOD TRIGGERED");
        adapter.getFilter().filter(newText);
    }


    @Override
    public void onClickboxclick(int position) {
        Place place = mPlaces.get(position);
        if(place.is_favourite()){
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") was favourited");
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is currently set to\n"+ place.is_favourite());

            place.set_favourite(false);
            mListViewModel.update(place);
            adapter.notifyDataSetChanged();

            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is no longer favourited");
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is currently set to\n"+ place.is_favourite());
        }
        else{
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") was not favourited");
            place.set_favourite(true);
            mListViewModel.update(place);
            adapter.notifyDataSetChanged();
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is now favourited");
        }
    }

    @Override
    public void onChecked(boolean checked){
        Log.d(TAG, "onChecked: ");
    }
}

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
                adapter.setPlaces(places);
                mPlaces.addAll(places);
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

        FavouritesPlace favourite = new FavouritesPlace();
        int id = place.getID();
        String name = place.getPlace();
        String image_address = place.getImage_address();
        int infections = place.getInfections();
        int deaths = place.getDeaths();
        int recovered = place.getRecovered();
        String date = place.getDate();
        boolean is_favourite = place.is_favourite();

        favourite.setID(id);
        favourite.setRegion(name);
        favourite.setImage_address(image_address);
        favourite.setInfections(infections);
        favourite.setDeaths(deaths);
        favourite.setRecovered(recovered);
        favourite.setDate(date);
        favourite.set_favourite(is_favourite);

        if(place.is_favourite()){
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") was favourited");
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is currently set to\n"+
                    place.is_favourite());

            //Keith this is the method I put in for the delete - it plumbs down into the repository
            //where a new thread is created to find the favorite via a name search and then delete
            //that favorite from the fav database
            mListViewModel.delFavourite(place.getPlace());

            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is no longer favourited");
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is currently set to\n"+
                    place.is_favourite());

        }
        else{
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") was not favourited");
            place.set_favourite(true);
            mListViewModel.update(place);
            adapter.notifyDataSetChanged();
            favourite.set_favourite(true);
            mListViewModel.insertFavourite(favourite);
            Log.d(TAG, "onClickboxclick: place ("+place.getPlace()+") is now favourited");
        }
    }
}

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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.febris.ListViewModel;
import com.project.febris.R;
import com.project.febris.adapters.PlacesRecyclerAdapter;
import com.project.febris.models.Place;
import com.project.febris.models.PlaceWithDatasets;
import com.project.febris.util.SwipeControllerFrag2;
import com.project.febris.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

//public class Fragment2 extends Fragment implements PlacesRecyclerAdapter.OnClickboxListener {
public class Fragment2 extends Fragment {
    private static final String TAG = "FRAGMENT 2";
//    private RecyclerView mRecyclerView;
//    private PlacesRecyclerAdapter adapter;
//    private SwipeControllerFrag2 swipeControllerFrag2;
//    private CustomViewPager viewPager;
//
    private List<PlaceWithDatasets> mPlaces = new ArrayList<>();
    private ListViewModel mListViewModel;

//    DataTransfertoActivity dataTransfertoActivity;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_screen_2, container, false);
        mListViewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);

//        initRecyclerView(root);
        initViewModel();
//        initSwipeController();

//        viewPager = getActivity().findViewById(R.id.view_pager);
        return root;
    }

//    public void initRecyclerView(View root){
//        mRecyclerView = root.findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(0);
//        mRecyclerView.addItemDecoration(itemDecorator);
////        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
//        adapter = new PlacesRecyclerAdapter(this);
//        mRecyclerView.setAdapter(adapter);
//    }

    private void initViewModel(){
        mListViewModel.getAllPlaces().observe(this, new Observer<List<PlaceWithDatasets>>() {
            @Override
            public void onChanged(List<PlaceWithDatasets> places) {
                Log.d(TAG, "onChanged: ");
                mPlaces = places;
//                adapter.setPlaces(places);
//                adapter.notifyItemRangeChanged(0, places.size());
            }
        });
    }

//    private void initSwipeController(){
//        swipeControllerFrag2 = new SwipeControllerFrag2();
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeControllerFrag2);
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);
//    }
//
//    public void search(String newText){
//        Log.d(TAG, "TEST METHOD TRIGGERED");
//        adapter.getFilter().filter(newText);
//    }

//
//    @Override
//    public void onClickboxclick(int position) {
//        PlaceWithDatasets place = mPlaces.get(position);
//        if(place.getPlace().isFavourite()){
//            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") was favourited");
//            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") is currently set to\n"+ place.getPlace().isFavourite());
//
//            place.getPlace().setFavourite(false);
//            mListViewModel.update(place.getPlace());
//
//            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") is no longer favourited");
//            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") is currently set to\n"+ place.getPlace().isFavourite());
//        }
//        else{
//            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") was not favourited");
//            place.getPlace().setFavourite(true);
//            mListViewModel.update(place.getPlace());
//
//            Log.d(TAG, "onClickboxclick: place ("+place.getPlace().getLocation()+") is now favourited");
//        }
//    }
//
//    @Override
//    public void onChecked(boolean checked){
//        Log.d(TAG, "onChecked: ");
//    }
//
//    @Override
//    public void dataScreen(int position) {
//        Log.d(TAG, "dataScreen: clicked " + mPlaces.get(position).getPlace().getLocation());
//
////        mListViewModel.callRetrofitSpecificCountryData(mPlaces.get(position).getPlace());
//
//        mListViewModel.clearSelected();
//        PlaceWithDatasets place = mPlaces.get(position);
//        place.getPlace().setSelected(true);
//        mListViewModel.update(place.getPlace());
//
////        dataTransfertoActivity.sendInfo(position);
//        viewPager.setCurrentItem(3);
//    }

//    public void setDataTransfertoActivity(DataTransfertoActivity callback){
//        dataTransfertoActivity = callback;
//    }

//    public interface DataTransfertoActivity{
//        void sendInfo(int position);
//    }
}

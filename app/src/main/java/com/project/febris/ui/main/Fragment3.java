package com.project.febris.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.project.febris.ListViewModel;
import com.project.febris.R;
import com.project.febris.models.Place;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
    public static final String TAG = "Fragment3";
    private ListViewModel mListViewModel;
    public LiveData<List<Place>> mPlaces;
    public List<Place> mPlacesLocal = new ArrayList<>();
    private TextView countryHeader;
    private TextView totalInfections;
    private TextView currentInfections;
    private TextView deaths;
    private TextView recovered;

    public Place selectedCountry;

    LineGraphSeries<DataPoint> series;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_screen_3, container, false);
        countryHeader = root.findViewById(R.id.country_header);
        totalInfections = root.findViewById(R.id.data_total_infections);
        currentInfections = root.findViewById(R.id.data_current_infections);
        deaths = root.findViewById(R.id.data_deaths);
        recovered = root.findViewById(R.id.data_recovered);

        initViewModel();

        double y,x;
        x = -2.0;

        GraphView graph = (GraphView) root.findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        for(int i = 0; i < 500; i++){
            x = x+0.09;
            y = Math.sin(x);
            series.appendData(new DataPoint(x,y), true, 100);
        }
        graph.addSeries(series);

        return root;
    }

    private void initViewModel(){
        mListViewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);
        mPlaces = mListViewModel.getAllPlaces();
        mListViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                setPlaces(places);
                try {
//                    countryHeader.setText(mPlaces.getValue().get(32).getPlace());
//                    totalInfections.setText("" + mPlaces.getValue().get(35).getInfections());
//                    currentInfections.setText("" + mPlaces.getValue().get(35).getCurrentInfections());
//                    deaths.setText("" + mPlaces.getValue().get(35).getDeaths());
//                    recovered.setText("" + mPlaces.getValue().get(35).getRecovered());

                }
                catch (Exception err){
                    Log.d(TAG, "onChanged: " + err);
                }
            }
        });
        mListViewModel.getSelectedCountry().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                try {
                    countryHeader.setText(places.get(0).getPlace());
                    totalInfections.setText("" + places.get(0).getInfections());
                    currentInfections.setText("" + places.get(0).getCurrentInfections());
                    deaths.setText("" + places.get(0).getDeaths());
                    recovered.setText("" + places.get(0).getRecovered());
                }
                catch (Exception err){
                    Log.d(TAG, "onChanged: " + err);
                }
            }
        });
    }

    public void setPlaces(List<Place> places){
        this.mPlacesLocal = places;
        mListViewModel.setPlacesLocal(places);
        Log.d(TAG, "setPlaces: " + this.mPlacesLocal.size());
    }

    public void setSelectedCountry(int position){
        Log.d(TAG, "setSelectedCountry: " + mListViewModel.getPlacesLocal().size());


    }


    public void search(String newText){
        Log.d(TAG, "search: ");
    }
}

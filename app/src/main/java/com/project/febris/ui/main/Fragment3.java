package com.project.febris.ui.main;

import android.graphics.Color;
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
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.project.febris.ListViewModel;
import com.project.febris.R;
import com.project.febris.models.Place;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment  {
    public static final String TAG = "Fragment3";
    private ListViewModel mListViewModel;
    public LiveData<List<Place>> mPlaces;
    public MutableLiveData<List<Place>> mPlacesLocal;
    private TextView countryHeader;
    private TextView totalInfections;
    private TextView currentInfections;
    private TextView deaths;
    private TextView recovered;
    private MutableLiveData<List<Place>> totDeathsCountry;
    private float mValue;
    private LineChart mLineChart;

//    private String mDeaths = "";

    public Place selectedCountry;
    List<Entry> entriesActive = new ArrayList<Entry>();
    List<Entry> entriesDeaths = new ArrayList<Entry>();


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
        initGraphViews(root);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initViewModel(){
        Log.d(TAG, "initViewModel: called");
        mListViewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);
        mPlaces = mListViewModel.getAllPlaces();
        mListViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {

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
                Log.d(TAG, "onChanged: get selected Country called: "+ places.size());
                try {
                    Log.d(TAG, "onChanged: places = " + places.get(0).toString());
                    setTable(places);
                    entriesActive.clear();
                    setData(places);

                }
                catch (Exception err){
                    Log.d(TAG, "onChanged: " + err);
                }
//                try {
//                    float deathFloat = (float)places.get(0).getDeaths();
//                    setData(deathFloat);
//
//
//                    Log.d(TAG, "onChanged: mValue "+mValue);
//                    countryHeader.setText(places.get(0).getPlace());
//                    totalInfections.setText("" + places.get(0).getInfections());
//                    currentInfections.setText("" + places.get(0).getCurrentInfections());
//                    deaths.setText("" + places.get(0).getDeaths());
//                    recovered.setText("" + places.get(0).getRecovered());
//                }
//                catch (Exception err){
//                    Log.d(TAG, "onChanged: " + err);
//                }
            }
        });

    }

    private void setTable(List<Place> places){
        countryHeader.setText(places.get(0).getPlace());
        totalInfections.setText("" + places.get(0).getInfections());
        currentInfections.setText("" + places.get(0).getCurrentInfections());
        deaths.setText("" + places.get(0).getDeaths());
        recovered.setText("" + places.get(0).getRecovered());
    }


    private void setData(List<Place> places){
        entriesActive.clear();
        entriesDeaths.clear();
        mLineChart.invalidate();
        mLineChart.clear();

        Log.d(TAG, "setData: size of array "+ places.size());

        for(int i = 0; i < places.size(); i++){
            entriesActive.add(new Entry(i, places.get(i).getCurrentInfections()));
            entriesDeaths.add(new Entry(i, places.get(i).getDeaths()));
            Log.d(TAG, "setData: place " + places.get(i).getPlace());
        }

        LineDataSet activeDataSet = new LineDataSet(entriesActive, "Infections");
        activeDataSet.setColor(Color.BLUE);
        activeDataSet.setDrawCircles(false);
        activeDataSet.setLineWidth(1f);
        LineData lineDataSet = new LineData(activeDataSet);

        LineDataSet deathsDataSet = new LineDataSet(entriesDeaths, "Deaths");
        deathsDataSet.setColor(Color.RED);
        deathsDataSet.setDrawCircles(false);
        deathsDataSet.setLineWidth(1f);
        lineDataSet.addDataSet(deathsDataSet);

        mLineChart.setData(lineDataSet);
        mLineChart.notifyDataSetChanged();
        mLineChart.invalidate();
    }


    private void initGraphViews(View root){
        Log.d(TAG, "initGraphViews: called");

        mLineChart = (LineChart) root.findViewById(R.id.line_graph);
        mLineChart.getDescription().setText("Covid-19 Data");



        // Setting references for the X / Y axes
        XAxis xAxis = mLineChart.getXAxis();
        YAxis yAxisLeft = mLineChart.getAxisLeft();
        YAxis yAxisRight = mLineChart.getAxisRight();
        xAxis.setCenterAxisLabels(false);
        yAxisLeft.setCenterAxisLabels(false);

        // Remove the background grid lines
        yAxisLeft.setDrawGridLines(true);
        yAxisRight.setDrawGridLines(false);
        xAxis.setDrawGridLines(false);

        // Leveling the position of the X / Y axes (i.e. so 0 is hard up against the lines)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0);
        yAxisLeft.setAxisMinimum(0f);

        // No labeling on the right hand Y axis of the graph
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);

        mLineChart.setTouchEnabled(true);
    }

    public void search(String newText){
        Log.d(TAG, "search: ");
    }
}

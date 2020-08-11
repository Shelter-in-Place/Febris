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
import com.project.febris.models.Dataset;
import com.project.febris.models.Place;
import com.project.febris.models.PlaceWithDatasets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class FragmentTop2 extends Fragment  {
    public static final String TAG = "Fragment Top 2";
    private ListViewModel mListViewModel;
    public List<PlaceWithDatasets> mPlaces = new ArrayList<>();
    public MutableLiveData<List<Place>> mPlacesLocal;
    private TextView countryHeader;
    private TextView totalInfections;
    private TextView currentInfections;
    private TextView deaths;
    private TextView recovered;
    private MutableLiveData<List<Place>> totDeathsCountry;
    private float mValue;
    private LineChart mLineChart;
    private List<Entry> entriesNewCases = new ArrayList<Entry>();
    private List<Entry> entriesNewDeaths = new ArrayList<Entry>();
    List<Dataset> datasets = new ArrayList<Dataset>();

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_top_2, container, false);
        initViewModel();
        initGraphViews(root);

        return root;
    }

    private void initViewModel(){
        Log.d(TAG, "initViewModel: called");
        mListViewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);
        mListViewModel.getFavPlaces().observe(this, new Observer<List<PlaceWithDatasets>>() {
            @Override
            public void onChanged(List<PlaceWithDatasets> places) {

                try {
                    mPlaces = places;
                    setData(mPlaces);
                }
                catch (Exception err){
                    Log.d(TAG, "onChanged: " + err);
                }
            }
        });

    }

    private void setData(List<PlaceWithDatasets> places){
        entriesNewCases.clear();
        entriesNewDeaths.clear();
        datasets.clear();
        mLineChart.invalidate();
        mLineChart.clear();

        try {
            Log.d(TAG, "setData: size of array "+ places.get(0).getDatasets().size());
            for(int i = 0; i< places.size(); i++){
                for(int x = 0; x< places.get(i).getDatasets().size(); x++){

                    Dataset dataset = places.get(i).getDatasets().get(x);
                    Log.d(TAG, "CHECK: " + dataset.getNew_cases());
                    datasets.add(new Dataset(null, dataset.getDate(), dataset.getTotal_cases(), dataset.getNew_cases(), dataset.getTotal_deaths(), dataset.getNew_deaths(), dataset.getTotal_cases_per_million(), dataset.getNew_cases_per_million(), dataset.getTotal_deaths_per_million(), dataset.getNew_deaths_per_million(), dataset.getCountry_key()));
                    Log.d(TAG, "CHECK 2: " + datasets.get(datasets.size()-1).getNew_cases());
                    Collections.sort(datasets, new SortbyDate());
                    Log.d(TAG, "Collections: " + datasets.get(datasets.size()-1).getDate() + ' ' + datasets.get(datasets.size()-1).getNew_cases());

                }

                for(int y = 0; y< places.get(i).getDatasets().size(); y++){
                    Log.d(TAG, "setData: date current =" + datasets.get(y).getDate() + ' ' + datasets.get(y).getNew_cases());
                    entriesNewCases.add(new Entry(y, datasets.get(y).getNew_cases()));
                }
            }

        }
        catch (Exception err){
            Log.d(TAG, "onChanged: " + err);
        }

        LineDataSet activeDataSet = new LineDataSet(entriesNewCases, "New Cases");
        activeDataSet.setColor(Color.BLUE);
        activeDataSet.setDrawCircles(false);
        activeDataSet.setLineWidth(1f);
        LineData lineDataSet = new LineData(activeDataSet);
//
//        LineDataSet deathsDataSet = new LineDataSet(entriesDeaths, "Deaths");
//        deathsDataSet.setColor(Color.RED);
//        deathsDataSet.setDrawCircles(false);
//        deathsDataSet.setLineWidth(1f);
//        lineDataSet.addDataSet(deathsDataSet);

        mLineChart.setData(lineDataSet);
        mLineChart.notifyDataSetChanged();
        mLineChart.invalidate();

    }


    private void initGraphViews(View root) {
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


    static class SortbyDate implements Comparator<Dataset>{
        @Override
        public int compare(Dataset a, Dataset b) {
            return a.getDate().compareTo(b.getDate());
        }
    }

//
//    public void search(String newText){
//        Log.d(TAG, "search: ");
//    }



    //    private void setTable(List<Place> places){
//        countryHeader.setText(places.get(0).getLocation());
////        totalInfections.setText("" + places.get(0).getInfections());
////        currentInfections.setText("" + places.get(0).getCurrentInfections());
////        deaths.setText("" + places.get(0).getDeaths());
////        recovered.setText("" + places.get(0).getRecovered());
//        totalInfections.setText("");
//        currentInfections.setText("");
//        deaths.setText("");
//        recovered.setText("");
//    }
}

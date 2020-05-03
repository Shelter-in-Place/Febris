package com.project.febris.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.project.febris.R;

public class Fragment3 extends Fragment {
    private static final String TAG = "Fragment3";
    LineGraphSeries<DataPoint> series;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_screen_3, container, false);


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

    public void search(String newText){
        Log.d(TAG, "search: ");
    }
}

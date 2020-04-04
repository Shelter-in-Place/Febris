package com.project.febris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GraphScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_screen);
    }

    public void buttonClick(View view){
        Button b = (Button) view;
        Intent i = new Intent(GraphScreen.this, MainActivity.class);
        GraphScreen.this.startActivity(i);
    }
}

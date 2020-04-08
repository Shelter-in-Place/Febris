//this is a sample note
package com.project.febris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CardView card_1;
    private CardView card_2;
    private CardView card_3;
    private CardView card_4;
    private CardView card_5;
    private CardView card_6;
    private CardView card_7;
    private CardView card_8;
    private CardView card_9;
    private CardView card_10;
    private CardView card_11;
    private CardView card_12;
    private CardView card_13;
    private CardView card_14;

    private Button text_1;
    private Button text_2;
    private Button text_3;
    private Button text_4;
    private Button text_5;
    private Button text_6;
    private Button text_7;
    private Button text_8;
    private Button text_9;
    private Button text_10;
    private Button text_11;
    private Button text_12;
    private Button text_13;
    private Button text_14;

    private Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card_1 = findViewById(R.id.card_1);
        card_2 = findViewById(R.id.card_2);
        card_3 = findViewById(R.id.card_3);
        card_4 = findViewById(R.id.card_4);
        card_5 = findViewById(R.id.card_5);
        card_6 = findViewById(R.id.card_6);
        card_7 = findViewById(R.id.card_7);
        card_8 = findViewById(R.id.card_8);
        card_9 = findViewById(R.id.card_9);
        card_10 = findViewById(R.id.card_10);
        card_11 = findViewById(R.id.card_11);
        card_12 = findViewById(R.id.card_12);
        card_13 = findViewById(R.id.card_13);
        card_14 = findViewById(R.id.card_14);

        this.text_1 = (Button) card_1.findViewById(R.id.text);
        text_2 = (Button) card_2.findViewById(R.id.text);
        text_3 = (Button) card_3.findViewById(R.id.text);
        text_4 = (Button) card_4.findViewById(R.id.text);
        text_5 = (Button) card_5.findViewById(R.id.text);
        text_6 = (Button) card_6.findViewById(R.id.text);
        text_7 = (Button) card_7.findViewById(R.id.text);
        text_8 = (Button) card_8.findViewById(R.id.text);
        text_9 = (Button) card_9.findViewById(R.id.text);
        text_10 = (Button) card_10.findViewById(R.id.text);
        text_11 = (Button) card_11.findViewById(R.id.text);
        text_12 = (Button) card_12.findViewById(R.id.text);
        text_13 = (Button) card_13.findViewById(R.id.text);
        text_14 = (Button) card_14.findViewById(R.id.text);

        //Instantiating a new database
        this.database = new Database();
        setText(text_1, "New York");
        setText(text_2, "New Jersey");
        setText(text_3, "Michigan");
        setText(text_4, "California");
        setText(text_5, "Massachusetts");
        setText(text_6, "Illinois");
        setText(text_7, "Florida");
        setText(text_8, "Louisiana");
        setText(text_9, "Pennsylvania");
        setText(text_10, "Washington");
        setText(text_11, "Georgia");
        setText(text_12, "Texas");
        setText(text_13, "Colorado");
        setText(text_14, "Connecticut");
    }

    public void setText(Button button, String state){
        int result = this.database.infected_data.get(state);
        button.setText(state + " \nInfections: " + String.valueOf(result));
    }

    public void buttonClick(View view){
        Button b = (Button) view;
        Intent i = new Intent(MainActivity.this, GraphScreen.class);
        MainActivity.this.startActivity(i);
    }
//test Shelter in Place
}

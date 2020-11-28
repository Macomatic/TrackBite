package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

public class FoodGraph_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_graph__page);
    }

    protected void onStart() {
        super.onStart();
        Bundle extra = getIntent().getExtras();
        String[] values = extra.getStringArray("values");

        TextView timeRange = (TextView) findViewById(R.id.timeRange);
        TextView foodAnalysed = (TextView) findViewById(R.id.foodAnalysed);

        timeRange.setText(values[0]);
        foodAnalysed.setText(values[1]);
    }
    public void backFoodGraphPage (View view){
        Intent intent = new Intent (getApplicationContext(), FoodAnalysis_Page.class);
        startActivity(intent);
    }
}
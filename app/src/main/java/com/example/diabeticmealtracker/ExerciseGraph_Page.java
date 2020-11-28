package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExerciseGraph_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_graph__page);
    }

    protected void onStart() {
        super.onStart();
        Bundle extra = getIntent().getExtras();
        String[] values = extra.getStringArray("values");

        TextView timeRange = (TextView) findViewById(R.id.activityTimeRange);
        TextView exerciseAnalysis = (TextView) findViewById(R.id.exerciseAnalysis);

        timeRange.setText(values[0]);
        exerciseAnalysis.setText(values[1]);
    }

    public void backExerciseGraphPage (View view){
        Intent intent = new Intent (getApplicationContext(), ExerciseAnalysis_Page.class);
        startActivity(intent);
    }
}
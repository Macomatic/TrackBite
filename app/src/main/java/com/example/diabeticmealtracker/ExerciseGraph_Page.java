package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

        if (values[0].equals("Week") || values[0].equals("Month") || values[0].equals("Year")) {
            timeRange.setText("Data for the last "+values[0]);
        }
        else {
            String firstDate = convertStringToDate(values[0].substring(0,8));
            String secondDate = convertStringToDate(values[0].substring(9));
            timeRange.setText("From "+firstDate+" to "+secondDate);
        }
        if (values[1].equals("All")) {
            exerciseAnalysis.setText("Analyzing all possible values");
        }
        else {
            exerciseAnalysis.setText("Analyzing " + values[1]);
        }

    }

    public String convertStringToDate(String date) { // Format of yyyymmdd
        String[] months = new String[]{"January", "February", "March", "April","May","June","July","August","September","October","November","December"};
        int monthIndex = Integer.parseInt(date.substring(2,4))-1;
        return months[monthIndex] + " " + date.substring(0,2) + ", " + date.substring(4);
    }

    public void backExerciseGraphPage (View view){
        Intent intent = new Intent (getApplicationContext(), ExerciseAnalysis_Page.class);
        startActivity(intent);
    }
}
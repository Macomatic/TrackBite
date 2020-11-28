package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAnalysis_Page extends AppCompatActivity {

    private String currActivityAnalyse, currTimeRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_analysis__page);

        EditText firstRange = (EditText) findViewById(R.id.firstCustomRange);
        EditText lastRange = (EditText) findViewById(R.id.lastCustomRange);

        Spinner rangeSpinner = (Spinner) findViewById(R.id.timeFrameSpinner);
        Spinner exerciseSpinner = (Spinner) findViewById(R.id.exerciseAnalyseSpinner);

        // List of Activities
        List<String> timeRangeArray = new ArrayList<String>();
        timeRangeArray.add("None");
        timeRangeArray.add("Week");
        timeRangeArray.add("Month");
        timeRangeArray.add("Year");

        List<String> exerciseAnalysisArray = new ArrayList<>();
        exerciseAnalysisArray.add("All");
        exerciseAnalysisArray.add("Calories Burned");
        exerciseAnalysisArray.add("Active hours");

        // Array adapter (context, layout of spinner, and array values in the spinner)
        ArrayAdapter<String> timeRangeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeRangeArray);
        ArrayAdapter<String> exerciseAnalysisAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exerciseAnalysisArray);

        // set drop down menu
        timeRangeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rangeSpinner.setAdapter(timeRangeAdapter);

        exerciseAnalysisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseSpinner.setAdapter(exerciseAnalysisAdapter);

        rangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                currTimeRange = timeRangeArray.get(pos);
                if (currTimeRange.equals("None")) {
                    firstRange.getText().clear();
                    lastRange.getText().clear();
                    firstRange.setEnabled(true);
                    lastRange.setEnabled(true);
                    firstRange.setHint("ddmmyyyy");
                    lastRange.setHint("ddmmyyyy");
                }
                else {
                    firstRange.getText().clear();
                    lastRange.getText().clear();
                    firstRange.setEnabled(false);
                    lastRange.setEnabled(false);
                    firstRange.setHint("N/A");
                    lastRange.setHint("N/A");
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        exerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                currActivityAnalyse = exerciseAnalysisArray.get(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void backExerciseAnalysis (View view){
        Intent intent = new Intent (getApplicationContext(), Data_Analysis_Page.class);
        startActivity(intent);
    }
    public void nextExerciseAnalysis (View view){
        EditText firstRange = (EditText) findViewById(R.id.firstCustomRange);
        EditText lastRange = (EditText) findViewById(R.id.lastCustomRange);
        String firstRangeText = firstRange.getText().toString();
        String lastRangeText = lastRange.getText().toString();
        if (this.currActivityAnalyse == null || (this.currTimeRange.equals("None") && (firstRangeText.length() != 8 || lastRangeText.length() != 8))) {
            Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            String[] graphValues = new String[2];
            if (this.currTimeRange.equals("None") == false) {
                graphValues[0] = this.currTimeRange;
            }
            else {
                graphValues[0] = firstRangeText + "-" + lastRangeText;
            }
            graphValues[1] = this.currActivityAnalyse;
            Intent intent = new Intent (getApplicationContext(), ExerciseGraph_Page.class);
            startActivity(intent.putExtra("values", graphValues));
        }
    }
}
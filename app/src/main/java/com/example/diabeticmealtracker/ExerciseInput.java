package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ExerciseInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_input);

        Spinner actSpinner = (Spinner) findViewById(R.id.activitySpinner);
        Spinner unitSpinner = (Spinner) findViewById(R.id.walkingDistUnitSpinner);
        Spinner bikeSpinner = (Spinner) findViewById(R.id.bikingSpinner);

        // List of Activities
        List<String> activityArray = new ArrayList<String>();
        activityArray.add("Biking");
        activityArray.add("Walking");
        activityArray.add("Running");

        List<String> unitArray = new ArrayList<>();
        unitArray.add("KM");
        unitArray.add("Steps");

        List<String> bikingArray = new ArrayList<>();
        bikingArray.add("Slow");
        bikingArray.add("Moderate");
        bikingArray.add("Fast");


        // Array adapter (context, layout of spinner, and array values in the spinner)
        ArrayAdapter<String> actAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activityArray);
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitArray);
        ArrayAdapter<String> bikingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bikingArray);

        // set drop down menu
        actAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bikingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        actSpinner.setAdapter(actAdapter);
        unitSpinner.setAdapter(unitAdapter);
        bikeSpinner.setAdapter(bikingAdapter);

        // Gets all dynamic elements
        TextView numOfSteps = (TextView) findViewById(R.id.walkingText);
        TextView walkingDistText = (TextView) findViewById(R.id.walkingDistText);

        actSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if (activityArray.get(pos).equals("Biking")) {
                    numOfSteps.setVisibility(View.GONE);
                    walkingDistText.setVisibility(View.GONE);
                    unitSpinner.setVisibility(View.GONE);
                }
                else {
                    numOfSteps.setVisibility(View.VISIBLE);
                    walkingDistText.setVisibility(View.VISIBLE);
                    unitSpinner.setVisibility(View.VISIBLE);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                // your code here
            }

        });
    }



    public void backExerciseInput (View view){
        Intent intent = new Intent (getApplicationContext(), Exercise_Page.class);
        startActivity(intent);
    }


    public void doneInput (View view){
        finish();
    }
}
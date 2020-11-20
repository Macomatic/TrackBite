package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ExerciseInput extends AppCompatActivity {

    private double weight; // kg
    private double height; // cm
    private int age; // yrs
    private String sex; // male or female
    private double duration; // hrs
    private String currActivity;
    private String currSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.weight = 60.0;
        this.height = 170.0;
        this.age = 50;
        this.sex = "Female";
        this.duration = 3.0;
        this.currActivity = "";
        this.currSpeed = "";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_input);

        Spinner actSpinner = (Spinner) findViewById(R.id.activitySpinner);
        Spinner bikeSpinner = (Spinner) findViewById(R.id.bikingSpinner);

        // List of Activities
        List<String> activityArray = new ArrayList<String>();
        activityArray.add("Biking");
        activityArray.add("Walking");
        activityArray.add("Running");

        List<String> bikingArray = new ArrayList<>();
        bikingArray.add("Slow");
        bikingArray.add("Moderate");
        bikingArray.add("Fast");


        // Array adapter (context, layout of spinner, and array values in the spinner)
        ArrayAdapter<String> actAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activityArray);
        ArrayAdapter<String> bikingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bikingArray);

        // set drop down menu
        actAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bikingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        actSpinner.setAdapter(actAdapter);
        bikeSpinner.setAdapter(bikingAdapter);

        // Gets all dynamic elements


        actSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                currActivity = activityArray.get(pos);
                currSpeed = bikingArray.get(pos);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                // your code here
            }

        });
    }

    private double BMR(){
        //https://www.omnicalculator.com/health/bmr#:~:text=This%20BMR%20formula%20is%20as,males%20and%20%2D161%20for%20females.
        double value = 0;
        value = 10 * this.weight + 6.25 * this.height - 5 * this.age;

        if (this.sex.equals("Male")){
            value+= 5;
        }
        else{
            value-=161;
        }

        return value;
    }

    private double METS (){
        if (currActivity.equals("Biking")) {
            if (this.currSpeed.equals("Slow")){
                return 3.5;
            }
            else if (this.currSpeed.equals("Moderate")){
                return 5.8;
            }
            else{
                return 8;
            }
        }
        else if (currActivity.equals("Walking")) {
            if (this.currSpeed.equals("Slow")) {
                return 2;
            }
            else if (this.currSpeed.equals("Moderate")) {
                return 2.9;
            }
            else {
                return 3.5;
            }
        }
        else { // Running
            if (this.currSpeed.equals("Slow")) {
                return 11;
            }
            else if (this.currSpeed.equals("Moderate")) {
                return 11.7;
            }
            else {
                return 12.5;
            }
        }

    }

    private double calories(){
        return BMR() * METS() / 24 * this.duration;
    }

    public void backExerciseInput (View view){
        Intent intent = new Intent (getApplicationContext(), Exercise_Page.class);
        startActivity(intent);
    }

    public void doneInput (View view){
        EditText durationInput = (EditText) findViewById(R.id.hoursInput);
        if (TextUtils.isEmpty(durationInput.getText())) {
            Toast.makeText(getApplicationContext(), "Please put an hour input", Toast.LENGTH_SHORT).show();
        }
        else {
            this.duration = Float.parseFloat(durationInput.getText().toString().trim());
            Toast.makeText(getApplicationContext(), "Successfully added exercise", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), SuccessExerciseInput_Page.class);
            startActivity(intent);
        }

    }
}
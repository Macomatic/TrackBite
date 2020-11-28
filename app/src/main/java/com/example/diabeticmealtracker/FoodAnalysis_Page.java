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

public class FoodAnalysis_Page extends AppCompatActivity {

    private String currRange;
    private String currFood;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_analysis_page);

        EditText firstRange = (EditText) findViewById(R.id.customRange1);
        EditText lastRange = (EditText) findViewById(R.id.customRange2);

        Spinner rangeSpinner = (Spinner) findViewById(R.id.rangeSpinner);
        Spinner foodSpinner = (Spinner) findViewById(R.id.foodSpinner);

        // List of Activities
        List<String> rangeArray = new ArrayList<String>();
        rangeArray.add("None");
        rangeArray.add("Week");
        rangeArray.add("Month");
        rangeArray.add("Year");

        List<String> foodArray = new ArrayList<>();
        foodArray.add("All");
        foodArray.add("Carbohydrates");
        foodArray.add("Fats");
        foodArray.add("Protein");
        foodArray.add("Sugar");

        // Array adapter (context, layout of spinner, and array values in the spinner)
        ArrayAdapter<String> rangeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rangeArray);
        ArrayAdapter<String> foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, foodArray);

        // set drop down menu
        rangeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rangeSpinner.setAdapter(rangeAdapter);

        foodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(foodAdapter);

        rangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                currRange = rangeArray.get(pos);
                if (currRange.equals("None")) {
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

        foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                currFood = foodArray.get(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void backFoodDataAnalysis (View view){
        Intent intent = new Intent (getApplicationContext(), Data_Analysis_Page.class);
        startActivity(intent);
    }
    public void nextFoodDataAnalysis (View view){
        EditText firstRange = (EditText) findViewById(R.id.customRange1);
        EditText lastRange = (EditText) findViewById(R.id.customRange2);
        String firstRangeText = firstRange.getText().toString();
        String lastRangeText = lastRange.getText().toString();
        if (this.currFood == null || (this.currRange.equals("None") && (firstRangeText.length() != 8 || lastRangeText.length() != 8))) {
            Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            String[] graphValues = new String[2];
            if (this.currRange.equals("None") == false) {
                graphValues[0] = this.currRange;
            }
            else {
                graphValues[0] = firstRangeText + "-" + lastRangeText;
            }
            graphValues[1] = this.currFood;
            Intent intent = new Intent (getApplicationContext(), FoodGraph_Page.class);
            startActivity(intent.putExtra("values", graphValues));
        }

    }
}
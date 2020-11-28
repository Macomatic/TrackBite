package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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
        rangeArray.add("    ");
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
                currFood = foodArray.get(pos);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
    }
    public void backFoodDataAnalysis (View view){
        Intent intent = new Intent (getApplicationContext(), Data_Analysis_Page.class);
        startActivity(intent);
    }
    public void nextFoodDataAnalysis (View view){
        Intent intent = new Intent (getApplicationContext(), FoodGraph_Page.class);
        startActivity(intent);
    }
}
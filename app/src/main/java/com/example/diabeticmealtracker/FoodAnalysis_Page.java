package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FoodAnalysis_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_analysis_page);
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
package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FoodGraph_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_graph__page);
    }
    public void backFoodGraphPage (View view){
        Intent intent = new Intent (getApplicationContext(), FoodAnalysis_Page.class);
        startActivity(intent);
    }
}
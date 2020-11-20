package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExerciseAnalysis_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_analysis__page);
    }
    public void backExerciseAnalysis (View view){
        Intent intent = new Intent (getApplicationContext(), Data_Analysis_Page.class);
        startActivity(intent);
    }
    public void nextExerciseAnalysis (View view){
        Intent intent = new Intent (getApplicationContext(), ExerciseGraph_Page.class);
        startActivity(intent);
    }
}
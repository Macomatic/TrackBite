package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExerciseGraph_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_graph__page);
    }
    public void backExerciseGraphPage (View view){
        Intent intent = new Intent (getApplicationContext(), ExerciseAnalysis_Page.class);
        startActivity(intent);
    }
}
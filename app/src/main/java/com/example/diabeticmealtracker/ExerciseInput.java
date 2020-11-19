package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExerciseInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_input);
    }
    public void backExerciseInput (View view){
        Intent intent = new Intent (getApplicationContext(), Exercise_Page.class);
        startActivity(intent);
    }
}
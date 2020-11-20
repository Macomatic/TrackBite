package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuccessExerciseInput_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_exercise_input__page);
    }
    public void finishExerciseEntry (View view){
        Intent intent = new Intent (getApplicationContext(), Exercise_Page.class);
        startActivity(intent);
    }
}
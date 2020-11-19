package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TypeOfDance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_dance);
    }
    public void backTypeOfDance (View view){
        Intent intent = new Intent (getApplicationContext(), ExerciseChoice.class);
        startActivity(intent);
    }
}
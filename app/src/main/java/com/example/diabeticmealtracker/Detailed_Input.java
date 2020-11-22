package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Detailed_Input extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed__input);
    }
    public void backDetailedInputPage (View view){
        Intent intent = new Intent (getApplicationContext(), Manual_Input.class);
        startActivity(intent);
    }
}
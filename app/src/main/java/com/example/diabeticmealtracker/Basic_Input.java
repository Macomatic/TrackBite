package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Basic_Input extends AppCompatActivity {

    // element variables
    EditText name, servingSize, fats, carbohydrates, sugar, fibre;
    Button done;
    // Firebase
    DatabaseReference databaseFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic__input);

        // add all the edit text
        name = (EditText) findViewById(R.id.basicInputName);
        servingSize = (EditText) findViewById(R.id.basicInputServing);
        fats = (EditText) findViewById(R.id.basicInputFat);
        carbohydrates = (EditText) findViewById(R.id.basicInputCarbs);
        sugar = (EditText) findViewById(R.id.basicInputSugar);
        fibre = (EditText) findViewById(R.id.basicInputFibre);
        // submit button
        done = (Button) findViewById(R.id.basicInputDone);
        // database reference
        databaseFoods = FirebaseDatabase.getInstance().getReference().child("Food");
    }

}
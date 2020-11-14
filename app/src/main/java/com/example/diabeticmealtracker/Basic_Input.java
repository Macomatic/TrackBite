package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Basic_Input extends AppCompatActivity {

    // element variables
    EditText txtName, txtServingSize, txtFats, txtCarbohydrates, txtSugar, txtFibre;
    Button done;
    // Firebase
    DatabaseReference databaseFoods;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic__input);

        // add all the edit text
        txtName = (EditText) findViewById(R.id.basicInputName);
        txtServingSize = (EditText) findViewById(R.id.basicInputServing);
        txtFats = (EditText) findViewById(R.id.basicInputFat);
        txtCarbohydrates = (EditText) findViewById(R.id.basicInputCarbs);
        txtSugar = (EditText) findViewById(R.id.basicInputSugar);
        txtFibre = (EditText) findViewById(R.id.basicInputFibre);
        // submit button
        done = (Button) findViewById(R.id.basicInputDone);
        // database reference
        food = new Food();
        databaseFoods = FirebaseDatabase.getInstance().getReference().child("Food");
        // done button
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // parsing the input from the input fields
                String name = (String) txtName.getText().toString().trim();
                float servingSize = Float.parseFloat(txtServingSize.getText().toString().trim());
                float fats = Float.parseFloat(txtFats.getText().toString().trim());
                float carbohydrates = Float.parseFloat(txtCarbohydrates.getText().toString().trim());
                float sugar = Float.parseFloat(txtSugar.getText().toString().trim());
                float fibre = Float.parseFloat(txtFibre.getText().toString().trim());
                // setting the field inputs into the food object
                food.setName(name);
                food.setServingSize(servingSize);
                food.setFat(fats);
                food.setCarbohydrates(carbohydrates);
                food.setSugar(sugar);
                food.setFibre(fibre);
                // push food object onto firebase
                databaseFoods.child(name).setValue(food);
                // notification saying the input has been successfully added to firebase
                Toast.makeText(Basic_Input.this, "Input Successful", Toast.LENGTH_LONG).show();
            }
        });
    }

}
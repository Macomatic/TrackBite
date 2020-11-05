package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InputSelection extends AppCompatActivity {

    Button manualInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page);

        //
        manualInput = (Button) findViewById(R.id.manualinput);
    }

    public void onManualInput(View view){
        Intent intent = new Intent (InputSelection.this, ManualInput.class);
        startActivityForResult(intent,0);
    }
}
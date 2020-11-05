package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManualInput extends AppCompatActivity {

    Button basicInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input);

        //
        basicInput = (Button) findViewById(R.id.basicinput);
    }

    public void onBasicInput(View view) {
        Intent intent = new Intent(ManualInput.this, BasicInput.class);
        startActivityForResult(intent, 0);
    }
}
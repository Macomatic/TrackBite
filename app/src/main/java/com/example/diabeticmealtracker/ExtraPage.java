package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExtraPage extends AppCompatActivity {

    double itemValue;
    String value;
    String text;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_page);

        spinner = (Spinner) findViewById(R.id.heightValues);
        adapter = ArrayAdapter.createFromResource(this, R.array.unit1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        EditText measurement = (EditText) findViewById(R.id.heightEditText);
        TextView output = (TextView) findViewById(R.id.textView47);
        text = measurement.getText().toString();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                value = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void backButtonClick (View view){
        finish();
    }


    public void generateButtonClick (View view){
        //finish();
        Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
        if(value.equals("Cm-Inches")){
            if(isNumeric(text)){
                double outputValue = Double.parseDouble(text);
                outputValue = outputValue / 2.54;
                Toast.makeText(getApplicationContext(), String.valueOf(outputValue), Toast.LENGTH_SHORT).show();
                EditText measurement = (EditText) findViewById(R.id.heightEditText);
                TextView output = (TextView) findViewById(R.id.textView47);
                output.setText(String.valueOf(outputValue));
                output.setText("HELLO");
            }
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }



}
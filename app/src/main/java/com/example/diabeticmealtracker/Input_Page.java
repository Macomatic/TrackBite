package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Input_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page);
    }

    public void onManualInput(View view){
        Intent intent = new Intent (Input_Page.this, Manual_Input.class);
        startActivity(intent);
    }
    public void onBarcodeScan(View view){
        Intent intent = new Intent (Input_Page.this, Barcode_Scan.class);
        startActivity(intent);
    }
    public void databaseButtonClick (View view){
        Intent intent = new Intent (getApplicationContext(), Database_Page.class);
        startActivity(intent);
    }
    public void backInputPage (View view){
        finish();
    }
    public void onScanNutritionalFacts (View view){
        Intent intent = new Intent (getApplicationContext(), ScanNutritionalFacts.class);
        startActivity(intent);
    }
}
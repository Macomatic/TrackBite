package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainScreenPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_page);
    }

    public void inputButtonClick (View view){
        Intent intent = new Intent (getApplicationContext(), Input_Page.class);
        startActivity(intent);
    }

    public void exerciseButtonClick (View view){
        Intent intent = new Intent (getApplicationContext(), Exercise_Page.class);
        startActivity(intent);
    }

    public void data_analysisButtonClick (View view){
        Intent intent = new Intent (getApplicationContext(), Data_Analysis_Page.class);
        startActivity(intent);
    }

    public void profileButtonClick (View view){
        Intent intent = new Intent (getApplicationContext(), ProfilePage.class);
        startActivity(intent);
    }

    public void extraButtonClick (View view){
        Intent intent = new Intent (getApplicationContext(), ExtraPage.class);
        startActivity(intent);
    }
}
package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Second_Notification_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__notification__page);
    }

    public void backButtonClick (View view){
        Intent intent = new Intent (getApplicationContext(), MainScreenPage.class);
        startActivity(intent);
    }

    public void mealButtonClick (View view){
        Intent intent = new Intent (getApplicationContext(), Notifcation_Setup_Meal.class);
        startActivity(intent);
    }

    public void exercisesButtonClick (View view){
        Intent intent = new Intent (getApplicationContext(), Notifcation_setup_exercise.class);
        startActivity(intent);
    }
}
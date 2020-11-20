package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Notifcation_setup_exercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifcation_setup_exercise);
    }
    public void backNotificationSetup (View view){
        Intent intent = new Intent (getApplicationContext(), Second_Notification_Page.class);
        startActivity(intent);
    }
}
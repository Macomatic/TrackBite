package com.example.diabeticmealtracker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class Notifcation_Setup_Meal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifcation__setup__meal);
    }

    public void backNotificationSetup (View view){
        Intent intent = new Intent (getApplicationContext(), Second_Notification_Page.class);
        startActivity(intent);
    }
}
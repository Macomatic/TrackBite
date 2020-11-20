package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UpdateProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_page);
    }
    public void finishUpdateMyProfile (View view){
        Intent intent = new Intent (getApplicationContext(), ProfilePage.class);
        startActivity(intent);
    }
}
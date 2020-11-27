package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProfilePage extends AppCompatActivity {

    private double weight; // kg
    private double height; // cm
    private int age; // yrs
    private String sex; // male or female
    Profile profile;
    DatabaseReference databaseProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        profile = new Profile();
        databaseProfile = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_page);
        CheckBox _checkMale = (CheckBox) findViewById(R.id.checkMale);
        CheckBox _checkFemale = (CheckBox) findViewById(R.id.checkFemale);

        _checkMale.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (_checkMale.isChecked()) {
                    _checkFemale.setVisibility(View.INVISIBLE);
                }
                if (!_checkMale.isChecked()){
                    _checkFemale.setVisibility(View.VISIBLE);
                }
            }
        });
        _checkFemale.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (_checkFemale.isChecked()) {
                    _checkMale.setVisibility(View.INVISIBLE);
                }
                if (!_checkFemale.isChecked()){
                    _checkMale.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void finishUpdateMyProfile(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
        startActivity(intent);
    }

    public void updateChangesMyProfile(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
        startActivity(intent);

        EditText _updateAge = (EditText) findViewById(R.id.updateAge);
        EditText _updateHeight = (EditText) findViewById(R.id.updateHeight);
        EditText _updateWeight = (EditText) findViewById(R.id.updateWeight);
        CheckBox _checkMale = (CheckBox) findViewById(R.id.checkMale);
        CheckBox _checkFemale = (CheckBox) findViewById(R.id.checkFemale);

        if (TextUtils.isEmpty(_updateAge.getText())) {
            Toast.makeText(getApplicationContext(), "Please put an age", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(_updateHeight.getText())) {
            Toast.makeText(getApplicationContext(), "Please put a height", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(_updateWeight.getText())) {
            Toast.makeText(getApplicationContext(), "Please put a weight", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(_checkMale.getText()) && TextUtils.isEmpty(_checkFemale.getText())){
            Toast.makeText(getApplicationContext(), "Please put a sex", Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(getApplicationContext(), "Successfully updated Profile", Toast.LENGTH_SHORT).show();

            this.age = Integer.parseInt(_updateAge.getText().toString().trim());
            this.height = Float.parseFloat(_updateHeight.getText().toString().trim());
            this.weight = Float.parseFloat(_updateHeight.getText().toString().trim());

            if (_checkMale.isChecked()){
                _checkFemale.setEnabled(false);
                this.sex = _checkMale.getText().toString().trim();
            }
            else if (_checkFemale.isChecked()){
                _checkMale.setEnabled(false);
                this.sex = _checkFemale.getText().toString().trim();
            }

            profile.setAge(age);
            profile.setHeight((float) height);
            profile.setWeight((float) weight);
            profile.setSex(sex);

            databaseProfile.child("Profile").setValue(profile);
        }
    }

}


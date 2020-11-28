package com.example.diabeticmealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class YogaInput extends AppCompatActivity {

    private double weight; // kg
    private double duration; // hrs
    private String currActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_input);

        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user
        DocumentReference docRef = db.collection("users").document(user.getUid().toString()).collection("userData").document("profile");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() { //Does the .get() command with a custom onComplete
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult(); //Grab snapshot of requirements
                    weight = Double.parseDouble(document.getString("Weight"));
                }
            }
        });

        currActivity = "";

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.LONG).format(currentTime);
        formattedDate = formattedDate.replace(",", "");
        String[] splitDate = formattedDate.split(" ");
        String month = convertMonthNum(splitDate[0]);
        String dateNum = splitDate[1];
        String year = splitDate[2];
        String date = year + month + dateNum;

        Spinner yogaSpinner = (Spinner) findViewById(R.id.yogaSpinner);

        // List of Activities
        List<String> yogaArray = new ArrayList<String>();
        yogaArray.add("Nadisodhana");
        yogaArray.add("Hatha");
        yogaArray.add("Sitting/Stretching");
        yogaArray.add("Surya Namaskar");
        yogaArray.add("Power Yoga");


        // Array adapter (context, layout of spinner, and array values in the spinner)
        ArrayAdapter<String> yogaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yogaArray);

        // set drop down menu
        yogaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yogaSpinner.setAdapter(yogaAdapter);

        yogaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                currActivity = yogaArray.get(pos);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                // your code here
            }

        });
    }

    public void doneInput (View view){}

    public void backExerciseInput (View view){
        Intent intent = new Intent (getApplicationContext(), Exercise_Page.class);
        startActivity(intent);

    }

    public String convertMonthNum(String month) {
        if (month.equals("January")) {
            return "01";
        } else if (month.equals("February")) {
            return "02";
        } else if (month.equals("March")) {
            return "03";
        } else if (month.equals("April")) {
            return "04";
        } else if (month.equals("May")) {
            return "05";
        } else if (month.equals("June")) {
            return "06";
        } else if (month.equals("July")) {
            return "07";
        } else if (month.equals("August")) {
            return "08";
        } else if (month.equals("September")) {
            return "09";
        } else if (month.equals("October")) {
            return "10";
        } else if (month.equals("November")) {
            return "11";
        } else if (month.equals("December")) {
            return "12";
        } else {
            return "MONTH ERROR";
        }
    }
}
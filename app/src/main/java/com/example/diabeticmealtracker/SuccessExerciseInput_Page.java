package com.example.diabeticmealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class SuccessExerciseInput_Page extends AppCompatActivity {

    DatabaseReference databaseProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_exercise_input__page);

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.LONG).format(currentTime);
        formattedDate = formattedDate.replace(",", "");
        String[] splitDate = formattedDate.split(" ");
        String month = convertMonthNum(splitDate[0]);
        String dateNum = splitDate[1];
        String year = splitDate[2];
        String date = month + dateNum + year;

        databaseProducts = FirebaseDatabase.getInstance().getReference("11262020");

    }
    protected void onStart() {
        super.onStart();

        databaseProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Exercise newActivity = dataSnapshot.getValue(Exercise.class);
                    Toast.makeText(getApplicationContext(), newActivity._exerciseActivity, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(null,String.valueOf(databaseError.getCode()));
                //Toast.makeText(getApplicationContext(), "The read failed: " + databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

//        databaseProducts.addValueEventListener(new ValueEventListener() {
//        //FirebaseDatabase db = FirebaseDatabase.getInstance(); //Grabs current instance of database
//        //DatabaseReference ref = db.getReference();
//        //ref.addValueEventListener(new ValueEventListener(){
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot){
//                // The current date when the button was pressed
//                Date currentTime = Calendar.getInstance().getTime();
//                String formattedDate = DateFormat.getDateInstance(DateFormat.LONG).format(currentTime);
//                formattedDate = formattedDate.replace(",", "");
//                String[] splitDate = formattedDate.split(" ");
//                String month = convertMonthNum(splitDate[0]);
//                String dateNum = splitDate[1];
//                String year = splitDate[2];
//                String date = month + dateNum + year;
//
//                TextView activity = (TextView) findViewById(R.id.activityValue);
//                TextView calories = (TextView) findViewById(R.id.caloriesValue);
//
//                try{
//                    //for(DataSnapshot ds : dataSnapshot.getChildren()){
//                        //Exercise newActivity = new Exercise();
//
//                        //Toast.makeText(getApplicationContext(), String.valueOf(ds.child(date).hasChildren("Exercise")), Toast.LENGTH_SHORT).show();
//
//                    //newActivity.setExerciseActivity(ds.child(date).child("Exercise").child("Walking").getValue(Exercise.class).getExerciseActivity());
//                        //newActivity.setCaloriesBurned(ds.child(date).child("Exercise").child("Walking").getValue(Exercise.class).getCaloriesBurned());
//
//
//                        //calories.setText(String.valueOf(exercise.getCaloriesBurned()));
//                    for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
//                        //Exercise newActivity = new Exercise();
//                        Exercise newActivity = postSnapshot.getValue(Exercise.class);
//                        //newActivity.setExerciseActivity(postSnapshot.child(date).child("Exercise").child("Walking").getValue(Exercise.class).getExerciseActivity());
//                        Toast.makeText(getApplicationContext(), newActivity.getExerciseActivity(), Toast.LENGTH_SHORT).show();
//                        activity.setText(newActivity.getExerciseActivity());
//                        calories.setText(String.valueOf(newActivity.getCaloriesBurned()));
//                    }
//                }
//                catch (Exception e){
//                    Toast.makeText(getApplicationContext(), String.valueOf(e), Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError){
//
//            }
//
//        });
    }


    public void finishExerciseEntry (View view){
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
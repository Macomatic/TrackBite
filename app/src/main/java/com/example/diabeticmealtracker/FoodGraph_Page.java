package com.example.diabeticmealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class FoodGraph_Page extends AppCompatActivity {

    public String displayedContent;
    public String dateRange;
    public String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_graph__page);
    }

    protected void onStart() {
        super.onStart();
        Bundle extra = getIntent().getExtras();
        String[] values = extra.getStringArray("values");

        TextView timeRange = (TextView) findViewById(R.id.timeRange);
        TextView foodAnalysed = (TextView) findViewById(R.id.foodAnalysed);

        timeRange.setText(values[0]);
        foodAnalysed.setText(values[1]);
    }
    public void backFoodGraphPage (View view)
    {
        finish();
    }

    public void switchGraph(View view) {

//        if (state) {
//            generatePieGraph(view);
//        }
//        else {
//            generateLineGraph(view);
//        }
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

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public String dateRetriever() {
        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.LONG).format(currentTime);
        formattedDate = formattedDate.replace(",", "");
        String[] splitDate = formattedDate.split(" ");
        String month = convertMonthNum(splitDate[0]);
        String dateNum = splitDate[1];
        String year = splitDate[2];
        String date = year + month + dateNum;
        return date;
    }

    public String dateDecrementer(String date, String decrementInterval) { // date = yyyymmdd
        int[] daysPerMonth = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        String newDate;
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6));


        if (decrementInterval.equals("Week")) {
            day -= 7;
            if (day <= 0) {
                month-=1;
                if (month == 0) {
                    year-=1;
                    month = 12;
                    day = daysPerMonth[month-1];
                }
                else {
                    day = daysPerMonth[month-1]-day;
                }
            }

        }
        else if (decrementInterval.equals("Month")) {
            month-=1;
            if (month == 0) {
                year-=1;
                month = 12;
                day = daysPerMonth[month-1];
            }

        }
        else { // Year
            year-=1;
        }
        newDate = String.valueOf(year)+String.valueOf(month)+String.valueOf(day);
        return newDate;
    }

    public String dateReorganizer(String date) { // Input as ddmmyyyy, output as yyyymmdd
        return date.substring(4)+date.substring(2,4)+date.substring(0,2);
    }

    public String convertStringToDate(String date) { // Format of yyyymmdd
        int monthIndex = Integer.parseInt(date.substring(4,6))-1;
        return this.months[monthIndex] + " " + date.substring(6) + ", " + date.substring(0,4);
    }

    public void generatePieGraph(View view) {

    }

    public void generateLineGraph(View view) {

    }

    public void backExerciseGraphPage (View view){
        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user

        db.collection("users").document(user.getUid()).collection("userData").document("Analysis")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        db.collection("users").document(user.getUid()).collection("userData").document("LineAnalysis")
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
}
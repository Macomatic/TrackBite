package com.example.diabeticmealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
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

    public String dateRange, displayedContent, whichGraph;
    public String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_graph__page);
        Button button = (Button) findViewById(R.id.button9);
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                button.setBackgroundResource(R.drawable.my_button_design);
                button.setText("seconds remaining: " + millisUntilFinished / 1000);
                button.setTextColor(getApplication().getResources().getColor(R.color.textColor));
                button.setBackgroundColor(getApplication().getResources().getColor(R.color.button_background2));
                button.setEnabled(false);
                button.setBackgroundResource(R.drawable.my_button_design);
            }

            public void onFinish() {
                button.setText("Generate");
                button.setTextColor(getApplication().getResources().getColor(R.color.white));
                button.setBackgroundResource(R.drawable.my_button_design);
                button.setEnabled(true);
            }
        }.start();

        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user
    }

    protected void onStart() {
        super.onStart();
        Bundle extra = getIntent().getExtras();
        String[] values = extra.getStringArray("values");


        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user

        TextView timeRange = (TextView) findViewById(R.id.timeRange);
        TextView foodAnalysed = (TextView) findViewById(R.id.foodAnalysed); //exerciseAnalysis

        timeRange.setText(values[0]);
        foodAnalysed.setText(values[1]);

        if (values[0].equals("Week") || values[0].equals("Month") || values[0].equals("Year")) {
            timeRange.setText("Data for the last " + values[0]);
            String currDate = dateRetriever();
            this.dateRange = dateDecrementer(currDate, values[0]) + "-" + currDate;
        } else {
            String firstDate = convertStringToDate(dateReorganizer(values[0].substring(0, 8)));
            String secondDate = convertStringToDate(dateReorganizer(values[0].substring(9)));
            timeRange.setText("From " + firstDate + " to " + secondDate);
            this.dateRange = dateReorganizer(values[0].substring(0, 8)) + "-" + dateReorganizer(values[0].substring(9));
        }
        if (values[1].equals("All")) {
            foodAnalysed.setText("Analyzing all possible values as portions");
            this.whichGraph = "Pie";
        } else {
            foodAnalysed.setText("Analyzing " + values[1] + " in grams");
        }

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
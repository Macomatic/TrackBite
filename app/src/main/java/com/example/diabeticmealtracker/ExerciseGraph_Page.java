package com.example.diabeticmealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseGraph_Page extends AppCompatActivity {

    public boolean displayCaloriesBurned, displayActiveHours;
    public String dateRange;
    public String[] months = new String[]{"January", "February", "March", "April","May","June","July","August","September","October","November","December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_graph__page);

        this.displayActiveHours = false;
        this.displayCaloriesBurned = false;

        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user
        db.collection("users").document(user.getUid().toString()).collection("userData")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> validDatesArray = new ArrayList<>();
                            Map<String,Object> validDates = new HashMap<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String docId = document.getId();
                                if (!docId.equals("profile") && !docId.equals("Analysis")) {
                                    int documentDate = Integer.parseInt(docId);
                                    if (documentDate >= Integer.parseInt(dateRange.substring(0, 8)) && documentDate <= Integer.parseInt(dateRange.substring(9))) {
                                        validDatesArray.add(docId);
                                    }
                                }
                            }
                            validDates.put("validDates",validDatesArray);
                            db.collection("users").document(user.getUid()).collection("userData").document("Analysis").set(validDates);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Error in retrieving documents from database", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    protected void onStart() {
        super.onStart();
        Bundle extra = getIntent().getExtras();
        String[] values = extra.getStringArray("values");

        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user

        TextView timeRange = (TextView) findViewById(R.id.activityTimeRange);
        TextView exerciseAnalysis = (TextView) findViewById(R.id.exerciseAnalysis);
        TextView testDBReading = (TextView) findViewById(R.id.testDBReading);

        if (values[0].equals("Week") || values[0].equals("Month") || values[0].equals("Year")) {
            timeRange.setText("Data for the last "+values[0]);
            String currDate = dateRetriever();
            this.dateRange = dateDecrementer(currDate, values[0]) + "-"+currDate;
        }
        else {
            String firstDate = convertStringToDate(values[0].substring(0,8));
            String secondDate = convertStringToDate(values[0].substring(9));
            timeRange.setText("From "+firstDate+" to "+secondDate);
            this.dateRange = dateReorganizer(values[0].substring(0,8)) + "-" + dateReorganizer(values[0].substring(9));
        }
        if (values[1].equals("All")) {
            exerciseAnalysis.setText("Analyzing all possible values");
            this.displayCaloriesBurned = true;
            this.displayActiveHours = true;
        }
        else {
            exerciseAnalysis.setText("Analyzing " + values[1]);
            if (values[1].equals("Calories Burned")) {
                this.displayCaloriesBurned = true;
                this.displayActiveHours = false;
            }
            else {
                this.displayCaloriesBurned = false;
                this.displayActiveHours = true;
            }
        }
        // TEMP CODE
        db.collection("users").document(user.getUid()).collection("userData").document("Analysis")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String dbContents = "";
                        DocumentSnapshot document = task.getResult();
                        List<String> dates = (List<String>) document.get("validDates");
//                        if (displayActiveHours) {
//                            for (String docID : docRef.get("validDates"))
//                        }
                        for (int i = 0; i < dates.size(); i++) {
                            dbContents+=dates.get(i)+" ";
                        }
                        testDBReading.setText(dbContents);
                    }

                });

        // END OF TEMP CODE

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
        int monthIndex = Integer.parseInt(date.substring(2,4))-1;
        return this.months[monthIndex] + " " + date.substring(0,2) + ", " + date.substring(4);
    }

    public void backExerciseGraphPage (View view){
        Intent intent = new Intent (getApplicationContext(), ExerciseAnalysis_Page.class);
        startActivity(intent);
    }
}
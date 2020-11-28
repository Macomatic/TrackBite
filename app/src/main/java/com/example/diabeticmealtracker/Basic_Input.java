package com.example.diabeticmealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basic_Input extends AppCompatActivity {

    // element variables
    EditText txtName, txtServingSize, txtFats, txtCarbohydrates, txtSugar, txtFibre, txtCalories;
    Button done, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic__input);

        // add all the edit text
        txtName = (EditText) findViewById(R.id.basicInputName);
        txtServingSize = (EditText) findViewById(R.id.basicInputServing);
        txtFats = (EditText) findViewById(R.id.basicInputFat);
        txtCarbohydrates = (EditText) findViewById(R.id.basicInputCarbs);
        txtSugar = (EditText) findViewById(R.id.basicInputSugar);
        txtFibre = (EditText) findViewById(R.id.basicInputFibre);
        txtCalories = (EditText) findViewById(R.id.basicInputCalories);
        // submit button
        done = (Button) findViewById(R.id.basicInputAdd);
        // clear button
        clear = (Button) findViewById(R.id.basicInputClear);
        // Firestore
        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user


        // Spinner for the meal times (breakfast, lunch, dinner)
        Spinner spnMeal = (Spinner) findViewById(R.id.mealSpinner);
        // List of meals
        List<String> mealArray = new ArrayList<String>();
        mealArray.add("Breakfast");
        mealArray.add("Lunch");
        mealArray.add("Dinner");
        mealArray.add("Snack");
        // Array adapter (context, layout of spinner, and array values in the spinner)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mealArray);
        // set drop down menu
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMeal.setAdapter(adapter);

        // done button
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // The current date when the button was pressed
                Date currentTime = Calendar.getInstance().getTime();
                String formattedDate = DateFormat.getDateInstance(DateFormat.LONG).format(currentTime);
                formattedDate = formattedDate.replace(",", "");
                String[] splitDate = formattedDate.split(" ");
                String month = convertMonthNum(splitDate[0]);
                String dateNum = splitDate[1];
                String year = splitDate[2];
                String date = year + month + dateNum;

                // parsing the input from the input fields
                String name = (String) txtName.getText().toString().trim();
                float servingSize = Float.parseFloat(txtServingSize.getText().toString().trim());
                float fats = Float.parseFloat(txtFats.getText().toString().trim());
                float carbohydrates = Float.parseFloat(txtCarbohydrates.getText().toString().trim());
                float sugar = Float.parseFloat(txtSugar.getText().toString().trim());
                float fibre = Float.parseFloat(txtFibre.getText().toString().trim());
                float calories = Float.parseFloat(txtCalories.getText().toString().trim());
                String meal = spnMeal.getSelectedItem().toString();
                // setting the field inputs into the food object
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("name", name);
                userInfo.put("servingSize", servingSize);
                userInfo.put("fats", fats);
                userInfo.put("carbohydrates", carbohydrates);
                userInfo.put("sugar", sugar);
                userInfo.put("fibre", fibre);
                userInfo.put("calories", calories);
                userInfo.put("meal", meal);

                // push food object onto firebase based on the meal time selected
                db.collection("users").document(user.getUid().toString()).collection("userData").document(date).collection("Food").document(name).set(userInfo, SetOptions.merge());
                // notification saying the input has been successfully added to firebase
                Toast.makeText(Basic_Input.this, "Input Successful", Toast.LENGTH_LONG).show();
            }
        });
        // clear button
        clear.setOnClickListener(new View.OnClickListener() {
            // clears all the input fields when the button is pressed
            public void onClick(View view) {
                txtName.setText("");
                txtServingSize.setText("");
                txtFats.setText("");
                txtCarbohydrates.setText("");
                txtSugar.setText("");
                txtFibre.setText("");
                txtCalories.setText("");
            }
        });
    }

    // converts the month into its numeric form
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

    public void backBasicInputPage(View view) {
        Intent intent = new Intent(getApplicationContext(), Manual_Input.class);
        startActivity(intent);
    }

}
package com.example.diabeticmealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Database_Page extends AppCompatActivity implements foodDatabaseDialog.foodDatabaseDialogListener {

    // RecyclerView
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;

    //Firestore
    private FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user

    // Variables need for the dialog
    private String nameOfFood;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database__page);

        // The current date when the button was pressed
        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.LONG).format(currentTime);
        formattedDate = formattedDate.replace(",", "");
        String[] splitDate = formattedDate.split(" ");
        String month = convertMonthNum(splitDate[0]);
        String dateNum = formatDate(splitDate[1]);
        String year = splitDate[2];
        date = year + month + dateNum;

        // assigning FirestoreList
        mFirestoreList = findViewById(R.id.foodRecyclerView);

        // Query
        Query query = db.collection("users").document(user.getUid().toString()).collection("userData").document("savedMeals").collection("Food");
        //RecyclerOptions
        FirestoreRecyclerOptions<Food> options = new FirestoreRecyclerOptions.Builder<Food>()
                .setQuery(query, Food.class)
                .build();
        // RecyclerView Adapter
        adapter = new FirestoreRecyclerAdapter<Food, FoodViewHolder>(options) {
            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_view_layout, parent, false);
                return new FoodViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull Food model) {
                holder.foodViewName.setText(model.getName());
            }
        };

        // Setting the properties of the firestore recyclerview list
        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }

    //ViewHolder
    private class FoodViewHolder extends RecyclerView.ViewHolder {

        // Textviews to show the info from the database
        private TextView foodViewName;
        private TextView foodViewMeal;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodViewName = itemView.findViewById(R.id.foodViewName);

            foodViewName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nameOfFood = foodViewName.getText().toString().trim().toLowerCase();
                    foodFromDatabase(v);
                }
            });
        }
    }

    // adapter start and stop listening methods
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
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

    public String formatDate(String date) {
        String newDate;
        if (date.length() == 1) {
            newDate = "0" + date;
            return newDate;
        } else {
            return date;
        }
    }

    // onClick for food in the database
    public void foodFromDatabase(View view) {
        foodDatabaseDialog();
    }

    // the dialog for food

    public void foodDatabaseDialog() {
        foodDatabaseDialog foodDatabaseDialog = new foodDatabaseDialog();
        foodDatabaseDialog.show(getSupportFragmentManager(), "add food");
    }

    // gets what the user selected from the dialog
    @Override
    public void add(boolean add, String serving) {
        DocumentReference foodRef = db.collection("users").document(user.getUid().toString()).collection("userData").document("savedMeals").collection("Food").document(nameOfFood);
        DocumentReference setRef = db.collection("users").document(user.getUid().toString()).collection("userData").document(date).collection("Food").document(nameOfFood);
        if (add == true) {
            foodRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() { //Does the .get() command with a custom onComplete
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult(); //Grab snapshot of requirements
                        Map<String, Object> addFood = new HashMap<>();
                        if (!document.exists()) {
                            // exercise
                            addFood.put("name", document.getString("name"));
                            addFood.put("meal", document.getString("meal"));
                            // basic
                            addFood.put("servingSize", document.getString("servingSize"));
                            addFood.put("carbohydrates", document.getString("carbohydrates"));
                            addFood.put("fats", document.getString("fats"));
                            addFood.put("calories", document.getString("calories"));
                            addFood.put("fibre", document.getString("fibre"));
                            addFood.put("sugar", document.getString("sugar"));
                            // detailed
                            addFood.put("saturatedFat", document.getString("saturatedFat"));
                            addFood.put("transFat", document.getString("transFat"));
                            addFood.put("cholesterol", document.getString("cholesterol"));
                            addFood.put("sodium", document.getString("sodium"));
                            addFood.put("protein", document.getString("protein"));
                            addFood.put("calcium", document.getString("calcium"));
                            addFood.put("potassium", document.getString("potassium"));
                            addFood.put("iron", document.getString("iron"));
                            addFood.put("zinc", document.getString("zinc"));
                            addFood.put("vitaminA", document.getString("vitaminA"));
                            addFood.put("vitaminB", document.getString("vitaminB"));
                            addFood.put("vitaminC", document.getString("vitaminC"));
                            // Add to the daily food document
                            setRef.set(addFood);
                        } else {
                            // exercise
                            addFood.put("name", document.getString("name"));
                            addFood.put("meal", document.getString("meal"));
                            // basic
                            addFood.put("servingSize", addTwoStrings(serving, document.getString("servingSize")));
                            addFood.put("carbohydrates", multipleTwoStrings(serving, document.getString("carbohydrates")));
                            addFood.put("fats", multipleTwoStrings(serving, document.getString("fats")));
                            addFood.put("calories", multipleTwoStrings(serving, document.getString("calories")));
                            addFood.put("fibre", multipleTwoStrings(serving, document.getString("fibre")));
                            addFood.put("sugar", multipleTwoStrings(serving, document.getString("sugar")));
                            // detailed
                            addFood.put("saturatedFat", multipleTwoStrings(serving, document.getString("saturatedFat")));
                            addFood.put("transFat", multipleTwoStrings(serving, document.getString("transFat")));
                            addFood.put("cholesterol", multipleTwoStrings(serving, document.getString("cholesterol")));
                            addFood.put("sodium", multipleTwoStrings(serving, document.getString("sodium")));
                            addFood.put("protein", multipleTwoStrings(serving, document.getString("protein")));
                            addFood.put("calcium", multipleTwoStrings(serving, document.getString("calcium")));
                            addFood.put("potassium", multipleTwoStrings(serving, document.getString("potassium")));
                            addFood.put("iron", multipleTwoStrings(serving, document.getString("iron")));
                            addFood.put("zinc", multipleTwoStrings(serving, document.getString("zinc")));
                            addFood.put("vitaminA", multipleTwoStrings(serving, document.getString("vitaminA")));
                            addFood.put("vitaminB", multipleTwoStrings(serving, document.getString("vitaminB")));
                            addFood.put("vitaminC", multipleTwoStrings(serving, document.getString("vitaminC")));
                            // Add to the daily food document
                            setRef.set(addFood);
                        }
                    }
                }
            });
        }
    }

    // add two numerical string values
    public String addTwoStrings(String value1, String value2) {
        return String.valueOf(Float.parseFloat(value1) + Float.parseFloat(value2));
    }

    // multiply two numerical string values
    public String multipleTwoStrings(String value1, String value2) {
        return String.valueOf(Float.parseFloat(value1) * Float.parseFloat(value2));
    }

    // back button
    public void backDatabasePage(View view) {
        finish();
    }
}
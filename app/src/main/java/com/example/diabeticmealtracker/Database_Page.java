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

    // Variables for saving food
    private Map<String, Object> addFood = new HashMap<>();
    private String name, meal, servingSize, carbohydrates, fats, calories, fibre, sugar;
    private String saturatedFat, transFat, cholesterol, sodium, protein, calcium, potassium, iron, zinc;
    private String vitaminA, vitaminB, vitaminC;

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
        // if the user agrees to add meal to daily intake
        if (add == true) {
            // gets information from saved meal and the amount of servings that the user added
            foodRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() { //Does the .get() command with a custom onComplete
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult(); //Grab snapshot of requirements
                        // exercise
                        name = document.getString("name");
                        meal = document.getString("meal");
                        // basic
                        servingSize = serving;
                        carbohydrates = document.getString("carbohydrates");
                        fats = document.getString("fats");
                        calories = document.getString("calories");
                        fibre = document.getString("fibre");
                        sugar = document.getString("sugar");
                        // detailed
                        saturatedFat = document.getString("saturatedFat");
                        transFat = document.getString("transFat");
                        cholesterol = document.getString("cholesterol");
                        sodium = document.getString("sodium");
                        protein = document.getString("protein");
                        calcium = document.getString("calcium");
                        potassium = document.getString("potassium");
                        iron = document.getString("iron");
                        zinc = document.getString("zinc");
                        vitaminA = document.getString("vitaminA");
                        vitaminB = document.getString("vitaminB");
                        vitaminC = document.getString("vitaminC");
                    }
                }
            });
            // updated the servings on the daily food document
            setRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() { //Does the .get() command with a custom onComplete
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult(); //Grab snapshot of requirements
                        Map<String, Object> addFood = new HashMap<>();
                        if (!document.exists()) {
                            // exercise
                            addFood.put("name", name);
                            addFood.put("meal", name);
                            // basic
                            addFood.put("servingSize", serving);
                            addFood.put("carbohydrates", carbohydrates);
                            addFood.put("fats", fats);
                            addFood.put("calories", calories);
                            addFood.put("fibre", fibre);
                            addFood.put("sugar", sugar);
                            // detailed
                            addFood.put("saturatedFat", saturatedFat);
                            addFood.put("transFat", transFat);
                            addFood.put("cholesterol", cholesterol);
                            addFood.put("sodium", sodium);
                            addFood.put("protein", protein);
                            addFood.put("calcium", calcium);
                            addFood.put("potassium", potassium);
                            addFood.put("iron", iron);
                            addFood.put("zinc", zinc);
                            addFood.put("vitaminA", vitaminA);
                            addFood.put("vitaminB", vitaminB);
                            addFood.put("vitaminC", vitaminC);
                            // Add to the daily food document
                            setRef.set(addFood);
                        } else {
                            // exercise
                            addFood.put("name", document.getString("name"));
                            addFood.put("meal", document.getString("meal"));
                            // basic
                            addFood.put("servingSize", addTwoStrings(serving, document.getString("servingSize")));
                            addFood.put("carbohydrates", carbohydrates);
                            addFood.put("fats", fats);
                            addFood.put("calories", calories);
                            addFood.put("fibre", fibre);
                            addFood.put("sugar", sugar);
                            // detailed
                            addFood.put("saturatedFat", saturatedFat);
                            addFood.put("transFat", transFat);
                            addFood.put("cholesterol", cholesterol);
                            addFood.put("sodium", sodium);
                            addFood.put("protein", protein);
                            addFood.put("calcium", calcium);
                            addFood.put("potassium", potassium);
                            addFood.put("iron", iron);
                            addFood.put("zinc", zinc);
                            addFood.put("vitaminA", vitaminA);
                            addFood.put("vitaminB", vitaminB);
                            addFood.put("vitaminC", vitaminC);
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
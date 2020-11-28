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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Database_Page extends AppCompatActivity {

    // RecyclerView
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;


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
        String dateNum = splitDate[1];
        String year = splitDate[2];
        String date = year + month + dateNum;

        //Firestore
        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user

        // assigning FirestoreList
        mFirestoreList = findViewById(R.id.foodRecyclerView);

        // Query
        Query query = db.collection("users").document(user.getUid().toString()).collection("userData").document(date).collection("Food");
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
                holder.foodViewMeal.setText(model.getMeal() + "");
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
            foodViewMeal = itemView.findViewById(R.id.foodViewMeal);
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

    // back button
    public void backDatabasePage(View view) {
        Intent intent = new Intent(getApplicationContext(), Input_Page.class);
        startActivity(intent);
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
}
package com.example.diabeticmealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.LayoutInflater.from;

public class Database_Page extends AppCompatActivity {

    //Database
    DatabaseReference databaseFoods;
    Food food;
    //RecyclerView
    private FirebaseRecyclerOptions<Food> options;
    private FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;
    private RecyclerView foodRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database__page);

        //
        databaseFoods = FirebaseDatabase.getInstance().getReference().child("11202020").child("Food").child("Breakfast");

        //RecyclerView
        foodRecyclerView = findViewById(R.id.foodRecyclerView);
        foodRecyclerView.setHasFixedSize(true);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<Food>().setQuery(databaseFoods, Food.class).build();
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull Food model) {
                holder.foodViewName.setText("" + model.getName());
                holder.foodViewMeal.setText("" + model.getServingSize());
            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_view_layout, parent, false);
                return new FoodViewHolder(v);
            }
        };

        adapter.startListening();
        foodRecyclerView.setAdapter(adapter);
    }

    public void backDatabasePage(View view) {
        Intent intent = new Intent(getApplicationContext(), Input_Page.class);
        startActivity(intent);
    }
}
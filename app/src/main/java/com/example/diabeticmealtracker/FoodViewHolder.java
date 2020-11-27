package com.example.diabeticmealtracker;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodViewHolder extends RecyclerView.ViewHolder {

    TextView foodViewName, foodViewMeal;
    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        foodViewName = itemView.findViewById(R.id.foodViewName);
        foodViewMeal = itemView.findViewById(R.id.foodViewMeal);
    }
}

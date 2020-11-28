package com.example.diabeticmealtracker;

public class Food {
    private String _name, _meal;
    private float _servingSize, _fat, _carbohydrates, _sugar, _fibre, _calories;

    // constructor
    public Food() {
    }

    // setter and getter
    // name
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    // meal
    public String getMeal() {
        return _meal;
    }

    public void setMeal(String meal) {
        _meal = meal;
    }

    // serving size
    public float getServingSize() {
        return _servingSize;
    }

    public void setServingSize(float servingSize) {
        _servingSize = servingSize;
    }

    // fat
    public float getFat() {
        return _fat;
    }

    public void setFat(float fat) {
        _fat = fat;
    }

    // carbs
    public float getCarbohydrates() {
        return _carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        _carbohydrates = carbohydrates;
    }

    // sugar
    public float getSugar() {
        return _sugar;
    }

    public void setSugar(float sugar) {
        _sugar = sugar;
    }

    // fibre
    public float getFibre() {
        return _fibre;
    }

    public void setFibre(float fibre) {
        _fibre = fibre;
    }

    // calories
    public float getCalories() {
        return _calories;
    }

    public void setCalories(float calories) {
        _calories = calories;
    }
}

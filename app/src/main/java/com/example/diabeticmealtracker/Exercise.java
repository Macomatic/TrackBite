package com.example.diabeticmealtracker;

public class Exercise {
    private String _name, _exerciseActivity;
    private double _caloriesBurned, _duration;

    public Exercise() {
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public double getCaloriesBurned() {
        return _caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        _caloriesBurned = caloriesBurned;
    }

    public String getExerciseActivity() {
        return _exerciseActivity;
    }

    public void setExerciseActivity(String exerciseActivity) {
        _exerciseActivity = exerciseActivity;
    }

    public double getExerciseDuration() {
        return _duration;
    }

    public void setExerciseDuration(double duration) {
        _duration = duration;
    }

}


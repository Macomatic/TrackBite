package com.example.diabeticmealtracker;

public class Exercise {
    public String _exerciseActivity;
    public double _caloriesBurned, _exerciseDuration;

    public Exercise() {
    }

    public Exercise(String exerciseActivity, double caloriesBurned, double exerciseDuration){
        _exerciseActivity = exerciseActivity;
        _caloriesBurned = caloriesBurned;
        _exerciseDuration = exerciseDuration;
    }

    //public String getName() {
      //  return _name;
   // }

    //public void setName(String name) {
      //  _name = name;
    //}

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

        return _exerciseDuration;

    }

    public void setExerciseDuration(double duration) {
        _exerciseDuration = duration;
    }

}


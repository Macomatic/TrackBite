package com.example.diabeticmealtracker;

public class Profile {
    private String _name;
    private int _age;
    private String _sex;
    private float _height, _weight, 

    // constructor
    public Profile(){
    }

    // setter and getter
    // name
    public String getName (){
        return _name;
    }
    public void setName (String name){
        _name = name;
    }
    public int getAge() {
      return _age;
    }
    public void setAge(int age) {
      _age = age;
    }
    public String getSex() {
      return _sex;
    }
    public void setSex(String sex) {
      _sex = sex;
    }
    public float getHeight() {
      return _height;
    }
    public void setHeight(float height) {
      _height = height
    }
    public float getWeight() {
      return _weight;
    }
    public void setWeight(float weight) {
      _weight = weight;
    }
}

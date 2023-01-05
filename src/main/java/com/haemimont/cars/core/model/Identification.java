package com.haemimont.cars.core.model;

import java.util.Random;

public class Identification {
    private final String[] arrayOfColors = {"R", "B", "G"};
    private String classification;
    private String id;
    private String make;
    private String modelYear;
    private int year;
    private final String color = getRandomColor();

    private final int price = getYear()*10/3;

    private String getRandomColor() {
        Random random = new Random();
        int select = random.nextInt(arrayOfColors.length);
        return arrayOfColors[select];
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public String getColor(){
        return this.color;
    }
}

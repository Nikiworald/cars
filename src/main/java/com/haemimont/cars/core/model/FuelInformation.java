package com.haemimont.cars.core.model;

public class FuelInformation {
    private int cityMpg;
    private String fuelType;
    private int highwayMpg;

    public int getCityMpg() {
        return cityMpg;
    }

    public void setCityMpg(int cityMpg) {
        this.cityMpg = cityMpg;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getHighwayMpg() {
        return highwayMpg;
    }

    public void setHighwayMpg(int highwayMpg) {
        this.highwayMpg = highwayMpg;
    }
}

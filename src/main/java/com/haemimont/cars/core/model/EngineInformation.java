package com.haemimont.cars.core.model;

public class EngineInformation {
    private EngineStatistics engineStatistics;
    private String driveLine;
    private String engineType;
    private boolean hybrid;
    private int numberOfForwardGears;
    private String transmission;

    public EngineStatistics getEngineStatistics() {
        return engineStatistics;
    }

    public void setEngineStatistics(EngineStatistics engineStatistics) {
        this.engineStatistics = engineStatistics;
    }

    public String getDriveLine() {
        return driveLine;
    }

    public void setDriveLine(String driveLine) {
        this.driveLine = driveLine;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public boolean isHybrid() {
        return hybrid;
    }

    public void setHybrid(boolean hybrid) {
        this.hybrid = hybrid;
    }

    public int getNumberOfForwardGears() {
        return numberOfForwardGears;
    }

    public void setNumberOfForwardGears(int numberOfForwardGears) {
        this.numberOfForwardGears = numberOfForwardGears;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}

package com.haemimont.cars.core.model;

public class Car {

    private final Dimension dimension;
    private final EngineInformation engineInformation;
    private final FuelInformation fuelInformation;
    private final Identification identification;

    public Car(CarBuilder carBuilder) {
        this.dimension = carBuilder.getDimension();
        this.engineInformation = carBuilder.getEngineInformation();
        this.fuelInformation = carBuilder.getFuelInformation();
        this.identification = carBuilder.getIdentification();
    }

    public Dimension getDimension() {
        return dimension;
    }

    public EngineInformation getEngineInformation() {
        return engineInformation;
    }

    public FuelInformation getFuelInformation() {
        return fuelInformation;
    }

    public Identification getIdentification() {
        return identification;
    }

// --Commented out by Inspection START (6/22/2023 2:58 PM):
//    public String getId(){
//        return getIdentification().getId();
//    }
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)
    public int getYear(){
        return getIdentification().getYear();
    }
    public String getModel() {
        return getIdentification().getModelYear();
    }
    public String getMake(){
        return getIdentification().getMake();
    }
    public String getDriveLin(){
        return getEngineInformation().getDriveLine();
    }
    public String getClassification(){
        return getIdentification().getClassification();
    }

}

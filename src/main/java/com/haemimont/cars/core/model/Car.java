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

}

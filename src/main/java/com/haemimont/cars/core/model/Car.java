package com.haemimont.cars.core.model;

public class Car {

    private final Dimension dimension;
    private final  EngineInformation engineInformation;
    private final FuelInformation fuelInformation;
    private final Identification identification;

    public Car(Dimension dimension, EngineInformation engineInformation, FuelInformation fuelInformation,
               Identification identification) {
        this.dimension = dimension;
        this.engineInformation = engineInformation;
        this.fuelInformation = fuelInformation;
        this.identification = identification;
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

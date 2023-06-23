package com.haemimont.cars.core.model;


public class CarBuilder {
    private Dimension dimension;
    private EngineInformation engineInformation;
    private FuelInformation fuelInformation;
    private Identification identification;

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

    public static CarBuilder newInstance() {
        return new CarBuilder();
    }

    public CarBuilder setDimension(final Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public CarBuilder setEngineInformation(final EngineInformation engineInformation) {
        this.engineInformation = engineInformation;
        return this;
    }

    public CarBuilder setFuelInformation(final FuelInformation fuelInformation) {
        this.fuelInformation = fuelInformation;
        return this;
    }

    public CarBuilder setIdentification(final Identification identification) {
        this.identification = identification;
        return this;
    }

    public CarBuilder setHeight(final int height) {
        this.dimension.setHeight(height);
        return this;
    }

    public CarBuilder setLength(final int length) {
        this.dimension.setLength(length);
        return this;
    }

    public CarBuilder setWidth(final int width) {
        this.dimension.setWidth(width);
        return this;
    }

    public CarBuilder setHybrid(final Boolean hybrid) {
        this.engineInformation.setHybrid(hybrid);
        return this;
    }

    public CarBuilder setNumberOfForwardGears(final int numberOfForwardGears) {
        this.engineInformation.setNumberOfForwardGears(numberOfForwardGears);
        return this;
    }

    public CarBuilder setCityMpg(final int cityMpg) {
        this.fuelInformation.setCityMpg(cityMpg);
        return this;
    }

    public CarBuilder setHighwayMpg(final int highwayMpg) {
        this.fuelInformation.setHighwayMpg(highwayMpg);
        return this;
    }

    public CarBuilder setYear(final int year) {
        this.identification.setYear(year);
        return this;
    }

    public CarBuilder setHorsePower(final int horsePower) {
        this.engineInformation.getEngineStatistics().setHorsePower(horsePower);
        return this;

    }

    public CarBuilder setTorque(final int torque) {
        this.engineInformation.getEngineStatistics().setTorque(torque);
        return this;
    }

    public CarBuilder setHeight(final String height) {
        this.dimension.setHeight(Integer.parseInt(height));
        return this;
    }

    public CarBuilder setLength(final String length) {
        this.dimension.setLength(Integer.parseInt(length));
        return this;
    }

    public CarBuilder setWidth(final String width) {
        this.dimension.setWidth(Integer.parseInt(width));
        return this;
    }

    public CarBuilder setDriveLine(final String driveLine) {
        this.engineInformation.setDriveLine(driveLine);
        return this;
    }

    public CarBuilder setEngineType(final String engineType) {
        this.engineInformation.setEngineType(engineType);
        return this;
    }

    public CarBuilder setHybrid(final String hybrid) {
        this.engineInformation.setHybrid(Boolean.parseBoolean(hybrid));
        return this;
    }

    public CarBuilder setNumberOfForwardGears(final String numberOfForwardGears) {
        this.engineInformation.setNumberOfForwardGears(Integer.parseInt(numberOfForwardGears));
        return this;
    }

    public CarBuilder setTransmission(final String transmission) {
        this.engineInformation.setTransmission(transmission);
        return this;
    }

    public CarBuilder setCityMpg(final String cityMpg) {
        this.fuelInformation.setCityMpg(Integer.parseInt(cityMpg));
        return this;
    }

    public CarBuilder setFuelType(final String fuelType) {
        this.fuelInformation.setFuelType(fuelType);
        return this;
    }

    public CarBuilder setHighwayMpg(final String highwayMpg) {
        this.fuelInformation.setHighwayMpg(Integer.parseInt(highwayMpg));
        return this;
    }

    public CarBuilder setClassification(final String classification) {
        this.identification.setClassification(classification);
        return this;
    }

    public CarBuilder setId(final String id) {
        this.identification.setId(id);
        return this;
    }

    public CarBuilder setMake(final String make) {
        this.identification.setMake(make);
        return this;
    }

    public CarBuilder setModelYear(final String modelYear) {
        this.identification.setModelYear(modelYear);
        return this;
    }

    public CarBuilder setYear(final String year) {
        this.identification.setYear(Integer.parseInt(year));
        return this;
    }

    public CarBuilder setHorsePower(final String horsePower) {
        this.engineInformation.getEngineStatistics().setHorsePower(Integer.parseInt(horsePower));
        return this;

    }

    public CarBuilder setTorque(final String torque) {
        this.engineInformation.getEngineStatistics().setTorque(Integer.parseInt(torque));
        return this;
    }

    public CarBuilder setColor(final String color) {
        this.identification.setColor(color);
        return this;
    }

    public CarBuilder setPrice(final double price) {
        this.identification.setPrice(price);
        return this;
    }

    public Car build() {
        return new Car(this);
    }
}
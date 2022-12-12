package com.haemimont.cars.core.model;

import javafx.util.Builder;

import java.math.BigDecimal;

public class Car {

    private final Dimension dimension;
    private final  EngineInformation engineInformation;
    private final FuelInformation fuelInformation;
    private  final Identification identification;
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
    public Car(CarBuilder carBuilder) {
        this.dimension = carBuilder.dimension;
        this.engineInformation = carBuilder.engineInformation;
        this.fuelInformation = carBuilder.fuelInformation;
        this.identification = carBuilder.identification;
    }
    public static class CarBuilder{
        private Dimension dimension;
        private   EngineInformation engineInformation;
        private  FuelInformation fuelInformation;
        private  Identification identification;
        public static CarBuilder newInstance()
        {
            return new CarBuilder();
        }

        public CarBuilder setDimension(final Dimension dimension){
            this.dimension = dimension;
            return this;
        }
        public CarBuilder setEngineInformation(final EngineInformation engineInformation ){
            this.engineInformation = engineInformation;
            return this;
        }

        public CarBuilder setFuelInformation(final FuelInformation fuelInformation){
            this.fuelInformation = fuelInformation;
            return this;
        }
        public CarBuilder setIdentification(final Identification identification){
            this.identification = identification;
            return this;
        }
        public  CarBuilder setHeight(final String height){
            this.dimension.setHeight(Integer.parseInt(height));
            return this;
        }
        public  CarBuilder setLength(final String length){
            this.dimension.setLength(Integer.parseInt(length));
            return this;
        }
        public CarBuilder setWidth(final String width){
            this.dimension.setWith(Integer.parseInt(width));
            return this;
        }
        public CarBuilder setDriveLine(final String driveLine){
            this.engineInformation.setDriveLine(driveLine);
            return this;
        }
        public CarBuilder setEngineType(final String engineType){
            this.engineInformation.setEngineType(engineType);
            return this;
        }
        public CarBuilder setHybrid(final String hybrid){
            this.engineInformation.setHybrid(Boolean.parseBoolean(hybrid));
            return this;
        }
        public CarBuilder setNumberOfForwardGears(final String numberOfForwardGears){
            this.engineInformation.setNumberOfForwardGears(Integer.parseInt(numberOfForwardGears));
            return this;
        }
        public CarBuilder setTransmission(final String transmission){
            this.engineInformation.setTransmission(transmission);
            return  this;
        }
        public CarBuilder setCityMpg(final String cityMpg){
            this.fuelInformation.setCityMpg(Integer.parseInt(cityMpg));
            return this;
        }
        public CarBuilder setFuelType(final String fuelType){
            this.fuelInformation.setFuelType(fuelType);
            return this;
        }
        public CarBuilder setHighwayMpg(final String highwayMpg) {
            this.fuelInformation.setHighwayMpg(Integer.parseInt(highwayMpg));
            return this;
        }
        public CarBuilder setClassification(final String classification){
            this.identification.setClassification(classification);
            return this;
        }
        public CarBuilder setId(final String id){
            this.identification.setId(id);
            return this;
        }
        public CarBuilder setMake(final String make){
            this.identification.setMake(make);
            return this;
        }
        public CarBuilder setModelYear(final String modelYear){
            this.identification.setModelYear(modelYear);
            return this;
        }
        public CarBuilder setYear(final String year){
            this.identification.setYear(Integer.parseInt(year));
            return this;
        }
        public CarBuilder setHorsePower(final String horsePower){
            this.engineInformation.getEngineStatistics().setHorsePower(Integer.parseInt(horsePower));
            return this;

        }
        public  CarBuilder setTorque(final String torque){
            this.engineInformation.getEngineStatistics().setTorque(Integer.parseInt(torque));
            return this;
        }
        public Car build()
        {
            return new Car(this);
        }
        public String getValues() {
            return ""+dimension.getHeight()+dimension.getLength();
        }







    }

}

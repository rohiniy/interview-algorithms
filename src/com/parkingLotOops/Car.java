package com.parkingLotOops;

public class Car implements Vehicle {
  private VehicleType type;

  public Car(VehicleType type) {
    this.type = type;
  }

  public VehicleType getType() {
    return this.type;
  }

   public VehicleSize getSize() {
    return VehicleSize.MEDIUM;
  }
}

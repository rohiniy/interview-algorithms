package com.parkingLotOops;

public class Bike implements Vehicle {
  private VehicleType type;

  public Bike(VehicleType type) {
    this.type = type;
  }

  public VehicleType getType() {
    return type;
  }

  public VehicleSize getSize() {
    return VehicleSize.SMALL;
  }
}

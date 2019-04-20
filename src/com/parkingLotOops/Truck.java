package com.parkingLotOops;

public class Truck implements Vehicle{
  private VehicleType type;

  public Truck(VehicleType type) {
    this.type = type;
  }

  public VehicleType getType() {
    return type;
  }

  public VehicleSize getSize() {
    return VehicleSize.LARGE;
  }
}

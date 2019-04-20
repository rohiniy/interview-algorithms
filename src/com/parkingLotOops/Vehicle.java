package com.parkingLotOops;

public interface Vehicle {
  enum VehicleSize {
    SMALL,
    MEDIUM,
    LARGE
  }

  enum VehicleType {
    REGULAR,
    HANDICAPPED
  }

  VehicleSize getSize();
  VehicleType getType();
}

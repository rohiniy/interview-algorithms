package com.parkingLotOops;

public class ParkingSpace implements Space{
  private VehicleSize vehicleSize;
  private VehicleType vehicleType;
  private boolean isTaken;

  public ParkingSpace(VehicleSize vehicleSize, VehicleType vehicleType) {
    this.vehicleSize = vehicleSize;
    this.vehicleType = vehicleType;
  }

  // space interface
  @Override
  public boolean getIsTaken() {
    return isTaken;
  }

  @Override
  public void setIsTaken(boolean isTaken) {
    this.isTaken = true;
  }


  public VehicleSize getSize() {
    return this.vehicleSize;
  }
  public VehicleType getType() {
    return this.vehicleType;
  }
}

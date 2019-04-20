package com.parkingLotOops;

public class Driver {
    public static void main(String[] args) {

      int numberOfSmallRegularSpaces = 9;
      int numberOfMediumRegularSpaces = 24;
      int numberofLargeRegularSpaces = 10;
      int numberOfHandicapMediumSpaces = 5;

      ParkingLot parkingLot = new ParkingLot(9,
          24,
          10,
          0,
          5,
          0);

      // Parking
      System.out.println("Parking Medium Handicapped cars");
      for (int i = 0; i < 6; i++) {
        parkingLot.park(new Car(Vehicle.VehicleType.HANDICAPPED));
      }

      System.out.println();

      System.out.println("Parking Large regular trucks");
      for (int j = 0; j < 8; j++) {
        parkingLot.park(new Truck(Vehicle.VehicleType.REGULAR));
      }

      System.out.println();

      System.out.println("Parking Medium Regular cars");
      for(int k = 0; k < 3; k++) {
        parkingLot.park(new Car(Vehicle.VehicleType.REGULAR));
      }

      System.out.println();

      System.out.println("Parking Large Handicapped truck");
      parkingLot.park(new Truck(Vehicle.VehicleType.HANDICAPPED));

      System.out.println();

      System.out.println("Parking Large Regular truck");
      parkingLot.park(new Truck(Vehicle.VehicleType.REGULAR));

      System.out.println();

      System.out.println("Parking Large Handicapped truck");
      parkingLot.park(new Truck(Vehicle.VehicleType.HANDICAPPED));

      System.out.println();

      System.out.println("Parking Small Regular bike");
      for(int m = 0; m < 3; m++) {
        parkingLot.park(new Bike(Vehicle.VehicleType.REGULAR));
      }

      System.out.println();

      System.out.println("Parking Medium regular car");
      parkingLot.park(new Car(Vehicle.VehicleType.REGULAR));

      System.out.println();

      System.out.println("Is the parking lot full? " + parkingLot.getIsParkingLotFull());
      System.out.println();
      System.out.println("What spots are left?\n" + parkingLot.toStringSpotsLeft());

    }
}

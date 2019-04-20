package com.parkingLotOops;

import java.util.*;

// class that will have multiple ParkingSpaces
public class ParkingLot {
  private HashMap<Vehicle.VehicleSize, HashMap<Vehicle.VehicleType,
      ArrayList<ParkingSpace>>> parkingAvailableBySize = new HashMap<>();

  // HASHMAP STRUCTURE
  // (SMALL, (R, H))
  // (MEDIUM, (R, H))
  // (LARGE, (R, H))

  final private Vehicle.VehicleType[] vehicleTypes = Vehicle.VehicleType.values();
  final private Vehicle.VehicleSize[] vehicleSizes = Vehicle.VehicleSize.values();

  public ParkingLot(int numberOfSmallRegularSpaces,
                    int numberOfMediumRegularSpaces,
                    int numberOfLargeRegularSpaces,
                    int numberOfSmallHandicappedSpaces,
                    int numberOfMediumHandicappedSpaces,
                    int numberOfLargeHandicappedSpaces) {

    int[][] numberOfSpaces = { { numberOfSmallRegularSpaces, numberOfSmallHandicappedSpaces},
        { numberOfMediumRegularSpaces, numberOfMediumHandicappedSpaces},
        {numberOfLargeRegularSpaces, numberOfLargeHandicappedSpaces } };

    // Initialize the parking lot...

    for (int sizeIndex = 0; sizeIndex < vehicleSizes.length; sizeIndex++) {
      for (int vehicleTypeIndex = 0; vehicleTypeIndex < vehicleTypes.length; vehicleTypeIndex++) {
        ArrayList<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 0; i < numberOfSpaces[sizeIndex][vehicleTypeIndex]; i++) {
          ParkingSpace p = new ParkingSpace(vehicleSizes[sizeIndex], vehicleTypes[vehicleTypeIndex]);
          spaces.add(p);

        }

        if (parkingAvailableBySize.containsKey(vehicleSizes[sizeIndex])) {
          HashMap<Vehicle.VehicleType, ArrayList<ParkingSpace>> previousTypeToSpaceMap = parkingAvailableBySize.get(vehicleSizes[sizeIndex]);
          previousTypeToSpaceMap.put(vehicleTypes[vehicleTypeIndex], spaces);

        } else {
          HashMap<Vehicle.VehicleType, ArrayList<ParkingSpace>> typeToSpaceMap = new HashMap();
          typeToSpaceMap.put(vehicleTypes[vehicleTypeIndex], spaces);
          parkingAvailableBySize.put(vehicleSizes[sizeIndex], typeToSpaceMap);
        }

      }
    }
    System.out.println("Init");


  }

  public ParkingLot(HashMap<Vehicle.VehicleSize, HashMap<Vehicle.VehicleType, ArrayList<ParkingSpace>>> parkingAvaliableBySize) {
    this.parkingAvailableBySize = parkingAvaliableBySize;
  }

  public ParkingSpace park(Vehicle c) {
    ParkingSpace attemptedPark = tryToPark(c);
    if (attemptedPark != null) {
      attemptedPark.setIsTaken(true);
      System.out.println("The car has parked in a " + attemptedPark.getSize() + " " + attemptedPark.getType());
    } else {
      System.out.println("The car cannot be parked at this time.");
    }
    return attemptedPark;
  }

  private ParkingSpace tryToPark(Vehicle vehicle) {
    Vehicle.VehicleSize size = vehicle.getSize();
    Vehicle.VehicleType type = vehicle.getType();


    // LARGE PARKING SPOTS
    if (size.equals(Vehicle.VehicleSize.LARGE)) {
      // if this is null, then no spaces are avaliable
      return findAvaliableSpaceWithType(type, parkingAvailableBySize.get(size));


      // MEDIUM PARKING SPOTS
    } else if (size.equals(Vehicle.VehicleSize.MEDIUM)) {
      ParkingSpace mediumSpace = findAvaliableSpaceWithType(type, parkingAvailableBySize.get(size));
      if (mediumSpace != null) {
        return mediumSpace;
      } else {
        // Check large spaces --> if this is null, then no fitting spaces are avaliable
        return findAvaliableSpaceWithType(type, parkingAvailableBySize.get(Vehicle.VehicleSize.LARGE));
      }

      // SMALL PARKING SPOTS
    } else if (size.equals(Vehicle.VehicleSize.SMALL)){
      ParkingSpace smallSpace = findAvaliableSpaceWithType(type, parkingAvailableBySize.get(size));
      if (smallSpace != null) {
        return smallSpace;
      } else {
        // Check medium spaces
        ParkingSpace mediumSpace = findAvaliableSpaceWithType(type, parkingAvailableBySize.get(Vehicle.VehicleSize.MEDIUM));
        if (mediumSpace != null) {
          return mediumSpace;
        } else {
          // Check large spaces --> if this is null, then no appropriate spaces are avaliable
          return findAvaliableSpaceWithType(type, parkingAvailableBySize.get(Vehicle.VehicleSize.LARGE));
        }
      }

    } else {
      return null;
    }
  }


  private ParkingSpace findAvaliableSpaceWithType(Vehicle.VehicleType vehicleType,
                                                  HashMap<Vehicle.VehicleType, ArrayList<ParkingSpace>>
                                                      parkingAvailableByType) {
    if (vehicleType.equals(Vehicle.VehicleType.HANDICAPPED)) {
      // Check handicapped spaces first
      ArrayList<ParkingSpace> handicappedSpaces = parkingAvailableByType.get(vehicleType);
      ParkingSpace handicappedSpace = areSpacesTaken(handicappedSpaces);
      if (handicappedSpace != null) {
        return handicappedSpace;
      } else {
        // Check regular spaces
        ArrayList<ParkingSpace> regularSpaces = parkingAvailableByType.get(Vehicle.VehicleType.REGULAR);
        return areSpacesTaken(regularSpaces);
      }
    } else {
      // check regular spaces
      ArrayList<ParkingSpace> regularSpaces = parkingAvailableByType.get(Vehicle.VehicleType.REGULAR);
      return areSpacesTaken(regularSpaces);
    }
  }

  private ParkingSpace areSpacesTaken(ArrayList<ParkingSpace> parkingSpaces) {
    if (parkingSpaces == null) {
      return null;
    }

    for (ParkingSpace space : parkingSpaces) {
      if (!space.getIsTaken()) {
        return space;
      }
    }
    return null;
  }

  public boolean getIsParkingLotFull() {
    for (int sizeIndex = 0; sizeIndex < vehicleSizes.length; sizeIndex++) {
      for (int vehicleTypeIndex = 0; vehicleTypeIndex < vehicleTypes.length; vehicleTypeIndex++) {
        ParkingSpace space = findAvaliableSpaceWithType(vehicleTypes[vehicleTypeIndex],
            parkingAvailableBySize.get(vehicleSizes[sizeIndex]));
        if (space == null) {
          return false;
        }
      }
    }

    // no null spots mean the parking lot is full.
    return true;
  }

  private int toStringHelper_AvaliableSpacesWithTypeAndSize(Vehicle.VehicleType vehicleType,
                                                            Vehicle.VehicleSize vehicleSize) {
    HashMap<Vehicle.VehicleType, ArrayList<ParkingSpace>> avaliableByTypeAndSize =
        parkingAvailableBySize.get(vehicleSize);
    ArrayList<ParkingSpace> exactSpaces = avaliableByTypeAndSize.get(vehicleType);
    int count = 0;
    for (ParkingSpace space : exactSpaces) {
      if (!space.getIsTaken()) {
        count++;
      }
    }
    return count;
  }

  public String toStringSpotsLeft() {
    String description = "";
    for (int sizeIndex = 0; sizeIndex < vehicleSizes.length; sizeIndex++) {
      for (int vehicleTypeIndex = 0; vehicleTypeIndex < vehicleTypes.length; vehicleTypeIndex++) {
        int count = toStringHelper_AvaliableSpacesWithTypeAndSize(vehicleTypes[vehicleTypeIndex],
            vehicleSizes[sizeIndex]);
        description = description + "There are " + count + " " + vehicleSizes[sizeIndex] + " " +
            vehicleTypes[vehicleTypeIndex] + " spots left.\n";
      }

    }
    return description;
  }
}

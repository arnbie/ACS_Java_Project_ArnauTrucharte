package base;

import java.util.ArrayList;

public final class DirectoryDoors {
  private static ArrayList<Door> allDoors;

  // The doors are created in DirectoryAreas and then sent to this function to store in allDoors
  public static void makeDoors(ArrayList<Door> doors) {
    allDoors = doors;
  }


  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // this is needed by RequestRefresh
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

}

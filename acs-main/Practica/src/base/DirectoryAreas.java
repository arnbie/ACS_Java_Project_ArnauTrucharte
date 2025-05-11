package base;


import base.jerarchy.Area;
import base.jerarchy.Partition;
import base.jerarchy.Space;
import java.util.ArrayList;
import java.util.Arrays;

// Class to create and store all data about areas of the building,
// it will have a root that being the first partition
// called building

public class DirectoryAreas {
  private static Partition root;

  public static void makeAreas() {
    Clock clock=Clock.getInstance();
    Partition building = new Partition("building", null);
    root = building;
    Partition basement = new Partition("basement", building);
    Partition groundFloor = new Partition("ground_floor", building);
    Partition floor1 = new Partition("floor", building);
    Space stairs = new Space("stairs", building);
    Space exterior = new Space("exterior", building);
    Space parking = new Space("parking", basement);
    Space hall = new Space("hall", groundFloor);
    Space room1 = new Space("room1", groundFloor);
    Space room2 = new Space("room2", groundFloor);
    Space room3 = new Space("room3", floor1);
    Space corridor = new Space("corridor", floor1);
    Space it = new Space("it", floor1);
    Door d1 = new Door("D1", exterior, parking,clock);
    Door d2 = new Door("D2", stairs, parking,clock);
    Door d3 = new Door("D3", exterior, hall,clock);
    Door d4 = new Door("D4", stairs, hall,clock);
    Door d5 = new Door("D5", hall, room1,clock);
    Door d6 = new Door("D6", hall, room2,clock);
    Door d7 = new Door("D7", stairs, corridor,clock);
    Door d8 = new Door("D8", corridor, room3,clock);
    Door d9 = new Door("D9", corridor, it,clock);
    DirectoryDoors.makeDoors(new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9)));
  }

  public static Area findAreaById(String id) {
    return root.findAreaById(id);
  }
}

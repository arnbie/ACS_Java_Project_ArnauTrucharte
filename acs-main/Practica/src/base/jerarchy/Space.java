package base.jerarchy;

import base.Door;
import java.util.ArrayList;

// Similar to Partitions class, but this class though it also inherits Area,
// stores associated doors.
public class Space extends Area {
  private final ArrayList<Door> associatedDoors;

  public Space(String name, Partition partOf) {
    super(name, partOf);
    associatedDoors = new ArrayList<>();
  }

  // Since a Space does not have sub-Areas, findAreaById will stop at
  // this point if this object does not share de id
  @Override
  public Area findAreaById(String id) {
    return super.findAreaById(id);
  }

  // Returns an array of all associated doors to this space
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return this.associatedDoors;
  }

  // Just a way of adding doors post creation.
  public void addDoor(Door inputDoor) {
    if (!this.associatedDoors.contains(inputDoor)) {
      this.associatedDoors.add(inputDoor);
    }
  }
}

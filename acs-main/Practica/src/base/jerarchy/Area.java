package base.jerarchy;

import base.Door;
import java.util.ArrayList;

// Abstract class that implements Areas, those Areas can be Partitions
// (those will contain other Areas, those areas might be both Partitions and Spaces)
// or Spaces (which will have only doors)
public abstract class Area {
  // Variable id will act more as a name of the Area than a real id
  private final String id;

  // partOf != null covers the case in which we create a root Area,
  // the one that will store the entire Tree of the building
  protected Area(String id, Partition partOf) {
    this.id = id;
    if (partOf != null) {
      partOf.addArea(this);
    }
  }

  // Function to be used when searching an Area by its ID, an object of this
  // class will receive an id and then check
  public Area findAreaById(String id) {
    if (this.id.equals(id)) {
      return this;
    }
    return null;
  }

  public String getId() {
    return this.id;
  }

  public abstract ArrayList<Door> getDoorsGivingAccess();
}

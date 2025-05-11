package base.jerarchy;

import base.Door;
import java.util.ArrayList;

// A partition is an area that may contain more Areas, inherits Area class.
public class Partition extends Area {

  // associatedAreas stores any Area inside a Partition
  private final ArrayList<Area> associatedAreas;

  public Partition(String name, Partition partOf) {
    super(name, partOf);
    this.associatedAreas = new ArrayList<>();
  }

  // When we call findAreaById to this object firts it will check if it's id is the one we
  // are looking for by calling its super class function. if a false is returned, it will then
  // check if any of its children has the id we are looking for
  @Override
  public Area findAreaById(String id) {
    Area returning = super.findAreaById(id);
    if (returning == null) {
      for (Area toCheck : associatedAreas) {
        returning = toCheck.findAreaById(id);
        if (returning != null) {
          break;
        }
      }
    }
    return returning;
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> allDoors = new ArrayList<>();

    for (Area area : this.associatedAreas) {
      ArrayList<Door> areaDoors = area.getDoorsGivingAccess();
      allDoors.addAll(areaDoors);
    }

    return allDoors;
  }

  public void addArea(Area area) {
    associatedAreas.add(area);
  }
}

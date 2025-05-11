package base.doorstates;

import base.Door;

// This class implements the states the doors might have instead of having to keep the state
// inside a variable.
// Also, this class is abstract which means that is to be inherited
public abstract class DoorState {
  // Any state will keep the door that has the state
  private final Door door;

  // We still need a builder
  protected DoorState(Door door) {
    this.door = door;
  }

  public Door getDoor() {
    return this.door;
  }

  public abstract boolean open();

  public abstract boolean close();

  public abstract boolean unlock();

  public abstract boolean lock();

  public abstract String toString();

  public abstract boolean isClosed();

}

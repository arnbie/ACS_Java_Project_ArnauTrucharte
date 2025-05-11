package base.doorstates;

import base.Door;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

// This class slightly resembles its Locked counterpart but this class
// has to remember if it's open or close, since an unlocked door might be closed
// or opened, thats why whe now have a variable called "open"
public class Unlocked extends DoorState {
  private boolean open;
  private static final Logger logger = (Logger) LoggerFactory.getLogger("DoorState.Unlocked");

  public Unlocked(Door door) {
    super(door);
    this.open = false;
  }

  // If a door is called to be opened, if it's opened, it fails
  // if it's close, then it opens
  @Override
  public boolean open() {
    logger.debug("open() called");
    if (!open) {
      logger.info("Opening door");
      this.open = true;
      return true;
    } else {
      logger.info("Assuming door is already open");
      logger.warn("Can't open door");
      return false;
    }
  }

  // Opposed to above
  @Override
  public boolean close() {
    logger.debug("close() called");
    if (open) {
      logger.info("Closing door");
      this.open = false;
      return true;
    } else {
      logger.info("Assuming door is already closed");
      logger.warn("Can't close door");
      return false;
    }
  }

  // An unlocked door cannot be unlocked
  @Override
  public boolean unlock() {
    logger.warn("Can't unlock open door");
    return false;
  }

  // An unlocked door can change to locked state
  // but only if it's already closed
  @Override
  public boolean lock() {
    logger.debug("lock() called");
    if (!open) {
      logger.info("Locking door");
      Door door = super.getDoor();
      door.setDoorState(new Locked(door));
      return true;
    } else {
      logger.warn("Can't lock door");
      return false;
    }
  }

  @Override
  public String toString() {
    return "unlocked";
  }

  @Override
  public boolean isClosed() {
    logger.debug("isClosed() called");
    if (this.open) {
      logger.debug("door is open");
      return false;
    } else {
      logger.debug("door is open");
      return true;
    }
  }
}

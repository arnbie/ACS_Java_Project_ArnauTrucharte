package base;

import base.doorstates.DoorState;
import base.doorstates.Locked;
import base.jerarchy.Space;
import base.requests.RequestReader;
import ch.qos.logback.classic.Logger;
import java.util.ArrayList;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

// In this clase now the state is stored in an object of a class called DoorState
public class Door {
  private final String id;
  private DoorState doorState;
  private final Space from;
  private final Space to;

private Clock clock;
  private static final Logger logger = (Logger) LoggerFactory.getLogger("Door");

  public Door(String id, Space from, Space to,Clock clock) {
    logger.debug("Entering Door Constructor");
    this.id = id;
    this.doorState = new Locked(this);
    this.from = from;
    this.to = to;
    //this.from.addDoor(this);
    this.to.addDoor(this);
    this.clock=clock;
    //Create and add DoorObserver as an Observer to the clock
    DoorObserver DoorObserver= new DoorObserver(this);
    clock.addObserver(DoorObserver);
  }

  public void setDoorState(DoorState inputDoorState) {
    this.doorState = inputDoorState;
  }

  public void processRequest(RequestReader request) {
    logger.debug("Entering Door processRequest");
    logger.info("Proccessing requst");
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      logger.info("Request Authorized");
      String action = request.getAction();
      logger.debug("Request is to" + action);
      doAction(action);
    } else {
      logger.warn("Request not authorized");
      System.out.println("not authorized");
    }
    request.setDoorStateName(getLocked());
  }

  // The function below has changed, so it follows the functionality of the DoorState class instead
  // of checking built-in variables to check if it's closed, open, locked or unlocked
  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        if (!this.doorState.open()) {
          logger.warn("Could not open the door, presumably already open or locked");
        }
        break;
      case Actions.CLOSE:
        if (!this.doorState.close()) {
          logger.warn("Could not close the door, presumably already closed or unlocked");
        }
        break;
      case Actions.LOCK:
        if (!this.doorState.lock()) {
          logger.warn("Could not lock the door, presumably already locked or open");
        }
        break;
      case Actions.UNLOCK:
        if (!this.doorState.unlock()) {
          logger.warn("Could not unlock the door, presumably already unlocked or open");
        }
        break;
      case Actions.UNLOCK_SHORTLY:
        // TODO
        logger.warn("Action " + action + " not implemented yet");
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return this.doorState.isClosed();
  }

  public String getId() {
    return id;
  }

  public ArrayList<Space> getSpaces() {
    logger.info("Getting door spaces");
    ArrayList<Space> ret = new ArrayList<>();
    logger.debug("Adding " + this.from.getId());
    ret.add(this.from);
    logger.debug("Adding " + this.to.getId());
    ret.add(this.to);
    return ret;
  }

  public String getLocked() {
    return this.doorState.toString();
  }

  public Space getFromSpace() {
    return this.from;
  }

  public Space getToSpace() {
    return this.to;
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + this.doorState.toString()
        + ", state=" + getLocked()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getLocked());
    json.put("closed", this.doorState.toString());
    return json;
  }

}



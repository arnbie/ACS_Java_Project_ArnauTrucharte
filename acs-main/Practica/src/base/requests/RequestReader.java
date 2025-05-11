package base.requests;

import base.DirectoryDoors;
import base.DirectoryUsers;
import base.Door;
import base.User;
import base.UserGroup;
import ch.qos.logback.classic.Logger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

// This class will be generated each time a request for a door is generated
public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;
  private static final Logger logger = (Logger) LoggerFactory.getLogger("Request.Reader");

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    logger.info("RequestReader() called, " + credential + ", " + action + ", "
            + now + ", " + doorId);
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }


  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
            + "credential=" + credential
            + ", userName=" + userName
            + ", action=" + action
            + ", now=" + now
            + ", doorID=" + doorId
            + ", closed=" + doorClosed
            + ", authorized=" + authorized
            + ", reasons=" + reasons
            + "}";
  }

  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  public void process() {
    User user = DirectoryUsers.findUserByCredential(credential);
    Door door = DirectoryDoors.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    assert user != null;
    logger.warn("User " + user.getCredential() + "has issued a request for door " + door.getId());
    authorize(user, door);
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(User user, Door door) {
    logger.debug("authorize() called");
    authorized = false;
    if (user == null) {
      logger.warn("User doesn't exist");
      addReason("user doesn't exists");
    } else {
      UserGroup group = user.getGroup();
      if (group.isActionValid(this.action)) {
        if (group.isDoorValid(door)) {
          if (group.isDateValid(this.now)) {
            authorized = true;
            logger.warn("Request permited");
            addReason("acces is permited");
          } else {
            logger.warn("Request made in wrong time");
            addReason("user is out of date");
          }
        } else {
          logger.warn("door is not accessible for user");
          addReason("door " + door.getId() + " is not accessible for the user");
        }
      } else {
        logger.warn("User action is not permited");
        addReason("user action is not permited");
      }
    }
  }
}



package base;
import java.util.Observer;
import java.util.Observable;


public class DoorObserver implements Observer {
  private Door door;

  public DoorObserver(Door door) {
    this.door = door;
  }

  @Override
  public void update(Observable o, Object arg) {
    // Handle the update from the Clock
    if (arg instanceof Integer) {
      int seconds = (Integer) arg;

    }
  }
}

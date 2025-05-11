package base;

import java.util.ArrayList;
import java.util.Objects;

public final class DirectoryUsers {
  private static final ArrayList<User> users = new ArrayList<>();

  public static void makeUsers() {

    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later
    users.add(new User("Bernat", "12345", Objects.requireNonNull(DirectoryUserGroups.findUserGrupoByName("blank"))));
    users.add(new User("Blai", "77532", Objects.requireNonNull(DirectoryUserGroups.findUserGrupoByName("blank"))));

    // employees :
    // Sep. 1 2023 to Mar. 1 2024
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    users.add(new User("Ernest", "74984", Objects.requireNonNull(DirectoryUserGroups.findUserGrupoByName("employer"))));
    users.add(new User("Eulalia", "43295", Objects.requireNonNull(DirectoryUserGroups.findUserGrupoByName("employer"))));

    // managers :
    // Sep. 1 2023 to Mar. 1 2024
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    users.add(new User("Manel", "95783", Objects.requireNonNull(DirectoryUserGroups.findUserGrupoByName("manager"))));
    users.add(new User("Marta", "05827", Objects.requireNonNull(DirectoryUserGroups.findUserGrupoByName("manager"))));

    // admin :
    // always=2023 to 2100
    // all days of the week
    // all actions
    // all spaces
    users.add(new User("Ana", "11343", Objects.requireNonNull(DirectoryUserGroups.findUserGrupoByName("administrator"))));
  }

  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }

}

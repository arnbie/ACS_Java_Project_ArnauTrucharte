package base;

import base.jerarchy.Space;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

// This directory will keep track of all groups created but also take care of creating them.
public class DirectoryUserGroups {
  private static ArrayList<UserGroup> groups;

  public static void makeUserGroups() {
    groups = new ArrayList<>();
    // At first we will generate all objects and arrays needed to
    // initialise everything that the groups will
    // need to work properly.
    // Those arrays will contain the valid actions of each group
    ArrayList<String> actionsEmployer = new ArrayList<>();
    actionsEmployer.add(Actions.OPEN);
    actionsEmployer.add(Actions.CLOSE);
    actionsEmployer.add(Actions.UNLOCK_SHORTLY);

    ArrayList<String> actionsManager = new ArrayList<>();
    actionsManager.add(Actions.OPEN);
    actionsManager.add(Actions.CLOSE);
    actionsManager.add(Actions.UNLOCK_SHORTLY);
    actionsManager.add(Actions.LOCK);
    actionsManager.add(Actions.UNLOCK);

    ArrayList<String> actionsAdministrator = new ArrayList<>();
    actionsAdministrator.add(Actions.OPEN);
    actionsAdministrator.add(Actions.CLOSE);
    actionsAdministrator.add(Actions.UNLOCK_SHORTLY);
    actionsAdministrator.add(Actions.LOCK);
    actionsAdministrator.add(Actions.UNLOCK);

    // We now get to make the dates
    // Cambiar horas y minutos
    LocalDateTime startEmployer = LocalDateTime.of(2023, 9, 1, 0, 0);
    LocalDateTime endEmployer = LocalDateTime.of(2024, 3, 1, 0, 0);

    LocalDateTime startManager = LocalDateTime.of(2023, 9, 1, 0, 0);
    LocalDateTime endManager = LocalDateTime.of(2024, 3, 1, 0, 0);

    LocalDateTime startAdministrator = LocalDateTime.of(0, 1, 1, 0, 0);
    LocalDateTime endAdministrator = LocalDateTime.of(9999, 12, 30, 0, 0);

    // Valid spaces for groups
    ArrayList<Space> spacesEmployer = new ArrayList<>();
    spacesEmployer.add((Space) DirectoryAreas.findAreaById("hall"));
    spacesEmployer.add((Space) DirectoryAreas.findAreaById("room1"));
    spacesEmployer.add((Space) DirectoryAreas.findAreaById("room2"));
    spacesEmployer.add((Space) DirectoryAreas.findAreaById("room3"));
    spacesEmployer.add((Space) DirectoryAreas.findAreaById("corridor"));
    spacesEmployer.add((Space) DirectoryAreas.findAreaById("it"));
    spacesEmployer.add((Space) DirectoryAreas.findAreaById("stairs"));
    spacesEmployer.add((Space) DirectoryAreas.findAreaById("exterior"));

    ArrayList<Space> spacesManager = new ArrayList<>();
    spacesManager.add((Space) DirectoryAreas.findAreaById("parking"));
    spacesManager.add((Space) DirectoryAreas.findAreaById("hall"));
    spacesManager.add((Space) DirectoryAreas.findAreaById("room1"));
    spacesManager.add((Space) DirectoryAreas.findAreaById("room2"));
    spacesManager.add((Space) DirectoryAreas.findAreaById("room3"));
    spacesManager.add((Space) DirectoryAreas.findAreaById("corridor"));
    spacesManager.add((Space) DirectoryAreas.findAreaById("it"));
    spacesManager.add((Space) DirectoryAreas.findAreaById("stairs"));
    spacesManager.add((Space) DirectoryAreas.findAreaById("exterior"));

    ArrayList<Space> spacesAdministrator = new ArrayList<>();
    spacesAdministrator.add((Space) DirectoryAreas.findAreaById("parking"));
    spacesAdministrator.add((Space) DirectoryAreas.findAreaById("hall"));
    spacesAdministrator.add((Space) DirectoryAreas.findAreaById("room1"));
    spacesAdministrator.add((Space) DirectoryAreas.findAreaById("room2"));
    spacesAdministrator.add((Space) DirectoryAreas.findAreaById("room3"));
    spacesAdministrator.add((Space) DirectoryAreas.findAreaById("corridor"));
    spacesAdministrator.add((Space) DirectoryAreas.findAreaById("it"));
    spacesAdministrator.add((Space) DirectoryAreas.findAreaById("stairs"));
    spacesAdministrator.add((Space) DirectoryAreas.findAreaById("exterior"));

    // To check valid days of the week for each group
    ArrayList<DayOfWeek> daysEmployer = new ArrayList<>();
    daysEmployer.add(DayOfWeek.MONDAY);
    daysEmployer.add(DayOfWeek.TUESDAY);
    daysEmployer.add(DayOfWeek.WEDNESDAY);
    daysEmployer.add(DayOfWeek.THURSDAY);
    daysEmployer.add(DayOfWeek.FRIDAY);

    ArrayList<DayOfWeek> daysManager = new ArrayList<>();
    daysManager.add(DayOfWeek.MONDAY);
    daysManager.add(DayOfWeek.TUESDAY);
    daysManager.add(DayOfWeek.WEDNESDAY);
    daysManager.add(DayOfWeek.THURSDAY);
    daysManager.add(DayOfWeek.FRIDAY);
    daysManager.add(DayOfWeek.SATURDAY);

    ArrayList<DayOfWeek> daysAdministrator = new ArrayList<>();
    daysAdministrator.add(DayOfWeek.MONDAY);
    daysAdministrator.add(DayOfWeek.TUESDAY);
    daysAdministrator.add(DayOfWeek.WEDNESDAY);
    daysAdministrator.add(DayOfWeek.THURSDAY);
    daysAdministrator.add(DayOfWeek.FRIDAY);
    daysAdministrator.add(DayOfWeek.SATURDAY);
    daysAdministrator.add(DayOfWeek.SUNDAY);

    // Initializing schedule objects
    Schedule scheduleEmployer = new Schedule(startEmployer, endEmployer, 9, 0, 17, 0,
            daysEmployer);
    Schedule scheduleManager = new Schedule(startManager, endManager, 8, 0, 20, 0, daysManager);
    Schedule scheduleAdministrator = new Schedule(startAdministrator, endAdministrator, 0, 0, 24,
            60, daysAdministrator);

    // Creating of each group, notice how the blank group gets some null objects,
    // thats because in UseGroup if any of those parts are null,
    // when making a request it will automatically return false
    UserGroup employer = new UserGroup("employer", actionsEmployer, spacesEmployer,
            scheduleEmployer);
    UserGroup manager = new UserGroup("manager", actionsManager, spacesManager, scheduleManager);
    UserGroup administrator = new UserGroup("administrator", actionsAdministrator,
            spacesAdministrator, scheduleAdministrator);
    UserGroup blank = new UserGroup("blank", null, null, null);

    // We add the groups to the array
    groups.add(employer);
    groups.add(manager);
    groups.add(administrator);
    groups.add(blank);

    System.out.println("MakeUserGroups");
  }

  // Function to search a group by its name
  // Since it is static it will be accesible by any class
  public static UserGroup findUserGrupoByName(String name) {
    for (UserGroup group : groups) {
      if (group.getName().equals(name)) {
        return group;
      }
    }
    return null;
  }
}

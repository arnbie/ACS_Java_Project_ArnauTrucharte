package base;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

// This class will be used when making a petition for a door to check if it is a valid time.
// That is, if the petition Date is between dates, has been made in a valid day of
// the week and also at a valid hour.
public class Schedule {
  private final LocalDateTime fromD;
  private final LocalDateTime toD;
  private final int fromHour;
  private final int fromMinute;
  private final int toHour;
  private final int toMinute;
  private final ArrayList<DayOfWeek> days;

  public Schedule(LocalDateTime start, LocalDateTime end, int startHour, int startMinute,
                  int endHour, int endMinute, ArrayList<DayOfWeek> validDays) {
    this.fromD = start;
    this.toD = end;
    this.fromHour = startHour;
    this.fromMinute = startMinute;
    this.toHour = endHour;
    this.toMinute = endMinute;
    this.days = validDays;
  }

  // Check if the date is between the 2 LocalDateTimes specified in the variables
  // if the day is valid and also if the hour of the request is valid
  public boolean inSchedule(LocalDateTime data) {
    boolean checkData = data.isAfter(this.fromD) && data.isBefore(this.toD);
    boolean checkDay = days.contains(data.getDayOfWeek());
    boolean checkFromHour;
    if (data.getHour() == this.fromHour) {
      checkFromHour = data.getMinute() >= this.fromMinute;
    } else {
      checkFromHour = data.getHour() >= this.fromHour;
    }
    boolean checkToHour;
    if (data.getHour() == this.toHour) {
      checkToHour = data.getMinute() <= this.toMinute;
    } else {
      checkToHour = data.getHour() <= this.toHour;
    }
    return checkData && checkDay && checkFromHour && checkToHour;
  }
}

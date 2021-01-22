import java.util.Scanner;

public class Roster {
  private int RosterSize;
  private String title;
  private Student[] students;

  public Roster(String title, int RosterSize) {
    this.students = students;
    this.title = title;
    this.RosterSize = RosterSize;
  }

  public void setStudents(Student[] students) {
    this.students = students;
  }

  public Student[] getStudents() {
    return students;
  }

  public int getRosterSize() {
    return RosterSize;
  }

  public void printRoster() {
    System.out.println(this.title + ": " + (RosterSize) + " students");
  }

}

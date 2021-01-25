import java.util.Scanner;

/**
 * Contains code for singular class roster.
 * Has students, title, and size
 */
public class Roster {
  private int RosterSize;
  private String title;
  private Student[] students;

  public Roster(String title, int RosterSize) {
    this.title = title;
    this.RosterSize = RosterSize;
  }

  public void setStudents(Student[] students) {
    this.students = students;
  }

  public String getTitle(){
    return title;
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

  public void printStudents(){
    for(int i = 0; i < getRosterSize(); i++){
      System.out.println(getStudents()[i].getStudentName());
    }
  }
}

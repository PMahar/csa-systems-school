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

  /**
   * Create a new student
   */
  public Student addStudent() {
    Scanner scl = new Scanner(System.in);
    Scanner sct = new Scanner(System.in);
    System.out.println("Student Name: ");
    String name = scl.nextLine();
    System.out.println("Student Id:");
    int id = sct.nextInt();
    return new Student(name, id); // Construct a new student
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

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Rosters will contain an array of Student
 * objects, an integer representing their size,
 * and a title
 */
public class Roster {
  private int RosterSize;
  private String title;
  private ArrayList<Student> students = new ArrayList<>();

  /**
   * Constructor of Roster object
   * @param title Title of Roster object (E.g. grade, 12th grade, seniors)
   * @param RosterSize Integer representing student count
   */
  public Roster(String title, int RosterSize) {
    this.title = title;
    this.RosterSize = RosterSize;
  }

  /**
   * Constructs a new student object
   * @return Student object representing a student with a numerical ID and text name
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

  /**
   * Adds student from a created Student object
   * @param student The student to add
   */
  public void addStudent(Student student){
      students.add(student);
  }

  /**
   * Mutator for class student array
   * @param students Modified student array
   */
  public void setStudents(ArrayList<Student> students) {
    this.students = students;
  }

  /**
   * Accessor for title field
   * @return Title of roster
   */
  public String getTitle(){
    return title;
  }

  /**
   * Accessor for students array
   * @return Array of students within roster
   */
  public ArrayList<Student> getStudents() {
    return students;
  }

  /**
   * Accessor for size of current roster
   * @return Numerical length of students array
   */
  public int getRosterSize() {
    return RosterSize;
  }

  /**
   * Utility method for printing roster information to the console
   */
  public void printRoster() {
    System.out.println(this.title + ": " + (RosterSize) + " students");
  }

}

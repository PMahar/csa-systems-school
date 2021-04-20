import java.util.Scanner;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Rosters will contain an array of Student
 * objects, an integer representing their size,
 * and a title
 */
public class Roster {
  private int rosterSize;
  private String rosterTitle;
  private String rosterUUID;
  private ArrayList<Student> students = new ArrayList<>();

  /**
   * Constructor of Roster object
   * @param title Title of Roster object (E.g. grade, 12th grade, seniors)
   * @param rosterSize Integer representing student count
   */
  public Roster(String title, int rosterSize) {
    this.rosterTitle = title;
    this.rosterSize = rosterSize;
    this.rosterUUID = UUID.randomUUID().toString();
  }

  /**
   * Overloaded constructor of a Roster object, which contains students
   * @param rosterTitle String title of the roster (i.e. "12th grade")
   * @param rosterSize Integer value of the size of the roster
   * @param rosterUUID Unique identifier of roster object, if already present in a file
   */
  public Roster(String rosterTitle, int rosterSize, String rosterUUID) {
    this.rosterTitle = rosterTitle;
    this.rosterSize = rosterSize;
    this.rosterUUID = rosterUUID;
  }

  public String getRosterUUID() {
    return rosterUUID;
  }

  /**
   * Constructs a new student object
   * @return Student object representing a student with a numerical ID and text name
   */
  public Student addStudent() {
    Scanner scl = new Scanner(System.in);
    Scanner sct = new Scanner(System.in);
    System.out.println("Add student - Student name: ");
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
  public String getRosterTitle(){
    return rosterTitle;
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
    return rosterSize;
  }


  /**
   * Utility method for printing roster information to the console
   */
  public void printRoster() {
    System.out.println(this.rosterTitle + ": " + (rosterSize) + " students");
  }
}

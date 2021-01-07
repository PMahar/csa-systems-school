import java.util.Scanner;

/**
 * The Student class will contain behaviors for accessing and setting values for course enrollments
 * and the attendance roster. printRoster is also available to access and display the current roster.
 */

public class Student {
  private int sID;
  public int studentCount = 0;
  public String[] students = new String[studentCount];
  private String[] studBackup;
  public String[] enrollments;

  /**
   * Adds a student to the current roster. This is intended to be called multiple times
   * over a while loop.
   *
   * @param id Integer id of each student
   */

  public void newStudent(int id) {
    //Create an array, and procedurally back it up by copying its contents to
    //another array of greater size after each iteration
    this.students = new String[studentCount + 1];
    //If the backup exists, use it
    if (!(studBackup == null)) {
      for (int i = 0; i < students.length; i++) {
        students[i] = studBackup[i];
      }
    }
    studentCount++;
    students[studentCount - 1] = "" + id;
    studBackup = new String[students.length + 1];
    for (int i = 0; i < students.length; i++) {
      studBackup[i] = students[i];
    }
  }

  /**
   * Submenu for adding courses per each student. Courses are concatenated into comma-separated
   * strings in an array for each student.
   *
   * @return Array of enlisted courses, indexed by student
   */

  public String[] addCourses() {
    Scanner cst = new Scanner(System.in); // token-based
    Scanner csl = new Scanner(System.in); // line-based
    String course = "";
    this.enrollments = new String[students.length];
    //For every student, prompt for classes to concatenate to a
    //string array
    for (int i = 0; i < students.length; i++) {
      System.out.print("ID " + students[i] + "- Number of enlisted courses: ");
      int enlist = cst.nextInt() - 1;
      for (int j = 0; j <= enlist; j++) {
        System.out.print("Course " + (j + 1) + " for student " + students[i] + ": ");
        if (j != enlist) {
          course += csl.nextLine() + ", ";
        } else {
          course += csl.nextLine();
        }
      }
      this.enrollments[i] = course;
      course = "";
    }
    return this.enrollments;
  }

  /**
   * Prints the current roster with course enrollments
   * @param enrollments String array of courses (defined as this.enrollments)
   */

  public void printRoster(String[] enrollments) {
    System.out.println();
    for (int i = 0; i < students.length; i++) {
      System.out.println("[" + students[i] + "]    " + "[" + enrollments[i] + "]");
    }
  }

}
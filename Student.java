import java.util.Scanner;

public class Student {
  private int sID;
  private int studentCount = 0;
  private String[] students = new String[studentCount];
  private String[] studBackup;
  private String[] enrollments;

  public Student() {

  }

  public void newStudent(int id) {
    this.students = new String[studentCount + 1];
    if (!(studBackup == null)) {
      for (int i = 0; i < students.length; i++) {
        students[i] = studBackup[i];
      }
    }
    studentCount++;
    students[studentCount - 1] = "" + id;
    this.studBackup = new String[students.length + 1];
    for (int i = 0; i < students.length; i++) {
      studBackup[i] = students[i];
    }
// debug:
//    for (int x = 0; x < backup.length; x++) {
//      System.out.println("BACKUP: " + backup[x]);
//    }
//    for (int x = 0; x < students.length; x++) {
//      System.out.println("STUDENTS: " + students[x]);
//    }
  }

  public String[] addCourses() {
    Scanner cst = new Scanner(System.in); // token-based
    Scanner csl = new Scanner(System.in); // line-based
    String course = "";
    this.enrollments = new String[students.length];
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
//    for (int x = 0; x < enrollments.length; x++) {
//      System.out.println("enrollments: " + enrollments[x]);  debug
//    }
    return this.enrollments;
  }

  public void printRoster(String[] enrollments) {
    System.out.println();
    for (int i = 0; i < students.length; i++) {
      System.out.println("[" + students[i] + "]    " + "[" + enrollments[i] + "]");
    }
  }

}
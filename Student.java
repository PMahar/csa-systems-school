import java.util.Scanner;

public class Student {
  private int sID;
  private String[] sClasses;
  int studentCount = 0;
  String[] students = new String[studentCount];
  String[] studBackup;
  String[] classBackup;

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
//debug:
//    for (int x = 0; x < backup.length; x++) {
//      System.out.println("BACKUP: " + backup[x]);
//    }
//    for (int x = 0; x < students.length; x++) {
//      System.out.println("STUDENTS: " + students[x]);
//    }
  }

  public String[] addCourses() {
    Scanner cs = new Scanner(System.in);
    String course = "";
    String[] enrollments = new String[students.length];
    for (int i = 0; i < students.length; i++) {
      System.out.println("ID " + students[i] + "- Number of enlisted courses : ");
      int enlist = cs.nextInt();
      for (int j = 0; j < enlist; j++) {
        System.out.print("Course " + (j + 1) + " for student " + students[i] + ": ");
        course += cs.nextLine() + ", ";
      }
      enrollments[i] = course;
      course = null;
    }
    return enrollments;
  }

  public void printRoster() {
    System.out.println();
    for (int i = 0; i < students.length; i++) {
      System.out.println("[" + students[i] + "]");
    }
  }
}

import java.util.Scanner;

/**
 * The Student class will contain behaviors for accessing
 * and setting values for course enrollments
 * and the attendance roster. printRoster is also available
 * to access and display the current roster.
 */

public class Student {
  public int studentCount = 0;
  public String[] students = new String[studentCount];
  private String[] studBackup;
  public String[] enrollments;
  private int[] studentID;
  private String[] studentName;
  private final Scanner scan = new Scanner(System.in);

  /**
   * This is the code to add students, taken from Runner
   * @param args allows for restarting at main prompt
   */
  public void addStudents(String[] args) {
    System.out.println("ADD STUDENT:\n");
    System.out.println("How many students do you plan on adding?");
    int studentNum = scan.nextInt(); // The amount of students to add
    if (studentNum > 0) { // Make sure that the user can't break the program
      // This will be used to "hold" multiple students ID's
      studentID = new int[studentNum];
      // This will be used to hold multiple student names
      studentName = new String[studentNum];
      // Add given student id's to roster
      for (int i = 0; i < studentNum; i++) {
        System.out.println("Student " + (i + 1) + "'s ID");
        int id = scan.nextInt();
        studentID[i] = id; // Add the students ID
        System.out.println("Student " + (i + 1) + "'s name");
        studentName[i] = scan.next(); // Add student name
      }
      // For every student there is, create a new student with given info
      for (int j = 0; j < studentNum; j++) {
        newStudent(studentID[j], studentName[j]); // Create a new student
      }
    } else {
      Runner.main(args);
    }
  }

  /**
   * Adds a student to the current roster. This is intended
   * to be called multiple times
   * over a while loop.
   *
   * @param id Integer id of each student
   * @param name Name of student to create
   */

  private void newStudent(int id, String name) {
    // Create an array, and procedurally back it up by copying its contents to
    // another array of greater size after each iteration
    Grades grades = new Grades();
    grades.initGrades(id);
    this.students = new String[studentCount + 1];
    // If the backup exists, use it
    if (!(studBackup == null)) {
      if (students.length >= 0) System.arraycopy(studBackup, 0, students, 0, students.length);
    }
    studentCount++;
    students[studentCount - 1] = "" + id + " " + name;
    studBackup = new String[students.length + 1];
    System.arraycopy(students, 0, studBackup, 0, students.length);
  }

  /**
   * Submenu for adding courses per each student. Courses are concatenated
   * into comma-separated
   * strings in an array for each student.
   *
   * @return Array of enlisted courses, indexed by student
   */

  public String[] addCourses() {
    Scanner cst = new Scanner(System.in); // token-based
    Scanner csl = new Scanner(System.in); // line-based
    String course = "";
    Grades grade = new Grades();
    this.enrollments = new String[studentName.length];
    //For every student, prompt for classes to concatenate to a
    //string array

    // For i < amount of students, increase
    for (int i = 0; i < studentName.length; i++) {
      grade.initGrades(studentID[i]); // Initialize grades for student
      System.out.print("ID " + studentID[i] +
              "- Number of enlisted courses: ");
      int enlist = cst.nextInt() - 1;

      for (int j = 0; j <= enlist; j++) {
        System.out.print("Course " + (j + 1) + " for student " +
                "" + studentName[i] + ": ");
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
   * Version of addCourses() that has parameters to allow for specific
   * student course enrollment
   * @param id the id of the student to enroll in a course
   * @return the enrolled classes
   */
  public String[] addCourses(int id){
    Scanner cst = new Scanner(System.in); // token-based
    Scanner csl = new Scanner(System.in); // line-based
    String course = "";
    Grades grade = new Grades();
    int idIndex = Runner.findInArray(studentID, id);
    this.enrollments = new String[studentName.length];
    //For every student, prompt for classes to concatenate to a
    //string array

    // For i < amount of students, increase
      System.out.print("ID " + studentID[idIndex] +
              "- Number of enlisted courses: ");
      int enlist = cst.nextInt() - 1;
      for (int j = 0; j <= enlist; j++) {
        System.out.print("Course " + (j + 1) + " for student " +
                "" + studentName[idIndex] + ": ");
        if (j != enlist) {
          course += csl.nextLine() + ", ";
        } else {
          course += csl.nextLine();
        }
      }
      this.enrollments[idIndex] = course;
    return this.enrollments;
  }

  /**
   * Version of addCourses() that has parameters to allow for specific
   * student course enrollment
   * @param name the name of the student to enroll in a course
   * @return the enrolled classes
   */
  public String[] addCourses(String name){
    Scanner cst = new Scanner(System.in); // token-based
    Scanner csl = new Scanner(System.in); // line-based
    String course = "";
    Grades grade = new Grades();
    int id = getStudentID(name);
    int idIndex = Runner.findInArray(studentID, id);
    this.enrollments = new String[studentName.length];

    System.out.print("ID " + studentID[idIndex] +
            "- Number of enlisted courses: ");
    int enlist = cst.nextInt() - 1;
    for (int j = 0; j <= enlist; j++) {
      System.out.print("Course " + (j + 1) + " for student " +
              "" + studentName[idIndex] + ": ");
      if (j != enlist) {
        course += csl.nextLine() + ", ";
      } else {
        course += csl.nextLine();
      }
    }
    this.enrollments[idIndex] = course;
    return this.enrollments;
  }

  /**
   * Prints the current roster with course enrollments
   * @param enrollments String array of courses
   *                    (defined as this.enrollments)
   */
  public void printRoster(String[] enrollments) {
    System.out.println();
    for (int i = 0; i < studentName.length; i++) {
      System.out.println("[" + studentName[i] + " ID: " +
              "" + getStudentID(studentName[i]) + "]    "
              + "[" + enrollments[i] + "]");
    }
  }

  /**
   * Self explanatory
   * @param name The name to find the corresponding ID of
   * @return the ID that corresponds to the given name
   */
  public int getStudentID(String name) {
    // Check that the things are valid
    if (!name.isEmpty() && studentName != null) {
      for (int i = 0; i < studentName.length; i++) {
        if (studentName[i].contains(name)) {
          return studentID[i];
        }
      }
    } else {
      System.out.println("Can't get student's id");
    }
    return Integer.MAX_VALUE;
  }

  /**
   * Self explanatory
   * @param id The id to find the corresponding name of
   * @return returns the name of the student with given ID
   */
  public String getStudentName(int id){
    if(id > 0) {
      for (int i = 0; i < studentID.length; i++) {
        if (studentID[i] == id) {
          return studentName[i];
        }
      }
    }
    return null;
    }
  }

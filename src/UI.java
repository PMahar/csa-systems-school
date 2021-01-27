
import java.util.Scanner;

/**
 * This is where the interfacing is between the user
 * and the code
 */
public class UI {
  private static Roster[] rosters;
  private static Teacher[] teachers;
  private static String school;

  public static void main(String[] args) {
    UI run = new UI();
    Scanner scl = new Scanner(System.in);
    Scanner sct = new Scanner(System.in);
    // Initialize the program for first-time use
    if (rosters == null) {
      run.setup();
    }
    System.out.println("\nSchool: " + school);
    System.out.println("|1 - Edit student information" +
            "\n|2 - Edit staff information" +
            "\n|3 - Edit marking period grades" +
            "\n|4 - Edit student course enrollments" +
            "\n|5 - Edit teacher's courses" +
            "\n|6 - Add Attendance Rosters" +
            "\n|7 - View roster");
    System.out.print("Please select an option: ");
    switch (sct.next()) {
      case "1":
        System.out.println("\nSelect an attendance roster to edit (Back - 0):");
        for (int i = 0; i < rosters.length; i++) { // Print the roster
          System.out.print((i + 1) + " - ");
          rosters[i].printRoster();
        }
        int select = sct.nextInt(); // Populate a roster object with students
        if (select == 0) { // Send the user back to the main prompt
          main(args);
        }
        int rosterSize = rosters[select - 1].getRosterSize();
        Student[] populateStudents = new Student[rosterSize];
        // for i < rosterSize increase i, execute code
        for (int i = 0; i < (rosters[select - 1].getRosterSize()); i++) {
          // Set index i of populateStudents to what the user gives in addStudent()
          populateStudents[i] = run.addStudent();
          rosters[select - 1].setStudents(populateStudents); // Add students to roster
          String rosterName = rosters[select - 1].getTitle();
          /*
          for(int j = 0; j < courses.length; j++){
            if(courses[j] != null) { // If there are courses
              if(courses[j].getCourseName().equalsIgnoreCase(rosterName)) {
                courses[j].addStudent(populateStudents[i]);
              }
            }
          }

           */
        }
        main(args);
        break;
      case "2":
        if (teachers != null) {
          for (int i = 0; i < teachers.length; i++) {
            System.out.println(teachers[i].getTeacherID() + " - "
                    + teachers[i].getTeacherName()
                    /*+ teachers[i].catCourses()*/);
          }
        }
        System.out.println("Teacher Name (Back - 0): ");
        String teacherName = scl.next();
        if (teacherName.equals("0")) {
          main(args);
        }
        System.out.println("Teacher ID: ");
        int teacherID = sct.nextInt();
        run.addTeacher(teacherName, teacherID);
        main(args);
        break;
      case "3":
        // Edit/ add mpGrades
        System.out.println("Choose a roster to grade in:");
        // Print roster
        for(int i = 0; i < rosters.length; i++){
          System.out.print((i+1) + " - ");
          rosters[i].printRoster();
        }
        int rostChoice = sct.nextInt(); // Roster to grade from
        if (rosters[rostChoice - 1].getStudents() == null) {
          System.out.println("Please add the contents of this roster " +
                  "under 'Edit student information.'");
          main(args);
        }
        System.out.println("Choose a student to grade: ");
        // Print students
        for(int k = 0; k < rosters[rostChoice - 1].getStudents().length; k++){
          System.out.println((k+1) + " - " +
                  rosters[rostChoice - 1].getStudents()[k].getStudentName());
        }
        int studIndex = sct.nextInt(); // Student to grade
        // Find the course that is related to said roster
        Student courseFind = run.findStudent(rosters[rostChoice - 1].
                getStudents()[studIndex - 1].getStudentName());
        Course studCourse = null;
        if(courseFind.getCourses() != null) { // If course is valid find the matching roster
          for (int i = 0; i < courseFind.getCourseCount(); i++) {
              if (courseFind.getCourses()[i].getCourseName().
                      equalsIgnoreCase(rosters[rostChoice - 1].getTitle())) {
                studCourse = courseFind.getCourses()[i];
            }
          }
          System.out.println("Please enter marking period grade for " +
                  rosters[rostChoice - 1].getStudents()[studIndex - 1].getStudentName());
          int grade = sct.nextInt();
          MPGrade mpGrade = new MPGrade(studCourse,
                  rosters[rostChoice - 1].getStudents()[studIndex - 1], grade);
          rosters[rostChoice-1].getStudents()[studIndex-1].addMPGrade(studCourse,grade);
          mpGrade.printGrade();
        } else {
          System.out.println("Please enroll student in course first");
        }
        main(args);
      case "4":
        System.out.println("Please select a student (Back - 0: ");
        for (int i = 0; i < rosters.length; i++) {
          System.out.print((i + 1) + " - ");
          rosters[i].printRoster();
        }
        int rosterCourse = sct.nextInt();
        Student[] studentsCourse = rosters[rosterCourse - 1].getStudents();
        if (studentsCourse == null) {
          System.out.println("Please add the contents of this roster " +
                  "under 'Edit student information.'");
          main(args);
        }
        for (int i = 0; i < studentsCourse.length; i++) {
          System.out.println(i + 1 + " - [" + studentsCourse[i].getStudentID()
                  + "]" + "  " + studentsCourse[i].getStudentName());
        }
        int studentChoice = sct.nextInt();
        System.out.println("Enter enrollments for " +
                studentsCourse[studentChoice - 1].getStudentName()
                            +", " + studentsCourse[studentChoice - 1].getStudentID());
        System.out.print("Number of enrollments: ");
        int courseCountStudent = sct.nextInt();
        System.out.println("Press enter after each course to add it to " +
                "the enrollment list.");
        for (int i = 0; i < courseCountStudent; i++) {
          studentsCourse[studentChoice - 1].addCourses(scl.nextLine());
        }
        studentsCourse[studentChoice - 1].listCourses();
        main(args);
        break;
      case "5":
        if (teachers == null) {
          System.out.println("Please add teachers under 'Edit staff " +
                  "information.'");
          main(args);
        }
        System.out.println("Please select a teacher (Back - 0): ");
        for (int i = 0; i < teachers.length; i++) {
          System.out.println(teachers[i].getTeacherName());
        }
        int teacherChoice = sct.nextInt();
        System.out.print("Number of enrollments: ");
        int courseCountTeacher = sct.nextInt();
        System.out.println("Press enter after each course to add it to " +
                "the enrollment list.");
        for (int i = 0; i < courseCountTeacher; i++) {
          teachers[teacherChoice - 1].addCourses(scl.nextLine());
        }
        main(args);
        break;
      case "6":
        System.out.println("\nCurrent Rosters for " + school + ": ");
        for (int i = 0; i < rosters.length; i++) {
          System.out.print((i + 1) + " - ");
          rosters[i].printRoster();
        }
        System.out.print("Enter roster title: ");
        String rosterTitle = scl.nextLine();
        System.out.print("Enter roster size: ");
        int rSize = sct.nextInt();
        run.addRoster(rosterTitle, rSize);
        main(args);
        break;
      case "7":
        System.out.println("\nSelect an attendance roster:");
        for (int i = 0; i < rosters.length; i++) {
          System.out.print((i + 1) + " - ");
          rosters[i].printRoster();
        }
        int rosterView = sct.nextInt();
        Student[] studentsView = rosters[rosterView - 1].getStudents();
        if (studentsView == null) {
          System.out.println("Please add the contents of this roster " +
                  "under 'Edit student information.'");
          main(args);
        }
        for (int i = 0; i < studentsView.length; i++) {
          System.out.println("[" + studentsView[i].getStudentID()
                  + "]" + "  " + studentsView[i].getStudentName() +
                  studentsView[i].catCourses());
        }
        main(args);
        break;
      default:
        System.out.println();
        main(args);
    }

  }

  /**
   * Prepares the program for first-time use, and is implemented
   * in the absence of existing data
   */
  private void setup() {
    Scanner scl = new Scanner(System.in); //line
    Scanner sct = new Scanner(System.in); //token
    System.out.print("Initial setup - Enter school name: ");
    school = scl.nextLine();
    System.out.print("                Enter roster title: ");
    String rosterTitle = scl.nextLine();
    System.out.print("                Enter roster size: ");
    int rSize = sct.nextInt();
    rosters = new Roster[1];
    // Assign the first index in array rosters to a new roster with
    // given title and size
    rosters[0] = new Roster(rosterTitle, rSize);
  }

  /**
   * Create a new student
   */
  private Student addStudent() {
    System.out.println(school);
    Scanner scl = new Scanner(System.in);
    Scanner sct = new Scanner(System.in);
    System.out.println("Student Name: ");
    String name = scl.nextLine();
    System.out.println("Student Id:");
    int id = sct.nextInt();
    return new Student(name, id); // Construct a new student
  }

  /**
   * Creates a new teacher
   */
  private void addTeacher(String name, int id) {
    if (teachers == null) {
      teachers = new Teacher[1];
      teachers[0] = new Teacher(name, id);
      return;
    }
    Teacher[] teachersBak = new Teacher[teachers.length + 1];
    for (int i = 0; i < teachers.length; i++) {
      teachersBak[i] = teachers[i];
    }
    teachers = new Teacher[teachersBak.length];
    for (int i = 0; i < teachersBak.length; i++) {
      teachers[i] = teachersBak[i];
    }
    teachers[teachers.length - 1] = new Teacher(name, id);
      /*
      System.out.println("Teacher's course (0 to escape): ");
      while(scl.hasNext() && !scl.next().contains("0")){
        Course course = new Course(scl.next());
        run.addTeacherCourse(teacherAdd, course);
      }

       */

  }


  private void addRoster(String title, int rosterSize) {
    Roster[] rostersBak = new Roster[rosters.length + 1];
    for (int i = 0; i < rosters.length; i++) {
      rostersBak[i] = rosters[i];
    }
    rosters = new Roster[rostersBak.length];
    for (int i = 0; i < rostersBak.length; i++) {
      rosters[i] = rostersBak[i];
    }
    rosters[rosters.length - 1] = new Roster(title, rosterSize);
  }

  /**
   * Finds a student from a string of their name
   * @param name The name of the student to search for
   * @return The student looked for, if not found returns null
   */
  private Student findStudent(String name){
    for(int i = 0; i < rosters.length; i++){
      for(int x = 0; x < rosters[i].getStudents().length; x++){
        if(rosters[i].getStudents()[x].getStudentName().equalsIgnoreCase(name)){
          return rosters[i].getStudents()[x];
        }
      }
    }
    System.err.println("Student not found");
    return null;
  }
}

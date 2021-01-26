
import java.util.Scanner;

/**
 * This is where the interfacing is between the user
 * and the code
 */
public class Interface {
  private static Roster[] rosters;
  private static Teacher[] teachers = new Teacher[5];
  private static Course[] courses = new Course[5];
  private static String school;

  public static void main(String[] args) {
    Interface run = new Interface();
    Scanner scl = new Scanner(System.in);
    Scanner sct = new Scanner(System.in);
    // Initialize the program for first-time use
    if (rosters == null) {
      run.setup();
    }
    System.out.println("\nSchool: " + run.school);
    System.out.println("1 - Edit student information | 2 - Edit staff information | 3 - Edit marking period grades | 4 - Add Attendance Rosters");
    // Prompt the user
    System.out.println("1 - Edit student information | 2 - Edit staff information " +
            "| 3 - Edit marking period grades | 4 - Add Attendance Rosters");
    System.out.println("5 - View roster");
    System.out.print("Please select an option: ");
    switch (scl.next()) {
      case "1": // Edit student info
        switch (sct.next()) {
          case "1":
            System.out.println("\nSelect an attendance roster to edit:");
            for (int i = 0; i < rosters.length; i++) {
              System.out.print((i + 1) + " - ");
              rosters[i].printRoster();
            }
            System.out.println("Back - 0");
            int select = sct.nextInt(); //populate a roster object with students
            if (select == 0) {
              main(args);
            }
            int rosterSize = rosters[select - 1].getRosterSize();
            Student[] populate = new Student[rosterSize];
            for (int i = 0; i < (rosters[select - 1].getRosterSize()); i++) {
              populate[i] = run.addStudent();
              rosters[select - 1].setStudents(populate);
            }
            main(args);
          case "4":
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

          case "2": // Edit staff info
            break;

          case "3": // Edit marking period grades
            // Print rosters of classes to grade
            System.out.println("\nSelect an attendance roster to grade from: ");
            for (int i = 0; i < rosters.length; i++) {
              // TODO: Try to figure out how to get student id instead of
              // TODO: value of "i"
              System.out.print((i + 1) + " - ");
              rosters[i].printRoster();
            }
            int classId = sct.nextInt(); // Get the class to grade
            // Print the student options
            rosters[classId - 1].printStudents();
            System.out.println("\nChoose a student to grade: ");
            String student = scl.next();
            // Find the corresponding course
            for (int i = 0; i < courses.length; i++) {
              if (courses[i] != null && courses[i].getCourseName().equals(rosters[classId - 1].getTitle())) {
                for (int j = 0; j < courses[i].getStudents().length; j++) {
                  if (courses[i].getStudents()[j].getStudentName().equalsIgnoreCase(student)) {
                    System.out.println("Grade for student: ");
                    int grade = sct.nextInt();
                    MPGrade mpGrade = new MPGrade(courses[i], courses[i].getStudents()[j], grade);
                  }
                }
              } else {
                System.out.println("No valid courses");
              }
            }
            break;

          case "5": // View roster
            System.out.println("\nSelect an attendance roster:");
            for (int i = 0; i < rosters.length; i++) {
              System.out.print((i + 1) + " - ");
              rosters[i].printRoster();
            }
            int roster = sct.nextInt();
            Student[] students = rosters[roster - 1].getStudents();
            if (students == null) {
              System.out.println("Please add the contents of this roster under 'Edit student information.'");
              main(args);
            }
            for (int i = 0; i < students.length; i++) {
              System.out.println("[" + students[i].getStudentID() + "]" + "  " + students[i].getStudentName());
            }
            main(args);
          default:
            System.out.println();
            main(args);
        }
        main(args);
    }
  }

  /**
   * Prepares the program for first-time use, and is implemented in the absence of existing data
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
    rosters[0] = new Roster(rosterTitle, rSize);
  }

  /**
   * Create a new student
   */
  private Student addStudent() {
    Scanner scl = new Scanner(System.in);
    Scanner sct = new Scanner(System.in);
    System.out.println("Student Name: ");
    String name = scl.nextLine();
    System.out.println("Student Id:");
    int id = sct.nextInt();
    return new Student(name, id);
  }

  /**
   * Creates a new teacher
   */
  private void addTeacher(){
    Interface run = new Interface();
    Scanner sct = new Scanner(System.in);
    Scanner scl = new Scanner(System.in);

    System.out.println("Teacher Name: ");
    String name = scl.next();
    System.out.println("Teacher ID: ");
    int id = sct.nextInt();
    if(id > 0){
      Teacher teacherAdd = new Teacher(name, id);
      for(int i = 0; i < teachers.length; i++){
        if(teacherAdd.getTeacherName() != null){
          teachers[i] = teacherAdd;
        }
      }
      System.out.println("Teacher's course (0 to escape): ");
      while(scl.hasNext() && !scl.next().contains("0")){
        Course course = new Course(scl.next());
        run.addTeacherCourse(teacherAdd, course);
      }
    } else if(id == 0){
      System.out.println("Invalid ID");
    }
    System.out.println("Successfully added teacher");
  }

  /**
   * Adds teacher to course and course to teacher
   * @param teacher The teacher to add to course
   * @param course The course to add to the teacher
   */
  private void addTeacherCourse(Teacher teacher, Course course){
    teacher.addCourse(course);
    course.addTeacher(teacher);
  }

  /**
   * creates a course object and a roster object
   * @param rosterTitle The title of the course/ title
   * @param rSize The amount of students in the course
   */
  private void addClass(String rosterTitle, int rSize){
    rosters = new Roster[1];
    rosters[0] = new Roster(rosterTitle, rSize);
    Course course = new Course(rosterTitle);
    for(int i = 0; i < courses.length; i++){
      if(courses[i] != null){
        courses[i] = course;
        break;
      }
    }
  }

  /**
   * Adds student to course and course to student
   * @param student Student to add to course
   * @param course Course to add to student
   */
  private void addStudentCourse(Student student, Course course){
    student.addCourse(course);
    course.addStudent(student);
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
}

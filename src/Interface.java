
import java.util.Scanner;

/**
 * This is where the interfacing is between the user
 * and the code
 */
public class Interface {
  private static Roster[] rosters;
  private static Teacher[] teachers = new Teacher[5];
  private String school;

  public static void main(String[] args) {
    Interface run = new Interface();
    Scanner scl = new Scanner(System.in);
    Scanner sct = new Scanner(System.in);
    //initialize the program for first-time use
    if (rosters == null) {
      run.setup();
    }
    System.out.println("1 - Edit student information | 2 - Edit staff information | 3 - Edit marking period grades | 4 - Add Attendance Rosters");
    System.out.println("5 - View roster");
    System.out.print("Please select an option: ");
    switch (scl.next()) {
      case "1":
        System.out.println("\nSelect an attendance roster to edit:");
        for (int i = 0; i < rosters.length; i++) {
          System.out.print((i + 1) + " - ");
          rosters[i].printRoster();
        }
        int select = sct.nextInt(); //populate a roster object with students
        //int select = roster.charAt(0);
        /*
        if (roster.contains("v")) {
          Student[] students = rosters[select - 1].getStudents();
          for (int i = 0; i < students.length; i++) {
            System.out.println("[" + students[i].getStudentID() + "]" + "  " + students[i].getStudentName());
          }
        }
         */
        int rosterSize = rosters[select - 1].getRosterSize();
        Student[] populate = new Student[rosterSize];
        for (int i = 0; i < (rosters[select - 1].getRosterSize()); i++) {
          populate[i] = run.addStudent();
          rosters[select - 1].setStudents(populate);
        }
        main(args);
      case "5":
        System.out.println("\nSelect an attendance roster:");
        for (int i = 0; i < rosters.length; i++) {
          System.out.print((i + 1) + " - ");
          rosters[i].printRoster();
        }
        int roster = sct.nextInt();
        Student[] students = rosters[roster - 1].getStudents();
        for (int i = 0; i < students.length; i++) {
            System.out.println("[" + students[i].getStudentID() + "]" + "  " + students[i].getStudentName());
        }
      default:
        System.out.println();
        main(args);
    }

  }

  private void setup() {
    Scanner scl = new Scanner(System.in); //line
    Scanner sct = new Scanner(System.in); //token
    System.out.print("Initial setup - Enter school name: ");
    school = scl.nextLine();
    System.out.print("                Enter roster title: ");
    String RosterTitle = scl.nextLine();
    System.out.print("                Enter roster size: ");
    int rSize = sct.nextInt();
    rosters = new Roster[1];
    rosters[0] = new Roster(RosterTitle, rSize);
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
    Student studAdd = new Student(name, id); // Construct a new student
    System.out.println("Student's courses (0 to escape, coma's in between names): ");
    //while (!scl.next().equals("0")) {
    //  Course course = new Course(scl.next());
    //  addStudentCourse(studAdd, course);
    //} else if (id == 0) {
    //  System.out.println("Invalid id");
    return studAdd;
  }

  /**
   * Creates a new teacher
   */
  private static void addTeacher(){
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
   * Adds student to course and course to student
   * @param student Student to add to course
   * @param course Course to add to student
   */
  private void addStudentCourse(Student student, Course course){
    student.addCourse(course);
    course.addStudent(student);
  }
}

import java.util.Scanner;

public class School {
  private Roster[] rosters;
  private Teacher[] teachers;
  private String schoolTitle;

  /**
   * Constructor of 'school' object, which contains attendance rosters, teachers, and all
   * respective attributes
   * @param rosters Setup attendance rosters
   * @param teachers Setup teachers
   * @param schoolTitle School title
   */
  public School(Roster[] rosters, Teacher[] teachers, String schoolTitle) {
    this.rosters = rosters;
    this.teachers = teachers;
    this.schoolTitle = schoolTitle;
  }

  /**
   * User interface for modifying 'school' objects within a district
   */
  public void editSchool() {
    Scanner sct = new Scanner(System.in);
    Scanner scl = new Scanner(System.in);
    System.out.println("\nSchool: " + schoolTitle);
    System.out.println("\n|0 - Back" +
            "\n|1 - Edit student information" +
            "\n|2 - Edit staff information" +
            "\n|3 - Edit marking period grades" +
            "\n|4 - Edit student course enrollments" +
            "\n|5 - Edit teacher's courses" +
            "\n|6 - Add attendance rosters" +
            "\n|7 - View roster");

    System.out.print("Please select an option: ");
    switch (sct.next()) {
      case "0":
        break;
      case "1":
        System.out.println("School - " + schoolTitle);
        System.out.println("\nSelect an attendance roster to edit (Back - 0):");
        for (int i = 0; i < rosters.length; i++) { // Print the roster
          System.out.print((i + 1) + " - ");
          rosters[i].printRoster();
        }
        int select = sct.nextInt(); // Populate a roster object with students
        if (select == 0) { // Send the user back to the main prompt
          editSchool();
        }
        int rosterSize = rosters[select - 1].getRosterSize();
        Student[] populateStudents = new Student[rosterSize];
        // for i < rosterSize increase i, execute code
        for (int i = 0; i < (rosters[select - 1].getRosterSize()); i++) {
          // Set index i of populateStudents to what the user gives in addStudent()
          populateStudents[i] = rosters[select - 1].addStudent();
          rosters[select - 1].setStudents(populateStudents); // Add students to roster
        }
        editSchool();
        break;
      case "2":
        if (teachers[teachers.length - 1] != null) {
          for (int i = 0; i < teachers.length; i++) {
            System.out.println(teachers[i].getTeacherID() + " - "
                    + teachers[i].getTeacherName()
                    + ": " + teachers[i].catCourses());
          }
        }
        System.out.println("Teacher Name (Back - 0): ");
        String teacherName = scl.next();
        if (teacherName.equals("0")) {
          editSchool();
        }
        System.out.println("Teacher ID: ");
        int teacherID = sct.nextInt();
        addTeacher(teacherName, teacherID);
        editSchool();
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
          editSchool();
        }
        System.out.println("Choose a student to grade: ");
        // Print students
        for(int k = 0; k < rosters[rostChoice - 1].getStudents().length; k++){
          System.out.println((k+1) + " - " +
                  rosters[rostChoice - 1].getStudents()[k].getStudentName());
        }
        int studIndex = sct.nextInt(); // Student to grade
        // Find the course that is related to said roster
        Student courseFind = findStudent(rosters[rostChoice - 1].
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
        editSchool();
      case "4":
        System.out.println("Please select a student (Back - 0): ");
        for (int i = 0; i < rosters.length; i++) {
          System.out.print((i + 1) + " - ");
          rosters[i].printRoster();
        }
        int rosterCourse = sct.nextInt();
        Student[] studentsCourse = rosters[rosterCourse - 1].getStudents();
        if (studentsCourse == null) {
          System.out.println("Please add the contents of this roster " +
                  "under 'Edit student information.'");
          editSchool();
        }
        for (int i = 0; i < studentsCourse.length; i++) {
          System.out.println(i + 1 + " - [" + studentsCourse[i].getId()
                  + "]" + "  " + studentsCourse[i].getStudentName());
        }
        int studentChoice = sct.nextInt();
        System.out.println("Enter enrollments for " +
                studentsCourse[studentChoice - 1].getStudentName()
                +", " + studentsCourse[studentChoice - 1].getId());
        System.out.print("Number of enrollments: ");
        int courseCountStudent = sct.nextInt();
        System.out.println("Press enter after each course to add it to " +
                "the enrollment list.");
        for (int i = 0; i < courseCountStudent; i++) {
          studentsCourse[studentChoice - 1].addCourses(scl.nextLine());
        }
        studentsCourse[studentChoice - 1].listCourses();
        editSchool();
        break;
      case "5":
        if (teachers[teachers.length - 1] == null) {
          System.out.println("Please add teachers under 'Edit staff " +
                  "information.'");
          editSchool();
        }
        for (int i = 0; i < teachers.length; i++) {
          System.out.println("[" + i + "] " + teachers[i].getTeacherName() + ": " +
                              teachers[i].catCourses());
        }
        System.out.print("Please select a teacher (Back - 0): ");
        int teacherChoice = sct.nextInt();
        System.out.print("Number of enrollments: ");
        int courseCountTeacher = sct.nextInt();
        System.out.println("Press enter after each course to add it to " +
                "the enrollment list.");
        for (int i = 0; i < courseCountTeacher; i++) {
          teachers[teacherChoice - 1].addCourses(scl.nextLine());
        }
        editSchool();
      case "6":
        System.out.println("\nCurrent Rosters for " + schoolTitle + ": ");
        for (int i = 0; i < rosters.length; i++) {
          System.out.print((i + 1) + " - ");
          rosters[i].printRoster();
        }
        System.out.print("Enter roster title: ");
        String rosterTitle = scl.nextLine();
        System.out.print("Enter roster size: ");
        int rSize = sct.nextInt();
        addRoster(rosterTitle, rSize);
        editSchool();
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
          editSchool();
        }
        if (studentsView != null) {
          for (int i = 0; i < studentsView.length; i++) {
            System.out.println("[" + studentsView[i].getId()
                    + "]" + "  " + studentsView[i].getStudentName() +
                    " " + studentsView[i].catCourses());
          }
        }
        editSchool();
        break;
      default:
        System.out.println();
        editSchool();
    }
  }

  /**
   * Creates a new teacher in an expanding array
   * @param name Name of teacher
   * @param id Numerical teacher ID
   */
  public void addTeacher(String name, int id) {
    if (teachers[teachers.length - 1] == null) {
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
  }

  /**
   * Creates a new attenance roster in an expanding array
   * @param title Title of roster (E.g. grade, 12th grade, seniors)
   * @param rosterSize Number of students in roster
   */
  public void addRoster(String title, int rosterSize) {
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
   * Accessor for school rosters
   * @return Current rosters
   */
  public Roster[] getRosters() {
    return rosters;
  }

  /**
   * Accessor for school teachers
   * @return Current teachers
   */
  public Teacher[] getTeachers() {
    return teachers;
  }

  /**
   * Accessor for school title
   * @return School title
   */
  public String getSchoolTitle() {
    return schoolTitle;
  }

  /**
   * Numerical count of total student array size between all rosters
   * @return Total student count
   */
  public int totalStudents() {
    int studentCount = 0;
    for (int i = 0; i < rosters.length; i++) {
      studentCount += rosters[i].getRosterSize();
    }
    return studentCount;
  }

  /**
   * Finds a student from a string of their name
   * @param name The name of the student to search for
   * @return The student looked for, if not found returns null
   */
  public Student findStudent(String name){
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

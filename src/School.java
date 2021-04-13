import java.util.Scanner;
import java.util.ArrayList;

public class School {
  private ArrayList<Roster> rosters;
  private ArrayList<Teacher> teachers;
  private String schoolTitle;

  /**
   * Constructor of 'school' object, which contains attendance rosters, teachers, and all
   * respective attributes
   * @param rosters Setup attendance rosters
   * @param teachers Setup teachers
   * @param schoolTitle School title
   */
  public School(ArrayList<Roster> rosters, ArrayList<Teacher> teachers, String schoolTitle) {
    this.rosters = rosters;
    this.teachers = teachers;
    this.schoolTitle = schoolTitle;
  }

  /**
   * User interface for modifying 'school' objects within a district
   */
  public int editSchool() {
    //Return an int to get a status of termination, and so that
    //the method always completes (or crashes)
    Scanner sct = new Scanner(System.in);
    Scanner scl = new Scanner(System.in);
    System.out.println("\nSchool: " + schoolTitle);
    System.out.println("\n|0 - Save and exit" +
            "\n|1 - Edit student information" +
            "\n|2 - Edit staff information" +
            "\n|3 - Edit marking period grades" +
            "\n|4 - Edit student course enrollments" +
            "\n|5 - Edit teacher's courses" +
            "\n|6 - Add attendance rosters" +
            "\n|7 - View roster" +
            "\n|8 - View marking period grades" +
            "\n|9 - View teachers");

    System.out.print("Please select an option: ");
    switch (sct.next()) {
      case "0":
        System.out.println();

        return 0; //If 0, save and quit
      case "1":
        System.out.println("School - " + schoolTitle);
        System.out.println("\nSelect an attendance roster to edit (Back - 0):");
        for (int i = 0; i < rosters.size(); i++) { // Print the roster
          System.out.print((i + 1) + " - ");
          rosters.get(i).printRoster();
        }
        int select = sct.nextInt(); // Populate a roster object with students
        if (select == 0) { // Send the user back to the main prompt
          return 1; //Return 1 to go back
        }
        ArrayList<Student> populateStudents = new ArrayList<>();
        // for i < rosterSize increase i, execute code
        for (int i = 0; i < (rosters.get(select - 1).getRosterSize()); i++) {
          // Set index i of populateStudents to what the user gives in addStudent()
          populateStudents.add(rosters.get(select - 1).addStudent());
          rosters.get(select - 1).setStudents(populateStudents); // Add students to roster
        }
        return 1;
      case "2":
        if (teachers != null) {
          for (int i = 0; i < teachers.size(); i++) {
            System.out.println(teachers.get(i).getId() + " - "
                    + teachers.get(i).getName()
                    + ": " + teachers.get(i).toString());
          }
        }
        System.out.println("Teacher Name (Back - 0): ");
        String teacherName = scl.next();
        if (teacherName.equals("0")) {
          return 1;
        }
        System.out.println("Teacher ID: ");
        int teacherID = sct.nextInt();
        teachers.add(new Teacher(teacherName, teacherID));
        return 1;

      case "3":
        // Edit/ add mpGrades
        System.out.println("\nChoose a roster to grade in:");
        // Print roster
        for (int i = 0; i < rosters.size(); i++) {
          System.out.print((i + 1) + " - ");
          rosters.get(i).printRoster();
        }
        int rostChoice = sct.nextInt(); // Roster to grade from
        if (rosters.get(rostChoice - 1).getStudents() == null) {
          System.out.println("\nPlease add the contents of this roster " +
                  "under 'Edit student information.'");
          return 1;
        }
        Roster rost = rosters.get(rostChoice - 1);
        System.out.println("\nChoose a student to grade: ");
        // Print students
        for (int j = 0; j < rost.getStudents().size(); j++) {
          System.out.println((j + 1) + " - " +
                  rost.getStudents().get(j).getName());
        }
        int studIndex = sct.nextInt(); // Student to grade
        // Find the actual student object
        Student student = rost.getStudents().get(studIndex - 1);
        System.out.println("Please enter course to add marking period grade to");
        // Print the courses
        for(int k = 0; k < student.getCourses().size(); k++){
          System.out.println((k + 1) + " - " +
                  student.getCourses().get(k).getCourseName());
        }
        Course course = student.getCourses().get(sct.nextInt() - 1);
        if(course == null){
          System.out.println("Please add courses enrollments for the student");
        } else {
          System.out.println("Please enter grade for student:");
          int grade = sct.nextInt();
          student.addMPGrade(course,grade);
        }
        break;
      case "4":
        System.out.println("Please select a student (Back - 0): ");
        // Print the student options
        for (int i = 0; i < rosters.size(); i++) {
          System.out.print((i + 1) + " - ");
          rosters.get(i).printRoster();
        }
        int rosterCourse = sct.nextInt();
        if (rosterCourse == 0) {
          return 1;
        }
        ArrayList<Student> studentsCourse = rosters.get(rosterCourse - 1).getStudents();
        // If there are no students then ask for the user to add students
        if (studentsCourse == null) {
          System.out.println("Please add the contents of this roster " +
                  "under 'Edit student information.'");
          editSchool();
        } else {
          // Print the students
          for (int i = 0; i < studentsCourse.size(); i++) {
            System.out.println(i + 1 + " - [" + studentsCourse.get(i).getId()
                    + "]" + "  " + studentsCourse.get(i).getName());
          }
          int studentChoice = sct.nextInt();
          System.out.println("Enter enrollments for " +
                  studentsCourse.get(studentChoice - 1).getName()
                  + ", " + studentsCourse.get(studentChoice - 1).getId());
          System.out.print("Number of enrollments: ");
          int courseCountStudent = sct.nextInt();
          System.out.println("Press enter after each course to add it to " +
                  "the enrollment list.");
          // Add course
          for (int i = 0; i < courseCountStudent; i++) {
            String courseName = scl.nextLine();
            studentsCourse.get(studentChoice - 1).courses.add(new Course(courseName));
          }
          studentsCourse.get(studentChoice - 1).listCourses();
        }
        return 1;
      case "5":
        if (teachers.size() == 0) {
          System.out.println("Please add teachers under 'Edit staff " +
                  "information.'");
          return 1;
        }
        for (int i = 0; i < teachers.size(); i++) {
          System.out.println("[" + (i + 1) + "] " +
                  teachers.get(i).getName() + ": " +
                              teachers.get(i).toString());
        }
        System.out.print("Please select a teacher (Back - 0): ");
        int teacherChoice = sct.nextInt();
        if (teacherChoice == 0) {
          return 1;
        }
        System.out.print("Number of enrollments: ");
        int courseCountTeacher = sct.nextInt();
        System.out.println("Press enter after each course to add it to " +
                "the enrollment list.");
        for (int i = 0; i < courseCountTeacher; i++) {
          teachers.get(teacherChoice - 1).courses.add(new Course(scl.nextLine()));
        }
        editSchool();
      case "6":
        System.out.println("\nCurrent Rosters for " + schoolTitle + ": ");
        for (int i = 0; i < rosters.size(); i++) {
          System.out.print((i + 1) + " - ");
          rosters.get(i).printRoster();
        }
        System.out.print("Enter roster title: ");
        String rosterTitle = scl.nextLine();
        System.out.print("Enter roster size: ");
        int rSize = sct.nextInt();
        rosters.add(new Roster(rosterTitle, rSize));
        return 1;
      case "7":
        System.out.println("\nSelect an attendance roster:");
        for (int i = 0; i < rosters.size(); i++) {
          System.out.print((i + 1) + " - ");
          rosters.get(i).printRoster();
        }
        int rosterView = sct.nextInt();
        ArrayList<Student> studentsView = rosters.get(rosterView - 1).getStudents();
        if (studentsView == null) {
          System.out.println("Please add the contents of this roster " +
                  "under 'Edit student information.'");
          return 1;
        }
        if (studentsView != null) {
          for (int i = 0; i < studentsView.size(); i++) {
            System.out.println("[" + studentsView.get(i).getId()
                    + "]" + "  " + studentsView.get(i).getName() +
                    ": " + studentsView.get(i).toString());
          }
        }
        return 1;
      case "8": // This is viewing a students mpGrade
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose the roster of the student:");
        // Give the user a list of students to choose from
        for(int i = 0; i < rosters.size(); i++){
          System.out.print((i + 1) + " - " );
          rosters.get(i).printRoster();
        }
        // Get the user-chosen roster
        int rostNum = scan.nextInt() - 1;
        // Associate the num with a roster
        for(int j = 0; j < rosters.size(); j++){
          if(j == rostNum){
            System.out.println("\nPlease choose a student");
            // Print the students
            for(int l = 0; l < rosters.get(j).getStudents().size(); l++){
              System.out.println((l + 1) + " - " + rosters.get(j).getStudents().get(l).getName());
            }
            // Get the student chosen
            Student stud = rosters.get(j).getStudents().get(scan.nextInt() - 1);
            // If there isn't any courses...
            if(stud.courses.size() < 1){
              // Let them know and return them to the menu list
              System.out.println("\nPlease enter courses for the student");
              return 1;
            } else {
              System.out.println("\nChoose course to view grade:");
              // Print the course options
              for(int k = 0; k < stud.courses.size(); k++){
                System.out.println((k + 1)+ " - " + stud.getCourses().get(k).getCourseName());
              }
              // Catch the course
              int courseNum = scan.nextInt() - 1;
              // Get the course from the int
              stud.getMPGrade(stud.getCourses().get(courseNum)).printGrade();
            }
          }
        }
        return 1;
      case "9":
        System.out.println();
        for(Teacher t : teachers){
          System.out.println("[" + t.id + "]  " + t.name);
        }
      default:
        System.out.println();
        return 1;
    }
    return -1; //This shouldn't happen
  }


  /**
   * Accessor for school rosters
   * @return Current rosters
   */
  public ArrayList<Roster> getRosters() {
    return rosters;
  }

  /**
   * Accessor for school teachers
   * @return Current teachers
   */
  public ArrayList<Teacher> getTeachers() {
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
    for (int i = 0; i < rosters.size(); i++) {
      studentCount += rosters.get(i).getRosterSize();
    }
    return studentCount;
  }

  public void addStudent(Student stud){
    Scanner scan = new Scanner(System.in);
    System.out.println("Please choose roster to add " + stud.getName() + " to");
    for (int i = 0; i < rosters.size(); i++) { // Print the roster
      System.out.print((i + 1) + " - ");
      rosters.get(i).printRoster();
    }
    int select = scan.nextInt(); // Populate a roster object with students
    if (select == 0) { // Send the user back to the main prompt
      editSchool();
    }
    // for i < rosterSize increase i, execute code
    for (int i = 0; i < (rosters.get(select - 1).getRosterSize()); i++) {
      // Set index i of populateStudents to what the user gives in addStudent()
      if(!rosters.get(select - 1).getStudents().contains(stud)) {
        rosters.get(select - 1).addStudent(stud); // Add students to roster
      }
    }
  }

  /**
   * Finds a student from a string of their name
   * @param name The name of the student to search for
   * @return The student looked for, if not found returns null
   */

  /*
  public Student findStudent(String name){
    for(int i = 0; i < rosters.size(); i++){
      for(int x = 0; x < rosters.get(i).getStudents().size(); x++){
        if(rosters.get(i).getStudents().get(x).getName().equalsIgnoreCase(name)){
          return rosters.get(i).getStudents().get(x);
        }
      }
    }
    System.out.println("Student not found");
    return null;
  }

   */
}

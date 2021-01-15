import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Runner acts as a user interface, accessing and
 * setting values from other classes
 * */
public class Runner{
  // Declare these here so we don't overwrite
  // them when we call main(args) again
  public static Student st = new Student();
  public static Grades grades = new Grades();
  public static String[] courseList = new String[0];
  private static final Attendance attend = new Attendance();

  /**
   * Main
   * @param args Command-line arguments
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println();
    System.out.println("Grades - 1 | Modify student information " +
            "- 2 | Attendance - 3 | Quit - 4"); // Prompt the user
    System.out.print("Please select an option: ");
    switch (scan.nextInt()) {
      case 1:
        // If there are courses
        if (courseList.length != 0) {
          System.out.println("Edit Grades - 1 | View Grades - 2");
          switch (scan.nextInt()) {
            // Edit grades
            case 1:
              // Keep the code from exploding if theres nothing
              if (st.enrollments != null) {
                System.out.println();
                st.printRoster(courseList);
                System.out.print("Student ID or name to grade: ");
                if (scan.hasNextInt()) {
                  int idGrade = scan.nextInt();
                  // Get and retain the amount of courses
                  int cCount = grades.courseCount(courseList, idGrade);
                  // Make a new int[] with a length of
                  // the amount of courses taken
                  int[] courseGrades = new int[cCount];
                  // While i < amount of courses
                  for (int i = 0; i < cCount; i++) {
                    System.out.println("Grade for " +
                            st.getStudentName(idGrade) +
                            ", course " + (i + 1) + ": ");
                    // Set qGrade index of i to the users input
                    courseGrades[i] = scan.nextInt();
                    // Set the grades accordingly to values of qGrade
                    grades.setGrades(courseGrades[i], courseList[i],idGrade);
                  }
                } else if(scan.hasNext()){
                  String name = scan.next();
                  int cCount = grades.courseCount(courseList,
                          st.getStudentID(name));
                  int[] courseGrade = new int[cCount];
                  for(int i = 0; i < cCount; i++){
                    System.out.println("Grade for " + name + ", course " +
                            (i + 1) + ": ");
                    courseGrade[i] = scan.nextInt();
                    grades.setGrades(courseGrade[i], courseList[i], st.getStudentID(name));
                  }
                }
              }
              break;

            case 2: // User choose to view grades
              if (grades.validGrades()) {
                st.printRoster(courseList); // Print the roster
                System.out.println();
                System.out.println("Student ID or name to view: ");
                if(scan.hasNextInt()) {
                  grades.viewGrades(scan.nextInt());
                } else {
                  grades.viewGrades(scan.next());
                }
              } else {
                System.out.println("Please add grades first");
              }
              break;
          }
        } else { // If there aren't any courses listed
          // Prompt the user to make courses
          System.out.println("Please add a roster " +
                  "under 'Modify Roster.'");
          main(args); // Return to main "menu"
        }

        main(args);
        break;

      case 2: // Option 2: modify student info
        System.out.println();
        System.out.println("View Roster - 1 | Modify roster - 2 | Back - 3");
        System.out.print("Please select an option: ");
        switch (scan.nextInt()) {
          case 1: // View class roster
            if (courseList.length != 0) {
              st.printRoster(courseList);
            } else {
              System.out.println("Please add a roster under " +
                      "'Modify Roster.'");
              main(args);
            }
            main(args);
            break;

          case 2: // Modify class roster
            if (st.students.length == 0) {
              st.addStudents(args);
              courseList = st.addCourses();
              main(args);

              // If there are already students
            } else {
              System.out.println("Add Students - 1 | Enroll Students " +
                      "in class - 2 | Quit - 3");
              // If you want to add more students or classes
              // after original addition
              switch (scan.nextInt()) {
                case 1: // Add Students
                  st.addStudents(args);
                  break;
                case 2: // Enroll students in class
                  st.printRoster(courseList);
                  System.out.println("Please enter student id or name" +
                          " to enroll: ");
                  if(scan.hasNextInt()) {
                    courseList = st.addCourses(scan.nextInt());
                  } else {
                    courseList = st.addCourses(scan.next());
                  }
                  break;
                default:
                  main(args);
                  break;
              }
            }

            break;
          default:
            main(args);
            break;
        }
      case 3: // This is for attendance
        System.out.println("\nEdit attendance - 1 | View Attendance " +
                "- 2 | Go Back - 3");
        switch(scan.nextInt()){

          case 1: // Edit attendance
            st.printRoster(courseList);
            System.out.println("Please enter students ID: ");
            int studID = scan.nextInt();
            System.out.println("Student Absent - 1 | Student Present - 2");
            switch(scan.nextInt()){
              case 1:
                attend.studentAbsent(studID);
                break;
              case 2:
                attend.studentAttending(studID);
                break;
            }
            break;

            case 2: // Viewing attendance
            if(attend.attendSet()) {
              // TODO: Again, try to get this to work with names
              System.out.println("Please enter students ID: ");
              int id = scan.nextInt();
              int attendance = attend.getAttendance(id);
              System.out.println(st.getStudentName(id) + "'s attendance is " +
                      attendance + " days");
            } else {
              System.out.println("Please add to the students" +
                      " attendance first");
            }
            break;
        }
        main(args);
        break;

    case 4: // User decided to quit
      System.out.println();
      System.exit(0);
      break;
  }
  }

  /**
   * Used to find something in an array, returns -1 if there is an issue/ if
   * the thing to find doesn't exist
   * @param findingIn the int[] of where to search
   * @param toFind the int to look for
   * @return returns the index in the given int[] of where the thing toFind
   * is found
   */
  public static int findInArray(int[] findingIn, int toFind){
    for(int i = 0; i < findingIn.length; i++){
      if(findingIn[i] == toFind){
        return i;
      }
    }
    System.err.println("There was a problem finding the object");
    return -1;
  }

  /**
   * Used to find something in an array, returns -1 if there is an issue/ if
   * the thing to find doesn't exist
   * @param findingIn the String[] to search in
   * @param toFind the String to look for
   * @return The index in findingIn of where toFind was found
   */
  public static int findInArray(String[] findingIn, String toFind){
    for(int i = 0; i < findingIn.length; i++){
      if(findingIn[i].equals(toFind)){
        return i;
      }
    }
    System.err.println("There was an issue finding the object");
    return -1;
  }

  /**
   * Used to find something in an ArrayList, returns -1 if there is an issue/
   * if the thing to find doesn't exist
   * @param findingIn the ArrayList to search in
   * @param toFind the int to look for
   * @return the index in findingIn where toFind was found
   */
  public static int findInArrayList(ArrayList<Integer> findingIn, int toFind){
    for(int i = 0; i < findingIn.size(); i++){
      if(findingIn.get(i).equals(toFind)){
        return i;
      }
    }
    System.err.println("There's an issue finding values in this Array List");
    System.err.println(findingIn + " Found");
    System.err.println(toFind + " looking");
    return -1;
  }
}
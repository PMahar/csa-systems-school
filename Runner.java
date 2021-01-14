import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Runner acts as a user interface, accessing and
 * setting values from other classes
 * */
public class Runner{
  // Declare these here so we don't overwrite them when we call main(args) again
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
    System.out.println("Grades - 1 | Modify student information - 2 | Attendance - 3 | Quit - 4"); // Prompt the user
    System.out.print("Please select an option: ");
    switch (scan.nextInt()) {
      case 1:
        // If there are courses
        if (courseList.length != 0) {
          System.out.println("Edit Grades - 1 | View Grades - 2");
          switch (scan.nextInt()) {
            // Edit grades
            case 1:
              if (st.enrollments != null) { // Keep the code from exploding if theres nothing
                System.out.println();
                st.printRoster(courseList);
                System.out.println();
                System.out.print("Student ID or name to grade: ");
                if (scan.hasNextInt()) {
                  int idGrade = scan.nextInt();
                  int cCount = grades.courseCount(courseList, idGrade); // Get and retain the amount of courses
                  int[] courseGrades = new int[cCount]; // Make a new int[] with a length of the amount of courses taken
                  for (int i = 0; i < cCount; i++) { // While i < amount of courses
                    System.out.println("Grade for " + st.getStudentName(idGrade) +  ", course " + (i + 1) + ": ");
                    courseGrades[i] = scan.nextInt(); // Set qGrade index of i to the users input
                    grades.setGrades(courseGrades[i], courseList[i]); // Set the grades accordingly to values of qGrade
                  }
                } else if(scan.hasNext()){
                  String name = scan.next();
                  int cCount = grades.courseCount(courseList, st.getStudentID(name));
                  int[] courseGrade = new int[cCount];
                  for(int i = 0; i < cCount; i++){
                    System.out.println("Grade for " + name + ", course " +(i + 1) + ": ");
                    courseGrade[i] = scan.nextInt();
                    grades.setGrades(courseGrade[i], courseList[i]);
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
          System.out.println("Please add a roster under 'Modify Roster.'"); // Prompt the user to make courses
          main(args); // Return to main "menu"
        }

        main(args);
        break;

      case 2: // Option 2: modify student info
        System.out.println();
        System.out.println("View Roster - 1 | Modify roster - 2 | Back - 3"); // Prompt the user
        System.out.print("Please select an option: ");
        switch (scan.nextInt()) {
          case 1: // View class roster
            if (courseList.length != 0) {
              st.printRoster(courseList);
            } else {
              System.out.println("Please add a roster under 'Modify Roster.'");
              main(args);
            }
            main(args);
            break;

          case 2: // Modify class roster
            if (st.students.length == 0) {
              st.addStudents(args);
              courseList = st.addCourses();
              main(args);
            } else {
              System.out.println("Add Students - 1 | Enroll Students in class - 2 | Quit - 3");
              switch (scan.nextInt()) { // If you want to add more students or classes after original addition
                case 1: // Add Students
                  st.addStudents(args);
                  break;
                case 2: // Enroll students in class
                  courseList = st.addCourses();
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
        System.out.println("\nEdit attendance - 1 | View Attendance - 2 | Go Back - 3");
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
              System.out.println("Please add to the students attendance first");
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
  public static int findInArrayList(ArrayList findingIn, int toFind){
    for(int i = 0; i < findingIn.size(); i++){
      if(findingIn.get(i).equals(toFind)){
        return i;
      }
    }
    return -1;
  }
}

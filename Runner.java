import java.util.Scanner;

/**
 * Class Runner acts as a user interface, accessing and setting values from other classes
 * */
public class Runner{
  public static Student st = new Student();
  public static Grades grades = new Grades();       // Declare these here so we don't overwrite them
  public static String[] courseList = new String[0]; // When we call main(args) again

  /**
   * Main
   * @param args Command-line arguments
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println();
    System.out.println("Edit grades - 1 | Modify student information - 2 | Quit - 3"); // Prompt the user
    System.out.print("Please select an option: ");
    switch (scan.nextInt()) {
      case 1: // Edit grades
        if (courseList.length != 0) { // If there is a list of courses
          System.out.println("Edit Grades - 1 | View Grades - 2");
          switch (scan.nextInt()) {
            case 1: // Edit grades
              if (st.enrollments != null) { // Keep the code from exploding if theres nothing
                System.out.println();
                st.printRoster(courseList); // Print the roster
                System.out.println();
                // TODO: Add ability to use student name instead of ID
                System.out.print("Student ID to grade: "); // Ask for an ID
                int idGrade = scan.nextInt(); // Retain the ID
                int cCount = grades.courseCount(courseList, idGrade); // Get and retain the amount of courses
                int[] qGrade = new int[cCount]; // Make a new int[] with a length of the amount of courses taken
                for (int i = 0; i < cCount; i++) { // While i < amount of courses
                  System.out.println("Quarter grade for student " + idGrade + ", course " + (i + 1) + ": ");
                  qGrade[i] = scan.nextInt(); // Set qGrade index of i to the users input
                  grades.setGrades(qGrade); // Set the grades accordingly to values of qGrade
                }
              }
              break;

            case 2: // User choose to view grades
              if (grades.validGrades()) {
                st.printRoster(courseList); // Print the roster
                System.out.println();
                System.out.println("Student ID to view: ");
                grades.viewGrades();
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

    default:
    System.out.println();
    System.exit(0);
  }
  }
}

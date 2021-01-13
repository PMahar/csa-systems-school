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

  /**
   * Main
   * @param args Command-line arguments
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println();
    System.out.println("Grades - 1 | Modify student information - 2 | Quit - 3"); // Prompt the user
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
                grades.viewGrades(scan.next());
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

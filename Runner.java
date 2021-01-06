import java.util.Scanner;

/**
 * Class Runner acts as a user interface, accessing and setting values from other classes
 * */
public class Runner{
  public static Student st = new Student();
  public static Grades grades = new Grades();        // Declare these here so we don't overwrite them
  public static String[] courseList = new String[0]; // When we call main(args) again

  /**
   * Main
   * @param args Command-line arguments
   */
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    System.out.println();
    System.out.println("WIP please be patient, some features might not work");
    System.out.println();
    System.out.println("Edit grades - 1 | Modify student information - 2 | Quit - 3"); // Prompt the user
    System.out.print("Please select an option: ");
    switch(scan.nextInt()) {
      case 1: // Edit grades
        if (courseList.length != 0) { // If there is a list of courses
        System.out.println("Edit Grades - 1 | *WIP* View Grades - 2");
        switch(scan.nextInt()){
          case 1: // Edit grades
            System.out.println();
            st.printRoster(courseList); // Print the roster
            System.out.println();
            System.out.print("Student ID to grade: "); // Ask for an ID
            int idGrade = scan.nextInt(); // Retain the ID
            int cCount = grades.courseCount(courseList, idGrade); // Get and retain the amount of courses a student has
            int[] qGrade = new int[cCount]; // Make a new int[] with a length of the amount of courses taken
            for (int i = 0; i < cCount; i++) { // While i < amount of courses
              System.out.println("Quarter grade for student " + idGrade + ", course " + (i + 1) + ": ");
              qGrade[i] = scan.nextInt(); // Set qGrade index of i to the users input
              grades.setGrades(qGrade); // Set the grades accordingly to values of qGrade
            }
            break;
          case 2: // View grades           
            st.printRoster(courseList); // Print the roster
            System.out.println();
            System.out.println("Student ID to view: ");
            idGrade = scan.nextInt();
            grades.viewGrades();
            break;
        }
          } else { // If there aren't any courses listed
            System.out.println("Please add a roster under 'Modify Roster.'"); // Prompt the user to make courses
            main(args); // Return to main "menu"
          }
          main(args);
        break;
      case 2: // Modify student info
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
          case 2: // Modify class roster
            System.out.println("ADD STUDENT:");
            System.out.println("Student IDs (Enter '0' to complete)");
            while (true) { // while true add given student id's to roster
              int id = scan.nextInt();
              while (id != 0) {
                st.newStudent(id);
                id = scan.nextInt();
              }
              break;
            }
            courseList = st.addCourses();
            main(args);
            break;
          default:
            main(args);
        }

      default:
        System.out.println();
        System.exit(0);
      }
  }
}

import java.util.Scanner;

/**
 * Class Runner acts as a user interface, accessing and setting values from other classes
 * */
public class Runner{
  public static Student st = new Student();
  public static Grades grades = new Grades();        // Declare these here so we don't overwrite them
  public static String[] courseList = new String[0]; // when we call main(args) again

  /**
   * Main
   * @param args Command-line arguments
   */

  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    System.out.println();
    System.out.println("Edit grades - 1 | Modify student information - 2 | Quit - 3"); // Prompt the user
    System.out.print("Please select an option: ");
    switch(scan.nextInt()) {
      case 1: // Edit grades
        if (courseList.length != 0) {
        System.out.println();
        st.printRoster(courseList);
        System.out.println();
        System.out.print("Student ID to grade: ");
        int idGrade = scan.nextInt();
        int cCount = grades.courseCount(courseList, idGrade);
        int[] qGrade = new int[cCount];
          for (int i = 0; i < cCount; i++) {
          System.out.println("Quarter grade for student " + idGrade + ", course " + (i + 1) + ": ");
          qGrade[i] = scan.nextInt();
          grades.setGrades(qGrade);
        }
        } else {
          System.out.println("Please add a roster under 'Modify Roster.'");
          main(args);
        }
        main(args);
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
          default:
            main(args);
        }

      default:
        System.out.println();
        System.exit(0);
      }
  }
}

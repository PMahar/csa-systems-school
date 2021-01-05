import java.util.Scanner;

/**
 * The "starter" of this program
 * */

public class Runner{
  public static Student st = new Student();
  public static Grades grades = new Grades();        // Declare these here so we don't overwrite them
  public static String[] courseList = new String[0]; // when we call main(args) again

  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    System.out.println();
    System.out.println("Edit grades - 1 | Modify student information - 2 | Quit - 3"); // Prompt the user
    System.out.print("Please select an option: ");
    switch(scan.nextInt()) {
      case 1: // Edit grades
        grades.gradeUse(); // Send the user to the grade use prompt
        break;
      case 2: // Modify student info
        System.out.println();
        System.out.println("View Roster - 1 | Modify roster - 2 | Back - 3"); // Prompt the user
        System.out.print("Please select an option: ");
        String[] classes = new String[5];
        int cindex = classes.length;
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
            // System.out.println(scan.nextLine().length());
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

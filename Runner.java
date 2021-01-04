import java.util.Scanner;

/**
 * The "starter" of this program
 * */
public class Runner{
  public static void main(String[] args){
    //Issue - Must call main (or otherwise return to here-ish) without
    //reconstructing objects courses, expand, grades, attend, or st
    String[] courses = new String[1];
    String[] expand = new String[courses.length + 1];
    Grades grades = new Grades();
    Student st = new Student();
    int studentCount = 0;
    Scanner scan = new Scanner(System.in);
    System.out.println();
    System.out.println("Edit grades - 1 | Modify student information - 2 | Quit - 3");
    System.out.print("Please select an option: ");
    switch(scan.nextInt()) {
      case 1: // Edit grades
        grades.useGrades(); // Send the user to the grade use prompt
        break;
      case 2: // Modify student info
        System.out.println("View Roster - 1 | Modify roster - 2 | Back - 3");
        System.out.print("Please select an option: ");
        String[] classes = new String[5];
        int cindex = classes.length;
        switch (scan.nextInt()) {
          case 1: // View class roster
            st.printRoster();
 	    break;
          case 2: // Modify class roster
            System.out.println("ADD STUDENT:");
            System.out.print("Student IDs (Enter '0' to complete)");
            //System.out.println(scan.nextLine().length());
            while (true) { // while true add given student id's to roster
              int id = scan.nextInt();
              while (id != 0) {
                st.newStudent(id);
                id = scan.nextInt();
              }
              break;
            }
            st.printRoster(); // Print the new roster
            String[] courseList = st.addCourses();
            main(args);
          default:
            main(args);
        }
	break;
          
/*        case 3:
          System.out.println("View Roster - 1 | Modify roster - 2 | Modify enrolled courses - 3 | Back - 4");
          System.out.print("Please select an option: ");
          switch(scan.nextInt()) {
            case 2:
              System.out.println("ADD STUDENT:");
              System.out.print("Student ID: ");
              int id = scan.nextInt();
                studentCount++;
                System.out.println();
                System.out.println("Enrolled Courses (Enter empty line to complete)");
                //Create a new array for every entry, duplicate previous elements
                String nxtln = scan.nextLine();
                for (int x = 0; x < 4; x++) {
                  int items = 0;
                  //Copy between expand and students ONLY if students[items] won't be 0
                  if (items != 0) {
                    courses = new String[items + 1];
                    for (int i = 0; i < courses.length; i++) {
                      //Backup expand to courses, then get expand ready for next iteration
                      courses[i] = expand[i];
                      expand = new String[courses.length + 1];
                    }
                  }
                  items++;
                  courses[items - 1] = scan.nextLine();
                  for (int i = 0; i < courses.length; i++) {
                    //Backup courses to expand, then reconstruct courses in next iteration
                    expand[i] = courses[i];
                  }

                  int classIndex = courses.length;
                  Student st = new Student(id, classIndex, courses);
                  st.viewRoster();
//                main(cliArgs);
              }
            default:
              main(cliArgs);
          }
          */
        default:
          System.out.println();
          System.exit(0);
      }
      System.out.println("Continue - 1 | Quit - 2");
      switch(scan.nextInt()) {
	case 1:

	  break;
	default: 
	System.exit(0);
 	  break;
	default:
	  System.exit(0);
	  break;
	}
  }
}

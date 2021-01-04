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
    Attendance attend = new Attendance();
    Student st = new Student();
    int studentCount = 0;
    Scanner scan = new Scanner(System.in);
    System.out.println();
    System.out.println("Edit attendance - 1 | Edit grades - 2 | Modify student information - 3 | Quit - 4");
    System.out.print("Please select an option: ");
      switch(scan.nextInt()) {
        // TODO: add code here to continue to attendance prompt
        case 1:
          attend.Attendance();

        case 2:
          System.out.println("Do you want to change students grade or view their grades");
          if (scan.next().contains("iew")) {
            System.out.println("What is the id of the student you would like to view");
            grades.checkGrades(scan.nextInt());
          } else if (scan.next().contains("hange")) {
            grades.gradeStuds();
          }
        case 3:
          System.out.println("View Roster - 1 | Modify roster - 2 | Back - 3");
          System.out.print("Please select an option: ");
          String[] classes = new String[5];
          int cindex = classes.length;
          switch (scan.nextInt()) {
            case 1:
              st.printRoster();

            case 2:
              System.out.println("ADD STUDENT:");
              System.out.print("Student IDs (Enter '0' to complete)");
              //System.out.println(scan.nextLine().length());
              while (true) {
                int id = scan.nextInt();
                while (id != 0) {
                  st.newStudent(id);
                  id = scan.nextInt();
                }
                break;
              }
              st.printRoster();
              String[] courseList = st.addCourses();
              main(args);

            default:
              main(args);
          }


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
      System.out.println("Would you like to continue?");
      if (scan.next().contains("es")) {
        System.out.println("Are you conducting attendance, or are you dealing with grades?");
        if (scan.next().contains("ttendance")) {
          // TODO: add code here to continue to attendance prompt
          attend.Attendance();
        } else /*if(scan.next().contains("rades"))*/ {
          System.out.println("Do you want to change students grade or view their grades");
          if (scan.next().contains("iew")) {
            System.out.println("What is the id of the student you would like to view");
            grades.checkGrades(scan.nextInt());
          } else if (scan.next().contains("hange")) {
            grades.gradeStuds();
          }
        }
      } else {
        System.exit(0);
      }
  }
}

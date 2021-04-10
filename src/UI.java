import java.util.Scanner;
import java.util.ArrayList;

/**
 * Entry class, allows mutation of data fields through modifying 'school' objects
 * and their associated properties
 */
public class UI {
  private String district;
  private static ArrayList<School> schools;

  /**
   * Entry point method, constructs all necessary objects on startup as well
   * as manipulates 'school' objects
   * @param args Command-line arguments
   */
  public static void main(String[] args) {
    UI run = new UI();
    Scanner sct = new Scanner(System.in);
    Scanner scl = new Scanner(System.in);
    FileHandling write = new FileHandling();
    //saveInfo();
    // Initialize the program for first-time use
    if (schools == null) {
      run.setup();
    }
    for (int i = 0; i < schools.size(); i++) {
      System.out.println("Schools:");
      System.out.println("[" + (i + 1) + "] " + schools.get(i).getSchoolTitle() +
              " - " + schools.get(i).getRosters().size() +
              " attendance rosters with " + schools.get(i).totalStudents() +
              " students and " + schools.get(i).getTeachers().size() +
              " faculty members");
    }
    System.out.print("Select a school to modify (Add school - a | Quit - 0): ");
    String uiSelect = scl.nextLine();
    switch(uiSelect.charAt(0)) {
      case '0':
        System.exit(0);
      case 'a':
        System.out.print("School title: ");
        String schoolTitle = scl.nextLine();
        System.out.print("Roster title: ");
        String rosterTitle = scl.nextLine();
        System.out.print("Roster size: ");
        int rosterSize = sct.nextInt();
        ArrayList<Roster> addRoster = new ArrayList<>();
        addRoster.add(new Roster(rosterTitle, rosterSize));
        ArrayList<Teacher> addTeacher = new ArrayList<>();
        schools.add(new School(addRoster, addTeacher, schoolTitle));
        main(args);
      default:
        int schoolSelect = Integer.parseInt(uiSelect);
        int runtime = schools.get(schoolSelect - 1).editSchool();
        while (runtime == 1) {
          //Check status and keep running until it returns 0 (save and exit)
          runtime = schools.get(schoolSelect - 1).editSchool();
        }
        write.save(run.district, schools);
        System.out.println("saved");
        main(args);
    }
  }

  /**
   * Prepares the program for first-time use, and is implemented
   * in the absence of existing data
   */
  public void setup() {
    Scanner scl = new Scanner(System.in); //line
    Scanner sct = new Scanner(System.in); //token
    System.out.print("Initial setup - Enter district name: ");
    this.district = scl.nextLine();
    System.out.print("                Enter initial school name: ");
    String initSchool = scl.nextLine();
    System.out.print("                Enter roster title: ");
    String rosterTitle = scl.nextLine();
    System.out.print("                Enter roster size: ");
    int rSize = sct.nextInt();
    ArrayList<Roster> initRosters = new ArrayList<>();
    // Assign the first index in array rosters to a new roster with
    // given title and size
    initRosters.add(new Roster(rosterTitle, rSize));
    ArrayList<Teacher> initTeachers = new ArrayList<>();
    schools = new ArrayList<>();
    schools.add(new School(initRosters, initTeachers, initSchool));
    //saveInfo();
  }

  /**
   * Saves the info from a file and starts a FileHandling constructor
   */
  /*
  public static void saveInfo(){
    // Initialize FileHandling
    FileHandling fh = new FileHandling(".\\information.txt"); //Use . so the class can be run from anywhere
    ArrayList<Roster> rosters = new ArrayList<>(Arrays.asList(new Roster(fh.getRostName(), 3)));
    // TODO: Add district
    School school = new School(rosters, new ArrayList<Teacher>(),
            fh.getSchoolName());
    for(int i = 0; i < fh.getStudents().size(); i++){
      if(!fh.getStudents().get(i).getName().contains(" ")) {
        school.addStudent(fh.getStudents().get(i));
      }
    }
    schools.add(school);
  }

   */
}

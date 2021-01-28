import java.util.Scanner;

/**
 * This is where the interfacing is between the user
 * and the code
 */
public class UI {
  private String district;
  private static School[] schools;

  public static void main(String[] args) {
    UI run = new UI();
    Scanner sct = new Scanner(System.in);
    Scanner scl = new Scanner(System.in);
    // Initialize the program for first-time use
    if (schools == null) {
      run.setup();
    }
    for (int i = 0; i < schools.length; i++) {
      System.out.println("Schools:");
      System.out.println("[" + (i + 1) + "] " + schools[i].getSchoolTitle() +
              " - " + schools[i].getRosters().length +
              " attendance rosters with " + schools[i].totalStudents() +
              " students and " + schools[i].getTeachers().length +
              " faculty members");
    }
    System.out.print("Select a school to modify (Add school - a | Quit - 0): ");
    String uiSelect = scl.nextLine();
    switch(uiSelect.charAt(0)) {
      case 0:
        System.exit(0);
      case 'a':
        System.out.print("School title: ");
        String schoolTitle = scl.nextLine();
        System.out.print("Roster title: ");
        String rosterTitle = scl.nextLine();
        System.out.print("Roster size: ");
        int rosterSize = sct.nextInt();
        Roster[] addRoster = new Roster[1];
        addRoster[0] = new Roster(rosterTitle, rosterSize);
        Teacher[] addTeacher = new Teacher[1];
        run.addSchool(addRoster, addTeacher, schoolTitle);
        main(args);
      default:
        int schoolSelect = Integer.parseInt(uiSelect);
        schools[schoolSelect - 1].editSchool();
        main(args);
    }
  }

  /**
   * Prepares the program for first-time use, and is implemented
   * in the absence of existing data
   */
  private void setup() {
    Scanner scl = new Scanner(System.in); //line
    Scanner sct = new Scanner(System.in); //token
    System.out.print("Initial setup - Enter district name: ");
    String districtName = scl.nextLine();
    setDistrict(districtName);
    System.out.print("                Enter initial school name: ");
    String initSchool = scl.nextLine();
    System.out.print("                Enter roster title: ");
    String rosterTitle = scl.nextLine();
    System.out.print("                Enter roster size: ");
    int rSize = sct.nextInt();
    Roster[] initRosters = new Roster[1];
    // Assign the first index in array rosters to a new roster with
    // given title and size
    initRosters[0] = new Roster(rosterTitle, rSize);
    Teacher[] initTeachers = new Teacher[1];
    schools = new School[1];
    schools[0] = new School(initRosters, initTeachers, initSchool);
  }


  public void setDistrict(String district) {
    this.district = district;
  }

  public void addSchool(Roster[] rosters, Teacher[] teachers, String schoolTitle) {
    School[] schoolsBak = new School[UI.schools.length + 1];
    for (int i = 0; i < schools.length; i++) {
      schoolsBak[i] = schools[i];
    }
    schools = new School[schoolsBak.length];
    for (int i = 0; i < schoolsBak.length; i++) {
      schools[i] = schoolsBak[i];
    }
    schools[schools.length - 1] = new School(rosters, teachers, schoolTitle);
  }

}

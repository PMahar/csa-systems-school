import java.util.Scanner;
import java.util.ArrayList;

/**
 * Entry class, allows mutation of data fields through modifying 'school' objects
 * and their associated properties
 */
public class UI {
  private District district;

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
    ArrayList<String> dirContents = write.dirContent();
    // Initialize the program for first-time use
    if (dirContents.size() == 0) {
      run.setup();
    } else {
      System.out.println("Found " + dirContents.size() + " district files in application directory:");
      for (int i = 0; i < dirContents.size(); i++) {
        System.out.println("[" + (i + 1) + "] " + dirContents.get(i));
      }
      System.out.print("Choose file to load ([#]) | Make new (n) | Quit (0): ");
      String setupDialog = scl.nextLine();
      if (setupDialog.toLowerCase().charAt(0) == 'n') {
        run.setup();
      } else if (setupDialog.toLowerCase().charAt(0) == '0') {
        System.exit(0);
      } else {
        char setupChar = setupDialog.charAt(0);
        System.out.println("load ");
        write.load(dirContents.get(Character.getNumericValue(setupChar) - 1));
      }

    }

    for (int i = 0; i < run.district.getSchools().size(); i++) {
      System.out.println("Schools:");
      System.out.println("[" + (i + 1) + "] " + run.district.getSchools().get(i).getSchoolTitle() +
          " - " + run.district.getSchools().get(i).getRosters().size() +
          " attendance rosters with " + run.district.getSchools().get(i).totalStudents() +
          " students and " + run.district.getSchools().get(i).getTeachers().size() +
          " faculty members");
    }
    System.out.print("Select a school to modify (Add school - a | Quit - 0): ");
    String uiSelect = scl.nextLine();
    switch (uiSelect.charAt(0)) {
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
        run.district.getSchools().add(new School(addRoster, addTeacher, schoolTitle));
        write.save(run.district.getDistrictTitle(), run.district);
        main(args);
      default:
        int schoolSelect = Integer.parseInt(uiSelect);
        int edit = run.district.getSchools().get(schoolSelect - 1).editSchool();
        while (edit == 1) {
          //Check status and keep running until it returns 0 (save and exit)
          edit = run.district.getSchools().get(schoolSelect - 1).editSchool();
          write.save(run.district.getDistrictTitle(), run.district); //Auto-save so the user (or developer)
        }                                                            //doesn't rage quit when there's an error
        write.save(run.district.getDistrictTitle(), run.district);
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
    this.district = new District(scl.nextLine());
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
    district.getSchools().add(new School(initRosters, initTeachers, initSchool));
  }
}

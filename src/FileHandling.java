import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with the main searching for, and
 * editing of the file used to store information
 * In a scenario where it's more than one person using
 * this program we would instead store this file on a
 * server/ network somewhere
 */
public class FileHandling {
  private File file = null;
  private Scanner fileScan;
  private String districtName = "";
  private String schoolName = "";
  private String rostName = "";
  private int rostSize;
  private ArrayList<Student> students = new ArrayList<>();

  /*
  public FileHandling(String filePath) {
    this.file = new File(filePath);
    init();
  }
   */

  /**
   * Used at the start of the program or if loading
   * new information from separate file
   */
  /*
  private void init() {
    //TODO: Change this extension!!!
    try {
      if (file.createNewFile()) {
        System.out.println("File not found, creating new file");
      } else {
        Scanner scan = new Scanner(file);
        // TODO: Find a good delimiter to use
        scan.useDelimiter("\\*");
        // Set up the given variables
        for(int i = 0; i < file.length(); i++){
          switch(i){
            case 0:
              districtName = scan.next();
              break;
            case 1:
              schoolName = scan.next();
              break;
            case 2:
              rostName = scan.next();
              break;
            case 3:
              scan.next();
              // TODO: Figure out how to do rostSize
              break;
            default:
              String name = " ";
              int id = 0;
              while(scan.hasNext()){
                name = scan.next();
                id = scan.nextInt();
                if(!name.isBlank()){
                  students.add(new Student(name, id));
                  name = " ";
                }
              }
              break;
          }
        }
      }
    } catch (IOException e) {
      System.err.println("IOException caught, advise administrator" + "\n" + e);
    }
    if(!file.canRead()){
      System.err.println("Can not read the file. Please advise administrator");
    }
  }

   */

  public void save(String filename, ArrayList<School> schools) {
    /*
    A standard file will look like this:
    {
      property{
        attribute
        attribute
        attribute
      }
    }
     */
    this.file = new File(".\\" + filename + ".sch");
    //Use our own extension so it doesn't mess with the user's defaults
    try(PrintWriter pw = new PrintWriter(this.file)) {
      pw.print("{\n" + //Header
               " schools{\n");
      //Open brackets
      for (int s = 0; s < schools.size(); s++) { //Open schools
        pw.println("  " + schools.get(s).getSchoolTitle() + "{");
        //        schoolName{
        for (int r = 0; r < schools.get(s).getRosters().size(); r++) { //Open rosters
         pw.println("   " + schools.get(s).getRosters().get(r).getTitle() + "{");
        //         rosterName{
          for (int st = 0; st < schools.get(s).getRosters().get(r).getStudents().size(); st++) { //Open students
            pw.println("    " + schools.get(s).getRosters().get(r).getStudents().get(st).getName() + "," +
                    schools.get(s).getRosters().get(r).getStudents().get(st).getId());
        //          studentName{
          }



          //Close brackets (if they exist)
          if (schools.get(s).getRosters().get(r).getStudents().size() != 0)
          pw.println("   }"); //Close students
        }
        if (schools.get(s).getRosters().size() != 0)
        pw.println("  }"); //Close rosters
      }
      pw.println(" }"); //Close schools
      pw.print("}"); //Footer
    } catch (IOException e) {
      System.out.println("Couldn't write file. Maybe access is denied?");
      System.err.println(e);
    }
  }

  public String getDistrictName(){
    return districtName;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public String getRostName() {
    return rostName;
  }

  public int getRostSize() {
    return rostSize;
  }

  public ArrayList<Student> getStudents() {
    return students;
  }
}

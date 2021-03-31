import java.io.File;
import java.io.IOException;
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

  public FileHandling(String filePath) {
    this.file = new File(filePath);
    init();
  }

  /**
   * Used at the start of the program or if loading
   * new information from separate file
   */
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
      System.err.println("IOException caught, advise administrator");
    }
    if(!file.canRead()){
      System.out.println("Can not read the file. Please advise administrator");
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

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class deals with the main searching for, and
 * editing of the file used to store information
 * In a scenario where it's more than one person using
 * this program we would instead store this file on a
 * server/ network somewhere
 */
public class FileHandling {
  private final String fileName = "src\\information.txt";

  private File file = null;
  private String districtName = "";
  private String schoolName = "";
  private String rostName = "";
  private String rostSize = "";
  /**
   * Used at the start of the program or if loading
   * new information from separate file
   */
  public void init() {
    //TODO: Change this extension!!!
    file = new File(fileName);
    try {
      if (file.createNewFile()) {
        System.out.println("File not found, creating new file");
      } else {
        Scanner scan = new Scanner(file);
        // TODO: Find a good delimiter to use
        scan.useDelimiter(",");
        districtName = scan.next();
        schoolName = scan.next();
        rostName = scan.next();
        rostSize = scan.next() + "";
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

  public String getRostSize() {
    return rostSize;
  }

  public boolean hasSchoolInfo(){
    if(getDistrictName() != null && getSchoolName() != null
            && getRostName() != null && getRostSize() != null){
      return true;
    }
    return false;
  }

  public File getFile() {
    return file;
  }
}

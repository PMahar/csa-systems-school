import java.util.ArrayList;
import java.util.UUID;

/**
 * District objects act as a parent object to schools and maintain them in a collection
 */
public class District {
  private String districtTitle;
  private ArrayList<School> schools = new ArrayList<>();
  private String districtUUID;

  public District(String districtTitle) {
    this.districtTitle = districtTitle;
    this.districtUUID = UUID.randomUUID().toString();
  }

  public District(String districtTitle, String districtUUID) {
    this.districtTitle = districtTitle;
    this.districtUUID = districtUUID;
  }

  /**
   * Overloaded constructor of a District object
   * @param districtTitle String title of the district
   * @param schools ArrayList of existing schools
   * @param districtUUID Unique identifier, if already present in a file
   */
  public District(String districtTitle, ArrayList<School> schools, String districtUUID) {
    this.districtTitle = districtTitle;
    this.schools = schools;
    this.districtUUID = districtUUID;
  }

  public String getDistrictTitle() {
    return this.districtTitle;
  }

  public String getDistrictUUID() {
    return this.districtUUID;
  }

  public ArrayList<School> getSchools() {
    return this.schools;
  }

  public void setSchools(ArrayList<School> schools) {
    this.schools = schools;
  }

}

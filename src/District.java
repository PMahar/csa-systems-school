import java.util.ArrayList;
import java.util.UUID;

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

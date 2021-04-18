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

  public String getDistrictTitle() {
    return this.districtTitle;
  }

  public String getDistrictUUID() {
    return this.districtUUID;
  }

  public ArrayList<School> getSchools() {
    return this.schools;
  }

  public void addSchool(ArrayList<Roster> rosters, ArrayList<Teacher> teachers, String schoolTitle) {
    this.schools.add(new School(rosters, teachers, schoolTitle));
  }

}

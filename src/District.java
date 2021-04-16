import java.util.ArrayList;

public class District {
  private String districtTitle;
  private ArrayList<School> schools = new ArrayList<>();

  public District() {

  }

  public District(String districtTitle) {
    this.districtTitle = districtTitle;
  }

  public String getDistrictTitle() {
    return this.districtTitle;
  }

  public ArrayList<School> getSchools() {
    return this.schools;
  }

  public void addSchool(ArrayList<Roster> rosters, ArrayList<Teacher> teachers, String schoolTitle) {
    this.schools.add(new School(rosters, teachers, schoolTitle));
  }

}

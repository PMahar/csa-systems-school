/**
 * Teacher objects will represent faculty members within the school, with fields for their name,
 * numerical ID, and courses taught
 */
public class Teacher extends MemberSuper {
// teacher requires course objects

  /**
   * Constructor of teacher object
   * @param name Name of teacher
   * @param id Numerical ID of teacher
   */
  public Teacher(String name, int id) {
    super(name, id);
  }

  public Teacher(String name, int id, String memberUUID) {
    super(id, name, memberUUID);
  }
}

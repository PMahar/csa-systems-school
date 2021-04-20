/**
 * Teacher objects will represent faculty members within the school, with fields for their name,
 * numerical ID, and courses taught
 */
public class Teacher extends MemberSuper {
// teacher requires course objects

  public Teacher(String name, int id) {
    super(name, id);
  }

  /**
   * Overloaded constructor of a teacher object
   * @param name Name of teacher
   * @param id Numerical ID of teacher
   * @param memberUUID Unique identifier of a teacher, if present in a file
   */
  public Teacher(String name, int id, String memberUUID) {
    super(id, name, memberUUID);
  }
}

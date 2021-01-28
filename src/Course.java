
/**
 * Course objects represent class enrollments, and are attributed to both
 * Teacher and Student objects
 */
public class Course {
  private String courseName;

  /**
   * Constructs a new Course object with a given course title
   * @param courseName Title of course to attribute
   */
  public Course(String courseName){
    this.courseName = courseName;
  }

  /**
   * Accessor for courseName field
   * @return Title of course
   */
  public String getCourseName() {
    return courseName;
  }

}

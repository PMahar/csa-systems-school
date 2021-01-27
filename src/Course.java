
/**
 * This class will add students, remove students,
 * to a course, this is for singular course, not multiple
 */
public class Course {
  private String courseName;

  /**
   * This is a constructor of course, it takes the course
   * name, It's meant to only handle one course at a time,
   * use this if you only have the course name and not who teaches
   * it
   * @param courseName The name of the course
   */
  public Course(String courseName){
    this.courseName = courseName;
  }



  public String getCourseName() {
    return courseName;
  }

}

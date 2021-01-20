/**
 * This class will add students, remove students,
 * to a course, this is for singular course, not multiple
 */
public class Course {
  private String courseName;
  private String[] students;
  private String[] teachers;

  /**
   * This is a constructor of course, it takes multiple
   * students and multiple teachers, this is if the course is bulk
   * created and has all desired variables
   * If there is only one teacher then provide a String[] with
   * one object
   * @param courseName the name of the course
   * @param students String[] of students attending the course
   * @param teachers String[] of teachers teaching/ assisting
   *                 with the class
   */
  public Course(String courseName, String[] students, String[] teachers){
    this.courseName = courseName;
    this.students = students;
    this.teachers = teachers;
  }

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

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String[] getStudents() {
    return students;
  }

  public void setStudents(String[] students) {
    this.students = students;
  }

  public String[] getTeachers() {
    return teachers;
  }

  public void setTeachers(String[] teachers) {
    this.teachers = teachers;
  }
}

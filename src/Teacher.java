import java.util.ArrayList;

/**
 * This class handles teacher/ teaching activities
 * This class is meant to deal with a single teacher at
 * a time
 */
public class Teacher {
private String teacherName;
private int teacherID;
private ArrayList<Course> courses = new ArrayList<Course>();
// TODO: Create a method that affiliates a teacher with courses

  public Teacher(String name, int id) {
    teacherName = name;
    teacherID = id;
  }

  /**
   * Adds multiple courses to teacher's list of courses
   * @param courses The courses to add
   */
  public void addCourses(ArrayList<Course> courses) {
    this.courses.addAll(courses);
  }

  /**
   * Adds a single course to teacher's list of courses
   * @param course The course to add
   */
  public void addCourse(Course course){
    this.courses.add(course);
  }
}

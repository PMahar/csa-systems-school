import java.util.ArrayList;

/**
 * Teacher objects will represent faculty members within the school, with fields for their name,
 * numerical ID, and courses taught
 */
public class Teacher implements SchoolMember {
private String teacherName;
private int teacherID;
private ArrayList<Course> courses;
// teacher requires course objects

  /**
   * Constructor of teacher object
   * @param name Name of teacher
   * @param id Numerical ID of teacher
   */
  public Teacher(String name, int id) {
    teacherName = name;
    teacherID = id;
  }

  /**
   * Adds multiple courses to teacher's list of courses
   * @param courseName The courses to add
   */
  public void addCourses(String courseName) {
    courses.add(new Course(courseName));
  }

  /**
   * Concatenate courses to a string for printing
   * @return String-formatted courses
   */
  public String catCourses() {
    String courseConcat = "";
    if (courses == null) {
      courseConcat = "(None)";
      courses = new ArrayList<>();
      return courseConcat;
    }
    for (int i = 0; i < courses.size() - 1; i++) {
      courseConcat += (courses.get(i).getCourseName() + ", ");
    }
    courseConcat += courses.get(courses.size() - 1).getCourseName();
    return courseConcat;
  }

  /**
   * Accessor for field teacherName
   * @return Name of selected teacher
   */
  public String getTeacherName() {
    return teacherName;
  }

  @Override
  /**
   * Accessor for field teacherID
   * @return Numerical ID of selected teacher
   */
  public int getId() {
    return teacherID;
  }

  @Override
  public String getName() {
    return teacherName;
  }

  @Override
  public ArrayList<Course> getCourses() {
    return courses;
  }
}


/**
 * Teacher objects will represent faculty members within the school, with fields for their name,
 * numerical ID, and courses taught
 */
public class Teacher implements SchoolMember {
private String teacherName;
private int teacherID;
private Course[] courses;
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
    if (courses == null) {
      courses = new Course[1];
      courses[0] = new Course(courseName);
      return;
    }
    Course[] coursesBak = new Course[courses.length + 1];
    for (int i = 0; i < courses.length; i++) {
      coursesBak[i] = courses[i];
    }
    courses = new Course[coursesBak.length];
    for (int i = 0; i < coursesBak.length; i++) {
      courses[i] = coursesBak[i];
    }
    courses[courses.length - 1] = new Course(courseName);
  }

  /**
   * Concatenate courses to a string for printing
   * @return String-formatted courses
   */
  public String catCourses() {
    String courseConcat = "";
    if (courses == null) {
      courseConcat = "(None)";
      return courseConcat;
    }
    for (int i = 0; i < courses.length - 1; i++) {
      courseConcat += (courses[i].getCourseName() + ", ");
    }
    courseConcat += courses[courses.length - 1].getCourseName();
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
  public Course[] getCourses() {
    return courses;
  }
}

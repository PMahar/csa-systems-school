import java.util.ArrayList;

/**
 * This class deals with adding courses, getting mpGrades,
 * and other *singular* student activities.
 */
public class Student {
  // Every Student object will require an individual name,
  // ID, and list of enrolled courses
  // (as well as any other objects like lunch requirement)
  int studentID;
  String studentName;
  private ArrayList<Course> courses = new ArrayList<>();
  // TODO: Create a method to add courses to student

  // Add an element to the student array

  // Constructor to instantiate student object from psvm
  public Student(String name, int id) {
    this.studentName = name;
    this.studentID = id;
  }

  /**
   * This adds multiple courses at once to a student's course
   * list
   * @param courses The courses to add
   */
  public void addCourses(ArrayList<Course> courses){
    this.courses.addAll(courses);
  }

  /**
   * This adds a single course to a student's course
   * list
   * @param course The course to add
   */
  public void addCourse(Course course){
    this.courses.add(course);
  }

  public int getStudentID() {
    return studentID;
  }

  public void setStudentID(int studentID) {
    this.studentID = studentID;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public ArrayList<Course> getCourses() {
    return courses;
  }

  public void setCourses(ArrayList<Course> courses) {
    this.courses = courses;
  }
}

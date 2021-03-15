import java.util.ArrayList;
import java.util.HashMap;

/**
 * Student objects represent students within a school, with names, numerical IDs,
 * attended courses, and marking period grades
 */
public class Student implements SchoolMember{
  // Every Student object will require an individual name,
  // ID, and list of enrolled courses
  // (as well as any other objects like lunch requirement)
  private int id;
  private String name;
  private ArrayList<Course> courses;
  private HashMap<Course, MPGrade> mpGrades = new HashMap<>();
  //student will require both mpgrade and course objects

  // Add an element to the student array

  // Constructor to instantiate student object from psvm

  /**
   * Constructor of student object
   * @param name Name of student
   * @param id Numerical ID of student
   */
  public Student(String name, int id) {
    this.name = name;
    this.id = id;
  }

  /**
   * Add student courses into an expanding array
   * @param courseName String representing a course name
   */
  @Override
  public void addCourses(String courseName) {
    courses.add(new Course(courseName));
  }

  /**
   * Accessor for length of courses array
   * @return Integer representing number of enlisted courses
   */
  public int getCourseCount() {
    return this.courses.size();
  }

  /**
   * Utility method for printing contents of course array to console
   */
  public void listCourses(){
    System.out.println(this.getName() + "'s courses are: ");
    for (Course cours : courses) {
      System.out.print(cours.getCourseName() + ", ");
    }
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
    for (int i = 0; i < courses.size() - 1; i++) {
      courseConcat += (courses.get(i).getCourseName() + ", ");
    }
    courseConcat += courses.get(courses.size() - 1).getCourseName();
    return courseConcat;
  }

  /**
   * Accessor for student ID
   * @return Selected student's numerical identifier
   */
  public int getId() {
    return id;
  }

  /**
   * Accessor for student name
   * @return Selected student's name
   */
  public String getName() {
    return name;
  }

  /**
   * Accessor for selected student's enrollment array
   * @return Student's course array
   */
  public ArrayList<Course> getCourses(){
    return courses;
  }

  /**
   * Add marking period grades into an expanding array
   * @param course Course associated with grade
   * @param grade Numerical marking period grade
   */
  public void addMPGrade(Course course, int grade){
    mpGrades.put(course,new MPGrade(course, this, grade));
  }


  /**
   * Gets the mpGrade object of a specified course from the student
   * @param course This is the course to get the marking period object of
   * @return the MPGrade object
   */
  public MPGrade getMPGrade(Course course){
    if(mpGrades.containsKey(course)){
      return mpGrades.get(course);
    }
    return null;
  }
}

import java.util.ArrayList;
import java.util.HashMap;

public class MemberSuper implements SchoolMember{
  int id;
  String name;

  ArrayList<Course> courses = new ArrayList<>();
  HashMap<Course, MPGrade> mpGrades = new HashMap<>();

  @Override
  public int id() {
    return this.id();
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public ArrayList<Course> courses() {
    return this.courses;
  }

  @Override
  public String toString() {
    String courseConcat = "";
    if (this.courses.size() == 0) {
      courseConcat = "(None)";
      return courseConcat;
    }
    for (int i = 0; i < this.courses.size() - 1; i++) {
      courseConcat += (this.courses.get(i).getCourseName() + ", ");
    }
    courseConcat += this.courses.get(this.courses.size() - 1).getCourseName();
    return courseConcat;
  }

  MemberSuper(String name, int id) {
    this.name = name;
    this.id = id;
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

  /**
   * Utility method for printing contents of course array to console
   */
  public void listCourses(){
    System.out.println(this.getName() + "'s courses are: ");
    for (Course course : courses) {
      System.out.print(course.getCourseName() + ", ");
    }
  }

  /**
   * Gets the mpGrade object of a specified course from the student
   * @param course This is the course to get the marking period object of
   * @return the MPGrade object
   */
  public MPGrade getMPGrade(Course course){
    if(mpGrades != null && mpGrades.containsKey(course)){
      return mpGrades.get(course);
    } else if(mpGrades == null){
      System.out.println("You need to add marking period grades");
    }
    return null;
  }

  public void addMPGrade(Course course, int grade){
    mpGrades.put(course,new MPGrade(course, this, grade));
  }

}

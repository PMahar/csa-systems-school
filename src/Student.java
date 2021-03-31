
/**
 * Student objects represent students within a school, with names, numerical IDs,
 * attended courses, and marking period grades
 */
public class Student extends MemberSuper {
  //student will require both mpgrade and course objects

  // Add an element to the student array

  // Constructor to instantiate student object from psvm

  /**
   * Constructor of student object
   * @param name Name of student
   * @param id Numerical ID of student
   */
  public Student(String name, int id) {
    super(name, id);
  }

  /**
   * Gets the mpGrade object of a specified course from the student
   * @param course This is the course to get the marking period object of
   * @return the MPGrade object
   */
  public MPGrade getMPGrade(Course course){
    if(mpGrades != null && mpGrades.containsKey(course)){
      return mpGrades.get(course);
    }
    System.out.println("You need to add marking period grades");
    return null;
  }
}

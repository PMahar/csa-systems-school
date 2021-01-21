
/**
 * This class deals with adding courses, getting mpGrades,
 * and other *singular* student activities.
 */
public class Student {
  // Every Student object will require an individual name,
  // ID, and list of enrolled courses
  // (as well as any other objects like lunch requirement)
  private int studentID;
  private String studentName;
  // TODO: Figure out a better length
  private Course[] courses = new Course[100];
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
  public void addCourses(Course[] courses){
    for(int i = 0; i < courses.length; i++){
      for(int j = 0; j < this.courses.length; j++){
        if(this.courses[i] == null){
          this.courses[i] = courses[j];
        }
      }
    }
  }

  /**
   * This adds a single course to a student's course
   * list
   * @param course The course to add
   */
  public void addCourse(Course course){
    for(int i = 0; i < this.courses.length; i++){
      if(courses[i] == null){
        this.courses[i] = course;
      }
    }
  }

  /**
   * Checks if a student is valid
   * @return True if the student is valid
   */
  public boolean isValid(){
    if (!this.getStudentName().isBlank()){
      return true;
    } else {
      System.err.println("Student not found");
      return false;
    }
  }

  public void listCourses(){
    System.out.println(this.getStudentName() + "'s courses are: ");
    for(int i = 0; i < courses.length; i ++){
      System.out.print(courses[i] + ", ");
    }
  }

  public void changeName(String name){
    if(this.isValid()){
      this.studentName = name;
    }
  }

  public int getStudentID() {
    return studentID;
  }

  public String getStudentName() {
    return studentName;
  }
}

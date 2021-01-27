
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
  private Course[] courses;
  private MPGrade[] mPGrade;
  //student will require both mpgrade and course objects

  // Add an element to the student array

  // Constructor to instantiate student object from psvm
  public Student(String name, int id) {
    this.studentName = name;
    this.studentID = id;
  }

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

  public int getCourseCount() {
    return this.courses.length;
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
      System.out.print(courses[i].getCourseName() + ", ");
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
    for (int i = 0; i < courses.length - 1; i++) {
      courseConcat += (courses[i].getCourseName() + ", ");
    }
    courseConcat += courses[courses.length - 1].getCourseName();
    return courseConcat;
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

  public Course[] getCourses(){
    return courses;
  }

  public void addMPGrade(Course course, int grade){
    if(mPGrade == null){
      mPGrade = new MPGrade[1];
      mPGrade[0] = new MPGrade(course, this, grade);
    }
    MPGrade[] mPGradeBack = new MPGrade[mPGrade.length + 1];
    for (int i = 0; i < mPGrade.length; i++) {
      mPGradeBack[i] = mPGrade[i];
    }
    mPGrade = new MPGrade[mPGradeBack.length];
    for (int i = 0; i < mPGradeBack.length; i++) {
      mPGrade[i] = mPGradeBack[i];
    }
    mPGrade[mPGrade.length - 1] = new MPGrade(course, this, grade);
  }
}

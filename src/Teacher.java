
/**
 * This class handles teacher/ teaching activities
 * This class is meant to deal with a single teacher at
 * a time
 */
public class Teacher {
private String teacherName;
private int teacherID;
private Course[] courses = new Course[100];

  public Teacher(String name, int id) {
    teacherName = name;
    teacherID = id;
  }

  /**
   * Adds multiple courses to teacher's list of courses
   * @param courses The courses to add
   */
  public void addCourses(Course[] courses) {
    for(int i = 0; i < courses.length; i++){
      for(int j = 0; i < this.courses.length; j++){
        if(this.courses[i] == null){ // if there isn't a value then add the course
          this.courses[i] = courses[j];

        }
      }
    }
  }

  /**
   * Adds a single course to teacher's list of courses
   * @param course The course to add
   */
  public void addCourse(Course course){
    for(int i = 0; i < courses.length; i ++){
      if(courses[i] == null){
        courses[i] = course;
      }
    }
  }

  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public int getTeacherID() {
    return teacherID;
  }

  public void setTeacherID(int teacherID) {
    this.teacherID = teacherID;
  }

}


/**
 * This class will add students, remove students,
 * to a course, this is for singular course, not multiple
 */
public class Course {
  private String courseName;
  // TODO: Figure out how to set a better length
  private Student[] students = new Student[100];
  private Teacher[] teachers = new Teacher[100];

  /**
   * This is a constructor of course, it takes the course
   * name, It's meant to only handle one course at a time,
   * use this if you only have the course name and not who teaches
   * it
   * @param courseName The name of the course
   */
  public Course(String courseName){
    this.courseName = courseName;
  }

  /**
   * Adds multiple students to course's list of students
   * @param students ArrayList of Students to add
   */
  public void addStudents(Student[] students){
    for(int i = 0; i < students.length; i++){
      for(int j = 0; j < this.students.length; j++){
        if(this.students[i] == null){
          this.students[i] = students[j];
        }
      }
    }
  }

  /**
   * Adds a single student to course's list of students
   * @param student The student to add
   */
  public void addStudent(Student student){
    for (int i = 0; i < this.teachers.length; i++) {
      if (students[i] != null) {
        this.students[i] = student;
      }
    }
  }

  /**
   * Adds multiple teachers to course's list of teachers
   * @param teachers The teachers to add
   */
  public void addTeachers(Teacher[] teachers){
    for(int i = 0; i < teachers.length; i++){
      for(int j = 0; j < this.teachers.length; j++){
        if(this.teachers[i] == null){
          this.teachers[i] = teachers[j];
        }
      }
    }
  }

  /**
   * Adds a single teacher to course's list of teachers
   * @param teacher The teacher to add
   */
  public void addTeacher(Teacher teacher){
    for(int i = 0; i < teachers.length; i++){
      if(teachers[i] == null){
        teachers[i] = teacher;
      }
    }
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

}

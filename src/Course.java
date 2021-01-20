import java.util.ArrayList;

/**
 * This class will add students, remove students,
 * to a course, this is for singular course, not multiple
 */
public class Course {
  private String courseName;
  private ArrayList<Student> students = new ArrayList<>();
  private ArrayList<Teacher> teachers = new ArrayList<>();

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
  public void addStudents(ArrayList<Student> students){
    this.students.addAll(students);
  }

  /**
   * Adds a single student to course's list of students
   * @param student The student to add
   */
  public void addStudent(Student student){
    this.students.add(student);
  }

  /**
   * Adds multiple teachers to course's list of teachers
   * @param teachers The teachers to add
   */
  public void addTeachers(ArrayList<Teacher> teachers){
    this.teachers.addAll(teachers);
  }

  /**
   * Adds a single teacher to course's list of teachers
   * @param teacher The teacher to add
   */
  public void addTeacher(Teacher teacher){
    this.teachers.add(teacher);
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public ArrayList<Student> getStudents() {
    return students;
  }

  public void setStudents(ArrayList<Student> students) {
    this.students = students;
  }

  public ArrayList<Teacher> getTeachers() {
    return teachers;
  }

  public void setTeachers(ArrayList<Teacher> teachers) {
    this.teachers = teachers;
  }
}

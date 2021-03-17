/**
 * This class will handle accessing a student's
 * marking period grade for a course, this is a singular
 * class so it's for one grade
 */
public class MPGrade {
  private Course course;
  private Student student;
  private int grade;

  public MPGrade(Course course, Student student, int grade) {
    this.course = course;
    this.student = student;
    this.grade = grade;
  }

  public void printGrade(){
    System.out.println(student.getName() + "'s grade in " + course.getCourseName()
            + " is currently " + grade + "%");
  }
}

/**
 * This class will handle accessing a student's
 * marking period grade for a course, this is a singular
 * class so it's for one grade
 */
public class MPGrade {
  private Course course;
  private MemberSuper student;
  private int grade;

  public Course getCourse() {
    return course;
  }

  public MemberSuper getStudent() {
    return student;
  }

  public int getGrade() {
    return grade;
  }

  public MPGrade(Course course, MemberSuper student, int grade) {
    this.course = course;
    this.student = student;
    this.grade = grade;
  }

  public void printGrade(){
    if(course != null && student != null && grade >= 0){
      System.out.println(student.getName() + "'s grade in " + course.getCourseName()
              + " is currently " + grade + "%");
    } else {
      System.out.println("Error occurred, please advise administrator");
    }
  }

}

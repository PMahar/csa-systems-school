import java.util.Arrays;

/**
 * This contains the outline for student information
 * */
public class Student {
  // This is the student id that is being evalutated
  private int studID = Integer.MIN_VALUE;
  private String[] studClasses;

  /**
  *Accessor for studID
  *@return Student ID as integer
  */

  public int getStudentID(){
    return this.studID;
  }

  /**
  *Constructor Student instantiates a new Student object with ID and classes
  *@param sID Student id as integer
  *@param sClasses Enrollment list for student in string array
  */
  public Student(int sID, String[] sClasses) {
    this.studID = sID;
    this.studClasses = new String[sClasses.length];
    for (int i = 0; i < sClasses.length; i++) {
      studClasses[i] = sClasses[i];
    }
  }


//Test main for debugging
//  public static void main(String[] args) {
//   String[] classes = new String[]{"Anthro", "CSA", "Calc AB"};
//    Student st = new Student(2396, classes);
//    System.out.println(st.studID);
//    for (int i = 0; i < st.studClasses.length; i++) {
//      System.out.println(st.studClasses[i]);
//    }
//  }
}

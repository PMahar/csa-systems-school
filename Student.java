/**
 * This contains the outline for student information
 * */
public class Student {
  // This is the student id that is being evalutated
  private int studID = Integer.MIN_VALUE;
  private int classIndex;
  private int[] indices;
  private String[] students;
  private String[] studClasses;

  /**
  *Accessor for studID
  *@return Student ID as integer
  */

  public int getStudentID(){
    return this.studID;
  }

  public Student() {

  }

  /**
  *Constructor Student instantiates a new Student object with ID and classes
  *@param sID Student id as integer
  *@param sClasses Enrollment list for student in string array
  */
  public Student(int sID, int cIndex, String[] sClasses) {
    this.studID = sID;
    this.classIndex = cIndex;
    this.indices = new int[0];
    this.studClasses = sClasses;
    for (int i = 0; i < sClasses.length; i++) {
      System.out.println(sClasses[i]);
    }
    //Expand the current array of students by one per instantiation of Student constructor
    //Copy the contents of students to expand, then reconstruct students with previous elements plus new id
    if (students != null) {
      String[] expand = new String[students.length + 1];
      for (int i = 0; i < students.length; i++) {
        expand[i] = students[i];
        students = new String[expand.length];
        for (int j = 0; j < students.length; j++) {
          students[j] = expand[j];
        }
        students[students.length - 2] = "" + sID;
      }
    } else {
      students = new String[1];
      students[0] = "" + sID;
    }
    int[] expand;
//    if (indices == null) {
      indices = new int[students.length + 1];
//    }
    expand = new int[indices.length + 1];
    System.out.println("students" + students.length);
    System.out.println("indices" + indices.length);
    System.out.println("expand" + expand.length);
    for (int i = 0; i < students.length; i++) {
      //Make a list of indices to assign IDs to course lists
      for (int j = 0; j < expand.length - 1; j++) {
        indices[j] = expand[j];
        indices[students.length - 1] = classIndex;
      }
      expand[i] = indices[i];
    }
  }

  public void viewRoster() {
    for (int i = 0; i < students.length; i++) {
      String classes = "";
      String prevClasses = "";
      for (int j = 0; j < studClasses.length; j++) {
        classes = prevClasses + studClasses[j];
        prevClasses = classes;
      }
      System.out.println("[" + i + 1 + "]     " + "[" + students[i] + "]    " + classes);
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

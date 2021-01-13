/**
 * The Grades class contains accessor and setter methods for grade-related data, as well as a
 * print function similar to Student's
 * */
public class Grades extends Student {
  
  // int[] grades will be used to pass along grade value from
  // grades = {Anthro grade, compsci grade, chem grade,
  // calc grade, quarter1, quarter2, quarter3,quarter4}
  // TODO: comment and describe what these things are
  private String[] gradeBook;
  private String[] gradeBack;
  private int gradeCount = 0;

  /**
   * Setter for string-formatted grades
   * @param gradeSet Int[] of grade values, passed from interface
   * */
  public void setGrades(int[] gradeSet) {
    this.gradeBook = new String[gradeCount + 1]; // Create a String with grades
    if (!(gradeBack == null)) { // If there aren't any grades? // TODO: Patrick is this correct
      for (int i = 0; i < gradeBook.length; i++) { // while i < gradeBook length assign gradeBook to the value of gradeBack
        gradeBook[i] = gradeBack[i];
      }
    }
    gradeCount++;
    for (int i = 0; i < gradeSet.length - 1; i++) {
      gradeBook[gradeCount - 1] = " " + gradeSet[i];
    }
    gradeBack = new String[gradeBook.length + 1];
//    for (int i = 0; i < gradeBook.length; i++) {
//      gradeBack[i] = gradeBook[i];
//    }
  }

  /**
   * Parses String[] enrollments to determine the quantity of classes each student is
   * enrolled in
   * @param courses Array of course lists
   * @param id ID of student to parse (index of students[])
   * @return amount of courses
   * */
  public int courseCount(String[] courses, int id) {
    int courseCount = 1;
    int sIndex = 0;
    for (int i = 0; i < students.length; i++) {
      //If the ID at students[i] is the one specified, store its value
      int stud = Integer.parseInt(students[i]);
      if (stud == id) {
        sIndex = i;
      }
    }
    //Pull a course list by taking the element at the found index, then over
    //every character in its contents, count its commas to derive number of courses
    String cList = courses[sIndex];
    int cLen = courses[sIndex].length();
    for (int i = 0; i < cLen; i++) {
      if (cList.charAt(i) == ',') {
        courseCount++;
      }
    }
    return courseCount;
  }
  
  /**
   * (Intended to) Print current grade list
   * */
  public void viewGrades(){
    System.out.println();
    // TODO: Figure out how to chose specific student
    for (int i = 1; i == gradeBook.length; i++) {
      System.out.println("Grade " + i + " " +gradeBook[i]);
    }
  }

  /**
   * Checks to see if any grades have been added
   * @return Return true if the student has valid grades
   */
  public boolean validGrades() {
    if(gradeBook != null && gradeBack != null){
      return true;
    }
    return false;
  }
}
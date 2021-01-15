import java.util.ArrayList;

/**
 * The Grades class contains accessor and setter methods for
 * grade-related data, as well as a
 * print function similar to Student's
 * */
public class Grades extends Student {
  
  // int[] grades will be used to pass along grade value from
  // grades = {Anthro grade, compsci grade, chem grade,
  // calc grade, quarter1, quarter2, quarter3,quarter4}
  // TODO: comment and describe what these things are
  // Contains the courses of the students
  private ArrayList<String> gradeCourse = new ArrayList<>();
  // Contains grades
  private ArrayList<Integer> studentGrades = new ArrayList<>();
  // Contains student ID's
  private ArrayList<Integer> studentID = new ArrayList<>();
  private int gradeCount = 0;
  private final Student st = new Student();

  public void initGrades(int id) {
    studentID.add(id);
    // TODO: Add students enrolled courses to gradeCourse
  }

  /**
   * Setter for string-formatted grades
   * @param grade Int of grade, passed from interface
   * @param course The name of the course to grade
   * @param id The id of the grade to set to
   * */
  public void setGrades(int grade, String course, int id) {
    gradeCourse.add(course);
    studentGrades.add(grade);
    studentID.add(id);
  }

  /**
   * Parses String[] enrollments to determine the quantity of
   * classes each student is
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
    //every character in its contents, count its commas to derive number of
    // courses
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
   * View the students grades
   * @param id The id of the student who's grades are to be viewed
   * */
  public void viewGrades(int id){
    System.out.println();
    // TODO: Figure out how to chose specific student
    for (int i = 0; i == studentGrades.size(); i++) {
      System.out.println("Grade " + i + " " + studentGrades.get(i-1));
    }
  }

  /**
   * View the students grades
   * @param name The name of the student who's grades are to be viewed
   */
  public void viewGrades(String name){
    System.out.println();
    int id = st.getStudentID(name);
    System.err.println("id is " + id);
    int idIndex = Runner.findInArrayList(studentID, id);
    System.out.println("Grade for " + name + " in " + gradeCourse.get(idIndex) +
            " is " + studentGrades.get(idIndex));
    // TODO: Figure out how to chose specific student
    for (int i = 0; i == studentGrades.size(); i++) {
      System.out.println("Grade " + i + " " + studentGrades.get(i));
    }
  }

  /**
   * Checks to see if any grades have been added
   * @return Return true if the student has valid grades
   */
  public boolean validGrades() {
    if(!studentGrades.isEmpty() && !studentID.isEmpty() && !gradeCourse.isEmpty()){
      return true;
    }
    return false;
  }
}
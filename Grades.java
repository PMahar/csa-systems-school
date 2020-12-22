/**
 * This will be used to get grades
 * */
public class Grades {
  
  // int[] grades will be used to pass along grade value from 
  int[] grades = {0,0,0,0,0,0,0}; // TODO: Figure out what we want the "example"/ default grades to be
  /**
   * This is the parents interface to check grades
   * @param id the id of the student to check grades of
   * */
  public void checkGrades(int id){
    // TODO: get the students grades from somewhere
    System.out.print("Your student's grades are ");
    arrayToString(grades);
  }
  
  /**
   * This converts an int[] to a String so it can be output
   * @param arr the int[] to be converted
   * @return the value of arr as a String
   * */
  private String arrayToString(int[] arr){
    for(int i : arr){
      System.out.println(i + " ");
    }
  }
}
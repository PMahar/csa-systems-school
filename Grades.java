import java.util.Scanner;
/**
 * This will be used to get grades
 * */
public class Grades {
  
  // int[] grades will be used to pass along grade value from 
  int[] grades = {0,0,0,0,0,0,0}; // TODO: Figure out what we want the "example"/ default grades to be
  /**
   * This is the method to check a student's grades
   * @param id the id of the student to check grades of
   * */
  public void checkGrades(int id){
    // TODO: get the students grades from somewhere
    System.out.print("Your student's grades are ");
    arrayToString(grades);
  }
  
  /**
   * This is the method used for a teacher to change a students grade
   * */
  public void gradeStuds(){
    Scanner scan = new Scanner(System.in);
    System.out.println("Do you want to view grades or change grades?");
    if(scan.next().equalsIgnoreCase("View")){
      
    }
  }
  /**
   * This converts an int[] to a String and then prints it
   * @param arr the int[] to be converted
   * */
  private void arrayToString(int[] arr){
    for(int i : arr){
      System.out.println(i + ", ");
    }
  }
}
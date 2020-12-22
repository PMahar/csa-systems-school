import java.util.Scanner;

/**
 * The "starter" of this program
 * */
public class Runner{
  public static void main(String[] args){
    Grades grades = new Grades();
    Scanner scan = new Scanner(System.in);
    System.out.println("Are you a teacher or parent?");
    // If the user is a teacher, prompt with appropriate teacher interfaces
    if(scan.nextString().contains("teacher")){
      System.out.println("Are you conducting attendance, or are you changing grades?");
      if(scan.nextString().equalsIgnoreCase("Attendance")){
        // TODO: add code here to continue to attendance prompt
      } else if(scan.nextString().contains("grades")){
        // TODO: add code here to continue to grading prompt
      }
    } else if(scan.nextString().equalsIgnoreCase("Parent")){
      System.out.println("Would you like to check your child's grades?"); 
      if(scan.nextString().equalsIgnoreCase("Yes")){ // TODO: add case for checking report card
        System.out.println("What is your child's student id?");
        grades.checkGrades(scan.nextInt());
      } // TODO: add code for a different scenario
      
    }
  }
}
import java.util.Scanner;

/**
 * The "starter" of this program
 * */
public class Runner{
  public static void main(String[] args){
    Grades grades = new Grades();
    Attendance attend = new Attendance();
    Scanner scan = new Scanner(System.in);
    System.out.println("Are you a teacher or parent?");
    // If the user is a teacher, prompt with appropriate teacher interfaces
    if(scan.next().equalsIgnoreCase("teacher")){
      System.out.println("Are you conducting attendance, or are you dealing with grades?");
      if(scan.next().equalsIgnoreCase("Attendance")){
        // TODO: add code here to continue to attendance prompt
        attend.Attendance();
      } else if(scan.next().equalsIgnoreCase("grades")){
        System.out.println("Do you want to change students grade or view their grades");
        if(scan.next().contains("View")){
          System.out.println("What is the id of the student you would like to view");
          grades.checkGrades(scan.nextInt());
        }else if(scan.next().contains("Change")){
          grades.gradeStuds();
        }
      }
    } else if(scan.next().equalsIgnoreCase("Parent")){
      System.out.println("Would you like to check your child's grades?"); 
      if(scan.next().equalsIgnoreCase("Yes")){ // TODO: add case for checking report card
        System.out.println("What is your child's student id?");
        grades.checkGrades(scan.nextInt());
      } // TODO: add code for a different scenario
      
    }
  }
}
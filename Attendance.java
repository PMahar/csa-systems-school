import java.util.Scanner;
/**
 * This contains the outlien for attendance information
 * */
public class Attendance {
  // This is 
  public int daysAbs = 0;
  
  /**
   * This is to be used at the beginning of a class to show which students are absent and those who are not
   * */
  public void Attendance(){
    int[] ids = new int[10];
    Scanner scan = new Scanner(System.in);
    System.out.println("What are the id's of the tardy students?");
    int i = 0;
    while(scan.hasNextInt()){
      ids[i] = scan.nextInt();
      i++;
    }
  }
}
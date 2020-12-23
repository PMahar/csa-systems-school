import java.util.Scanner;
/**
 * This will be used to get/ set and interface with grades
 * */
public class Grades {
  
  // int[] grades will be used to pass along grade value from 
  // grades = {Anthro grade, compsci grade, chem grade, 
  // calc grade, quarter1, quarter2, quarter3,quarter4}
  int[] grades = {0,0,0,0,0,0,0,0}; // TODO: Figure out what we want the "example"/ default grades to be
  
  /**
   * This is the method to check a student's grades
   * @param id the id of the student to check grades of
   * */
  public void checkGrades(int id){
    // TODO: get the students grades from somewhere
    printGrades();
  }
  
  /**
   * This is the method used for a teacher to change a students grade
   * */
  public void gradeStuds(){
    Scanner scan = new Scanner(System.in);
    System.out.println("Are you grading Anthropology, Computer Science, Chemistry, or Calculus?");
    if(scan.next().contains("nthropology")){
      System.out.println("What would you like to change the grade to?");
      setGrade(scan.nextInt(), 0);
    } else if(scan.next().contains("omputer")){
     System.out.println("What would you like to change the grade to?");
     setGrade(scan.nextInt(), 1);
    } else if(scan.next().contains("hemistry")){
      System.out.println("What would you like to change the grade to?");
      setGrade(scan.nextInt(), 2);
    } else if(scan.next().contains("alculus")){
      System.out.println("What would you like to change the grade to?");
      setGrade(scan.nextInt(), 3);
    }
    System.out.println("WOuld you like the quarter grade to be graded for you?");
    if(scan.next().contains("es")){
      System.out.println("Is it the first, second, third, or fourth quarter?");
      if(scan.next().contains("irst")){
        
      }
    }
  }
  
  /**
   * Set a quarter grade based upon given information
   * @param quarter the quarter currently in session
   * */
  private void setQuarterGrade(int quarter){
    int anthro = grades[0];
    int compSci = grades[1];
    int chem = grades[2];
    int calc = grades[3];
    int avg = ((anthro + compSci) + (chem + calc)) / 4;
    setGrade(avg, (quarter + 3));
  }
  
  /**
   * Prints the students grades
   * */
  public void printGrades(){
    for(int i = 0; i < grades.length; i++){
      // Switch statement to add information to the output of grades
      switch(i){
      case 0:
        System.out.print("Anthropology grade is ");
        break;
      case 1:
        System.out.print("Computer Science grade is ");
        break;
      case 2:
        System.out.print("Chemistry grade is ");
        break;
      case 3:
        System.out.print("Calculus grade is ");
        break;
      case 4:
        System.out.print("Quarter 1 grade is ");
        break;
      case 5:
        System.out.print("Quarter 2 grade is ");
        break;
      case 6:
        System.out.print("Quarter 3 grade is ");
        break;
      case 7:
        System.out.print("Quarter 4 grade is ");
        break;
        default:
          break;
      }
      System.out.println(grades[i]);
    }
  }
  
  /**
   * Sets a value of grades[]
   * @param grade the value to change to
   * @param index the index of the value to change
   * */
  private void setGrade(int grade, int index){
    grades[index] = grade;
  }
}
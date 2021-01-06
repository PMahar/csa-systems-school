import java.util.Scanner;

/**
 * This will be used to get/ set and interface with grades
 * */
public class Grades {
  
  // int[] grades will be used to pass along grade value from 
  // grades = {Anthro grade, compsci grade, chem grade, 
  // calc grade, quarter1, quarter2, quarter3,quarter4}
  int[] grades = new int[8]; // TODO: Figure out what we want the "example"/ default grades to be
  private Runner run = new Runner();
  private Scanner scan = new Scanner(System.in);

  /**
   * This is the method to check a student's grades
   * @param id the id of the student to check grades of
   * */
  public void checkGrades(int id){
    // TODO: get the students grades from somewhere
    int[] zeros = {0,0,0,0,0,0,0,0};
    // TODO: Add a check to see if the student already exists
    if (grades == null){
      System.out.println("Please add grades first"); 
      gradeUse();
    } else {
      printGrades();
    }
    run.main(null);
  }
  
  /**
   *  This is the method to check a student's grades
   * @param id the id of the student to check grades of
   * @param type is 'c' if wanted to print the class grades, is 'q' if you want the quarter grades to be printed
   * */
  public void checkGrades(int id, char type){
    // TODO: get the students grades from somewhere
    int[] zeros = {0,0,0,0,0,0,0,0};
    // TODO: Add a check to see if the student already exists
    if (grades == null){
      System.out.println("Please add grades first"); 
      gradeUse();
    } else {
      switch (type){
        case 'c':
          printClassGrades();
          break;
        case 'q':
          printQuarterGrades();
          break;
      }
    }
    run.main(null);
  }
  
  /** 
   * This is the main interface for grading/ checking grades
   * */
  public void gradeUse(){
    Scanner scan = new Scanner(System.in);
    System.out.println("Change grades - 1 | View grades - 2");
    switch(scan.nextInt()) {
      case 1: // If the user choses to change grades
        gradeStuds();
        break;
      case 2: // If the user decides to  view grades
        System.out.println(" Class Grades - 1 | Quarter Grades - 2");
        switch (scan.nextInt()){
          case 1: // Class grades
            checkGrades(scan.nextInt(),'c');
            break;
          case 2: // Quarter grades
            checkGrades(scan.nextInt(),'q');
            break;
          default:
            break;
        }
        break;
    }
    run.main(null);
}  

  /**
   * This is the method used for a teacher to change a students grade
   * */
  public void gradeStuds(){
    System.out.println("Please enter which of the following you are grading");
    System.out.println(" Anthropology - 1 | Computer Science - 2 | Chemistry - 3 | Calculus - 4 | Quit - 5 |");
    int choice = scan.nextInt(); // This is the choice of the user
    System.out.println("Please enter new grade");
    int grade = scan.nextInt(); // This is the new grade
    switch(choice){
      case 1: // If user choose Anthropology
        setGrade(grade,(choice - 1));
        break;
      case 2: // If the user choose Computer Science
        setGrade(grade, (choice - 1));
        break;
      case 3: // If the user choose Chemistry
        setGrade(grade, (choice - 1));
        break;
      case 4: // If the user choose calculus
        setGrade(grade, (choice - 1));
        break;
      default:
        
        break;
    }
    System.out.println("Would you like the quarter to be graded for you?");
    System.out.println("Yes - 1 | No - 2");
    switch (scan.nextInt()){
      case 1: // Yes
        System.out.println("Is it the 1st, 2nd, 3rd, 4th quarter?");
        System.out.println("First Quarter - 1 | Second Quarter - 2 | Third Quarter - 3 | Fourth Quarter - 4");
        switch (scan.nextInt()){
          case 1:
            gradeQuarter(1);
            break;
          case 2:
            gradeQuarter(2);
            break;
          case 3:
            gradeQuarter(3);
            break;
          case 4:
            gradeQuarter(4);
            break;
          default:
            System.out.println("Invalid response. Quiting");
            System.exit(0);
            break;
    }
      case 2: // No
        break;
  }
    run.main(null);
}
  
  /**
   * Prints the students grades
   * */
  public void printGrades(){
    for(int i = 0; i < grades.length; i++){
      // Switch statement to add information to the output of grades
      switch(i){
      case 0: // Print the Anthropology grade
        System.out.print("Anthropology grade is ");
        break;
      case 1: // Print the Computer Science grade
        System.out.print("Computer Science grade is ");
        break;
      case 2: // Print the Chemistry grade
        System.out.print("Chemistry grade is ");
        break;
      case 3: // Print the Calulus grade
        System.out.print("Calculus grade is ");
        break;
      case 4: // Print the first quarter grade
        System.out.print("Quarter 1 grade is ");
        break;
      case 5: // Print the second quarter grade
        System.out.print("Quarter 2 grade is ");
        break;
      case 6: // Print the third quarter grade
        System.out.print("Quarter 3 grade is ");
        break;
      case 7: // Print the fourth quarter grade
        System.out.print("Quarter 4 grade is: ");
        break;
        default:
          break;
      }
      System.out.println(grades[i]);
    }
  }
 
  /**
   * Prints the class grades of the student
   * */
  public void printClassGrades(){
    String class1 = ""; // This is used as a filler
    for(int i = 0; i < grades.length; i++){
      switch (i) {
        case 0: // Anthropology
          class1 = "Anthropology";
          break;
        case 1: // Comp Sci
          class1 = "Computer Science";
          break;
        case 2: // Chemistry
          class1 = "Chemistry";
          break;
        case 3: // Calculus
          class1 = "Calculus";
          break;
        default:
          break;
      }
      if (i < 4){
        System.out.println(class1 + " grade is: " + grades[i]);
      }
    }
    run.main(null);
  }
  
  /**
   * 
   * */
  public void printQuarterGrades(){
   String class1 = ""; // This is used as a filler
   for(int i = 0; i < grades.length; i++){
     switch (i) {
       case 4: // First quarter
         class1 = "Quarter one";
         break;
       case 5: // Second quarter
         class1 = "Quarter two";
         break;
       case 6: // Third quarter
         class1 = "Quarter three";
         break;
       case 7: // Fourth quarter
         class1 = "Quarter four";
         break;
       default:
         break;
     }
     if (i > 3){
      System.out.println(class1 + " grade is: " + grades[i]); 
     }
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
     
  /**
   * Set a quarter grade based upon given information
   * @param quarter the quarter currently in session
   * */
  private void gradeQuarter(int quarter){
    int anthro = grades[0];
    int compSci = grades[1];
    int chem = grades[2];
    int calc = grades[3];
    int avg = ((anthro + compSci) + (chem + calc)) / 4;
    setGrade(avg, (quarter + 3));
  }
}
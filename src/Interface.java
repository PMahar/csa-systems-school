import java.util.Scanner;

public class Interface {
  Student[] Students = new Student[5];


  public static void main(String[] args) {
    Interface run = new Interface();
    //add students or something
    Scanner sct = new Scanner(System.in);
    Scanner scl = new Scanner(System.in);
    //Get an int as an array size, then add to student[] for however many
    //user has specified
    //Use backup arrays when returning to this dialog (adding or removing after setting once)
    for (int i = 0; i < 5; i++) {
      System.out.println("name: ");
      String name = scl.nextLine();
      System.out.println("id");
      int id = sct.nextInt();
      System.out.println("courses (0 to escape): ");
      /*
      while (true) {
        if (scl.nextInt() == 0) {
          break;
        } else {
         break;
         //Pass a string[] of courses to a course class to be added as a course object
        }
      }
       */
      Student studAdd = new Student(name, id);
      run.Students[i] = studAdd;
    }
    run.printRoster();
  }

  //Method for listing all stored data as text
  public void printRoster() {
    System.out.println("STUDENTS: ");
    //Do some printf magic here
    System.out.println("Student name:                 Student ID:");
    for (int i = 0; i < Students.length; i++) {
      System.out.println(Students[i].studentName + "                " + Students[i].studentID);
    }
  }
}

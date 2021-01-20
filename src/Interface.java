import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is where the interfacing is between the user
 * and the code
 */
public class Interface {
  Student[] Students = new Student[5];

  public static void main(String[] args) {
    Interface run = new Interface();
    // Add students or something
    Scanner sct = new Scanner(System.in); // Token based
    Scanner scl = new Scanner(System.in); // Line based
    // Get an int as an array size, then add to student[] for however many
    // User has specified
    // Use backup arrays when returning to this dialog (adding or removing
    // after setting once)
    for (int i = 0; i < 5; i++) {
      System.out.println("Student Name: ");
      String name = scl.nextLine();
      System.out.println("Student Id:");
      int id = sct.nextInt();
      if(id > 0) { // Make sure the id is valid
        Student studAdd = new Student(name, id); // Construct a new student
        run.Students[i] = studAdd; // Add student to list of students
        System.out.println("Student's courses (0 to escape, coma's in between names): ");
        while(scl.hasNext() && !scl.next().contains("0")){
          Course course = new Course(scl.next());
          studAdd.addCourse(course);
        }
      } else if(id == 0){
        System.out.println("Invalid id");
      }
      System.out.println("Successfully added student");
      /*
      while (true) {
        if (scl.nextInt() == 0) {
          break;
        } else {
         break;
         // Pass a string[] of courses to a course class to be added as a course object
        }
      }
       */

    }
    run.printRoster();
  }

  // Method for listing all stored data as text
  public void printRoster() {
    System.out.println("STUDENTS: ");
    System.out.println();
    // Do some printf magic here
    System.out.println("Student name:                 Student ID:");
    for (int i = 0; i < Students.length; i++) {
      System.out.println(Students[i].studentName + "                " + Students[i].studentID);
    }
  }
}

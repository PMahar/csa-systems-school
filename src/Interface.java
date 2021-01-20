
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is where the interfacing is between the user
 * and the code
 */
public class Interface {
  ArrayList<Student> Students = new ArrayList<>();

  public static void main(String[] args) {
    Interface run = new Interface();
    Scanner sct = new Scanner(System.in); // Token based
    Scanner scl = new Scanner(System.in); // Line based

    // Get an int as an array size, then add to student[] for however many
    // User has specified
    // Use backup arrays when returning to this dialog (adding or removing
    // after setting once)
    // Add students
    System.out.println("How many students are you adding?");
    int toAdd = sct.nextInt();
    for (int i = 0; i < toAdd; i++) {
      // TODO: Add interfacing to interact with the user and add courses,
      //  teachers and everything else
      System.out.println("Student Name: ");
      String name = scl.nextLine();
      System.out.println("Student Id:");
      int id = sct.nextInt();
      if(id > 0) { // Make sure the id is valid
        Student studAdd = new Student(name, id); // Construct a new student
        run.Students.add(studAdd); // Add student to list of students
        System.out.println("Student's courses (0 to escape, coma's in between names): ");
        while(scl.hasNext() && !scl.next().contains("0")){
          Course course = new Course(scl.next());
          studAdd.addCourse(course);
        }
      } else if(id == 0){
        System.out.println("Invalid id");
      }
      System.out.println("Successfully added student");
    }
  }

  // Method for listing all stored data as text
  public void printRoster() {
    System.out.println("STUDENTS: ");
    System.out.println();
    // Do some printf magic here
    System.out.println("Student name:                 Student ID:");
    for (int i = 0; i < Students.size(); i++) {
      System.out.println(Students.get(i).getStudentName() + "                "
              + Students.get(i).getStudentID());
    }
  }
}

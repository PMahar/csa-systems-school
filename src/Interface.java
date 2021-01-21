
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is where the interfacing is between the user
 * and the code
 */
public class Interface {
  private static Student[] Students = new Student[100];

  public static void main(String[] args) {
    Interface run = new Interface();
    Scanner sct = new Scanner(System.in); // Token based
    Scanner scl = new Scanner(System.in); // Line based

    System.out.println("Are you a teacher - 1 | or Student - 2");
    // Get an int as an array size, then add to student[] for however many
    // User has specified
    // Use backup arrays when returning to this dialog (adding or removing
    // after setting once)
    // Add students
    switch (sct.nextInt()) {
      case 1: // Teacher
        System.out.println("How many students are you adding?");
        int toAdd = sct.nextInt();
        for (int i = 0; i < toAdd; i++) {
          // TODO: Add interfacing to interact with the user and add courses,
          //  teachers and everything else
          addStudent();
        }
        break;
      case 2: // Student
        System.out.println("Are you a new student? Yes - 1 | WIP No - 2");
        switch(scl.nextInt()){
          case 1:
            addStudent();
            break;
          case 2:
            // TODO: Figure out how to call objects that are already made
            System.out.println("What is your ID?");
            int id = sct.nextInt();
            System.out.println("What's your name");
            String name = sct.next();
            break;
        }
    }
  }

  private static void addStudent(){
    Interface run = new Interface();
    Scanner scl = new Scanner(System.in);
    Scanner sct = new Scanner(System.in);

    System.out.println("Student Name: ");
    String name = scl.nextLine();
    System.out.println("Student Id:");
    int id = sct.nextInt();
    if (id > 0) { // Make sure the id is valid
      Student studAdd = new Student(name, id); // Construct a new student
      for(int i = 0; i < Students.length; i++){
        if(!studAdd.isValid(studAdd)) {
          run.Students[i] = studAdd; // Add student to list of students
        }
      }
      System.out.println("Student's courses (0 to escape, coma's in between names): ");
      while (scl.hasNext() && !scl.next().contains("0")) {
        Course course = new Course(scl.next());
        studAdd.addCourse(course);
      }
    } else if (id == 0) {
      System.out.println("Invalid id");
    }
    System.out.println("Successfully added student");
  }

  /**
   * Adds a teacher to a course and a course to a teacher
   */
  private void addTeacherCourse(Teacher teacher, Course course){

  }

  // Method for listing all stored data as text
  public void printRoster() {
    System.out.println("STUDENTS: ");
    System.out.println();
    // Do some printf magic here
    System.out.println("Student name:                 Student ID:");
    for (int i = 0; i < Students.length; i++) {
      System.out.println(Students[i].getStudentName() + "                "
              + Students[i].getStudentID());
    }
  }
}

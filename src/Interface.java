
import java.util.Scanner;

/**
 * This is where the interfacing is between the user
 * and the code
 */
public class Interface {
  private static Student[] students = new Student[5];
  private static Teacher[] teachers = new Teacher[5];

  public static void main(String[] args) {
    Interface run = new Interface();
    Scanner sct = new Scanner(System.in); // Token based
    Scanner scl = new Scanner(System.in); // Line based

    System.out.println("Are you a teacher - 1 | or Student - 2 | Quit - 3");
    // Get an int as an array size, then add to student[] for however many
    // User has specified
    // Use backup arrays when returning to this dialog (adding or removing
    // after setting once)
    // Add students
    switch (sct.nextInt()) {
      case 1: // Teacher
        System.out.println("Add things - 1 | Grade things - 2");
        switch (sct.nextInt()){
          case 1:
        System.out.println("Add teacher - 1 | Add student - 2");
        switch (sct.nextInt()) {
          case 1: // Add teacher
            addTeacher();
            break;
          case 2: // Add student
            System.out.println("How many students are you adding?");
            int toAdd = sct.nextInt();
            for (int i = 0; i < toAdd; i++) {
              addStudent();
            }
            break;
        }
        break;
          case 2: // Grade
            System.out.println("Student ID or name to grade: ");
            if(scl.hasNextInt()){
              // Work off of student ID
              for(int i = 0; i < students.length; i ++){
                if(students[i].getStudentID() == scl.nextInt()){

                }
              }
            } else if(scl.hasNext()){
              // Work off of student name
            }
            break;
    }
        System.out.println("Continue as teacher? Yes - 1 | No - 2");
        if(sct.hasNextInt()) {
          switch (sct.nextInt()) {
            case 1:
              // TODO: Add more here
              break;
            case 2:
              main(args);
              break;
          }
        } else {
          System.out.println("Please enter a number instead");
        }
        break;
      case 2: // Student
        System.out.println("Are you a new student? Yes - 1 | No - 2");
        switch(scl.nextInt()){
          case 1:
            addStudent();
            break;
          case 2:
            Student stud;
            System.out.println("What is your ID?");
            int id = sct.nextInt();
            System.out.println("What's your name");
            String name = sct.next();
            // "Find" the student
            for (Student student : students) {
              if (student.isValid()) {
                if (student.getStudentID() == id) {
                  stud = student;
                  System.out.println("View classes - 1 | WIP View grades - 2 | Change profile - 3");
                  switch (sct.nextInt()) {
                    case 1: // View classes
                      stud.listCourses();
                      break;
                    case 2: // View grades
                      // TODO: view grades
                      break;
                    case 3: // Change profile
                      System.out.println("Change name - 1 | Change ID - 2");
                      switch(sct.nextInt()){
                        case 1:
                          System.out.println("What's the changed name?");
                          stud.changeName(sct.next());
                          break;
                        case 2:
                          System.out.println("Not implemented yet");
                          break;
                      }
                  }
                }
              }
            }
            break;
        }
        System.out.println("Continue as student? Yes - 1| No - 2");
        switch(scl.nextInt()){
          case 1: // Yes
            // TODO: Add more for student interactions
            break;
          case 2:
            main(args);
            break;
        }
        break;
      case 3:
        System.exit(0);
        break;
    }
  }

  /**
   * Create a new student
   */
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
      for(int i = 0; i < students.length; i++){
          System.err.println("Added student");
          students[i] = studAdd; // Add student to list of students
      }
      System.out.println("Student's courses (0 to escape, coma's in between names): ");
      while (!scl.next().equals("0")) {
        Course course = new Course(scl.next());
        run.addStudentCourse(studAdd, course);
      }
    } else if (id == 0) {
      System.out.println("Invalid id");
    }
  }

  /**
   * Creates a new teacher
   */
  private static void addTeacher(){
    Interface run = new Interface();
    Scanner sct = new Scanner(System.in);
    Scanner scl = new Scanner(System.in);

    System.out.println("Teacher Name: ");
    String name = scl.next();
    System.out.println("Teacher ID: ");
    int id = sct.nextInt();
    if(id > 0){
      Teacher teacherAdd = new Teacher(name, id);
      for(int i = 0; i < teachers.length; i++){
        if(teacherAdd.getTeacherName() != null){
          teachers[i] = teacherAdd;
        }
      }
      System.out.println("Teacher's course (0 to escape): ");
      while(scl.hasNext() && !scl.next().contains("0")){
        Course course = new Course(scl.next());
        run.addTeacherCourse(teacherAdd, course);
      }
    } else if(id == 0){
      System.out.println("Invalid ID");
    }
    System.out.println("Successfully added teacher");
  }

  /**
   * Adds teacher to course and course to teacher
   * @param teacher The teacher to add to course
   * @param course The course to add to the teacher
   */
  private void addTeacherCourse(Teacher teacher, Course course){
    teacher.addCourse(course);
    course.addTeacher(teacher);
  }

  /**
   * Adds student to course and course to student
   * @param student Student to add to course
   * @param course Course to add to student
   */
  private void addStudentCourse(Student student, Course course){
    student.addCourse(course);
    course.addStudent(student);
  }

  // Method for listing all stored data as text
  public void printRoster() {
    System.out.println("STUDENTS: ");
    System.out.println();
    // Do some printf magic here
    System.out.println("Student name:                 Student ID:");
    for (int i = 0; i < students.length; i++) {
      System.out.println(students[i].getStudentName() + "                "
              + students[i].getStudentID());
    }
  }
}

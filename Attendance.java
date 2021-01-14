import java.util.ArrayList;

// TODO: Maybe in Systems 2, make the info in here go straight to a file

/**
 * This class will deal with students' attendance
 */
public class Attendance extends Student{
  // This is the id of the student, followed by the amount of days they have
  // attended
  public ArrayList<Integer> studentAttended = new ArrayList<>();
  // This is the amount of days a student must attend in order to proceed
  // to the next grade
  private final int requiredAttend = 180;

  /**
   * If a student is absent then use this
   * @param id the id of the student to check
   */
  public void studentAbsent(int id) {
    // If the student is already in the system
   if(Runner.findInArrayList(studentAttended, id) != -1){
     int index = Runner.findInArrayList(studentAttended, id);
     int prevAttend = studentAttended.get(index+1);
     studentAttended.set(index + 1, prevAttend-1);
   }
  }

  /**
   * If a student is in class then use this
   * @param id the id of the student in class
   */
  public void studentAttending(int id){
    if(Runner.findInArrayList(studentAttended, id) != -1){
      int index = Runner.findInArrayList(studentAttended,id);
      int prevAttend = studentAttended.get(index + 1);
      studentAttended.set(index + 1, prevAttend + 1);
    }
  }

  /**
   * Returns if the student has attended the minimum required days
   * @param id ID of the student to check
   * @return True if the student has met the requirements, false otherwise
   */
  public boolean canPass(int id){
    for(int i = 0; i < studentAttended.size(); i++) {
      if (studentAttended.get(i).equals(id)) {
        if(studentAttended.get(i+1) > requiredAttend){
          return true;
        } else {
          return false;
        }
      }
    }
    System.err.println("Couldn't check students attendance.");
    return false;
  }

  /**
   * Get the attendance of a specific student
   * @param id The ID of the student
   * @return returns the days they've attended school
   */
  public int getAttendance(int id) {
    int index = Runner.findInArrayList(studentAttended,id);
    return studentAttended.get(index + 1);
  }

  /**
   * Returns if the attendance has been set previously
   * @return True if the ArrayList has been added too, false otherwise
   */
  public boolean attendSet() {
    if(studentAttended.isEmpty()) {
      return false;
    } else{
      return true;
    }
  }
}

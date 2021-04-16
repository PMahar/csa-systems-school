import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class deals with the main searching for, and
 * editing of the file used to store information
 * In a scenario where it's more than one person using
 * this program we would instead store this file on a
 * server/ network somewhere
 */
public class FileHandling {
  private File file = null;

  public ArrayList<School> load(String fileName){
    // Use a hashmap so we can have one big dump of info
    HashMap<String, ArrayList<String>> info = new HashMap<>();
    // "Find" the file
    File file = new File("src\\" +fileName + ".sch");
    // Try this and catch the exceptions
    try(Scanner in = new Scanner(file)){
      // If the file exists...
      if(file.exists()) {
        // For every line in the file
        for (int i = 0; i < file.length(); i++) {
          // While we have a next line
          if(in.hasNextLine()) {
            // Save the line
            String line = in.nextLine();
            // Split up the line so we can get what kind of object we need to make
            String[] lineSplit = line.split(",");
            // Get the length of the object name
            // So we can cut it out in the future
            int len = lineSplit[0].length();
            // Get the object name
            String key = line.substring(0, len);
            // Get everything after the key name
            String value = line.substring(len + 1);
            // If the HashMap doesn't have the object we have
            if (!info.containsKey(key)) {
              // Then add it to the HashMap
              info.put(key, new ArrayList<String>());
            }
            // Then get the ArrayList and add the values we just got to it
            info.get(key).add(value);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] values;
    // Make an ArrayList where we can save
    // Students to add to a roster
    ArrayList<Student> students = new ArrayList<>();
    // For every bit of student information we get
    for(String stud : info.get("Student")) {
      // Split up the String by commas
      values = stud.split(",");
      // Create a new Student using the values given
      Student s = new Student(values[0],Integer.valueOf(values[1]));
      // Add the Student to the ArrayList of Students
      students.add(s);
    }
    // Repeat the process done above with students but instead with
    // Rosters, Teachers, Schools, and possibly eventually district's
    ArrayList<Roster> rosters = new ArrayList<>();
    for(String rost : info.get("Roster")){
      values = rost.split(",");
      Roster r = new Roster(values[0],Integer.valueOf(values[1]));
      // Compare the student id's to the id's that the roster has
      // of students in said roster
      for(Student s : students){
        for(int i = 0; i < values.length; i ++){
          // Make sure we are comparing by id's not size
          if(i > 1){
            if(Integer.valueOf(values[i]) == s.id){
              r.addStudent(s);
            }
          }
        }
      }
      rosters.add(r);
    }
    ArrayList<Teacher> teachers = new ArrayList<>();
    for(String teach : info.get("Teacher")){
      values = teach.split(",");
      Teacher t = new Teacher(values[0],Integer.valueOf(values[1]));
      teachers.add(t);
    }
    ArrayList<School> schools = new ArrayList<>();
    for(String school : info.get("School")){
      values = school.split(",");
      School s = new School(rosters,teachers,values[0]);
      schools.add(s);
    }
    return schools;
  }

  public void save(String filename, ArrayList<School> schools) {
    this.file = new File(".\\" + filename + ".sch");
    //Use our own extension so it doesn't mess with the user's defaults
    try(PrintWriter pw = new PrintWriter(this.file)) {
      pw.print("{\n" + //Header
               " schools{\n");
      //Open brackets
      for (int s = 0; s < schools.size(); s++) { //Open schools
        pw.println("  " + schools.get(s).getSchoolTitle() + "{");
        //        schoolName{
        for (int r = 0; r < schools.get(s).getRosters().size(); r++) { //Open rosters
         pw.println("   " + schools.get(s).getRosters().get(r).getTitle() + "{");
        //         rosterName{
          for (int st = 0; st < schools.get(s).getRosters().get(r).getStudents().size(); st++) { //Open students
            pw.println("    " + schools.get(s).getRosters().get(r).getStudents().get(st).getName() + "," +
                    schools.get(s).getRosters().get(r).getStudents().get(st).getId());
        //          studentName{
          }



          //Close brackets (if they exist)
          if (schools.get(s).getRosters().get(r).getStudents().size() != 0)
          pw.println("   }"); //Close students
        }
        if (schools.get(s).getRosters().size() != 0)
        pw.println("  }"); //Close rosters
      }
      pw.println(" }"); //Close schools
      pw.print("}"); //Footer
    } catch (IOException e) {
      System.out.println("Couldn't write file. Maybe access is denied?");
      System.err.println(e);
    }
  }

  /**
   * List all files in the current working directory of type .sch
   * @return *.sch files
   */
  public ArrayList<String> dirContent() {
    File dir = new File(".");
    ArrayList<String> dirContent = new ArrayList<>();
    File[] files = dir.listFiles();
    for (int i = 0; i < files.length; i++) {
      if(files[i].isFile()) {
        if(files[i].toString().endsWith(".sch"))
          dirContent.add(files[i].toString());
      }
    }
    return dirContent;
  }
}

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

/**
 * This class deals with the main searching for, and
 * editing of the file used to store information
 * In a scenario where it's more than one person using
 * this program we would instead store this file on a
 * server/ network somewhere
 */
public class FileHandling {
  private File file;
  private District userDistrict;
  private HashMap<School, ArrayList<Roster>> userSchools = new HashMap<>();
  private HashMap<Roster, ArrayList<Student>> userRosters = new HashMap<>();
  private HashMap<Teacher, ArrayList<Course>> userTeachers = new HashMap<>();
  private HashMap<Student, ArrayList<Course>> userStudents = new HashMap<>();

  /**
   * Reads the contents of a plain-text school data file, parses its contents, and copies over to memory
   * @param fileName String value of file to load (".\\filename.sch")
   */
  public void load(String fileName){
    Roster lastRoster = new Roster("Backup", 1); //Store a previous roster in case
    int lineNumber = 0;                                       //a student gets separated
    // "Find" the file
    File data = new File(fileName);
    // Try this and catch the exceptions
    try(Scanner readLine = new Scanner(data)){
      readLine.useDelimiter(",|\\r|\\n"); //Ignore commas, carriage returns, and newline tokens in a file
      // If the file exists...
      if(data.exists()) {
        // For every line in the file
        for (int i = 0; i < data.length(); i++) {
          // While we have a next line
          String readData = "";
          if (readLine.hasNext()) //Wrap all hasNexts in a check or suffer
            readData = readLine.next();
          if (!(readData.equals(""))) {
            switch (readData) { //Read the first string before a "," in a line
              case "district":
                userDistrict = new District(readLine.next(), readLine.next()); //Get the next two, the name and the UUID
                lineNumber ++;
                break;

              case "school":
                ArrayList<Roster> schoolRosters = new ArrayList<>();
                String userSchoolTitle = readLine.next();
                String userSchoolUUID = readLine.next();
                if (readLine.hasNext()) { String ignore = readLine.next(); } //Absorb the "" at the end of a line
                while (readLine.hasNext() && readLine.next().equals("roster")) { //(but only if it exists first)
                  Roster userRoster = new Roster(readLine.next(), Integer.parseInt(readLine.next()), readLine.next());
                  lineNumber ++; //Done constructing, we've (hopefully) just finished a line
                  lastRoster = userRoster;
                  schoolRosters.add(userRoster); //Populate an arraylist to use for the hashmap later
                  ArrayList<Student> rosterStudents = new ArrayList<>();

                  //while loops are used to load by hierarchy, so that empty parents (like a student without courses)
                  //don't make the next student get constructed as a course
                  if (readLine.hasNext()) {
                    String ignore = readLine.next();
                  }
                  while (readLine.hasNext() && readLine.next().equals("student")) {
                    Student userStudent = new Student(readLine.next(), Integer.parseInt(readLine.next()),
                        readLine.next());
                    lineNumber ++;
                    rosterStudents.add(userStudent);
                    ArrayList<Course> studentCourses = new ArrayList<>();

                    if (readLine.hasNext()) {
                      String ignore = readLine.next();
                    }
                    while (readLine.hasNext() && readLine.next().equals("course")) {
                      studentCourses.add(new Course(readLine.next()));
                    }
                    userStudents.put(userStudent, studentCourses);
                  }
                  userRosters.put(userRoster, rosterStudents);
                }

                ArrayList<Teacher> schoolTeachers = new ArrayList<>();
                if (readLine.hasNext()) {
                  String ignore = readLine.next();
                }
                while (readLine.hasNext() && readLine.next().equals("teacher")) {
                  Teacher schoolTeacher = new Teacher(readLine.next(), Integer.parseInt(readLine.next()),
                      readLine.next());
                  lineNumber ++;
                  schoolTeachers.add(schoolTeacher);
                  ArrayList<Course> teacherCourses = new ArrayList<>();

                  if (readLine.hasNext()) {
                    String ignore = readLine.next();
                  }
                  while (readLine.hasNext() && readLine.next().equals("course")) {
                    Course teacherCourse = new Course(readLine.next());
                    lineNumber ++;
                    teacherCourses.add(teacherCourse);
                  }
                  userTeachers.put(schoolTeacher, teacherCourses);
                }
                userSchools.put(new School(schoolRosters, schoolTeachers, userSchoolTitle, userSchoolUUID),
                    schoolRosters);
                break;

              case "student":
                System.out.println("Student got separated from roster. Adding anyway.");
                ArrayList<Student> addBackup = userRosters.get(lastRoster);
                addBackup.add(new Student(readLine.next(), Integer.parseInt(readLine.next()), readLine.next()));
                userRosters.put(lastRoster, addBackup);
                System.out.println("Added " + addBackup.get(addBackup.size() - 1).getName() + ", " +
                    addBackup.get(addBackup.size() - 1).getMemberUUID());
                //Let this fall through so the user can know what type got rejected anyway

              default:
                System.out.println(fileName + ": Unknown type " + readData + " at line " + (lineNumber + 1));
                break;
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Scans all data within a district, and systemically copies it to collections in memory with setUserData
   * @param filename String title of the district
   * @param userDistrict District object containing all data to write to file
   */
  public void save(String filename, District userDistrict) {
    this.userDistrict = userDistrict;
    filename += System.currentTimeMillis() +  ".sch";
    this.file = new File(filename);
    setUserData(userDistrict);
    //Use our own extension so it doesn't mess with the user's defaults
    try(PrintWriter pw = new PrintWriter(this.file)) { //Avoid circular references by writing with hierarchy
      pw.println("district," + userDistrict.getDistrictTitle() + "," + userDistrict.getDistrictUUID());
      for (School scKey: userSchools.keySet()) {
        pw.println("school," + scKey.getSchoolTitle() + "," + scKey.getSchoolUUID());

        for (Roster rKey : userRosters.keySet()) {
          pw.println("roster," + rKey.getRosterTitle() + "," + rKey.getRosterSize() + "," + rKey.getRosterUUID());

          for (Student stKey : userStudents.keySet()) {
            pw.println("student," + stKey.getName() + "," + stKey.getId() + "," + stKey.getMemberUUID());

            for (Course courses : stKey.getCourses()) {
              pw.println("course," + courses.getCourseName());
            }
          }
        }
        for (Teacher tKey: userTeachers.keySet()) {
          pw.println("teacher," + tKey.getName() + "," + tKey.getId() + "," + tKey.getMemberUUID());

          for (Course courses : tKey.getCourses()) {
            pw.println("course," + courses.getCourseName());
          }
        }

      }
    } catch (IOException e) {
      System.out.println("Couldn't write file. Maybe access is denied?");
      System.err.println(e);
    }
  }

  /**
   * Populate a collection of all user data in memory
   * @param userDistrict Current user district
   */
  public void setUserData(District userDistrict) {
    int schoolCount = userDistrict.getSchools().size();
    this.userDistrict = userDistrict;
    for (int sc = 0; sc < schoolCount; sc++) {
      School currentSc = userDistrict.getSchools().get(sc);
      ArrayList<Roster> rList = userDistrict.getSchools().get(sc).getRosters();
      ArrayList<Teacher> tList = userDistrict.getSchools().get(sc).getTeachers();
      userSchools.put(currentSc, rList);
      int rosterCount = userDistrict.getSchools().get(sc).getRosters().size();
      int teacherCount = userDistrict.getSchools().get(sc).getTeachers().size();

      for (int r = 0; r < rosterCount; r++) {
        Roster currentR = rList.get(r);
        ArrayList<Student> sList = currentR.getStudents();
        userRosters.put(currentR, sList);
        int studentCount = sList.size();

        for (int s = 0; s < studentCount; s++) {
          Student currentS = sList.get(s);
          ArrayList<Course> cList = currentS.getCourses();
          userStudents.put(currentS, cList);
        }
      }

      for (int t = 0; t < teacherCount; t++) {
        Teacher currentT = tList.get(t);
        ArrayList<Course> cList = currentT.getCourses();
        userTeachers.put(currentT, cList);
      }
    }
  }

  /**
   * Accessor for all user data
   * @return All district read from a file
   */
  public District getUserData() {

    //Pull data from class collections
    ArrayList<School> userDistrictSchools = new ArrayList<>();
    ArrayList<School> schools = new ArrayList<>();
    ArrayList<Roster> schoolRosters = new ArrayList<>();
    ArrayList<Student> schoolStudents = new ArrayList<>();
    ArrayList<Teacher> schoolTeachers = new ArrayList<>();

    for (School sc : userSchools.keySet())
      userDistrictSchools.add(sc);
    userDistrict.setSchools(userDistrictSchools);

    for (School sc : userSchools.keySet()) {
      schools.add(sc);

      for (Roster r : userRosters.keySet()) {
        r.setStudents(userRosters.get(r));
        schoolRosters.add(r);

        for (Student st : userStudents.keySet()) {
          st.setCourses(userStudents.get(st));
          schoolStudents.add(st);
        }
      }

      schoolTeachers = new ArrayList<>();
      for (Teacher t : userTeachers.keySet()) {
        t.setCourses(userTeachers.get(t));
        schoolTeachers.add(t);
      }
    }

    //Store data to a returnable object
    for (School sc : schools) {
      sc.setRosters(schoolRosters);
      sc.setTeachers(schoolTeachers);
    }

    userDistrict.setSchools(schools);

    return new District(userDistrict.getDistrictTitle(), schools, userDistrict.getDistrictUUID());


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

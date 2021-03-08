import java.util.ArrayList;

public interface SchoolMember {
  int getId();
  String getName();
  ArrayList<Course> getCourses();
  void addCourses(String courseName);
  String catCourses();
}

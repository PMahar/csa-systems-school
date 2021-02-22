public interface SchoolMember {
  int getId();
  String getName();
  Course[] getCourses();
  void addCourses(String courseName);
  String catCourses();
}

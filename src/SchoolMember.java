import java.util.ArrayList;

public interface SchoolMember {
  int id();

  String name();

  ArrayList<Course> courses();

  String toString();
}
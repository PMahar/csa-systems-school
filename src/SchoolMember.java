import java.util.ArrayList;

public interface SchoolMember {
  int id();

  String name();

  String memberUUID();

  ArrayList<Course> courses();

  String toString();
}
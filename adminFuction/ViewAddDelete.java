package adminFuction;
import connection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;;
public class ViewAddDelete{

    public static void view(String dept,int sem){
        String query = "SELECT course_id, course_code, course_name, " +
                       "credits, schedule, prerequisites " +
                       "FROM courses " +
                       "WHERE department = ? AND semester = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, dept);
            stmt.setInt(2, sem);

            ResultSet rs = stmt.executeQuery();

    System.out.println("\nDepartment: "+dept+ "\nSem: "+sem);
    System.out.println("-------------------------------------");
    while (rs.next()) {
        System.out.println("Course Code: "+rs.getString("course_code"));
        System.out.println("Course Name: "+rs.getString("course_name"));
        System.out.println("Credits: "+rs.getInt("credits"));
        System.out.println("Schedule: "+rs.getString("schedule"));
        System.out.println("Prerequisite: "+rs.getString("prerequisites"));
        System.out.println();
    }
        }
        catch(SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        }
   }
   public static void add(String dept, int sem) {
         Scanner sc= new Scanner(System.in);
      System.out.println("\n===== ADD NEW COURSE =====");
    System.out.println("Department : " + dept);
    System.out.println("Semester   : " + sem);
    System.out.println("--------------------------");

    // Course ID
    System.out.print("Enter Course ID       : ");
    String courseId = sc.nextLine().toUpperCase().trim();

    // Course Code
    System.out.print("Enter Course Code     : ");
    String courseCode = sc.nextLine().toUpperCase().trim();

    // Course Name
    System.out.print("Enter Course Name     : ");
    String courseName = sc.nextLine().trim();

    // Credits — only allow 2 or 4
    int credits = 0;
    while (credits != 2 && credits != 4) {
        System.out.print("Enter Credits (2/4)   : ");
        try {
            credits = Integer.parseInt(sc.nextLine().trim());
            if (credits != 2 && credits != 4) {
                System.out.println("  Credits must be 2 or 4. Try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("  Invalid input. Enter 2 or 4.");
        }
    }

    // Professor ID
    System.out.print("Enter Professor ID    : ");
    String professorId = sc.nextLine().toUpperCase().trim();

    // Schedule
    System.out.print("Enter Schedule        : ");
    String schedule = sc.nextLine().trim();

    // Capacity
    int capacity = 0;
    while (capacity <= 0) {
        System.out.print("Enter Capacity        : ");
        try {
            capacity = Integer.parseInt(sc.nextLine().trim());
            if (capacity <= 0) {
                System.out.println("  Capacity must be greater than 0.");
            }
        } catch (NumberFormatException e) {
            System.out.println("  Invalid input. Enter a number.");
        }
    }

    // Prerequisites
    System.out.print("Enter Prerequisites (or press ENTER for none) : ");
    String prerequisites = sc.nextLine().trim();
    if (prerequisites.isEmpty()) prerequisites = "None";

    // Confirm
    System.out.println("\n--- Confirm Details ---");
    System.out.println("Course ID     : " + courseId);
    System.out.println("Course Code   : " + courseCode);
    System.out.println("Course Name   : " + courseName);
    System.out.println("Department    : " + dept);
    System.out.println("Semester      : " + sem);
    System.out.println("Credits       : " + credits);
    System.out.println("Professor ID  : " + professorId);
    System.out.println("Schedule      : " + schedule);
    System.out.println("Capacity      : " + capacity);
    System.out.println("Prerequisites : " + prerequisites);
    System.out.print("\nConfirm? (yes/no) : ");
    String confirm = sc.nextLine().trim();
    sc.close();
    if (!confirm.equalsIgnoreCase("yes")) {
        System.out.println("Cancelled. Course not added.");
        return;
    }
        

    // Insert into database
    String query = "INSERT INTO courses (course_id, course_code, course_name, department, " +
                   "semester, credits, professor_id, schedule, capacity, prerequisites) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, courseId);
        ps.setString(2, courseCode);
        ps.setString(3, courseName);
        ps.setString(4, dept);         // from parameter
        ps.setInt   (5, sem);          // from parameter
        ps.setInt   (6, credits);
        ps.setString(7, professorId);
        ps.setString(8, schedule);
        ps.setInt   (9, capacity);
        ps.setString(10, prerequisites);

        ps.executeUpdate();
        System.out.println("\nCourse added successfully!");

    } catch (SQLException e) {
        if (e.getErrorCode() == 1062) {
            System.out.println("Course ID already exists! Try a different ID.");
        } else {
            e.printStackTrace();
        }
    }
}
public static void delete(String dep, int sem) {
    Scanner sc = new Scanner(System.in);

    // First, show available courses for this dept/sem
    view(dep, sem);

    System.out.print("Enter Course ID to delete: ");
    String courseId = sc.nextLine().toUpperCase().trim();

    // Fetch and display the course to confirm
    String selectQuery = "SELECT * FROM courses WHERE course_id = ? AND department = ? AND semester = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(selectQuery)) {

        ps.setString(1, courseId);
        ps.setString(2, dep);
        ps.setInt(3, sem);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            System.out.println("No course found with ID: " + courseId + " in " + dep + ", Sem " + sem);
            return;
        }

        // Show course details before confirming
        System.out.println("\n--- Course to Delete ---");
        System.out.println("Course ID   : " + rs.getString("course_id"));
        System.out.println("Course Code : " + rs.getString("course_code"));
        System.out.println("Course Name : " + rs.getString("course_name"));
        System.out.println("Credits     : " + rs.getInt("credits"));
        System.out.println("Schedule    : " + rs.getString("schedule"));

        System.out.print("\nAre you sure you want to delete this course? (yes/no): ");
        String confirm = sc.nextLine().trim();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Cancelled. Course not deleted.");
            return;
        }

        // Delete the course
        String deleteQuery = "DELETE FROM courses WHERE course_id = ? AND department = ? AND semester = ?";
        try (PreparedStatement delPs = con.prepareStatement(deleteQuery)) {
            delPs.setString(1, courseId);
            delPs.setString(2, dep);
            delPs.setInt(3, sem);

            int rows = delPs.executeUpdate();
            if (rows > 0) {
                System.out.println("Course deleted successfully!");
            } else {
                System.out.println("Deletion failed. Please try again.");
            }
        }

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
}
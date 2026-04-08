package adminFuction;

import connection.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class AssignProfessorToCourse {

    private static Scanner scanner = new Scanner(System.in);

    public static void assignProfessor() {
        System.out.println("\n========== ASSIGN PROFESSOR TO COURSE ==========");

        // Step 1: Show all professors
        displayProfessors();

        // Step 2: Show all courses
        displayCourses();

        // Step 3: Take input
        System.out.print("\nEnter Professor ID to assign: ");
        int professorId = scanner.nextInt();

        System.out.print("Enter Course ID to assign professor to: ");
        int courseId = scanner.nextInt();

        // Step 4: Validate professor exists
        if (!professorExists(professorId)) {
            System.out.println("Error: Professor with ID " + professorId + " not found.");
            return;
        }

        // Step 5: Validate course exists
        if (!courseExists(courseId)) {
            System.out.println("Error: Course with ID " + courseId + " not found.");
            return;
        }

        // Step 6: Assign professor to course
        String query = "UPDATE courses SET professor_id = ? WHERE course_id = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, professorId);
            stmt.setInt(2, courseId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nProfessor successfully assigned to course!");
                displayAssignment(professorId, courseId);
            } else {
                System.out.println("Assignment failed. Please try again.");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    // -------------------------------------------------------

    private static void displayProfessors() {
        String query = "SELECT professor_id, name, department, office FROM professors";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()
        ) {
            System.out.println("\n--- Available Professors ---");
            System.out.printf("%-5s %-25s %-15s %-10s%n", "ID", "Name", "Department", "Office");
            System.out.println("-".repeat(60));

            while (rs.next()) {
                System.out.printf("%-5d %-25s %-15s %-10s%n",
                    rs.getInt("professor_id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getString("office"));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching professors: " + e.getMessage());
        }
    }

    // -------------------------------------------------------

    private static void displayCourses() {
        String query = "SELECT c.course_id, c.course_code, c.course_name, c.department, " +
                       "c.semester, COALESCE(p.name, 'Unassigned') AS professor " +
                       "FROM courses c LEFT JOIN professors p ON c.professor_id = p.professor_id " +
                       "ORDER BY c.department, c.semester";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()
        ) {
            System.out.println("\n--- Available Courses ---");
            System.out.printf("%-5s %-10s %-30s %-12s %-4s %-20s%n",
                              "ID", "Code", "Course Name", "Department", "Sem", "Current Professor");
            System.out.println("-".repeat(85));

            while (rs.next()) {
                System.out.printf("%-5d %-10s %-30s %-12s %-4d %-20s%n",
                    rs.getInt("course_id"),
                    rs.getString("course_code"),
                    rs.getString("course_name"),
                    rs.getString("department"),
                    rs.getInt("semester"),
                    rs.getString("professor"));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching courses: " + e.getMessage());
        }
    }

    // -------------------------------------------------------

    private static boolean professorExists(int professorId) {
        String query = "SELECT professor_id FROM professors WHERE professor_id = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, professorId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if row found

        } catch (SQLException e) {
            System.err.println("Validation error: " + e.getMessage());
            return false;
        }
    }

    // -------------------------------------------------------

    private static boolean courseExists(int courseId) {
        String query = "SELECT course_id FROM courses WHERE course_id = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if row found

        } catch (SQLException e) {
            System.err.println("Validation error: " + e.getMessage());
            return false;
        }
    }

    // -------------------------------------------------------

    private static void displayAssignment(int professorId, int courseId) {
        String query = "SELECT c.course_code, c.course_name, c.department, " +
                       "c.semester, c.schedule, p.name AS professor_name " +
                       "FROM courses c JOIN professors p ON c.professor_id = p.professor_id " +
                       "WHERE c.course_id = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Assignment Summary ---");
                System.out.println("Professor : " + rs.getString("professor_name"));
                System.out.println("Course    : " + rs.getString("course_code") 
                                   + " - " + rs.getString("course_name"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("Semester  : " + rs.getInt("semester"));
                System.out.println("Schedule  : " + rs.getString("schedule"));
            }

        } catch (SQLException e) {
            System.err.println("Error displaying assignment: " + e.getMessage());
        }
    }
}
package studentfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TAFunctions {

    
    public static void viewGradesByCourse(Connection conn, int courseId) {

        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT s.name, g.grade FROM grades g " +
                "JOIN students s ON g.student_id = s.student_id " +
                "WHERE g.course_id=?"
            );

            ps.setInt(1, courseId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nStudent Grades:");

            while (rs.next()) {
                System.out.println(rs.getString("name") + " → " + rs.getString("grade"));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

   
    public static void updateGrade(Connection conn, int studentId, int courseId,
                                   String grade, double gradePoints) {

        try {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE grades SET grade=?, grade_points=? WHERE student_id=? AND course_id=?"
            );

            ps.setString(1, grade);
            ps.setDouble(2, gradePoints);
            ps.setInt(3, studentId);
            ps.setInt(4, courseId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Grade Updated Successfully!");
            } else {
                System.out.println("No record found!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
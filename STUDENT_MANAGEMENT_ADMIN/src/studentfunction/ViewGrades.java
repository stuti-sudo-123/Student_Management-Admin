package studentfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewGrades {

    public static void show(Connection conn, int studentId) {

        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT course_id, grade FROM grades WHERE student_id=?"
            );

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nGrades:");

            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String grade = rs.getString("grade");

                System.out.println("Course ID: " + courseId + " | Grade: " + grade);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
package studentFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewSchedule {

    public static void show(Connection conn, int studentId) {

        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT c.course_code, c.course_name, c.schedule " +
                "FROM enrollments e JOIN courses c ON e.course_id = c.course_id " +
                "WHERE e.student_id=? AND e.status='enrolled'"
            );

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nSchedule:");

            while (rs.next()) {
                String code = rs.getString("course_code");
                String name = rs.getString("course_name");
                String schedule = rs.getString("schedule");

                System.out.println(code + " | " + name + " | " + schedule);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
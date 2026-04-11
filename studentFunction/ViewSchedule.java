package studentFunction;

import java.sql.*;

public class ViewSchedule {

    public static void show(Connection conn, int studentId) throws Exception {

        String q = """
                SELECT c.course_code, c.course_name, c.schedule
                FROM enrollments e
                JOIN courses c ON e.course_id = c.course_id
                WHERE e.student_id=? AND e.status='enrolled'
                """;

        PreparedStatement ps = conn.prepareStatement(q);
        ps.setInt(1, studentId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
        }
    }
}

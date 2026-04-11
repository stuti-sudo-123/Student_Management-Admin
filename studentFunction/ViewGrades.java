package studentFunction;

import java.sql.*;

public class ViewGrades {

    public static void show(Connection conn, int studentId) throws Exception {

        String q = "SELECT course_id, grade FROM grades WHERE student_id=?";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setInt(1, studentId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println("Course ID: " + rs.getInt(1) + " | Grade: " + rs.getString(2));
        }
    }
}
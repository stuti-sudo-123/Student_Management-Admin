package studentFunction;

import java.sql.*;

public class ViewCourses {

    public static void show(Connection conn, String dept, int sem) throws Exception {

        String q = "SELECT course_id, course_code, course_name FROM courses WHERE department=? AND semester=?";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setString(1, dept);
        ps.setInt(2, sem);

        ResultSet rs = ps.executeQuery();

        System.out.println("\nCourses:");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
        }
    }
}
package studentFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewCourses {

    public static void show(Connection conn, String dept, int sem) {

        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT course_id, course_code, course_name FROM courses WHERE department=? AND semester=?"
            );

            ps.setString(1, dept);
            ps.setInt(2, sem);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nCourses:");

            while (rs.next()) {
                int id = rs.getInt("course_id");
                String code = rs.getString("course_code");
                String name = rs.getString("course_name");

                System.out.println(id + " | " + code + " | " + name);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
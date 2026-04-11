package studentFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

    public static String dept;
    public static int sem;

    public static int[] login(Connection conn, String email, String password) {

        try {
        
            PreparedStatement ps = conn.prepareStatement(
                "SELECT user_id FROM users WHERE email=? AND password=? AND role='student'"
            );

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            int userId = rs.getInt("user_id");

        
            ps = conn.prepareStatement(
                "SELECT student_id, major, current_semester FROM students WHERE user_id=?"
            );

            ps.setInt(1, userId);

            rs = ps.executeQuery();
            rs.next();

            dept = rs.getString("major");
            sem = rs.getInt("current_semester");

            int studentId = rs.getInt("student_id");

            return new int[]{studentId};

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
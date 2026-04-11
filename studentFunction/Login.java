package studentFunction;

import java.sql.*;

public class Login {

    public static String dept;
    public static int sem;

    public static int[] login(Connection conn, String email, String password) throws Exception {

        String q = "SELECT user_id FROM users WHERE email=? AND password=? AND role='student'";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) return null;

        int userId = rs.getInt("user_id");

        q = "SELECT student_id, major, current_semester FROM students WHERE user_id=?";
        ps = conn.prepareStatement(q);
        ps.setInt(1, userId);

        rs = ps.executeQuery();
        rs.next();

        dept = rs.getString("major");
        sem = rs.getInt("current_semester");

        return new int[]{rs.getInt("student_id")};
    }
}
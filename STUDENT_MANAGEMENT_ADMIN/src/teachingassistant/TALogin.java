package user;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;
import professorFunction.ResourseClose;
import studentfunction.TeachingAssistant;

public class TALogin implements Login<TeachingAssistant> {

    String email;
    String password;
    Scanner sc = new Scanner(System.in);

    public void getLoginCredentials(String email, String password) {
        System.out.print("Enter Email: ");
        this.email = sc.nextLine();

        System.out.print("Enter Password: ");
        this.password = sc.nextLine();
    }

    public boolean verifyPassword(String username, String password) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=? AND role='ta'"
            );

            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ResourseClose.closeAll(con, ps, rs);
        }
    }

    public TeachingAssistant directAccount() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            ps = con.prepareStatement(
                "SELECT s.*, t.course_id FROM students s " +
                "JOIN ta_mapping t ON s.student_id = t.student_id " +
                "JOIN users u ON s.user_id = u.user_id " +
                "WHERE u.email=? AND u.role='ta'"
            );

            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new TeachingAssistant(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("major"),
                    rs.getInt("year"),
                    rs.getInt("current_semester"),
                    rs.getInt("course_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ResourseClose.closeAll(con, ps, rs);
        }

        return null;
    }

    public void createAccount(String id) {
        System.out.println("TA account creation handled by admin.");
    }
}
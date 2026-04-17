package user;

import professorfunction.ResourseClose;
import studentfunction.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import connection.DBConnection;

public class StudentLogin implements Login<Student>
{
    String email;
    String password;
    Scanner sc = new Scanner(System.in);

    
    public void getLoginCredentials()
    {
        System.out.print("Enter Email: ");
        this.email = sc.nextLine();

        System.out.print("Enter Password: ");
        this.password = sc.nextLine();
    }

    
    public boolean verifyPassword(String username, String password)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND role = 'student'";

        try
        {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.email);
            ps.setString(2, this.password);

            rs = ps.executeQuery();

            return rs.next();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally
        {
            ResourseClose.closeAll(con, ps, rs);
        }
    }

    
    public Student directAccount()
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Student student = null;

        String sql = "SELECT s.* FROM students s JOIN users u ON s.user_id = u.user_id WHERE u.email = ? AND u.role = 'student'";

        try
        {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.email);

            rs = ps.executeQuery();

            if (rs.next())
            {
                student = new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("major"),
                    rs.getInt("year"),
                    rs.getInt("current_semester")
                );
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            ResourseClose.closeAll(con, ps, rs);
        }

        return student;
    }

    public void createAccount(String id)
    {
        System.out.println("Student account creation handled separately.");
    }


}
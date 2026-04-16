package user;

import professorFunction.ResourseClose;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import connection.DBConnection;

public class AdminLogin implements Login<Admin>
{
    String email;
    String password;
    Scanner sc = new Scanner(System.in);

    public void getLoginCredentials(String email, String password)
    {
        System.out.print("Enter username: ");
        this.email = sc.nextLine();

        System.out.print("Enter Password: ");
        this.password = sc.nextLine();
    }

    public boolean verifyPassword(String username, String password)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND role = 'admin'";

        try
        {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.email);
            ps.setString(2, this.password);
            rs = ps.executeQuery();

            if (rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
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

    public Admin directAccount()
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Admin admin = null;

        String sql = "SELECT * FROM users WHERE email = ? AND role = 'admin'";

        try
        {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.email);
            rs = ps.executeQuery();

            if (rs.next())
            {
                admin = new Admin(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email")
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

        return admin;
    }

    public void createAccount(String id)
    {
        System.out.println("Admin accounts cannot be created here.");
    }
}
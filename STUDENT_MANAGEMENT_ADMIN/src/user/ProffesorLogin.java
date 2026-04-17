package user;
import professorfunction.ResourseClose;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import connection.DBConnection;


public class ProffesorLogin implements Login<Professor>
{
    String email;
    String password;
    Scanner sc = new Scanner(System.in);

    public void getLoginCredentials()
    {
        System.out.print("Enter username: ");
        this.email= sc.nextLine();

        System.out.print("Enter Password: ");
        this.password = sc.nextLine();
    }

    public boolean verifyPassword(String username, String password)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM professors WHERE email = ? AND password = ?";

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
    public Professor directAccount()
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Professor professor = null;

        String sql = "SELECT * FROM professors WHERE email = ?";

        try
        {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.email);
            rs = ps.executeQuery();

            if (rs.next())
            {
                professor = new Professor(
                    rs.getInt("professor_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("department"),
                    rs.getString("office")
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

        return professor;
    }

    // METHOD 4 — create account
    public void createAccount(String id)
    {
        System.out.println("Professors are created by admin only");
    }
}
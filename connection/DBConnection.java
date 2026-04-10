import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConnection 
{
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // Load properties when class is first used
    static 
    {
        try 
        {
            Properties props = new Properties();
            FileInputStream file = new FileInputStream("config.properties");
            props.load(file);
            URL      = props.getProperty("db.url");
            USER     = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
            file.close();
        } 
        catch (IOException e) 
        {
            System.out.println("config.properties file not found!");
        }
    }

    public static Connection getConnection() 
    {
        try 
        {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } 
        catch (SQLException e) 
        {
            System.out.println("Connection Failed: " + e.getMessage());
            return null;
        }
    }
}

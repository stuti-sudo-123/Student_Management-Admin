package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

<<<<<<< HEAD
public class DBConnection 
{
=======
public class DBConnection {

>>>>>>> 316cd0e69d60269cb48bc1e6b6725ab4648503f5
    private static String URL;
    private static String USER;
    private static String PASSWORD;

<<<<<<< HEAD
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
=======
    // ✅ Everything in ONE static block
    static {
        // Load the MySQL driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL Driver not found: " + ex.getMessage());
        }

        // Load credentials from config.properties (try classpath + current working dir)
        Properties props = new Properties();
        try {
            java.io.InputStream in = DBConnection.class.getResourceAsStream("/connection/config.properties");
            if (in == null) {
                in = new FileInputStream("config.properties");
            }
            if (in == null) {
                throw new IOException("config.properties not found in classpath or working directory");
            }
            props.load(in);
            in.close();

            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            
        } catch (IOException e) {
            System.out.println("config.properties file not found or not readable: " + e.getMessage());
>>>>>>> 316cd0e69d60269cb48bc1e6b6725ab4648503f5
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
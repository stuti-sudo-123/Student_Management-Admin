// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
   private static String URL;
   private static String USER;
   private static String PASSWORD;

   public DBConnection() {
   }

   public static Connection getConnection() {
      try {
         Connection var0 = DriverManager.getConnection(URL, USER, PASSWORD);
         return var0;
      } catch (SQLException var1) {
         System.out.println("Connection Failed: " + var1.getMessage());
         return null;
      }
   }

   static {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException var3) {
         System.out.println("MySQL Driver not found: " + var3.getMessage());
      }

      Properties var0 = new Properties();

      try {
         Object var1 = DBConnection.class.getResourceAsStream("/connection/config.properties");
         if (var1 == null) {
            var1 = new FileInputStream("config.properties");
         }

         if (var1 == null) {
            throw new IOException("config.properties not found in classpath or working directory");
         }

         var0.load((InputStream)var1);
         ((InputStream)var1).close();
         URL = var0.getProperty("db.url");
         USER = var0.getProperty("db.user");
         PASSWORD = var0.getProperty("db.password");
      } catch (IOException var2) {
         System.out.println("config.properties file not found or not readable: " + var2.getMessage());
      }

   }
}

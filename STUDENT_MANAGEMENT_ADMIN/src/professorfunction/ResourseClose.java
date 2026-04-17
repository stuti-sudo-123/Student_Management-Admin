package professorfunction;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourseClose 
{
   public static void closeAll(Connection con,PreparedStatement pstmt,ResultSet rs)
   {
     
        if(rs !=null)
        {
            try 
            {
            rs.close();
            rs=null;
            } 
            catch (SQLException e) 
            {
            e.printStackTrace();
            }
        }
        if(pstmt !=null)
        {
            try 
            {
            pstmt.close();
            pstmt=null;
            } 
            catch (SQLException e) 
            {
            e.printStackTrace();
            }
        }
        if(con !=null)
        {
            try 
            {
            con.close();
            con=null;
            } 
            catch (SQLException e) 
            {
            e.printStackTrace();
            }
        }
      }
   }    


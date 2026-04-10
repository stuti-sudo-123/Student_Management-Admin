
package professorFunction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.DBConnection;

public class CourseDAO 
{
   public List<Course>  getCoursesByProfessor(int professorId) throws CourseNotFoundException    
    {

      List<Course> list = new ArrayList<>();
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM courses WHERE professor_id = ?";
      try
      {
        con=DBConnection.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(7, professorId);
        rs = ps.executeQuery();
        while (rs.next()) 
        {
          list.add(new Course(
          rs.getInt("course_id"),
          rs.getString("course_code"),
          rs.getString("course_name"),
          rs.getString("department"),
          rs.getInt("semester"),
          rs.getInt("credits"),
          rs.getInt("professor_id"),
          rs.getString("schedule"),
          rs.getInt("capacity"),
          rs.getString("prerequisites")
          ));
        }
        if (list.isEmpty())
        {
          throw new CourseNotFoundException("No courses found for professor ID: " + professorId);
        } 
      }

      catch(SQLException ex)
      {
        ex.printStackTrace();
      }
      finally
      {
        ResourseClose.closeAll(con, ps, rs);
      }
        return list;

    }
  

  void updateCourse(int professorId, int courseId,
  String schedule, int credits, /*String prerequisites,*/ 
  int capacity) throws CourseNotFoundException,UnauthorizedAccessException
  {
    String checkSql="SELECT course_id FROM courses WHERE course_id = ? AND professor_id = ?";
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try 
    {
      con= DBConnection.getConnection();
      ps = con.prepareStatement(checkSql);
      ps.setInt(1, courseId);
      ps.setInt(2, professorId);
      rs = ps.executeQuery();

      if (!rs.next())
      throw new UnauthorizedAccessException("You are not authorized to update course ID: " + courseId);
    }
    catch (SQLException ex) 
    {
     ex.printStackTrace();
    } 
    String updateSql = "UPDATE courses SET schedule = ?, credits = ?, prerequisites = ?, capacity = ? WHERE course_id = ? AND professor_id = ?";
    try 
    {
      con = DBConnection.getConnection();
      ps = con.prepareStatement(updateSql);
      ps.setString(1, schedule);
      ps.setInt   (2, credits);
      //ps.setString(3, prerequisites);
      ps.setInt   (4, capacity);
      ps.setInt   (5, courseId);
      ps.setInt   (6, professorId);

      int rows = ps.executeUpdate();

      if (rows == 0)
      throw new CourseNotFoundException("Course ID " + courseId + " not found.");

      System.out.println("Course updated successfully!");
    } 
    catch (SQLException ex) 
    {
      ex.printStackTrace();
    }
    finally
    {
      ResourseClose.closeAll(con,ps,rs);
    }
  }


}



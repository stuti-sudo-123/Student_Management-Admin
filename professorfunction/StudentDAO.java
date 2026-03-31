package professorfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import connection.DBConnection;

public class StudentDAO 
{
     Set<Student> getEnrolledStudents(int professerID,int courseID) throws CourseNotFoundException,UnauthorizedAccessException
     {
        Set<Student> studentSet = null;
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String Checkquery="SELECT course_id FROM courses WHERE course_id = ? AND professor_id = ?";
        try
        {
            con=DBConnection.getConnection();
            pstmt=con.prepareStatement(Checkquery);
            pstmt.setInt(1, courseID);
            pstmt.setInt(2, professerID);
            rs=pstmt.executeQuery();
            if(!rs.next())
            {
                throw new UnauthorizedAccessException(courseID+" not found for proffessor Id:"+professerID);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            ResourseClose.closeAll(con, pstmt, rs);
        }
        try
        {
           con =DBConnection.getConnection();
           pstmt=con.prepareStatement("SELECT *from enrollments");
           while(rs.next())
            {
                 Student s = new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("year"),
                    rs.getInt("current_semester"),
                    rs.getString("status") );
                    studentSet.add(s);
            } 
            if (studentSet.isEmpty()) 
            {
                throw new CourseNotFoundException("No students enrolled in course ID: " /*+ courseId*/);    
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            ResourseClose.closeAll(con, pstmt, rs);
        }

        return studentSet;
        
     }
}

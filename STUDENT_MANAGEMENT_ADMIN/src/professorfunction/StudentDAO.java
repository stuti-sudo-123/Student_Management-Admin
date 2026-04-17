package professorfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import connection.DBConnection;

public class StudentDAO
{
    Set<Student> getEnrolledStudents(int professorId, int courseId)
            throws CourseNotFoundException, UnauthorizedAccessException
    {
        // Step 1: Check that this course belongs to this professor
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String checkQuery = "SELECT course_id FROM courses WHERE course_id = ? AND professor_id = ?";

        try
        {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(checkQuery);
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, professorId);
            rs = pstmt.executeQuery();

            if (!rs.next())
            {
                throw new UnauthorizedAccessException(
                    courseId + " not found for professor ID: " + professorId);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            ResourseClose.closeAll(con, pstmt, rs);  // safe to close — done reading
        }

        // Step 2: Fetch enrolled students for this course
        Set<Student> studentSet = new HashSet<>();  // was null — caused NullPointerException

        String enrollQuery =
            "SELECT s.student_id, s.name, s.email, s.year, s.current_semester, s.major " +
            "FROM students s " +
            "JOIN enrollments e ON s.student_id = e.student_id " +
            "WHERE e.course_id = ?";  // was "SELECT * FROM enrollments" with no filter or JOIN

        try
        {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(enrollQuery);
            pstmt.setInt(1, courseId);
            rs = pstmt.executeQuery();             // was missing — rs was still closed from Step 1

            while (rs.next())
            {
                Student s = new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("current_semester"),         // was in wrong order / wrong column
                    rs.getInt("year"),
                    rs.getString("major")
                );
                studentSet.add(s);
            }

            if (studentSet.isEmpty())
            {
                throw new CourseNotFoundException(
                    "No students enrolled in course ID: " + courseId);
            }
        }
        catch (SQLException ex)
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
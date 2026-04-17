package professorfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.DBConnection;

public class CourseDAO
{
    public List<Course> getCoursesByProfessor(int professorId) throws CourseNotFoundException
    {
        List<Course> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM courses WHERE professor_id = ?";

        try
        {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, professorId);
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
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            ResourseClose.closeAll(con, ps, rs);
        }

        return list;
    }

    public void updateCourse(int professorId, int courseId,
            String schedule, int credits, int capacity)
            throws CourseNotFoundException, UnauthorizedAccessException
    {
        // Step 1: Check ownership
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String checkSql = "SELECT course_id FROM courses WHERE course_id = ? AND professor_id = ?";

        try
        {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(checkSql);
            ps.setInt(1, courseId);
            ps.setInt(2, professorId);
            rs = ps.executeQuery();

            if (!rs.next())
                throw new UnauthorizedAccessException(
                    "You are not authorized to update course ID: " + courseId);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            ResourseClose.closeAll(con, ps, rs);
        }

        // Step 2: Perform update
        // schedule=1, credits=2, capacity=3, course_id=4, professor_id=5
        String updateSql =
            "UPDATE courses SET schedule = ?, credits = ?, capacity = ? " +
            "WHERE course_id = ? AND professor_id = ?";

        try
        {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(updateSql);
            ps.setString(1, schedule);
            ps.setInt(2, credits);
            ps.setInt(3, capacity);
            ps.setInt(4, courseId);
            ps.setInt(5, professorId);

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
            ResourseClose.closeAll(con, ps, null);
        }
    }
}
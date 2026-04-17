
package feedback;
import professorfunction.ResourseClose;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import connection.DBConnection;

public class FeedbackDAO {

    public void addFeedback(Feedback<?> feedback) {
        String query = "INSERT INTO feedback (student_id, course_id, rating, comment) VALUES (?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();
            Object value = feedback.getFeedbackValue();
            Integer rating = null;
            String comment = null;

            if (value instanceof Integer) {
                rating = (Integer) value;
            } else if (value instanceof String) {
                comment = (String) value;
            }

            ps = con.prepareStatement(query);
            ps.setInt(1, feedback.getStudentId());
            ps.setInt(2, feedback.getCourseId());

            if (rating != null) {
                ps.setInt(3, rating);
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            if (comment != null) {
                ps.setString(4, comment);
            } else {
                ps.setNull(4, Types.VARCHAR);
            }

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Feedback added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error adding feedback: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    public List<Feedback<?>> getFeedbackByCourse(int courseId) {
        List<Feedback<?>> feedbackList = new ArrayList<>();
        String query = "SELECT * FROM feedback WHERE course_id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                int cId = rs.getInt("course_id");

                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");

                if (!rs.wasNull() && comment != null && !comment.isEmpty()) {
       
                    Feedback<String> fb = new Feedback<>(studentId, cId, comment);
                    feedbackList.add(fb);
                } else {
                    Feedback<Integer> fb = new Feedback<>(studentId, cId, rating);
                    feedbackList.add(fb);
                }
            }

        }
        catch (SQLException e) 
        {
            System.out.println("Error fetching feedback: " + e.getMessage());
        }
        finally 
        {
           ResourseClose.closeAll(con, ps, rs);
        }
        

        return feedbackList;
    }
}
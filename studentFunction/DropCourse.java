package studentFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DropCourse {

    public static void drop(Connection conn, int studentId) {

        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Course ID: ");
            int courseId = sc.nextInt();

            PreparedStatement ps = conn.prepareStatement(
                "UPDATE enrollments SET status='dropped' WHERE student_id=? AND course_id=?"
            );

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            ps.executeUpdate();

            System.out.println("Course Dropped!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
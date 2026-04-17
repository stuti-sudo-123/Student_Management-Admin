package studentfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class RegisterCourse {

    public static void register(Connection conn, int studentId, String dept, int sem) {

        try {
            Scanner sc = new Scanner(System.in);

        
            ViewCourses.show(conn, dept, sem);

            System.out.print("Enter Course ID: ");
            int courseId = sc.nextInt();

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO enrollments(student_id, course_id, enrollment_date, status) VALUES (?, ?, CURDATE(), 'enrolled')"
            );

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            ps.executeUpdate();

            System.out.println("Course Registered!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
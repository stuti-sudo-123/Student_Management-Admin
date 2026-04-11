package studentFunction;

import java.sql.*;
import java.util.Scanner;

public class RegisterCourse {

    static Scanner sc = new Scanner(System.in);

    public static void register(Connection conn, int studentId, String dept, int sem) throws Exception {

        ViewCourses.show(conn, dept, sem);

        System.out.print("Enter Course ID: ");
        int cid = sc.nextInt();

        String q = "INSERT INTO enrollments(student_id, course_id, enrollment_date, status) VALUES (?, ?, CURDATE(), 'enrolled')";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setInt(1, studentId);
        ps.setInt(2, cid);

        ps.executeUpdate();
        System.out.println("Registered!");
    }
}
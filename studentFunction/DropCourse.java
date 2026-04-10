package studentFunction;

import java.sql.*;
import java.util.Scanner;

public class DropCourse {

    static Scanner sc = new Scanner(System.in);

    public static void drop(Connection conn, int studentId) throws Exception {

        System.out.print("Enter Course ID to drop: ");
        int cid = sc.nextInt();

        String q = "UPDATE enrollments SET status='dropped' WHERE student_id=? AND course_id=?";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setInt(1, studentId);
        ps.setInt(2, cid);

        ps.executeUpdate();
        System.out.println("Dropped!");
    }
}
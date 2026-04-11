package studentFunction;

import java.sql.*;
import java.util.Scanner;

public class SubmitComplaint {

    static Scanner sc = new Scanner(System.in);

    public static void submit(Connection conn, int studentId) throws Exception {

        sc.nextLine();

        System.out.print("Enter complaint: ");
        String desc = sc.nextLine();

        String q = "INSERT INTO complaints(student_id, description) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setInt(1, studentId);
        ps.setString(2, desc);

        ps.executeUpdate();
        System.out.println("Complaint submitted!");
    }
}
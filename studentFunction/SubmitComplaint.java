package studentFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class SubmitComplaint {

    public static void submit(Connection conn, int studentId) {

        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter complaint: ");
            String complaint = sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO complaints(student_id, description) VALUES (?, ?)"
            );

            ps.setInt(1, studentId);
            ps.setString(2, complaint);

            ps.executeUpdate();

            System.out.println("Complaint Submitted!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
package adminfunction;

import connection.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class HandleComplaints {

    private static Scanner scanner = new Scanner(System.in);

    public static void handleComplaints() {
        boolean back = false;

        while (!back) {
            System.out.println("\n========== HANDLE COMPLAINTS ==========");
            System.out.println("1. View All Complaints");
            System.out.println("2. Filter by Status (Pending / Resolved)");
            System.out.println("3. Filter by Date");
            System.out.println("4. Update Complaint Status");
            System.out.println("5. Back");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewAllComplaints();
                case 2 -> filterByStatus();
                case 3 -> filterByDate();
                case 4 -> updateComplaintStatus();
                case 5 -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // -------------------------------------------------------

    private static void viewAllComplaints() {
        String query = "SELECT c.complaint_id, s.name AS student_name, c.description, " +
                       "c.status, c.resolution_details, c.created_at " +
                       "FROM complaints c " +
                       "JOIN students s ON c.student_id = s.student_id " +
                       "ORDER BY c.created_at DESC";

        printComplaints(query);
    }

    // -------------------------------------------------------

    private static void filterByStatus() {
        System.out.println("\nFilter by:");
        System.out.println("1. Pending");
        System.out.println("2. Resolved");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String status = (choice == 1) ? "pending" : "resolved";

        String query = "SELECT c.complaint_id, s.name AS student_name, c.description, " +
                       "c.status, c.resolution_details, c.created_at " +
                       "FROM complaints c " +
                       "JOIN students s ON c.student_id = s.student_id " +
                       "WHERE c.status = '" + status + "' " +
                       "ORDER BY c.created_at DESC";

        System.out.println("\n--- Complaints: " + status.toUpperCase() + " ---");
        printComplaints(query);
    }

    // -------------------------------------------------------

    private static void filterByDate() {
        System.out.print("\nEnter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter end date   (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        String query = "SELECT c.complaint_id, s.name AS student_name, c.description, " +
                       "c.status, c.resolution_details, c.created_at " +
                       "FROM complaints c " +
                       "JOIN students s ON c.student_id = s.student_id " +
                       "WHERE DATE(c.created_at) BETWEEN '" + startDate + "' AND '" + endDate + "' " +
                       "ORDER BY c.created_at DESC";

        System.out.println("\n--- Complaints from " + startDate + " to " + endDate + " ---");
        printComplaints(query);
    }

    // -------------------------------------------------------

    private static void updateComplaintStatus() {
        // First show all complaints
        viewAllComplaints();

        System.out.print("\nEnter Complaint ID to update: ");
        int complaintId = scanner.nextInt();
        scanner.nextLine();

        // Validate complaint exists
        if (!complaintExists(complaintId)) {
            System.out.println("Error: Complaint ID " + complaintId + " not found.");
            return;
        }

        System.out.println("\nUpdate status to:");
        System.out.println("1. Pending");
        System.out.println("2. Resolved");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String newStatus = (choice == 1) ? "pending" : "resolved";
        String resolutionDetails = null;

        // If resolved, ask for resolution details
        if (newStatus.equals("resolved")) {
            System.out.print("Enter resolution details: ");
            resolutionDetails = scanner.nextLine();
        }

        String query = "UPDATE complaints SET status = ?, resolution_details = ? WHERE complaint_id = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, newStatus);
            stmt.setString(2, resolutionDetails);  // null if pending
            stmt.setInt(3, complaintId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("\nComplaint #" + complaintId + 
                                   " updated to: " + newStatus.toUpperCase());
                if (resolutionDetails != null) {
                    System.out.println("Resolution: " + resolutionDetails);
                }
            } else {
                System.out.println("Update failed. Please try again.");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    // -------------------------------------------------------

    private static void printComplaints(String query) {
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()
        ) {
            System.out.printf("%n%-5s %-20s %-35s %-10s %-30s %-12s%n",
                              "ID", "Student", "Description", "Status",
                              "Resolution", "Date");
            System.out.println("-".repeat(115));

            boolean found = false;
            while (rs.next()) {
                found = true;
                String resolution = rs.getString("resolution_details");
                if (resolution == null) resolution = "N/A";

                // Truncate long text for display
                String desc = rs.getString("description");
                if (desc.length() > 33) desc = desc.substring(0, 30) + "...";
                if (resolution.length() > 28) resolution = resolution.substring(0, 25) + "...";

                System.out.printf("%-5d %-20s %-35s %-10s %-30s %-12s%n",
                    rs.getInt("complaint_id"),
                    rs.getString("student_name"),
                    desc,
                    rs.getString("status").toUpperCase(),
                    resolution,
                    rs.getString("created_at").substring(0, 10)); // show date only
            }

            if (!found) System.out.println("No complaints found.");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    // -------------------------------------------------------

    private static boolean complaintExists(int complaintId) {
        String query = "SELECT complaint_id FROM complaints WHERE complaint_id = ?";
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try 
        {
        	conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, complaintId);
            rs = stmt.executeQuery();
            return rs.next();

        } 
        catch (SQLException e) {
            System.err.println("Validation error: " + e.getMessage());
            return false;
        }
    }
}
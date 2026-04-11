package adminFuction;
 
import connection.DBConnection;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
 
public class ManageStudentRecords {
 
    public static void viewAllStudents() {
        String query = "SELECT student_id, name, email, major, year, current_semester FROM students ORDER BY student_id";
 
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
 
            System.out.println("\n========== ALL STUDENTS ==========");
            System.out.printf("%-5s %-20s %-30s %-12s %-5s %-4s%n",
                              "ID", "Name", "Email", "Major", "Year", "Sem");
            System.out.println("-".repeat(80));
 
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-5d %-20s %-30s %-12s %-5d %-4d%n",
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("major"),
                    rs.getInt("year"),
                    rs.getInt("current_semester"));
            }
            if (!found) System.out.println("No students found.");
 
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 
    public static void viewStudentById(int studentId) throws StudentNotFound {
        String query = "SELECT student_id, name, email, major, year, current_semester FROM students WHERE student_id = ?";
 
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
 
            if (!rs.next()) {
                throw new StudentNotFound("No student found with ID: " + studentId);
            }
 
            System.out.println("\n========== STUDENT DETAILS ==========");
            System.out.println("ID       : " + rs.getInt("student_id"));
            System.out.println("Name     : " + rs.getString("name"));
            System.out.println("Email    : " + rs.getString("email"));
            System.out.println("Major    : " + rs.getString("major"));
            System.out.println("Year     : " + rs.getInt("year"));
            System.out.println("Semester : " + rs.getInt("current_semester"));
 
            // Show grades
            String gradeQuery =
                "SELECT g.grade_id, c.course_code, c.course_name, g.grade, g.grade_points, g.semester " +
                "FROM grades g JOIN courses c ON g.course_id = c.course_id " +
                "WHERE g.student_id = ? ORDER BY g.semester";
 
            PreparedStatement gs = conn.prepareStatement(gradeQuery);
            gs.setInt(1, studentId);
            ResultSet gr = gs.executeQuery();
 
            System.out.println("\n--- Grades ---");
            System.out.printf("%-8s %-8s %-25s %-6s %-7s %-4s%n",
                              "GradeID", "Code", "Course", "Grade", "Points", "Sem");
            System.out.println("-".repeat(65));
 
            boolean hasGrades = false;
            double total = 0;
            int count = 0;
            while (gr.next()) {
                hasGrades = true;
                total += gr.getDouble("grade_points");
                count++;
                System.out.printf("%-8d %-8s %-25s %-6s %-7.1f %-4s%n",
                    gr.getInt("grade_id"),
                    gr.getString("course_code"),
                    gr.getString("course_name"),
                    gr.getString("grade"),
                    gr.getDouble("grade_points"),
                    gr.getString("semester"));
            }
 
            if (!hasGrades) {
                System.out.println("No grades found.");
            } else {
                System.out.printf("%nCGPA: %.2f%n", total / count);
            }
 
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 
    // ─────────────────────────────────────────────
    //  UPDATE personal info
    // ─────────────────────────────────────────────
 
    public static void updateStudentInfo(int studentId) throws StudentNotFound, InvalidInput {
        Scanner sc = new Scanner(System.in);
 
        // Check student exists
        String checkQuery = "SELECT name, email, major, year, current_semester, user_id FROM students WHERE student_id = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement check = conn.prepareStatement(checkQuery);
            check.setInt(1, studentId);
            ResultSet rs = check.executeQuery();
 
            if (!rs.next()) {
                throw new StudentNotFound("No student found with ID: " + studentId);
            }
 
            System.out.println("\n===== UPDATE STUDENT INFO =====");
            System.out.println("(Press ENTER to keep current value)");
            System.out.println("-------------------------------");
 
            // Name
            System.out.print("Name [" + rs.getString("name") + "]: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) name = rs.getString("name");
 
            // Email
            System.out.print("Email [" + rs.getString("email") + "]: ");
            String email = sc.nextLine().trim();
            if (email.isEmpty()) email = rs.getString("email");
            if (!email.contains("@")) throw new InvalidInput("Invalid email: " + email);
 
            // Major
            System.out.print("Major [" + rs.getString("major") + "]: ");
            String major = sc.nextLine().trim().toUpperCase();
            if (major.isEmpty()) major = rs.getString("major");
 
            // Year
            System.out.print("Year [" + rs.getInt("year") + "]: ");
            String yearInput = sc.nextLine().trim();
            int year = yearInput.isEmpty() ? rs.getInt("year") : Integer.parseInt(yearInput);
            if (year < 1 || year > 4) throw new InvalidInput("Year must be between 1 and 4.");
 
            // Semester
            System.out.print("Semester [" + rs.getInt("current_semester") + "]: ");
            String semInput = sc.nextLine().trim();
            int sem = semInput.isEmpty() ? rs.getInt("current_semester") : Integer.parseInt(semInput);
            if (sem < 1 || sem > 8) throw new InvalidInput("Semester must be between 1 and 8.");
 
            int userId = rs.getInt("user_id");
 
            // Confirm
            System.out.println("\n--- Confirm Changes ---");
            System.out.println("Name     : " + name);
            System.out.println("Email    : " + email);
            System.out.println("Major    : " + major);
            System.out.println("Year     : " + year);
            System.out.println("Semester : " + sem);
            System.out.print("Confirm? (yes/no) : ");
            String confirm = sc.nextLine().trim();
 
            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Cancelled. No changes made.");
                return;
            }
 
            // Update students table
            String updateStudent = "UPDATE students SET name=?, email=?, major=?, year=?, current_semester=? WHERE student_id=?";
            PreparedStatement ps = conn.prepareStatement(updateStudent);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, major);
            ps.setInt(4, year);
            ps.setInt(5, sem);
            ps.setInt(6, studentId);
            ps.executeUpdate();
 
            // Keep users table in sync
            String updateUser = "UPDATE users SET name=?, email=? WHERE user_id=?";
            PreparedStatement ps2 = conn.prepareStatement(updateUser);
            ps2.setString(1, name);
            ps2.setString(2, email);
            ps2.setInt(3, userId);
            ps2.executeUpdate();
 
            System.out.println("Student info updated successfully!");
 
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 
    // ─────────────────────────────────────────────
    //  UPDATE grade
    // ─────────────────────────────────────────────
 
    public static void updateGrade(int studentId) throws StudentNotFound, InvalidInput {
        Scanner sc = new Scanner(System.in);
 
        // Show current grades first
        try {
            viewStudentById(studentId);
        } catch (StudentNotFound e) {
            throw e;
        }
 
        System.out.print("\nEnter Grade ID to update: ");
        int gradeId;
        try {
            gradeId = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInput("Invalid Grade ID entered.");
        }
 
        // Check grade belongs to this student
        String checkQuery = "SELECT grade_id FROM grades WHERE grade_id = ? AND student_id = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement check = conn.prepareStatement(checkQuery);
            check.setInt(1, gradeId);
            check.setInt(2, studentId);
            ResultSet rs = check.executeQuery();
 
            if (!rs.next()) {
                throw new InvalidInput("Grade ID " + gradeId + " does not belong to student " + studentId);
            }
 
            System.out.print("New Grade (AA/AB/BB/BC/CC/CD/DD/FF): ");
            String newGrade = sc.nextLine().trim().toUpperCase();
 
            double points = gradeToPoints(newGrade); // throws InvalidInput if bad
 
            System.out.print("Confirm update to " + newGrade + " (" + points + " pts)? (yes/no): ");
            String confirm = sc.nextLine().trim();
            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Cancelled.");
                return;
            }
 
            String updateQuery = "UPDATE grades SET grade = ?, grade_points = ? WHERE grade_id = ?";
            PreparedStatement ps = conn.prepareStatement(updateQuery);
            ps.setString(1, newGrade);
            ps.setDouble(2, points);
            ps.setInt(3, gradeId);
            ps.executeUpdate();
 
            System.out.println("Grade updated successfully!");
 
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 
    // ─────────────────────────────────────────────
    //  ADD grade
    // ─────────────────────────────────────────────
 
    public static void addGrade(int studentId) throws StudentNotFound, InvalidInput {
        Scanner sc = new Scanner(System.in);
 
        // Check student exists
        String checkQuery = "SELECT student_id FROM students WHERE student_id = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement check = conn.prepareStatement(checkQuery);
            check.setInt(1, studentId);
            ResultSet rs = check.executeQuery();
            if (!rs.next()) throw new StudentNotFound("No student found with ID: " + studentId);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
 
        System.out.println("\n===== ADD GRADE =====");
        System.out.println("Student ID : " + studentId);
        System.out.println("---------------------");
 
        System.out.print("Enter Course ID    : ");
        int courseId;
        try {
            courseId = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInput("Invalid Course ID.");
        }
 
        System.out.print("Enter Semester     : ");
        String semester = sc.nextLine().trim();
        if (semester.isEmpty()) throw new InvalidInput("Semester cannot be empty.");
 
        System.out.print("Enter Grade (AA/AB/BB/BC/CC/CD/DD/FF): ");
        String grade = sc.nextLine().trim().toUpperCase();
        double points = gradeToPoints(grade); // throws InvalidInput if bad
 
        System.out.println("\n--- Confirm ---");
        System.out.println("Student ID : " + studentId);
        System.out.println("Course ID  : " + courseId);
        System.out.println("Semester   : " + semester);
        System.out.println("Grade      : " + grade + " (" + points + " pts)");
        System.out.print("Confirm? (yes/no): ");
        String confirm = sc.nextLine().trim();
 
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Cancelled.");
            return;
        }
 
        String insertQuery = "INSERT INTO grades (student_id, course_id, grade, grade_points, semester) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertQuery);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setString(3, grade);
            ps.setDouble(4, points);
            ps.setString(5, semester);
            ps.executeUpdate();
            System.out.println("Grade added successfully!");
 
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 
    // ─────────────────────────────────────────────
    //  HELPER: grade letter → points
    // ─────────────────────────────────────────────
 
    private static double gradeToPoints(String grade) throws InvalidInput {
        switch (grade) {
            case "AA": return 10.0;
            case "AB": return 9.0;
            case "BB": return 8.0;
            case "BC": return 7.0;
            case "CC": return 6.0;
            case "CD": return 5.0;
            case "DD": return 4.0;
            case "FF": return 0.0;
            default: throw new InvalidInput("Invalid grade: " + grade + ". Use AA/AB/BB/BC/CC/CD/DD/FF.");
        }
    
    }
}
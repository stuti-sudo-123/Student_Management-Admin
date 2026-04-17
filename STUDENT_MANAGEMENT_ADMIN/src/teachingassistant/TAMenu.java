package teachingassistant;
import java.sql.Connection;
import java.util.Scanner;

import TeachingAssistant;

public class TAMenu {

    public static void menu(Connection conn, TeachingAssistant ta) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- TA MENU ---");
            System.out.println("1. View Courses");
            System.out.println("2. Register Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Submit Complaint");
            System.out.println("5. View Student Grades");
            System.out.println("6. Update Student Grades");
            System.out.println("7. Logout");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: ta.viewCourses(conn); break;
                case 2: ta.registerCourse(conn); break;
                case 3: ta.dropCourse(conn); break;
                case 4: ta.submitComplaint(conn); break;
                case 5: ta.viewStudentGrades(conn); break;
                case 6: ta.updateStudentGrades(conn); break;
                case 7: System.out.println("Logging out..."); break;
                default: System.out.println("Invalid choice");
            }

        } while (choice != 7);
    }
}
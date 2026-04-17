package studentfunction;

import connection.DBConnection;
import feedback.FeedbackService;

import java.sql.Connection;
import java.util.Scanner;

public class Studentmenu {

    public void show(Student student) {

        Scanner sc = new Scanner(System.in);

        int studentId      = student.getStudentId();
        String dept        = student.getMajor();        // department = major field
        int sem            = student.getCurrentSemester();

        try {
            Connection conn = DBConnection.getConnection();

            int choice;

            do {
                System.out.println("\n===== STUDENT MENU =====");
                System.out.println("Welcome, " + student.getName() + "!");
                System.out.println("1. View Courses");
                System.out.println("2. Register Course");
                System.out.println("3. View Schedule");
                System.out.println("4. View Grades");
                System.out.println("5. Drop Course");
                System.out.println("6. Submit Complaint");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");

                choice = Integer.parseInt(sc.nextLine().trim());   // safe int reading

                switch (choice) {
                    case 1:
                        ViewCourses.show(conn, dept, sem);
                        break;

                    case 2:
                        RegisterCourse.register(conn, studentId, dept, sem);
                        break;

                    case 3:
                        ViewSchedule.show(conn, studentId);
                        break;

                    case 4:
                        ViewGrades.show(conn, studentId);
                        break;

                    case 5:
                        DropCourse.drop(conn, studentId);
                        break;

                    case 6:
                        SubmitComplaint.submit(conn, studentId);
                        break;
                    case 7: // or whatever option number fits your student menu
                        System.out.print("Enter Course ID to give feedback: ");
                        int courseId = Integer.parseInt(sc.nextLine());

                        System.out.println("Feedback Type:");
                        System.out.println("1. Rating");
                        System.out.println("2. Comment");
                        System.out.print("Enter choice: ");
                        int feedbackType = Integer.parseInt(sc.nextLine());

                        if (feedbackType == 1) {
                            System.out.print("Enter Rating (e.g. 1-5): ");
                            int rating = Integer.parseInt(sc.nextLine());
                            FeedbackService.giveFeedback(studentId, courseId, rating);
                        } else if (feedbackType == 2) {
                            System.out.print("Enter Comment: ");
                            String comment = sc.nextLine();
                            FeedbackService.giveFeedback(studentId, courseId, comment);
                        } else {
                            System.out.println("Invalid feedback type.");
                        }
                        break;

                    case 0:
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } while (choice != 0);

        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
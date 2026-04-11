package studentFunction;
import connection.DBConnection;
import java.sql.Connection;
import java.util.Scanner;

public class Studentmenu {

   void show(){

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        try (Connection conn = DBConnection.getConnection()) {

            int[] data = Login.login(conn, email, password);

            if (data == null) {
                System.out.println("Invalid login!");
                return;
            }

            int studentId = data[0];
            String dept = Login.dept;
            int sem = Login.sem;

            while (true) {
                System.out.println("\n1.View Courses");
                System.out.println("2.Register Course");
                System.out.println("3.View Schedule");
                System.out.println("4.View Grades");
                System.out.println("5.Drop Course");
                System.out.println("6.Submit Complaint");
                System.out.println("0.Exit");

                int ch = sc.nextInt();

                switch (ch) {
                    case 1 -> ViewCourses.show(conn, dept, sem);
                    case 2 -> RegisterCourse.register(conn, studentId, dept, sem);
                    case 3 -> ViewSchedule.show(conn, studentId);
                    case 4 -> ViewGrades.show(conn, studentId);
                    case 5 -> DropCourse.drop(conn, studentId);
                    case 6 -> SubmitComplaint.submit(conn, studentId);
                    case 0 -> { return; }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
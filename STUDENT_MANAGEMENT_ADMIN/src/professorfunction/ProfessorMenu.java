package professorfunction;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ProfessorMenu
{
    CourseDAO cd = new CourseDAO();
    StudentDAO sd = new StudentDAO();
    Scanner sc=new Scanner(System.in);

    public void show(int professorId) throws CourseNotFoundException, UnauthorizedAccessException
    {
        int choice = -1;

        do
        {
            System.out.println("\n===== PROFESSOR MENU =====");
            System.out.println("1. View My Courses");
            System.out.println("2. Update Course Details");
            System.out.println("3. View Enrolled Students");
            System.out.println("0. Logout");
            System.out.print("Enter Choice: ");

            try
            {
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice)
                {
                    case 1:
                        viewMyCourses(professorId);
                        break;

                    case 2:
                        System.out.print("Enter Course ID: ");
                        int courseId = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter new Schedule: ");
                        String schedule = sc.nextLine().trim();

                        System.out.print("Enter new Credits: ");
                        int credits = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter new Capacity: ");
                        int capacity = Integer.parseInt(sc.nextLine().trim());

                        updateCourseDetails(professorId, courseId, schedule, credits, capacity);
                        break;

                    case 3:
                        System.out.print("Enter Course ID: ");
                        int cid = Integer.parseInt(sc.nextLine().trim());

                        viewEnrolledStudents(professorId, cid);
                        break;

                    case 0:
                        System.out.println("Logged out.");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Please enter a valid number.");
            }

        } while (choice != 0);
    }

    void viewMyCourses(int professorId) throws CourseNotFoundException
    {
        try
        {
            List<Course> courses = cd.getCoursesByProfessor(professorId);
            System.out.println("\n=== Your Courses ===");
            for (Course c : courses)
            {
                System.out.println(c);
            }
        }
        catch (CourseNotFoundException ex)
        {
            System.out.println("Error: " + ex.getMessage());   // was silently swallowed before
        }
    }

    void updateCourseDetails(int professorId, int courseId,
            String schedule, int credits, int capacity)
            throws CourseNotFoundException, UnauthorizedAccessException
    {
        try
        {
            cd.updateCourse(professorId, courseId, schedule, credits, capacity);
        }
        catch (CourseNotFoundException | UnauthorizedAccessException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void viewEnrolledStudents(int professorId, int courseId)
            throws CourseNotFoundException, UnauthorizedAccessException
    {
        Set<Student> students = sd.getEnrolledStudents(professorId, courseId);
        System.out.println("\n===== Enrolled Students =====");
        for (Student s : students)
        {
            System.out.println(s);
        }
    }
}
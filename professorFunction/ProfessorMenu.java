package professorFunction;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ProfessorMenu
{
        CourseDAO cd=new CourseDAO();
        StudentDAO sd=new StudentDAO();
        Scanner sc=new Scanner(System.in);
        int professorId;
        int courseId;
        String schedule;
        int credits; 
        int capacity;
    public void show()throws CourseNotFoundException,UnauthorizedAccessException
    {  
     
        
        int choice=-1;
        System.out.println("\n===== PROFESSOR MENU =====");
        System.out.println("1. View My Courses");
        System.out.println("2. Update Course Details");
        System.out.println("3. View Enrolled Students");
        System.out.println("0. Logout");
        System.out.print("Enter Choice: ");
        choice=sc.nextInt();
        while (choice!=0) 
        {
            switch(choice)
            {
                case 1:
                    viewMyCourses(professorId);
                    break;
                case 2:
                    UpdateCourseDetails(professorId,courseId,schedule,credits,capacity);
                    break;
                case 3:
                    viewEnrolledStudents(professorId,courseId);
                    break;
                case 0:
                    System.out.println("logged out...");

            }
        }
       
    }
    void viewMyCourses(int professorId)throws CourseNotFoundException 
    {
        try 
        {
        List<Course> courses=cd.getCoursesByProfessor(professorId);
        System.out.println("\n=== Your Courses ===");
        for (Course c : courses) 
        {
            System.out.println(c);
        }
       }
       catch(CourseNotFoundException ex)
       {
          ex.getMessage();
       }
    }
    void UpdateCourseDetails(int professorId, int courseId,
    String schedule, int credits, int capacity)throws CourseNotFoundException, UnauthorizedAccessException 
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
    void viewEnrolledStudents(int professerID,int courseID) throws CourseNotFoundException,UnauthorizedAccessException
    {
        Set<Student> s=sd.getEnrolledStudents(professerID, courseID);
        System.out.println("\n=====enrolled students======");
        for(Student s1:s)
        {
           System.out.println(s1);
        }
    }

}
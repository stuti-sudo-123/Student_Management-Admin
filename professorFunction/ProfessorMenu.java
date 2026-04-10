package professorFunction;

import java.util.Scanner;

public class ProfessorMenu
{
    public void show()
    {   CourseDAO cd=new CourseDAO();
        StudentDAO sd=new StudentDAO();
        int professorId;
        int courseId;
        String schedule;
        int credits; 
        int capacity;
        Scanner sc=new Scanner(System.in);
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
            
        
     
        }
        }
        
    }


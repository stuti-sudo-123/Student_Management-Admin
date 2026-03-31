package professorfunction;

import java.util.Scanner;

public class ProfessorMenu
{
    public void show()
    {   
       
        Scanner sc=new Scanner(System.in);
        int choice=-1;
       
        while (choice!=0) 
        {
           
        System.out.println("\n===== PROFESSOR MENU =====");
        System.out.println("1. View My Courses");
        System.out.println("2. Update Course Details");
        System.out.println("3. View Enrolled Students");
        System.out.println("0. Logout");
        System.out.print("Enter Choice: ");
        choice=sc.nextInt(); 
        switch(choice)
        {
            case 1:
            ;
        }
        }
        void viewcourses(int professerID)
        {

        }
        
    }
}

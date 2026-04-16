package user;
import java.util.Scanner;

import professorFunction.CourseNotFoundException;
import professorFunction.ProfessorMenu;
import professorFunction.UnauthorizedAccessException;

class LoginMenu 
{
    Scanner sc=new Scanner(System.in);
    ProffesorLogin p=new ProffesorLogin();
    ProfessorMenu p1=new ProfessorMenu();
    public void showlogin()
    {
        System.out.println("enter username");
        String username=sc.nextLine();
        System.out.println("enter password");
        String password=sc.nextLine();
        p.getLoginCredentials(username, password);
        try
        {
          /*  boolean b= p.verifyPassword(username, password);
            if(b==true)
            {
                p.directAccount();
               // System.out.println("\nWelcome " +p1.getname() + "!");
               ProffesorMenu p1=new ProfessorMenu();

            }*/

            if (p.verifyPassword(username, password)) 
            {
                Professor professor = p.directAccount();
                System.out.println("\nWelcome " + professor.getname() + "!");
                ProfessorMenu menu = new ProfessorMenu();
                p1.show() ;
            }
            else
            {
                throw new InvalidLoginException("Invalid email or password");
            }
        }
        catch(InvalidLoginException  ex)
        {
            ex.printStackTrace();
        }
        catch(CourseNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch(UnauthorizedAccessException ex)
        {
            ex.printStackTrace();
        }
    }
}
public class Main 
{
    public static void main(String[] args) 
    {
       LoginMenu l=new LoginMenu();
       l.showlogin();
    }    
}
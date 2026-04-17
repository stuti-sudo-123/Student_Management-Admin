package user;

import java.util.Scanner;

import professorfunction.CourseNotFoundException;
import professorfunction.ProfessorMenu;
import professorfunction.UnauthorizedAccessException;

public class LoginMenu 
{
    Scanner sc = new Scanner(System.in);

    public void showlogin()
    {
        ProffesorLogin p = new ProffesorLogin();
        p.getLoginCredentials();  
        try
        {
            if (p.verifyPassword(p.email, p.password))
            {
                Professor professor = p.directAccount();
                System.out.println("\nWelcome " + professor.getname() + "!");
                ProfessorMenu menu = new ProfessorMenu();
                menu.show(professor.getprofessorId());
            }
            else  
            {
                throw new InvalidLoginException("Invalid email or password");
            }
        }
        catch (InvalidLoginException ex)
        {
            ex.printStackTrace();
        }
        catch (CourseNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (UnauthorizedAccessException ex)
        {
            ex.printStackTrace();
        }
    }
}
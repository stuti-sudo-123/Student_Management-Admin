package user;

import java.util.Scanner;

import adminfunction.admin;
import adminfunction.AdminMenu;
import professorfunction.CourseNotFoundException;
import professorfunction.ProfessorMenu;
import professorfunction.UnauthorizedAccessException;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do
        {
            System.out.println("\n===== WELCOME TO SVNIT =====");
            System.out.println("1. Professor Login");
            System.out.println("2. Student Login");
            System.out.println("3. Admin Login");
            System.out.println("0. Exit");
            System.out.print("Enter Choice: ");

            try
            {
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice)
                {
                    case 1:
                        ProffesorLogin pl = new ProffesorLogin();
                        pl.getLoginCredentials();               // pass sc
                        if (pl.verifyPassword(pl.email, pl.password))
                        {
                            Professor p = pl.directAccount();
                            System.out.println("\nWelcome " + p.getname() + "!");
                            ProfessorMenu pm = new ProfessorMenu();
                            try {
                                pm.show(p.getprofessorId());   // pass sc
                            } catch (CourseNotFoundException e) {
                                e.printStackTrace();
                            } catch (UnauthorizedAccessException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            throw new InvalidLoginException("Invalid email or password!");
                        }
                        break;

                    case 2:
                        StudentLogin sl = new StudentLogin();
                        sl.getLoginCredentials();               // pass sc
                        if (sl.verifyPassword(sl.email, sl.password))
                        {
                            studentfunction.Student s = sl.directAccount();
                            System.out.println("\nWelcome " + s.getName() + "!");
                            studentfunction.Studentmenu sm = new studentfunction.Studentmenu();
                            sm.show(s);               // pass sc
                        }
                        else
                        {
                            throw new InvalidLoginException("Invalid email or password!");
                        }
                        break;

                    case 3:
                        AdminLogin al = new AdminLogin();
                        al.getLoginCredentials();               // pass sc
                        if (al.verifyPassword(al.email, al.password))
                        {
                            admin a = al.directAccount();
                            System.out.println("\nWelcome " + a.getName() + "!");
                            AdminMenu.AdminMnue();
                        }
                        else
                        {
                            throw new InvalidLoginException("Invalid email or password!");
                        }
                        break;

                    case 0:
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
            catch (InvalidLoginException ex)
            {
                System.out.println("Login Failed: " + ex.getMessage());
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Please enter a valid number.");
            }

        } while (choice != 0);

        sc.close();
    }
}
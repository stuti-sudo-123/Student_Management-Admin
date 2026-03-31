package adminFuction;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        ViewAddDelete vad = new ViewAddDelete();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            // ── MENU ──────────────────────────────────────
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║     COURSE CATALOGUE MENU    ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. View Courses              ║");
            System.out.println("║  2. Add Courses               ║");
            System.out.println("║  3. Delete Course             ║");
            System.out.println("║  4. Exit                      ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                // ── VIEW ──────────────────────────────────
                case 1:
                    try {
                        System.out.print("Enter Department: ");
                        String dep = sc.nextLine();

                        System.out.print("Enter Semester: ");
                        int sem = sc.nextInt();
                        sc.nextLine();

                        vad.view(dep, sem);

                    } catch (DepInvalid ex) {
                        System.out.println("Error: " + ex.getMessage());
                    } catch (SemInvalid ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;

                // ── ADD ───────────────────────────────────
                case 2:
                    try {
                        System.out.print("Enter Department: ");
                        String dep = sc.nextLine();

                        System.out.print("Enter Semester: ");
                        int sem = sc.nextInt();
                        sc.nextLine();

                        List<Course> newCourses = new ArrayList<>();
                        String more = "yes";

                        while (more.equalsIgnoreCase("yes")) {
                            System.out.print("Enter Course Code: ");
                            String code = sc.nextLine();

                            System.out.print("Enter Course Name: ");
                            String name = sc.nextLine();

                            System.out.print("Enter Credits: ");
                            int credits = sc.nextInt();
                            sc.nextLine();

                            newCourses.add(new Course(code, name, credits)); 
                            System.out.println("Course added to list!");

                            System.out.print("Add another course? (yes/no): ");
                            more = sc.nextLine();
                        }

                        vad.add(newCourses, dep, sem);  
                        System.out.println("All courses added successfully!");

                    } catch (DepInvalid ex) {
                        System.out.println("Error: " + ex.getMessage());
                    } catch (SemInvalid ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;

                // ── DELETE ────────────────────────────────
                case 3:
                    try {
                        System.out.print("Enter Department: ");
                        String dep = sc.nextLine();

                        System.out.print("Enter Semester: ");
                        int sem = sc.nextInt();
                        sc.nextLine();

                        // show courses first so user knows which code to enter
                        vad.view(dep, sem);

                        System.out.print("Enter Course Code to delete: ");
                        String code = sc.nextLine();

                        vad.delete(code, dep, sem);     

                    } catch (DepInvalid ex) {
                        System.out.println("Error: " + ex.getMessage());
                    } catch (SemInvalid ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;

                // ── EXIT ──────────────────────────────────
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter 1-4.");
            }

        } while (choice != 4);   

        sc.close();
    }
}

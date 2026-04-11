package adminFuction;
 
import java.util.Scanner;
 
public class adminMenu {
    public static void AdminMnue() {
        Scanner sc = new Scanner(System.in);
 
        int choice = 0;
        do {
            System.out.println("-------------------------------");
            System.out.println("|   WELCOME TO ADMIN MENU    |");
            System.out.println("-------------------------------");
            System.out.println();
            System.out.println(" 1. Manage Course Catalogue");
            System.out.println(" 2. Manage Student Records");
            System.out.println(" 3. Assign Professors To Courses");
            System.out.println(" 4. Handle Complaints");
            System.out.println("-1. To Exit");
            System.out.print("Enter your choice : ");
 
            try {
                choice = sc.nextInt();
            } catch (Exception ex) {
                System.out.println("Enter an integer, not another data type.");
                sc.nextLine(); // flush bad input
                choice = 0;
                continue;
            }
 
            // ─────────────────────────────────────────────────────
            // 1. MANAGE COURSE CATALOGUE
            // ─────────────────────────────────────────────────────
            if (choice == 1) {
                int x = 0;
                do {
                    System.out.println("\n== Manage Course Catalogue ==");
                    System.out.println(" 1. View Course Catalogue");
                    System.out.println(" 2. Add Course");
                    System.out.println(" 3. Delete Course");
                    System.out.println("-1. Back To Main Menu");
                    System.out.print("Enter your choice : ");
 
                    try {
                        x = sc.nextInt();
                    } catch (Exception ex) {
                        System.out.println("Enter an integer, not another data type.");
                        sc.nextLine();
                        x = 0;
                        continue;
                    }
 
                    if (x == 1 || x == 2 || x == 3) {
                        sc.nextLine(); // consume newline before reading dept
                        System.out.print("Enter The Department (AI/CSE/ECE/ELECTRICAL/MECHANICAL/CIVIL/CHEMICAL) : ");
                        String dept = sc.nextLine().trim().toUpperCase();
 
                        try {
                            if (dept.equals("AI") || dept.equals("CSE") || dept.equals("ELECTRICAL") ||
                                dept.equals("ECE") || dept.equals("MECHANICAL") || dept.equals("CIVIL") ||
                                dept.equals("CHEMICAL")) {
 
                                System.out.print("Enter Semester (1-8) : ");
                                int sem = sc.nextInt();
 
                                try {
                                    if (sem >= 1 && sem <= 8) {
                                        if      (x == 1) ViewAddDelete.view(dept, sem);
                                        else if (x == 2) ViewAddDelete.add(dept, sem);
                                        else             ViewAddDelete.delete(dept, sem);
                                    } else {
                                        throw new SemInvalid("Semester must be between 1 and 8.");
                                    }
                                } catch (SemInvalid ex) {
                                    System.out.println("Error: " + ex.getMessage());
                                }
 
                            } else {
                                throw new DepInvalid("Department entered is invalid: " + dept);
                            }
                        } catch (DepInvalid ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
 
                    } else if (x == -1) {
                        System.out.println("Going To Main Menu...");
                    } else {
                        System.out.println("Please enter a correct choice!");
                    }
 
                } while (x != -1);
 
            // ─────────────────────────────────────────────────────
            // 2. MANAGE STUDENT RECORDS
            // ─────────────────────────────────────────────────────
            } else if (choice == 2) {
                int x = 0;
                do {
                    System.out.println("\n== Manage Student Records ==");
                    System.out.println(" 1. View All Students");
                    System.out.println(" 2. View Student Detail By ID");
                    System.out.println(" 3. Update Student's Personal Information");
                    System.out.println(" 4. Update Student's Grade");
                    System.out.println(" 5. Add Grade For Student");
                    System.out.println("-1. Back To Main Menu");
                    System.out.print("Enter your choice : ");
 
                    try {
                        x = sc.nextInt();
                    } catch (Exception ex) {
                        System.out.println("Enter an integer, not another data type.");
                        sc.nextLine();
                        x = 0;
                        continue;
                    }
 
                    switch (x) {
                        case 1 -> ManageStudentRecords.viewAllStudents();
 
                        case 2 -> {
                            System.out.print("Enter Student ID : ");
                            try {
                                int id = sc.nextInt();
                                ManageStudentRecords.viewStudentById(id);
                            } catch (Exception ex) {
                                System.out.println("Error: " + ex.getMessage());
                                sc.nextLine();
                            }
                        }
 
                        case 3 -> {
                            System.out.print("Enter Student ID : ");
                            try {
                                int id = sc.nextInt();
                                ManageStudentRecords.updateStudentInfo(id);
                            } catch (Exception ex) {
                                System.out.println("Error: " + ex.getMessage());
                                sc.nextLine();
                            }
                        }
 
                        case 4 -> {
                            System.out.print("Enter Student ID : ");
                            try {
                                int id = sc.nextInt();
                                ManageStudentRecords.updateGrade(id);
                            } catch (Exception ex) {
                                System.out.println("Error: " + ex.getMessage());
                                sc.nextLine();
                            }
                        }
 
                        case 5 -> {
                            System.out.print("Enter Student ID : ");
                            try {
                                int id = sc.nextInt();
                                ManageStudentRecords.addGrade(id);
                            } catch (Exception ex) {
                                System.out.println("Error: " + ex.getMessage());
                                sc.nextLine();
                            }
                        }
 
                        case -1 -> System.out.println("Going To Main Menu...");
 
                        default -> System.out.println("Please enter a correct choice!");
                    }
 
                } while (x != -1);
 
            // ─────────────────────────────────────────────────────
            // 3. ASSIGN PROFESSORS TO COURSES
            // ─────────────────────────────────────────────────────
            } else if (choice == 3) {
                AssignProfessorToCourse.assignProfessor();
 
            // ─────────────────────────────────────────────────────
            // 4. HANDLE COMPLAINTS
            // ─────────────────────────────────────────────────────
            } else if (choice == 4) {
                HandleComplaints.handleComplaints();
 
            } else if (choice == -1) {
                System.out.println("Exiting Admin Menu. Goodbye!");
 
            } else {
                System.out.println("Please enter a correct choice!");
            }
 
        } while (choice != -1);
        sc.close();
    }
}
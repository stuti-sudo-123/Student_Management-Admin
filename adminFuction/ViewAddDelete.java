package adminFuction;
import connection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;;
public class ViewAddDelete{

    public static void view(String dept,int sem) throws DepInvalid,SemInvalid{
  dept = dept.toUpperCase();
        if (!dept.equals("AI") && !dept.equals("CSE") && !dept.equals("ELECTRICAL") &&
            !dept.equals("ECE") && !dept.equals("MECHANICAL") && !dept.equals("CIVIL") &&
            !dept.equals("CHEMICAL")) {
            throw new DepInvalid("Department entered is invalid: " + dept);
        }

        if (sem < 1 || sem > 8) {
            throw new SemInvalid("Semester must be between 1 and 8");
        }
        String query = "SELECT course_id, course_code, course_name, " +
                       "credits, schedule, prerequisites " +
                       "FROM courses " +
                       "WHERE department = ? AND semester = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, dept);
            stmt.setInt(2, sem);

            ResultSet rs = stmt.executeQuery();

    System.out.println("\nDepartment: "+dept+ "\nSem: "+sem);
    System.out.println("-------------------------------------");
    while (rs.next()) {
        System.out.println("Course Code: "+rs.getString("course_code"));
        System.out.println("Course Name: "+rs.getString("course_name"));
        System.out.println("Credits: "+rs.getInt("credits"));
        System.out.println("Schedule: "+rs.getString("schedule"));
        System.out.println("Prerequisite: "+rs.getString("prerequisites"));
        System.out.println();
    }
        }
        catch(SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        }
   }
   public static void main(String[] args) {
     Scanner sc= new Scanner(System.in);
    System.out.println("dept");
    String dept= sc.nextLine();     
    System.out.println("sem");
    int sem = sc.nextInt();
    try {
        ViewAddDelete.view(dept,sem);
    } catch (DepInvalid | SemInvalid ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    sc.close();
   }
}

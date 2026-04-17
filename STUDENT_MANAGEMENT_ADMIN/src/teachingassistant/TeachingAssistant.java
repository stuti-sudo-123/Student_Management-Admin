package studentfunction;

import java.sql.Connection;

public class TeachingAssistant extends Student {

    private int assignedCourseId; 

    public TeachingAssistant(int studentId, String name, String email,
                             String major, int year, int semester,
                             int assignedCourseId) {

        super(studentId, name, email, major, year, semester);
        this.assignedCourseId = assignedCourseId;
    }

    public int getAssignedCourseId() {
        return assignedCourseId;
    }

    public void setAssignedCourseId(int assignedCourseId) {
        this.assignedCourseId = assignedCourseId;
    }

    
    public void viewStudentsGrades(Connection conn) {
        TAFunctions.viewGradesByCourse(conn, assignedCourseId);
    }

    public void updateStudentGrade(Connection conn, int studentId, String grade, double points) {
        TAFunctions.updateGrade(conn, studentId, assignedCourseId, grade, points);
    }
}

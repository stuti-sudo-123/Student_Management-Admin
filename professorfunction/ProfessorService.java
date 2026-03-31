package professorfunction;

import java.util.*;

public class ProfessorService 
{

    CourseDAO  courseDAO  = new CourseDAO();
    StudentDAO studentDAO = new StudentDAO();

    public List<Course> viewMyCourses(int professorId)
            throws CourseNotFoundException 
    {
        return courseDAO.getCoursesByProfessor(professorId);
    }
    public void updateCourse(int professorId, int courseId,
    String schedule, int credits,
    String prerequisites, int capacity)
            throws CourseNotFoundException, UnauthorizedAccessException 
    {
        CourseDAO.updateCourse(professorId, courseId, schedule, credits, prerequisites, capacity);
    }

    public Set<Student> viewEnrolledStudents(int professorId, int courseId)
            throws CourseNotFoundException, UnauthorizedAccessException 
    {
        return studentDAO.getEnrolledStudents(professorId, courseId);
    }
}
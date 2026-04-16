package STUDENT_MANAGEMENT_ADMIN.professorFunction;

public class Course extends Object
{
    int courseId;
    String courseCode;
    String courseName;
    String department;
    int semester;
    int credits;
    int professorId;
    String schedule;
    int capacity;
    String prerequisites;
    
    Course(int courseId,String courseCode,String courseName,String department,int semester,int credits,int professorId,String schedule,int capacity,String prerequisites)
    {
        this.courseId=courseId;
        this.courseCode=courseCode;
        this.courseName=courseName;
        this.department=department;
        this.semester=semester;
        this.credits=credits;
        this.professorId=professorId;
        this.schedule=schedule;
        this.capacity=capacity;
        this.prerequisites=prerequisites;
    }
    int getcourseId()
    {
        return courseId;
    }
    String getCourseCode()    
    { 
        return courseCode; 

    }
    String getCourseName()    
    { 
        return courseName; 

    }
    String getDepartment()    
    { 
        return department; 

    }
    int getSemester()      
    {
        return semester; 

    }
    int getCredits()       
    { 
        return credits; 

    }
    int getProfessorId()   
    { 
        return professorId; 
    }
    String getSchedule()      
    { 
        return schedule; 

    }
    int getCapacity()      
    { 
        return capacity; 

    }
   String getPrerequisites() 
   { 
      return prerequisites; 
   }
   

}

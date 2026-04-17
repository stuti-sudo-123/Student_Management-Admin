package professorfunction;

public class Student 
{
    int studentId;
    String name;
    String email;
   // String major;
    int year;
    int currentSemester;
    String enrollmentStatus;
    Student(int studentId,String name,String email,int year,int currentSemester,String enrollmentStatus)
    {
        this.studentId=studentId;
        this.name=name;
        this.email=email;
        this.year=year;
        this.currentSemester=currentSemester;
        this.enrollmentStatus=enrollmentStatus;

    }
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", email=" + email + ", year=" + year
				+ ", currentSemester=" + currentSemester + ", enrollmentStatus=" + enrollmentStatus + "]";
	}    
    
}


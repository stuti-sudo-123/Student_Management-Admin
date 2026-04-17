package studentfunction;

public class Student
{
    private int studentId;
    private String name;
    private String email;
    private String major;
    private int year;
    private int currentSemester;

    public Student(int studentId, String name, String email, String major, int year, int currentSemester)
    {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.major = major;
        this.year = year;
        this.currentSemester = currentSemester;
    }

    // Getters
    public int getStudentId()
    {
        return studentId;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getMajor()
    {
        return major;
    }

    public int getYear()
    {
        return year;
    }

    public int getCurrentSemester()
    {
        return currentSemester;
    }

    // Optional: Setters (only if needed)
    public void setMajor(String major)
    {
        this.major = major;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public void setCurrentSemester(int currentSemester)
    {
        this.currentSemester = currentSemester;
    }

    // Display method (useful for debugging / UI)
    public void display()
    {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Major: " + major);
        System.out.println("Year: " + year);
        System.out.println("Current Semester: " + currentSemester);
    }

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", email=" + email + ", major=" + major
				+ ", year=" + year + ", currentSemester=" + currentSemester + "]";
	}
    
}
package user;

public class Professor
{
    int professorId;
    String name;
    String email;
    String department;
    String office;
   
    public Professor(int professorId, String name, String email, String department , String office) 
    {
      this.professorId=professorId;
      this.name=name;
      this.email=email;
      this.department=department;
      this.office=office;  
    }
    int getprofessorId()
    {
        return professorId;
    }
    public String getname()
    {
        return name;
    }
    String getemail()
    {
        return email;
    }
    String getdepartment()
    {
        return department;
    }
    String getoffice()
    {
        return office;
    }
}
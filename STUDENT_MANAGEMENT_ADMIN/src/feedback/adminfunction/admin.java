package adminfunction;

public class admin {
     private int userId;
    private String name;
    private String email;

    public admin(int userId, String name, String email)
    {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public int getUserId()    { return userId; }
    public String getName()   { return name; }
    public String getEmail()  { return email; }

    @Override
    public String toString()
    {
        return "Admin{userId=" + userId + ", name='" + name + "', email='" + email + "'}";
    }
}


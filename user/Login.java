package user;

public interface Login<T> 
{
    void createAccount(String id);
    void getLoginCredentials(String username, String password);
    boolean verifyPassword(String username, String password);
    T directAccount();
}



package user;

public interface Login<T> 
{
    void createAccount(String id);
    void getLoginCredentials();
    boolean verifyPassword(String username, String password);
    T directAccount();
}



package user;

public class InvalidLoginException extends Exception
{
   InvalidLoginException(String msg)
   {
     super(msg);
   }
    
}
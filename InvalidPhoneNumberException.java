package ex2;

public class InvalidPhoneNumberException extends Exception
{
	public InvalidPhoneNumberException(String errorMessage)
	{
        super(errorMessage);
    }
}

package ex2;
import java.util.*;

public class PhoneComparator implements Comparator<Contact>
{
	//this method compares between phone numbers of two contacts
	public int compare(Contact c1, Contact c2)
	{
		//will compare from largest to smallest
		return c2.getPhone().compareTo(c1.getPhone());
	}
}

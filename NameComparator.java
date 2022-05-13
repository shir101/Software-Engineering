package ex2;
import java.util.*;

public class NameComparator implements Comparator<Contact>
{
	//this method lexicographically compares between names of two contacts
	public int compare(Contact c1, Contact c2)
	{
		//will compare from smallest to largest
		return c1.getName().compareTo(c2.getName());
	}
}

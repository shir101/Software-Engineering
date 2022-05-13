package ex2;

import java.util.Comparator;

public class DateComparator implements Comparator<Rendezvous> 
{	
	//comparing the dates of the rendezvous
	public int compare(Rendezvous r1, Rendezvous r2)
	{
		return r1.getDate().compareTo(r2.getDate());
	}
}

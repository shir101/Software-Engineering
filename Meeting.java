package ex2;

import java.util.Date;

public class Meeting extends Rendezvous 
{
	private Contact c;
	
	// Meeting with a person (Contact)
	public Meeting(Date date, int duration, Contact c)
	{
		super(duration, date);
		this.c = c;
	}

	public Contact getC()
	{
		return c;
	}
	
	public Meeting(Meeting other) 
	{
		this(other.getDate(), other.getDuration(), other.getC());
	}
	
	public String toString() {
		return "(" + this.getDate() + ") A meeting with " + this.getC().getName();
	}
}
package ex2;

import java.util.Date;

public class Event extends Rendezvous
{
	String evDesc;
	
	//constructor
	public Event(Date date, int duration, String evDesc)
	{
		super(duration, date);
		this.evDesc = evDesc;
	}
	
	//copy constructor
	public Event(Event other) 
	{
		this(other.getDate(), other.getDuration(), other.getEvDesc());
	}
	
	public String getEvDesc()
	{
		return this.evDesc;
	}
	
	public String toString() {
		return "("+this.getDate()+") " + this.getEvDesc();
	}
	
}

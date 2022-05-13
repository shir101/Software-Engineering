package ex2;

import java.util.Date;

public abstract class Rendezvous
{
	protected Date date;
	protected int duration;
	
	// General event
	public Rendezvous(int dur, Date d)
	{
	  this.date = (Date) d.clone();
	  this.duration = dur;
	}
	
	public Date getDate() 
	{
		return (Date) date.clone();
	}

	public void setDate(Date date) 
	{
		this.date = (Date) date.clone();
	}

	public int getDuration() 
	{
		return duration;
	}
	
	public void setDuration(int duration) 
	{
		this.duration = duration;
	}

	//copy constructor
	public Rendezvous(Rendezvous other)
	{
		this(other.getDuration(), other.getDate());
	}
	
	
}

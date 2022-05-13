package ex2;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class CalendarApp extends PhoneBookUsingApp 
{
	private ArrayList<Rendezvous> calendar;
	
	public CalendarApp(PhoneBook pb)
	{
		super(pb);
		this.calendar = new ArrayList<Rendezvous>();
	}

	public ArrayList<Rendezvous> getCalendar()
	{
		return this.calendar;
	}
	
	public void addMeetingToCalendar()
	{
		System.out.println("Enter date: day//hour//minute");
		int day = Phone.getValidInt("day", 30);
		int hour = Phone.getValidInt("hour", 24);
		int minute = Phone.getValidInt("minute", 60);
		//all meetings are on April 2022
		Date date = new Date(2022, 4, day, hour, minute);
		int duration = Phone.getValidInt("duration in minutes", 60);
		System.out.println("Enter contact");
		Contact cont = getValidName();
		Meeting meeting = new Meeting(date,duration,cont);
		this.calendar.add(meeting);
	}
	
	public void addEventToCalendar()
	{
		System.out.println("Enter date: day//hour//minute");
		int day = Phone.getValidInt("day", 30);
		int hour = Phone.getValidInt("hour", 24);
		int minute = Phone.getValidInt("minute", 60);
		//all meetings are on April 2022
		Date date = new Date(2022,4,day,hour,minute);
		int duration = Phone.getValidInt("duration in minutes", 60);
		System.out.println("Enter event description");
		String evDesc = Phone.in.nextLine();
		Event event = new Event(date,duration,evDesc);
		this.calendar.add(event);
	}
	
	public void printSchedule()
	{
		for (Rendezvous r : this.calendar)
			System.out.println(r);
	}
	
	public Date addMinutes(Date d,int dur)
	{
		int newHour = d.getHours();
		int newMinutes = d.getMinutes() + dur;
		int newDay = d.getDay();
		if (newMinutes > 60) {
			newMinutes -= 60;
			newHour++;
		}
		if (newHour > 23) {
			newDay++;
			newHour -= 24;
		}
		int newHours = (newMinutes /60 + d.getHours())%24;
		return new Date(2022,4,newDay,newHour,newMinutes);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() 
	{
		boolean flag = true;
		Contact c;
		boolean hasPrinted;
		System.out.println("1. Add rendezvous to calendar\n2. Delete rendezvous from calender\n3. Print schedule by date\n4. Print all meetings with contact\n5. Detect overlapping rendezvous\n6. Print all/n7. exit :(");
		while(flag)
		{
			System.out.println("\n[Calendar Application]: Enter a number according to the menu above");
			String menu = Phone.in.next();
			Phone.in.nextLine();
			switch(menu) 
			{
				case "1":	//adding rendezvous
					String userInput;
					do {
						System.out.println("Enter rendezvous type (Meeting\\Event):");
						userInput = Phone.in.next().toLowerCase();	
					} while (!(userInput.equals("meeting") || userInput.equals("event")));
					
					if(userInput.equals("meeting"))
						addMeetingToCalendar();
					else	//adding event
						addEventToCalendar();
					break;
					
				case "2":	//delete rendezvous from calendar
					System.out.println("Enter the date of rendezvous you want to erase");
					int day = Phone.getValidInt("day",30);
					int hour = Phone.getValidInt("hour",0,23);
					int minute = Phone.getValidInt("minute",0,59);
					Date d = new Date(2022,4,day,hour,minute);
					for(Rendezvous r : this.calendar)
					{
						if(r.getDate().equals(d))
							this.calendar.remove(r);
					}
				    break;
					
				case "3":	//print schedule by date
					Collections.sort(this.getCalendar(),new DateComparator());
					int inputDay = Phone.getValidInt("day", 30);
					hasPrinted = false;
					System.out.println("Agenda for day " + inputDay);
					for (Rendezvous r : this.calendar) {
						if (r.getDate().getDay() == inputDay) {
							hasPrinted = true;
							System.out.println(r);
						}
					}
					if (!hasPrinted)
						System.out.println("You have a free schedule!");
					break;
					
				case "4":	//prints all meetings with someone by date
 					c = super.getValidName();
 					
 					Collections.sort(this.getCalendar(),new DateComparator());
					hasPrinted = false;
					System.out.println("All meetings with " + c.getName());
					for (Rendezvous r : this.calendar) {
						if ((r instanceof Meeting) && ((Meeting )r).getC().equals(c)) {
							hasPrinted = true;
							System.out.println(r);
						}
					}
					if (!hasPrinted)
						System.out.println("You have no meetings with " + c.getName());
					break;
							
				case "5":	//erasing colliding event
					Collections.sort(this.getCalendar(),new DateComparator());
					for(int i=1; i<this.calendar.size(); i++)
					{
						Date prev = this.calendar.get(i-1).getDate();
						int prevDur = this.calendar.get(i-1).getDuration();
						Date curr = this.calendar.get(i).getDate();
						Date endTimePrev = addMinutes(prev,prevDur);
						if(curr.before(endTimePrev))
						{
							this.calendar.remove(i);
							i = i-1;//TEST THISSSSSSSSS!!!!
						}
					}
					break;
					
				case "6":	//print all rendezvous
					this.printSchedule();
					break;
					
				case "7":	//exit
					System.out.println("Goodbye mate!");
					flag = false;
					break;
						
				default:	
					System.out.println("Not a valid menu options. please enter again");
					break;
			}
		}
	}

	@Override
	public void printDigest() {
		this.printSchedule();
	}

	@Override
	public String getAppName() 
	{
		return "Calendar App";
	}

	@Override
	public void syncFromPhoneBook() 
	{
		// remove contacts from history that no longer exist in phone book
		for(Rendezvous r: this.calendar) 
		{
			if(r instanceof Meeting)
			{
				Meeting m = (Meeting) r;
				ArrayList<Contact> tmp = this.pb.findContact(m.getC().getName());
				if(tmp.size()==0 || !tmp.get(0).equals(m.getC().getName())) // if the contact doesn't exist, or the contact is different from C, but has the same name as him
					this.calendar.remove(m);
			}
		}
	}
}

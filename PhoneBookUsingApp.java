package ex2;

import java.util.ArrayList;

public abstract class PhoneBookUsingApp implements Application
{
	protected PhoneBook pb;
	public abstract void syncFromPhoneBook();
	
	public PhoneBookUsingApp(PhoneBook pb) 
	{
		this.pb = pb;
	}
	
	//this function returns a valid name from the phone book
	protected Contact getValidName()
	{
		ArrayList<Contact> tmp;
		String name;
		boolean scream = false;
		do // ask user for contact name
		{
			if(scream)
				System.out.println("the name you entered is not a contact, please enter again");
			System.out.println("Enter name");
			name = Phone.in.nextLine();
			scream = true;
		} while ((tmp = this.pb.findContact(name)).size() == 0); // pb has a contact with the name which was given by the user
		return tmp.get(0);
	}
}

package ex2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactApplication extends PhoneBookUsingApp 
{
	public ContactApplication(PhoneBook pb) 
	{
		super(pb);
	}

	@Override
	public void run() 
	{
		String name, fileName;
		boolean flag = true;
		System.out.println("1. Add contact\n2. Remove contact\n3. Print phone book\n4. Find contact by name\n5. Sort contacts by name\n6. Sort contacts by phone number\n7. Remove duplicates\n8. Reverse phone book order\n9. Export to file\n10. Import from file\n11. Exit :(");
		while(flag)
		{
			System.out.println("\n[Contact Application]: Enter a number according to the menu above");
			
			String menu = Phone.in.next();
			Phone.in.nextLine();
			switch(menu) 
			{
				case "1":
					System.out.println("Enter name");
					name = Phone.in.nextLine();
					System.out.println("Enter phone number (05X-XXXXXXX or 0X-XXXXXXX)");
					String phoneNumber = Phone.in.next();
					Phone.in.nextLine();
					Contact c = new Contact(name,phoneNumber);
					this.pb.addContact(c);
					break;
					
				case "2":
					System.out.println("Enter name");
				    name = Phone.in.nextLine();
				    this.pb.removeContact(name);
				    break;
					
				case "3":
					this.pb.print();
					break;
					
				case "4":
					System.out.println("Enter name");
				    name = Phone.in.nextLine();
				    ArrayList<Contact> array = this.pb.findContact(name);
				    if(!array.isEmpty())
				    	System.out.println(array);
				    else
				    	System.out.println("The contact " + name + " wasn't found.");
					break;
							
				case "5":
					this.pb.sortNameLowToHigh();
					this.pb.print();
					break;
					
				case "6":
					this.pb.sortPhoneHighToLow();
					this.pb.print();
					break;
					
				case "7":
					this.pb.removeDuplicates();
					this.pb.print();
					break;
					
				case "8":
					this.pb.reverse();
					this.pb.print();
					break;
					
				case "9":
					System.out.println("Enter file name");
					fileName = Phone.in.nextLine();
					try
					{
						this.pb.writeTxtFile(fileName);
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				    break;
					
				case "10":
					System.out.println("Enter file name");
					fileName = Phone.in.nextLine();
					this.pb.importTxtFile(fileName);
					
				    break;
					
				case "11":
					System.out.println("Goodbye mate!");
					flag = false;
					break;
						
				default:	
					System.out.println("Not a valid number. please enter again");
					break;
			}
		}
	}

	@Override
	//this method prints the content of the application (the contacts) 
	public void printDigest() 
	{
		this.pb.print();
	}

	@Override
	//no need to update the application because it is the contact application
	public void syncFromPhoneBook() 
	{
		return;
	}

	@Override
	public String getAppName() {
		return "Contact App";
	}
	
}

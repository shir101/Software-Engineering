package ex2;
//finish implementing run and the rest of the function in the application
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SMSApplication extends PhoneBookUsingApp 
{
	private HashMap<Contact, String> chatHistory;
	
	public SMSApplication(PhoneBook pb) 
	{
		super(pb);
		this.chatHistory = new HashMap<Contact,String>();
	}
	
	//this function adds SMS to the chat with contact c
	private void addSMS(Contact c, String sms) 
	{
		String history = chatHistory.get(c);
		if (history == null)
			history = sms;
		else
			history += "\n" + sms;
        chatHistory.put(c, history);
	}
	
	//this function erases the chat with contact c
	private void eraseChat(Contact c) 
	{
		this.chatHistory.remove(c);
	}
	
	
	//this function prints the chat
	private void printChat(Contact c)
	{
		if(this.chatHistory.get(c)==null)
			System.out.println("the chat with "+c.getName()+" is empty :\\");
		else
			System.out.println("Chat history with " + c.getName()+":\n"+this.chatHistory.get(c));
	}
	
	//this function searches a sentence in all chats
	private void searchSentence(String sent)
	{
		boolean flag = false;
		for (HashMap.Entry<Contact, String> e : this.chatHistory.entrySet()) 
		{
			if (e.getValue().contains(sent)) 
			{
				System.out.println(e.getKey());
				flag = true;
			}
		}
		if (!flag)
			System.out.println("The search term you entered was not found");
	}
	
	private void printAll()
	{
		
		for (Contact c : this.chatHistory.keySet()) 
		{
			this.printChat(c);
		}
		
		if (this.chatHistory.keySet().isEmpty())
			System.out.println("WOW, such empty ^-^");
	}

	@Override
	public void run() 
	{
		boolean flag = true;
		Contact c;
		System.out.println("1. Send SMS to a contact\n2. Delete Conversation with contact\n3. Print conversation with contact\n4. Search phrase in chat\n5. Print all conversations\n6. Exit :(");
		while(flag)
		{
			System.out.println("\n[SMS Application]: Enter a number according to the menu above");
			
			String menu = Phone.in.next();
			Phone.in.nextLine();
			switch(menu) 
			{
				case "1":	//adding chat to a contact
					c = getValidName();
					System.out.println("Enter a message:");
					String sms = Phone.in.nextLine();
					this.addSMS(c,sms);
					break;
					
				case "2":	//erasing SMS
					c = getValidName();
					this.eraseChat(c);
				    break;
					
				case "3":	//printing chat
					c = getValidName();
					this.printChat(c);
					break;
					
				case "4":	//finding a sentence in chats
					System.out.println("Enter search term");
				    String sent = Phone.in.nextLine();
				    this.searchSentence(sent);
					break;
							
				case "5":	//print all chats
					this.printAll();
					break;
					
				case "6":
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
	public void printDigest() 
	{
		this.printAll();
	}

	@Override
	public void syncFromPhoneBook() 
	{
		// remove contacts from history that no longer exist in phone book
		for(Contact c: this.chatHistory.keySet()) {
			ArrayList<Contact> tmp = this.pb.findContact(c.getName());
			if(tmp.size()==0 || !tmp.get(0).equals(c)) // if the contact doesn't exist, or the contact is different from C, but has the same name as him
				this.eraseChat(c);
		}
	}

	@Override
	public String getAppName() {
		return "WhatsApp";
	}
}

package ex2;

import java.util.Scanner;

public class Phone 
{
	private Application[] apps;
	private PhoneBook pb;
	public static Scanner in = new Scanner(System.in);

	//the constructor
	public Phone() 
	{
		this.pb = new PhoneBook();
		this.pb.importTxtFile("nokiaTest.txt");
		this.apps = new Application[3];
		apps[0] = new ContactApplication(pb);
		apps[1] = new SMSApplication(pb);
		apps[2] = new CalendarApp(pb);
	}

	//this function runs the phone's main menu
	public void run() 
	{
		System.out.println("Welcome to JavaOS! made by group 4 :D");

		boolean exitFlag = false;
		while (!exitFlag) {
			System.out.println("1. Open an application\n2. Print application digests\n3. Turn off :(");
			System.out.println("\n[JavaOS]: Enter a number according to the menu above");

			String menu = in.next();
			in.nextLine();
			switch (menu) 
			{
			case "1": // Open an application
				this.openAppMenu();
				break;

			case "2": // print application digests
				this.printAllDigests();
				break;

			case "3": // exit
				exitFlag = true;
				break;

			default:
				System.out.println("Not a valid menu options. please enter again");
				break;
			}
		}
		System.out.println("Bye! Turning off...");
	}

	// this function prints all the applications' digests
	private void printAllDigests() {
		for (Application app : this.apps) {
			System.out.println("Digest of application " + app.getAppName() + ":");
			app.printDigest();
			System.out.println("\n");
		}

	}

	// this function prints the application menu
	private void openAppMenu() 
	{
		boolean exitFlag = false;
		while (!exitFlag) 
		{
			//print applications names 
			System.out.println("Installed applications:\n\t0. return to main menu");

			for (int i = 0; i < this.apps.length; i++) 
				System.out.println("\t" + (i + 1) + ". " + this.apps[i].getAppName());
			// get user input
			String menuInput = in.next();
			in.nextLine();
			
			//convert input to integer
			int j;
			try {
				j = Integer.parseInt(menuInput);
			} catch (NumberFormatException e) {
				j = -1; // not valid
			}

			// run application
			if (1 <= j && j <= this.apps.length) {
				Application current = this.apps[j - 1]; 
				if (current instanceof PhoneBookUsingApp)
					((PhoneBookUsingApp) current).syncFromPhoneBook();
				current.run();
			} else if (j == 0) {
				exitFlag = true;
				
			} else {
				System.out.println("Not a valid menu options. please enter again");
			}
		}
	}
	
	// get input from user to an integer value, in the range of 1 to max
		public static int getValidInt(String name, int min, int max) {
			int input = 0;
			do {
				try {
				System.out.println("Enter "+name+" ("+min+"-"+max+")");
				input = Phone.in.nextInt();
				} catch (Exception e) {}
			} while (input<min || input>max);
			return input;
		}
		
		public static int getValidInt(String name, int max) {
			return getValidInt(name, 1, max);
		}
}
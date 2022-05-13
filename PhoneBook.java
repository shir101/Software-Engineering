package ex2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class PhoneBook {
	// properties
	private ArrayList<Contact> pb;

	// default constructor
	public PhoneBook() {
		this.pb = new ArrayList<Contact>();
	}

	// copy constructor
	public PhoneBook(PhoneBook phonebook) {
		this.pb = new ArrayList<Contact>();
		for (Contact c : phonebook.getPB()) {
			this.addContact(c);
		}
	}

	// get and set methods
	public ArrayList<Contact> getPB() 
	{
		return pb;
	}

	private void setPB(ArrayList<Contact> pb) 
	{
		this.pb = pb;
	}

	// this methods adds a contact to the phone book
	public void addContact(Contact c) 
	{
		try 
		{
			Contact d = new Contact(c);
			if (this.findContact(d.getName()).size() != 0)
				System.out.println("The contact '" + d.getName() + "' already exists");
			else
				this.pb.add(d);

		} catch (InvalidPhoneNumberException e) {
			System.out.println("Invalid contact " + c.getName() + " - not added");
		}
	}

	// this methods erases a contact from the phone book (if appears multiple times,
	// it erases its first appearance
	public void removeContact(String name) {
		for (Contact c : this.pb) {
			if (c.getName().equals(name)) {
				this.pb.remove(c);
				return;
			}
		}
	}

	// this method prints all of the contacts to the screen
	public void print() {
		if (this.pb.isEmpty())
			System.out.println("the phone book is empty");
		else
			for (Contact c : this.pb)
				System.out.println(c.toString());
	}

	// this method finds a contact by name and returns all of its appearances
	public ArrayList<Contact> findContact(String name) {
		ArrayList<Contact> arr = new ArrayList<Contact>();
		for (Contact c : this.pb) {
			if (c.getName().equals(name)) {
				arr.add(c);
			}
		}
		return arr; // to do - in the main, if the list is empty print a message
	}

	// this method will sort the list lexicographically
	public void sortNameLowToHigh() {
		Collections.sort(this.pb, new NameComparator());
	}

	// this method will sort the list by phone number, from highest to lowest
	public void sortPhoneHighToLow() {
		Collections.sort(this.pb, new PhoneComparator());
	}

	// this method removes duplicates from the phone book
	public void removeDuplicates() {
		ArrayList<Contact> arr = new ArrayList<Contact>();
		for (Contact c : this.pb) {
			if (!arr.contains(c))
				arr.add(c);
		}
		this.setPB(arr);
	}

	// this method reverses the phone book
	public void reverse() {
		Collections.reverse(this.pb);
	}

	// this method saves the phone book data in a txt.file
	public void writeTxtFile(String fileName) throws IOException {
		File exportedFile = new File(fileName);
		FileWriter fileWriter = new FileWriter(exportedFile);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		for (Contact c : this.pb) {
			printWriter.println(c.export());
		}
		printWriter.close();
		fileWriter.close();
		System.out.println("Exported to file "+fileName+" successfully");
	}

	// this method imports contacts from a text file and adds them to the phone book
	public void importTxtFile(String fileName) 
	{
		try {
			Scanner inFile = new Scanner(new File(fileName));
	
			while(inFile.hasNext())
			{
				String str = inFile.nextLine();
				try 
				{
					Contact c = new Contact(str);
					this.addContact(c);
				}
				catch(InvalidPhoneNumberException e)
				{
					System.out.println("contact not imported - bad phone number");
				}
			}
			inFile.close();
			System.out.println("Imported successfully from "+fileName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			return;
		}	}

	// this method checks if the phone book is empty
	public boolean isEmpty() {
		return this.pb.isEmpty();
	}
}

package ex2;
//this structure represents a contact in the phone book
public class Contact
{
	//properties
	private String name;
	private String phone;
	
	//default constructor
	public Contact()
	{
		this.name = "";
		this.phone = "";
	}
	//constructor
	public Contact(String name, String phone)
	{
		this.name = name;
		this.phone = phone;
	}
	//copy constructor
	public Contact(Contact c) throws InvalidPhoneNumberException
	{
		this.setName(c.name);
		this.setPhone(c.phone);
	}
	
	//get methods
	public String getName() 
	{
		return name;
	}
	public String getPhone() 
	{
		return phone;
	}
	private void setName(String name)
	{
		this.name = name;
	}
	private void setPhone(String phone) throws InvalidPhoneNumberException
	{
		String mobileFormat = "^05[0-9]{1}\\-[0-9]{7}$";
		String homeFormat = "^03\\-[0-9]{7}$";
		if(phone.matches(mobileFormat)||phone.matches(homeFormat))
		{
			this.phone=phone;
		}
		else
		{
			throw(new InvalidPhoneNumberException("Invalid Phone Number"));
		}
	}
	
	//this method converts the input to a string
	public String toString()
	{
		return this.name + " " + this.phone;
	}
	
	//this method compares two contacts
	public boolean equals(Object obj)
	{
		if(obj instanceof Contact)
		{
			Contact c = (Contact)obj;
			if(this.getName().equals(c.getName()) && this.getPhone().equals(c.getPhone()))
				return true;
		}
		return false;
	}
	
	//this method exports this object details
	public String export() 
	{
		return this.getName() + ", " + this.getPhone();
	}
	
	//this method constructs a contact
	public Contact(String string) throws InvalidPhoneNumberException
	{
		String[] arr = string.split(", ");
		if (arr.length >= 2) 
		{
			this.setName(arr[0]);
			this.setPhone(arr[1]);
		}
	}
}

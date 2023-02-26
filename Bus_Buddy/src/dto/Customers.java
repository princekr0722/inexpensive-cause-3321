package dto;

import java.io.Serializable;
import java.time.LocalDate;

import common.CommanAmongAll;

public class Customers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int customerID;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String city;
	private String state;
	private int zipcode;
	private String phone;
	private String email;
	private String password;
	private LocalDate dateEntered;
	private double totalBalance;
	
	public Customers() {}

	
	
	public Customers(int customerID, String firstName, String lastName, LocalDate dob, String city, String state,
			int zipcode, String phone, String email, String password, LocalDate dateEntered, double totalBalance) {
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.dateEntered = dateEntered;
		this.totalBalance = totalBalance;
	}



	public Customers(String firstName, String lastName, LocalDate dob, String city, String state, int zipcode,
			String phone, String email, String password, LocalDate dateEntered) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.dateEntered = dateEntered;
		this.totalBalance = 0;
	}



	public Customers(String firstName, String lastName, LocalDate dob, String city, String state, int zipcode,
			String phone, String email, String password, LocalDate dateEntered, double totalBalance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.dateEntered = dateEntered;
		this.totalBalance = totalBalance;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(LocalDate dateEntered) {
		this.dateEntered = dateEntered;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	@Override
	public String toString() {
		return customerID + "\t" + firstName + "\t" + lastName + "\t"
				+ dob + "\t" + city + "\t" + state + "\t" + zipcode + "\t" + phone + "\t"
				+ email + "\t" + dateEntered + "\t" + totalBalance;
	}
	
	public void printUser() {
		int idWidth = "Customer ID".length();
		int nameWidth = "Your Name".length();
		int dobWidth = "DOB".length();
		int cityWidth = "City".length();
		int stateWidth = "State".length();
		int zipWidth = "Zip Code".length();
		int phoneWidth = "Phone".length();
		int emailWidth = "Email".length();
		int passwordWidth = "Password".length();
		int dateEnteredWidth = "Date Entered".length();
		int totalBalanceWidth = "Total Balance".length();
		
		if((this.getCustomerID()+"").length()>idWidth) {
    		idWidth = (this.getCustomerID()+"").length();
    	}
    	if(this.getFirstName().length()+this.getLastName().length()+1>nameWidth) {
    		nameWidth = this.getFirstName().length()+this.getLastName().length();
    	}
    	if((this.getDob()+"").length()>dobWidth) {
    		dobWidth = (this.getDob()+"").length();
    	}
    	if(this.getCity().length()>cityWidth) {
    		cityWidth = this.getCity().length();
    	}
    	if(this.getState().length()>stateWidth) {
    		stateWidth = this.getState().length();
    	}
    	if(this.getZipcode()+"".length()>zipWidth) {
    		zipWidth = (this.getZipcode()+"").length();
    	}
    	if((this.getPhone()+"").length()>phoneWidth) {
    		phoneWidth = this.getPhone().length();
    	}
    	if(this.getEmail().length()>emailWidth) {
    		emailWidth = this.getEmail().length();
    	}
    	if(this.getPassword().length()>passwordWidth) {
    		passwordWidth = this.getPassword().length();
    	}
    	if((this.getDateEntered()+"").length()>dateEnteredWidth) {
    		dateEnteredWidth = (this.getDateEntered()+"").length();
    	}
    	if((this.getTotalBalance()+" ").length()>totalBalanceWidth) {
    		totalBalanceWidth = (this.getTotalBalance()+" ").length();
    	}
    	
    	int max = 0;
	    if(idWidth>max)max = idWidth;
	    if(nameWidth>max)max = nameWidth;
	    if(dobWidth>max)max = dobWidth;
	    if(cityWidth>max)max = cityWidth;
	    if(stateWidth>max)max = stateWidth;
	    if(zipWidth>max)max = zipWidth;
	    if(phoneWidth>max)max = phoneWidth;
	    if(emailWidth>max)max = emailWidth;
	    if(passwordWidth>max)max = passwordWidth;
	    if(dateEnteredWidth>max)max = dateEnteredWidth;
	    if(totalBalanceWidth>max)max = totalBalanceWidth;
    	
    	System.out.println("===================="+CommanAmongAll.TEAL+"BUS DETAILS"+CommanAmongAll.RESET+"==================\n");
	    String formatString = "| %1$-" + max + "s | %2$-" + max + "s |\n";
	    
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "Field", "Value");
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "Customer ID", this.getCustomerID());
	    System.out.format(formatString, "Your Name", this.getFirstName()+" "+this.getLastName());
	    System.out.format(formatString, "DOB", this.getDob());
	    System.out.format(formatString, "City", this.getCity());
	    System.out.format(formatString, "State", this.getState());
	    System.out.format(formatString, "Zip Code", this.getZipcode());
	    System.out.format(formatString, "Phone", this.getPhone());
	    System.out.format(formatString, "Email", this.getEmail());
	    System.out.format(formatString, "Password", this.getPassword());
	    System.out.format(formatString, "Date Entered", this.getDateEntered());
	    System.out.format(formatString, "Total Balance", "₹"+this.getTotalBalance());
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	}
}

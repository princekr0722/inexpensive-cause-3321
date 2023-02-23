package dto;

import java.time.LocalDate;

public class Customers {
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
}

package dto;

import java.time.LocalDate;

public class Bookings {
	private int bookingID;
	private int customerID;
	private int busID;
	private LocalDate bookingDate;
	private LocalDate bookedDate;
	private int noOfPassanger;
	private double totalAmount;
	private String status;
	
	public Bookings() {}
	
	public Bookings(int customerID, int busID, LocalDate bookedDate, int noOfPassanger, double totalAmount, String status) {
		super();
		this.customerID = customerID;
		this.busID = busID;
		this.bookedDate = bookedDate;
		this.noOfPassanger = noOfPassanger;
		this.totalAmount = totalAmount;
		this.status = status;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getBusID() {
		return busID;
	}

	public void setBusID(int busID) {
		this.busID = busID;
	}

	public LocalDate getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}

	public int getNoOfPassanger() {
		return noOfPassanger;
	}

	public void setNoOfPassanger(int noOfPassanger) {
		this.noOfPassanger = noOfPassanger;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	@Override
	public String toString() {
		return bookingID + "\t" + customerID + "\t" + busID + "\t" + bookedDate
				+ "\t" + noOfPassanger + "\t" + totalAmount + "\t" + status;
	}
}

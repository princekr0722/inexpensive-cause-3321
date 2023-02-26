package dto;

import java.sql.SQLException;

import common.CommanAmongAll;
import dao.BusTableOperationsImpl;

public class TicketBookingReceipt {
	private Bookings booking;
	private Bus bus;
	
	public TicketBookingReceipt(Bookings booking) throws SQLException {
		this.booking = booking;
		this.bus = new BusTableOperationsImpl().getBusByID(booking.getBusID());
	}
	
	public TicketBookingReceipt(Bookings booking, Bus bus){
		this.booking = booking;
		this.bus = bus;
	}

	public Bookings getBooking() {
		return booking;
	}

	public void setBooking(Bookings booking) {
		this.booking = booking;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
	public void printEnteredDetails() {
	    int idWidth = "Bus ID:".length();
	    int nameWidth = "Bus Name:".length();
	    int typeWidth = "Bus Type:".length();
	    int seatWidth = "Total Seat:".length();
	    int departWidth = "Departure Point:".length();
	    int arriveWidth = "Arrival Point:".length();
	    int departTimeWidth = "Departure Time:".length();
	    int arriveTimeWidth = "Arrival Time:".length();
	    
	    int noOfPassengerWidth = "No of Passengers:".length();
	    int bookingDateWidth = "Date of Booking:".length();
	    int bookedDateWidth = "Booked Date:".length();
	    int priceWidth = "Ticket Price".length();
	    int totalAmountWidth = "Total Payable Amount:".length();
	    
	    
	    if ((bus.getBusID() + "").length() > idWidth) {
	        idWidth = (bus.getBusID() + "").length();
	    }
	    if (bus.getBusName().length() > nameWidth) {
	        nameWidth = bus.getBusName().length();
	    }
	    if (bus.getBusType().length() > typeWidth) {
	        typeWidth = bus.getBusType().length();
	    }
	    if ((bus.getTotalSeat() + "").length() > seatWidth) {
	        seatWidth = (bus.getTotalSeat() + "").length();
	    }
	    if (bus.getDeparturePoint().length() > departWidth) {
	        departWidth = bus.getDeparturePoint().length();
	    }
	    if (bus.getArrivalPoint().length() > arriveWidth) {
	        arriveWidth = bus.getArrivalPoint().length();
	    }
	    if ((bus.getDepartureTime() + "").length() > departTimeWidth) {
	        departTimeWidth = (bus.getDepartureTime() + "").length();
	    }
	    if ((bus.getArrivalTime() + "").length() > arriveTimeWidth) {
	        arriveTimeWidth = (bus.getArrivalTime() + "").length();
	    }
	    if ((booking.getNoOfPassenger() + "").length() > noOfPassengerWidth) {
	    	noOfPassengerWidth = (booking.getNoOfPassenger() + "").length();
	    }
	    if ((booking.getBookedDate() + "").length() > bookedDateWidth) {
	    	bookedDateWidth = (booking.getBookedDate() + "").length();
	    }
	    if ((booking.getBookingDate() + "").length() > bookingDateWidth) {
	    	bookingDateWidth = (booking.getBookingDate() + "").length();
	    }
	    if ((booking.getTotalAmount() + "").length() > totalAmountWidth) {
	    	totalAmountWidth = (booking.getTotalAmount() + "").length();
	    }

	    int max = 0;
	    if(idWidth>max)max = idWidth;
	    if(nameWidth>max)max = nameWidth;
	    if(typeWidth>max)max = typeWidth;
	    if(seatWidth>max)max = seatWidth;
	    if(departWidth>max)max = departWidth;
	    if(arriveWidth>max)max = arriveWidth;
	    if(departTimeWidth>max)max = departTimeWidth;
	    if(arriveTimeWidth>max)max = arriveTimeWidth;
	    if(priceWidth>max)max = priceWidth;
	    if(noOfPassengerWidth>max)max = noOfPassengerWidth;
	    if(bookedDateWidth>max)max = bookedDateWidth;
	    if(bookingDateWidth>max)max = bookingDateWidth;
	    if(totalAmountWidth>max)max = totalAmountWidth;
	    
		System.out.println("\n"+CommanAmongAll.TEAL+"TICKET BOOKING DETAILS:-"+CommanAmongAll.RESET);
		
		String formatString = "| %1$-" + max + "s | %2$-" + max + "s |\n";
	    
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "Field", "Value");
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "Bus ID", bus.getBusID());
	    System.out.format(formatString, "Bus Name", bus.getBusName());
	    System.out.format(formatString, "Bus Type", bus.getBusType());
	    System.out.format(formatString, "Total Seat", bus.getTotalSeat());
	    System.out.format(formatString, "Departure Point", bus.getDeparturePoint());
	    System.out.format(formatString, "Arrival Point", bus.getArrivalPoint());
	    System.out.format(formatString, "Departure Time", bus.getDepartureTime());
	    System.out.format(formatString, "Arrival Time", bus.getArrivalTime());
	    System.out.format(formatString, "Date of Booking", booking.getBookingDate());
	    System.out.format(formatString, "Booked Date", booking.getBookedDate());
	    System.out.format(formatString, "No of Passengers", booking.getNoOfPassenger());
	    System.out.format(formatString, "Ticket Price", bus.getTicketPrice());
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "Total Payable Amount", booking.getTotalAmount());
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	}
}

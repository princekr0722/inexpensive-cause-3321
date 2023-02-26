package dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dto.Bookings;
import dto.Bus;

public interface BookingsTableOperations {
	int checkAvailableSeats(Bus bus, LocalDate date) throws SQLException;
	boolean cancelABusTicket(Bookings booking) throws SQLException;
	boolean bookABusTicket(Bookings booking) throws SQLException;
	List<Bookings> getListOfAllBookings() throws SQLException;
	List<Bookings> getListOfAllActiveBookings() throws SQLException;
	List<Bookings> getListOfAllNonActiveBookings() throws SQLException;
	List<Bookings> getListOfAllBookingsByCustomerID(int id) throws SQLException;
	int printListOfBooking(List<Bookings> list, String heading);
	void printABooking(Bookings booking);
}

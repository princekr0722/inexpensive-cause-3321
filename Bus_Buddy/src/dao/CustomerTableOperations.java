package dao;

import java.sql.SQLException;

import dto.Bookings;

public interface CustomerTableOperations {
	boolean bookABusTicket(Bookings booking) throws SQLException;
	void cancelABusTicket(Bookings booking) throws SQLException;
}

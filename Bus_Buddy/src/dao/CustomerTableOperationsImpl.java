package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.CommanAmongAll;
import dto.Bookings;

public class CustomerTableOperationsImpl implements CustomerTableOperations {

	@Override
	public boolean bookABusTicket(Bookings booking) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String INSERT_QUERY = "INSERT INTO Bookings (customerID, busID, bookingDate, "
				+ "bookedDate, noOfPassanger, totalAmount, "
				+ "status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
		ps.setInt(1, booking.getCustomerID());
		ps.setInt(2, booking.getBusID());
		ps.setDate(3, Date.valueOf(booking.getBookingDate()));
		ps.setDate(4, Date.valueOf(booking.getBookedDate()));
		ps.setInt(5, booking.getNoOfPassanger());
		ps.setDouble(6, booking.getTotalAmount());
		ps.setString(7, booking.getStatus());
		
		int res = ps.executeUpdate();		
		DBUtility.closeConnection(conn);
		if(res==0) {
			return false;
		}
		return true;
	}

	@Override
	public void cancelABusTicket(Bookings booking) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT bookingID FROM Bookings WHERE bookingID = ? && DATEDIFF(CURRENT_DATE(), bookedDate)<1 && status = Active Limit 1");
		if(!CommanAmongAll.isResultSetEmpty(rs)) {
			System.out.println(CommanAmongAll.LIGHT_RED+"\nSorry, you cannot cancel ticket on last day."+CommanAmongAll.RESET);
			return;
		}
		
		
		String DELETE_QUERY = "DELETE FROM Bookings WHERE bookingID = ? && DATEDIFF(CURRENT_DATE(), bookedDate)>=1 && status = Active";
		
		PreparedStatement ps = conn.prepareStatement(DELETE_QUERY);
		
		ps.setInt(1, booking.getBookingID());
		
		int res = ps.executeUpdate();
		
		if(res==0) {
			System.out.println(CommanAmongAll.LIGHT_RED+"No ticket with ID "+booking.getBookingID()+" is booked!\n"+CommanAmongAll.RESET);;
		}else {
			System.out.println(CommanAmongAll.LIGHT_GREEN+"Ticket with ID "+booking.getBookingID()+" is canceled!"+CommanAmongAll.RESET);
		}
		DBUtility.closeConnection(conn);
	}

}

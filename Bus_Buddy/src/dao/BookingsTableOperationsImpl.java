package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import common.CommanAmongAll;
import dto.Bookings;
import dto.Bus;

public class BookingsTableOperationsImpl implements BookingsTableOperations {

	@Override
	public int checkAvailableSeats(Bus bus, LocalDate bookedDate) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String SELECT_QUERY = "SELECT Bk.bookedDate, Bk.busID, B.busName, B.totalSeat, "
				+ "sum(noOfPassenger) totalBooking FROM Bookings Bk "
				+ "INNER JOIN Customers C ON Bk.customerID = C.customerID "
				+ "INNER JOIN Buses B ON Bk.busID = B.busID "
				+ "GROUP BY Bk.bookedDate, Bk.busID, Bk.customerID;";
		
		PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("busID");
			LocalDate date = rs.getDate("bookedDate").toLocalDate();
			
			if(id==bus.getBusID() && bookedDate.isEqual(date)) {
				int res = rs.getInt("totalSeat") - rs.getInt("totalBooking");
				return res;
			}
		}
		return bus.getTotalSeat();
	}
	
	@Override
	public boolean bookABusTicket(Bookings booking) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String INSERT_QUERY = "INSERT INTO Bookings (customerID, busID, bookingDate, "
				+ "bookedDate, noOfPassenger, totalAmount, "
				+ "status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
		ps.setInt(1, booking.getCustomerID());
		ps.setInt(2, booking.getBusID());
		ps.setDate(3, Date.valueOf(booking.getBookingDate()));
		ps.setDate(4, Date.valueOf(booking.getBookedDate()));
		ps.setInt(5, booking.getNoOfPassenger());
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
	public boolean cancelABusTicket(Bookings booking) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT bookingID FROM Bookings WHERE bookingID = "+booking.getBookingID()+" && DATEDIFF(bookedDate, CURRENT_DATE())<1 && status = 'Active' Limit 1");
		if(!CommanAmongAll.isResultSetEmpty(rs)) {
			System.out.println(CommanAmongAll.LIGHT_RED+"\nSorry, you cannot cancel ticket on the last day."+CommanAmongAll.RESET);
			return false;
		}
		
		
		String DELETE_QUERY = "DELETE FROM Bookings WHERE bookingID = ? && DATEDIFF(bookedDate, CURRENT_DATE())>=1 && status = 'Active'";
		
		PreparedStatement ps = conn.prepareStatement(DELETE_QUERY);
		
		ps.setInt(1, booking.getBookingID());
		
		int res = ps.executeUpdate();
		
		if(res==0) {
			System.out.println(CommanAmongAll.LIGHT_RED+"No ticket with ID "+booking.getBookingID()+" is available!\n"+CommanAmongAll.RESET);;
			return false;
		}else {
			System.out.println(CommanAmongAll.LIGHT_GREEN+"Ticket with ID "+booking.getBookingID()+" is canceled!"+CommanAmongAll.RESET);
		}
		DBUtility.closeConnection(conn);
		return true;
	}
	
	@Override
	public List<Bookings> getListOfAllBookings() throws SQLException{
		Connection conn = DBUtility.getConnection();
		
		String SELECT_QUERY = "Select * from Bookings ORDER BY bookedDate";
		PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
		
		ResultSet rs = ps.executeQuery();
		if(CommanAmongAll.isResultSetEmpty(rs)) {
			DBUtility.closeConnection(conn);
			return null;
		}else {
			List<Bookings> list = new ArrayList<>();
			
			while(rs.next()) {
				int bookingID = rs.getInt("bookingID");
				int customerID = rs.getInt("customerID");
				int busID = rs.getInt("busID");
				LocalDate bookingDate = rs.getDate("bookingDate").toLocalDate();
				LocalDate bookedDate = rs.getDate("bookedDate").toLocalDate();
				int noOfPassenger = rs.getInt("noOfPassenger");
				double totalAmount = rs.getDouble("totalAmount");
				String status = rs.getString("status");
				
				Bookings booking = new Bookings(customerID, busID, bookingDate, bookedDate, noOfPassenger, totalAmount, status);
				booking.setBookingID(bookingID);
				list.add(booking);
			}
			DBUtility.closeConnection(conn);
			return list;
		}
	}

	@Override
	public List<Bookings> getListOfAllActiveBookings() throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String SELECT_QUERY = "Select * from Bookings WHERE status = 'Active' ORDER BY bookedDate";
		PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
		
		ResultSet rs = ps.executeQuery();
		if(CommanAmongAll.isResultSetEmpty(rs)) {
			DBUtility.closeConnection(conn);
			return null;
		}else {
			List<Bookings> list = new ArrayList<>();
			
			while(rs.next()) {
				int bookingID = rs.getInt("bookingID");
				int customerID = rs.getInt("customerID");
				int busID = rs.getInt("busID");
				LocalDate bookingDate = rs.getDate("bookingDate").toLocalDate();
				LocalDate bookedDate = rs.getDate("bookedDate").toLocalDate();
				int noOfPassenger = rs.getInt("noOfPassenger");
				double totalAmount = rs.getDouble("totalAmount");
				String status = rs.getString("status");
				
				Bookings booking = new Bookings(customerID, busID, bookingDate, bookedDate, noOfPassenger, totalAmount, status);
				booking.setBookingID(bookingID);
				list.add(booking);
			}
			DBUtility.closeConnection(conn);
			return list;
		}	
	}

	@Override
	public List<Bookings> getListOfAllNonActiveBookings() throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String SELECT_QUERY = "Select * from Bookings WHERE status = 'Non-Active' ORDER BY bookedDate";
		PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
		
		ResultSet rs = ps.executeQuery();
		if(CommanAmongAll.isResultSetEmpty(rs)) {
			DBUtility.closeConnection(conn);
			return null;
		}else {
			List<Bookings> list = new ArrayList<>();
			
			while(rs.next()) {
				int bookingID = rs.getInt("bookingID");
				int customerID = rs.getInt("customerID");
				int busID = rs.getInt("busID");
				LocalDate bookingDate = rs.getDate("bookingDate").toLocalDate();
				LocalDate bookedDate = rs.getDate("bookedDate").toLocalDate();
				int noOfPassenger = rs.getInt("noOfPassenger");
				double totalAmount = rs.getDouble("totalAmount");
				String status = rs.getString("status");
				
				Bookings booking = new Bookings(customerID, busID, bookingDate, bookedDate, noOfPassenger, totalAmount, status);
				booking.setBookingID(bookingID);
				list.add(booking);
			}
			DBUtility.closeConnection(conn);
			return list;
		}	
	}

	@Override
	public List<Bookings> getListOfAllBookingsByCustomerID(int id) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String SELECT_QUERY = "Select * from Bookings WHERE customerID = ? && status = 'Active' ORDER BY bookedDate";
		PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		if(CommanAmongAll.isResultSetEmpty(rs)) {
			DBUtility.closeConnection(conn);
			return null;
		}else {
			List<Bookings> list = new ArrayList<>();
			
			while(rs.next()) {
				int bookingID = rs.getInt("bookingID");
				int customerID = rs.getInt("customerID");
				int busID = rs.getInt("busID");
				LocalDate bookingDate = rs.getDate("bookingDate").toLocalDate();
				LocalDate bookedDate = rs.getDate("bookedDate").toLocalDate();
				int noOfPassenger = rs.getInt("noOfPassenger");
				double totalAmount = rs.getDouble("totalAmount");
				String status = rs.getString("status");
				
				Bookings booking = new Bookings(customerID, busID, bookingDate, bookedDate, noOfPassenger, totalAmount, status);
				booking.setBookingID(bookingID);
				list.add(booking);
			}
			DBUtility.closeConnection(conn);
			return list;
		}
	}

	@Override
	public int printListOfBooking(List<Bookings> list, String heading){
		System.out.println("\n"+CommanAmongAll.TEAL+heading.toUpperCase()+":-"+CommanAmongAll.RESET);
		if(list==null) {
			System.out.println(CommanAmongAll.GRAY+"No such booking is available to show :\\"+CommanAmongAll.RESET);
			return 0;
		}
		
		int bookingIDWidth = "Booking ID".length();
		int customerIDWidth = "Customer ID".length();
		int busIDWidth = "Bus ID".length();
		int bookingDateWidth = "Booking Date".length();
		int bookedDateWidth = "Booked Date".length();
		int noOfPassengerWidth = "No Of Passenger".length();
		int totalAmountWidth = "Total Amount".length();
		int statusWidth = "Status".length();
        
//        try {
        	
        for(int i=0; i<list.size(); i++) {
        	if((list.get(i).getBookingID()+"").length()>bookingIDWidth) {
        		bookingIDWidth = (list.get(i).getBookingID()+"").length();
        	}
        	if((list.get(i).getCustomerID()+"").length()>customerIDWidth) {
        		customerIDWidth = (list.get(i).getCustomerID()+"").length();
        	}
        	if((list.get(i).getBusID()+"").length()>busIDWidth) {
        		busIDWidth = (list.get(i).getBusID()+"").length();
        	}
        	if((list.get(i).getBookingDate()+"").length()>bookingDateWidth) {
        		bookingDateWidth = (list.get(i).getBookingDate()+"").length();
        	}
        	if((list.get(i).getBookedDate()+"").length()>bookedDateWidth) {
        		bookedDateWidth = (list.get(i).getBookedDate()+"").length();
        	}
        	if((list.get(i).getNoOfPassenger()+"").length()>noOfPassengerWidth) {
        		noOfPassengerWidth = (list.get(i).getNoOfPassenger()+"").length();
        	}
        	if((list.get(i).getTotalAmount()+"").length()>totalAmountWidth) {
        		totalAmountWidth = (list.get(i).getTotalAmount()+"").length();
        	}
        	if(list.get(i).getStatus().length()>statusWidth) {
        		statusWidth = list.get(i).getStatus().length();
        	}
        }
        // Print the header row
        String formatString = "| %1$-" + bookingIDWidth + "s | %2$-" + customerIDWidth + "s | %3$-" + busIDWidth + "s | %4$-" + bookingDateWidth + "s | %5$-" + bookedDateWidth + "s | %6$-" + noOfPassengerWidth + "s | %7$-" + totalAmountWidth + "s | %8$-" + statusWidth + "s |\n";
        String separator = new String(new char[bookingIDWidth + bookingIDWidth + busIDWidth + bookingDateWidth + bookedDateWidth + noOfPassengerWidth + totalAmountWidth + statusWidth + 26]).replace('\0', '-');
        
        System.out.println(separator);
        System.out.format(formatString, "Booking ID", "Customer ID", "Bus ID", "Booking Date", "Booked Date", "No Of Passenger", "Total Amount", "Satus");

        // Print the separator row
        System.out.println(separator);

        // Print each row of bus data
        for (Bookings booking : list) {
            System.out.format(formatString, booking.getBookingID(), booking.getCustomerID(), booking.getBusID(), booking.getBookingDate(), booking.getBookedDate(), booking.getNoOfPassenger(), booking.getTotalAmount(), booking.getStatus().toLowerCase().equals("active")? CommanAmongAll.LIGHT_GREEN+booking.getStatus()+CommanAmongAll.RESET: CommanAmongAll.LIGHT_RED+booking.getStatus()+CommanAmongAll.RESET);
        }
        System.out.println(separator+"\n");
        return 1;
	}
	
	@Override
	public void printABooking(Bookings booking) {
		
		int bookingIDWidth = "Booking ID".length();
		int customerIDWidth = "Customer ID".length();
		int busIDWidth = "Bus ID".length();
		int bookingDateWidth = "Booking Date".length();
		int bookedDateWidth = "Booked Date".length();
		int noOfPassengerWidth = "No Of Passenger".length();
		int totalAmountWidth = "Total Amount".length();
		int statusWidth = "Status".length();
	    
		if((booking.getBookingID()+"").length()>bookingIDWidth) {
    		bookingIDWidth = (booking.getBookingID()+"").length();
    	}
    	if((booking.getCustomerID()+"").length()>customerIDWidth) {
    		customerIDWidth = (booking.getCustomerID()+"").length();
    	}
    	if((booking.getBusID()+"").length()>busIDWidth) {
    		busIDWidth = (booking.getBusID()+"").length();
    	}
    	if((booking.getBookingDate()+"").length()>bookingDateWidth) {
    		bookingDateWidth = (booking.getBookingDate()+"").length();
    	}
    	if((booking.getBookedDate()+"").length()>bookedDateWidth) {
    		bookedDateWidth = (booking.getBookedDate()+"").length();
    	}
    	if((booking.getNoOfPassenger()+"").length()>noOfPassengerWidth) {
    		noOfPassengerWidth = (booking.getNoOfPassenger()+"").length();
    	}
    	if((booking.getTotalAmount()+"").length()>totalAmountWidth) {
    		totalAmountWidth = (booking.getTotalAmount()+"").length();
    	}
    	if(booking.getStatus().length()>statusWidth) {
    		statusWidth = booking.getStatus().length();
    	}

	    int max = 0;
	    if(bookingIDWidth>max)max = bookingIDWidth;
	    if(customerIDWidth>max)max = customerIDWidth;
	    if(busIDWidth>max)max = busIDWidth;
	    if(bookingDateWidth>max)max = bookingDateWidth;
	    if(bookedDateWidth>max)max = bookedDateWidth;
	    if(noOfPassengerWidth>max)max = noOfPassengerWidth;
	    if(totalAmountWidth>max)max = totalAmountWidth;
	    if(statusWidth>max)max = statusWidth;
	    
		System.out.println("=================="+CommanAmongAll.TEAL+"INDIVIDUAL BUS"+CommanAmongAll.RESET+"=================\n");
	    String formatString = "| %1$-" + max + "s | %2$-" + max + "s |\n";
	    
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "Field", "Value");
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "Booking ID", booking.getBookingID());
	    System.out.format(formatString, "Customer ID", booking.getCustomerID());
	    System.out.format(formatString, "Bus ID", booking.getBusID());
	    System.out.format(formatString, "Booking Date", booking.getBookingDate());
	    System.out.format(formatString, "Booked Date", booking.getBookedDate());
	    System.out.format(formatString, "No Of Passenger", booking.getNoOfPassenger());
	    System.out.format(formatString, "Total Amount", booking.getTotalAmount());
	    System.out.format(formatString, "Status", booking.getStatus());
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	}

	public static void markNotActiveBookings() throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String UPDATE_QUERY = "UPDATE Bookings SET status = 'Not Active' WHERE bookedDate < CURDATE()";
		PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY);
		
		ps.executeUpdate();
		
		DBUtility.closeConnection(conn);
	}
}

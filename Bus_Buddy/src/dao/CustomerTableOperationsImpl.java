package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import common.CommanAmongAll;
import dto.Customers;

public class CustomerTableOperationsImpl implements CustomerTableOperations {
	
	@Override
	public Customers getCustomerByID(int id) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String SELECT_QUERY = "Select * from Customers WHERE customerID = ? Limit 1";
		PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		if(CommanAmongAll.isResultSetEmpty(rs)) {
			DBUtility.closeConnection(conn);
			return null;
		}else {
			rs.next();
			int customerID = rs.getInt("customerID");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			LocalDate dob = rs.getDate("dob").toLocalDate();
			String city = rs.getString("city");
			String state = rs.getString("state");
			int zipcode = rs.getInt("zipcode");
			String phone = rs.getString("phone");
			String email = rs.getString("emailID"); 
			String password = rs.getString("password");
			LocalDate dateEntered = rs.getDate("dateEntered").toLocalDate();
			double totalBalance = rs.getDouble("totalBalance");
			
			DBUtility.closeConnection(conn);
			return new Customers(customerID,firstName, lastName, dob, city, state, zipcode, phone, email, password, dateEntered, totalBalance);
		}
	}
	
	@Override
	public boolean addNewCustomer(Customers customer) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String INSERT_QUERY = "INSERT INTO Customers (firstName, lastName, dob, city, "
				+ "state, zipCode, phone, emailID, Password, "
				+ "dateEntered, totalBalance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
		
		PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
		ps.setString(1, customer.getFirstName());
		ps.setString(2, customer.getLastName());
		ps.setDate(3, Date.valueOf(customer.getDob()));
		ps.setString(4, customer.getCity());
		ps.setString(5, customer.getState());
		ps.setInt(6, customer.getZipcode());
		ps.setString(7, customer.getPhone());
		ps.setString(8, customer.getEmail());
		ps.setString(9, customer.getPassword());
		ps.setDate(10, Date.valueOf(customer.getDateEntered()));
		
		int res = ps.executeUpdate();
		
		DBUtility.closeConnection(conn);
		if(res==0) {
			return false;
		}
		return true;

	}
	
	@Override
	public boolean updateCustomerByID(Customers customer) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String INSERT_QUERY = "UPDATE Customers SET firstName = ?, lastName = ?, dob = ?, city = ?, "
				+ "state = ?, zipCode = ?, phone = ?, emailID = ?, Password = ?, "
				+ "dateEntered = ?, totalBalance = ? WHERE customerID = ?";
		
		PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
		ps.setString(1, customer.getFirstName());
		ps.setString(2, customer.getLastName());
		ps.setDate(3, Date.valueOf(customer.getDob()));
		ps.setString(4, customer.getCity());
		ps.setString(5, customer.getState());
		ps.setInt(6, customer.getZipcode());
		ps.setString(7, customer.getPhone());
		ps.setString(8, customer.getEmail());
		ps.setString(9, customer.getPassword());
		ps.setDate(10, Date.valueOf(customer.getDateEntered()));
		ps.setDouble(11, customer.getTotalBalance());
		ps.setInt(12, customer.getCustomerID());
		
		int res = ps.executeUpdate();
		
		DBUtility.closeConnection(conn);
		if(res==0) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean deleteCustomerById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int verifyLogin(String identifier, String password) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String SELECT_QUERY = "Select customerID from Customers WHERE phone = ? && password = ? Limit 1";
		PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
		
		ps.setString(1, identifier);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		if(!CommanAmongAll.isResultSetEmpty(rs)) {
			rs.next();
			int customerID = rs.getInt("customerID");
			DBUtility.closeConnection(conn);
			return customerID;
		}
		
		SELECT_QUERY = "Select customerID from Customers WHERE emailID = ? && password = ? Limit 1";
		PreparedStatement ps2 = conn.prepareStatement(SELECT_QUERY);
		
		ps2.setString(1, identifier);
		ps2.setString(2, password);
		
		rs = ps2.executeQuery();
		if(!CommanAmongAll.isResultSetEmpty(rs)) {
			rs.next();
			int customerID = rs.getInt("customerID");
			DBUtility.closeConnection(conn);
			return customerID;
		}
		return 0;
	}
	
	
	
	@Override
	public boolean verifyMobile(String mob) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String SELECT_QUERY = "Select customerID from Customers WHERE phone = ? Limit 1";
		PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
		
		ps.setString(1, mob);
		
		ResultSet rs = ps.executeQuery();
		if(!CommanAmongAll.isResultSetEmpty(rs)) {
			DBUtility.closeConnection(conn);
			return false;
		}
		DBUtility.closeConnection(conn);
		return true;
	}
	
	@Override
	public boolean verifyEmail(String email) throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String SELECT_QUERY = "Select customerID from Customers WHERE emailID = ? Limit 1";
		PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
		
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		if(!CommanAmongAll.isResultSetEmpty(rs)) {
			DBUtility.closeConnection(conn);
			return false;
		}
		DBUtility.closeConnection(conn);
		return true;
	}
	
	@Override
	public List<Customers> getAllCustomers() throws SQLException {
		Connection conn = DBUtility.getConnection();
		
		String SELECT_QUERY = "Select * from Customers";
		PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
		
		ResultSet rs = ps.executeQuery();
		if(CommanAmongAll.isResultSetEmpty(rs)) {
			DBUtility.closeConnection(conn);
			return null;
		}else {
			List<Customers> list = new ArrayList<>();
			
			while(rs.next()) {
				int customerID = rs.getInt("customerID");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				LocalDate dob = rs.getDate("dob").toLocalDate();
				String city = rs.getString("city");
				String state = rs.getString("state");
				int zipcode = rs.getInt("zipcode");
				String phone = rs.getString("phone");
				String email = rs.getString("emailID"); 
				String password = rs.getString("password");
				LocalDate dateEntered = rs.getDate("dateEntered").toLocalDate();
				double totalBalance = rs.getDouble("totalBalance");
				
				list.add(new Customers(customerID,firstName, lastName, dob, city, state, zipcode, phone, email, password, dateEntered, totalBalance));
			}
			DBUtility.closeConnection(conn);
			return list;
		}
	}
	
	@Override
	public int printListOfCustomers(List<Customers> list) {
		System.out.println("\n"+CommanAmongAll.TEAL+"ALL CUSTOMERS"+CommanAmongAll.RESET+":-");
		if(list==null) {
			System.out.println(CommanAmongAll.GRAY+"No customer is present to show :\\"+CommanAmongAll.RESET);
			return 0;
		}
		
		int idWidth = "Customer ID".length();
		int nameWidth = "Customer Name".length();
		int dobWidth = "DOB".length();
		int cityWidth = "City".length();
		int stateWidth = "State".length();
		int zipWidth = "Zip Code".length();
		int phoneWidth = "Phone".length();
		int emailWidth = "Email".length();
		int passwordWidth = "Password".length();
		int dateEnteredWidth = "Date Entered".length();
		int totalBalanceWidth = "Total Balance".length();
        
//        try {
        	
        for(int i=0; i<list.size(); i++) {
        	if((list.get(i).getCustomerID()+"").length()>idWidth) {
        		idWidth = (list.get(i).getCustomerID()+"").length();
        	}
        	if(list.get(i).getFirstName().length()+list.get(i).getLastName().length()>nameWidth) {
        		nameWidth = list.get(i).getFirstName().length()+list.get(i).getLastName().length();
        	}
        	if((list.get(i).getDob()+"").length()>dobWidth) {
        		dobWidth = (list.get(i).getDob()+"").length();
        	}
        	if(list.get(i).getCity().length()>cityWidth) {
        		cityWidth = list.get(i).getCity().length();
        	}
        	if(list.get(i).getState().length()>stateWidth) {
        		stateWidth = list.get(i).getState().length();
        	}
        	if(list.get(i).getZipcode()+"".length()>zipWidth) {
        		zipWidth = (list.get(i).getZipcode()+"").length();
        	}
        	if((list.get(i).getPhone()+"").length()>phoneWidth) {
        		phoneWidth = list.get(i).getPhone().length();
        	}
        	if(list.get(i).getEmail().length()>emailWidth) {
        		emailWidth = list.get(i).getEmail().length();
        	}
        	if(list.get(i).getPassword().length()>passwordWidth) {
        		passwordWidth = list.get(i).getPassword().length();
        	}
        	if((list.get(i).getDateEntered()+"").length()>dateEnteredWidth) {
        		dateEnteredWidth = (list.get(i).getDateEntered()+"").length();
        	}
        	if((list.get(i).getTotalBalance()+"").length()>totalBalanceWidth) {
        		totalBalanceWidth = (list.get(i).getTotalBalance()+"").length();
        	}
        }
        // Print the header row
        String formatString = "| %1$-" + idWidth + "s | %2$-" + nameWidth + "s | %3$-" + dobWidth + "s | %4$-" + cityWidth + "s | %5$-" + stateWidth + "s | %6$-" + zipWidth + "s | %7$-" + phoneWidth + "s | %8$-" + emailWidth + "s | %9$-" + dateEnteredWidth + "s |\n";
        String separator = new String(new char[idWidth + nameWidth + dobWidth + cityWidth + stateWidth + zipWidth + phoneWidth + emailWidth + dateEnteredWidth+ 30]).replace('\0', '-');
        
        System.out.println(separator);
        System.out.format(formatString, "Customer ID", "Customer Name", "DOB", "City", "State", "Zip Code", "Phone", "Email", "Date ENtered", "Total Balance");

        // Print the separator row
        System.out.println(separator);

        // Print each row of bus data
        for (Customers cus : list) {
            System.out.format(formatString, cus.getCustomerID(), cus.getFirstName()+" "+cus.getLastName(), cus.getDob(), cus.getCity(), cus.getState(), cus.getZipcode()+"  ", cus.getPhone(), cus.getEmail(), cus.getDateEntered());
        }
        System.out.println(separator+"\n");
        return 1;
	}
}

package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Customers;

public interface CustomerTableOperations {
	
	boolean addNewCustomer(Customers customer) throws SQLException;
	boolean updateCustomerByID(Customers customer) throws SQLException;
	Customers getCustomerByID(int id) throws SQLException;
	boolean deleteCustomerById(int id) throws SQLException;
//	boolean bookABusTicket(Bookings booking) throws SQLException;
//	void cancelABusTicket(Bookings booking) throws SQLException;
	boolean verifyMobile(String mob) throws SQLException;
	boolean verifyEmail(String email) throws SQLException;
	int verifyLogin(String identifier, String password) throws SQLException;
	List<Customers> getAllCustomers() throws SQLException;
	int printListOfCustomers(List<Customers> list);
}

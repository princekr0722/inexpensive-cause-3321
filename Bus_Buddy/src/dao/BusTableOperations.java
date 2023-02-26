package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Bus;

public interface BusTableOperations {
	boolean addNewBus(Bus bus) throws SQLException;
	List<Bus> viewAllBuses() throws SQLException;
	Bus getBusByID(int id) throws SQLException;
	void deleteBusByID(int id) throws SQLException;
	void editBusDetails(Bus bus) throws SQLException;
	List<Bus> searchBusByStartAndEndPoint(String start, String end) throws SQLException;
	
	void printBus(Bus bus);
	int printListOfBuses(List<Bus> list);
}

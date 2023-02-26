package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import common.CommanAmongAll;
import dto.Bus;

public class BusTableOperationsImpl implements BusTableOperations{

	@Override
	public boolean addNewBus(Bus bus) throws SQLException {
		Connection conn = DBUtility.getConnection();
			
			String INSERT_QUERY = "INSERT INTO Buses (busName, busType, totalSeat, "
					+ "departurePoint, arrivalPoint, departureTime, "
					+ "arrivalTime, ticketPrice) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
			ps.setString(1, bus.getBusName());
			ps.setString(2, bus.getBusType());
			ps.setInt(3, bus.getTotalSeat());
			ps.setString(4, bus.getDeparturePoint());
			ps.setString(5, bus.getArrivalPoint());
			ps.setTime(6, Time.valueOf(bus.getDepartureTime()));
			ps.setTime(7, Time.valueOf(bus.getArrivalTime()));
			ps.setDouble(8, bus.getTicketPrice());
			
			int res = ps.executeUpdate();
			
			DBUtility.closeConnection(conn);
			if(res==0) {
				return false;
			}
			return true;

	}

	@Override
	public List<Bus> viewAllBuses() throws SQLException {
		Connection conn = null;
		List<Bus> list = new ArrayList<>();
		
			conn = DBUtility.getConnection();
			
			String SELECT_QUERY = "SELECT * FROM Buses";
			
			PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
			
			ResultSet rs = ps.executeQuery();
			
			if(CommanAmongAll.isResultSetEmpty(rs)) {
				DBUtility.closeConnection(conn);
				return null;
			}
			
			while(rs.next()) {
				Bus bus = new Bus();
				bus.setBusID(rs.getInt("busID"));
				bus.setBusName(rs.getString("busName"));
				bus.setBusType(rs.getString("busType"));
				bus.setTotalSeat(rs.getInt("totalSeat"));
				bus.setDeparturePoint(rs.getString("departurePoint"));
				bus.setArrivalPoint(rs.getString("arrivalPoint"));
				bus.setDepartureTime(rs.getTime("departureTime").toLocalTime());
				bus.setArrivalTime(rs.getTime("arrivalTime").toLocalTime());
				bus.setTicketPrice(rs.getDouble("ticketPrice"));
				
				list.add(bus);
			}
			DBUtility.closeConnection(conn);
			return list;
//			System.out.println(CommanAmongAll.RED+"Something is wrong, please try again later"+CommanAmongAll.RESET);
	}
	
	@Override
	public List<Bus> searchBusByStartAndEndPoint(String start, String end) throws SQLException {
		Connection conn = null;
		List<Bus> list = new ArrayList<>();
		
			conn = DBUtility.getConnection();
			
			String SELECT_QUERY = "SELECT * FROM Buses WHERE departurePoint = ? && arrivalPoint = ?";
			
			PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
			
			ps.setString(1, start);
			ps.setString(2, end);
			
			ResultSet rs = ps.executeQuery();
			
			if(CommanAmongAll.isResultSetEmpty(rs)) {
				DBUtility.closeConnection(conn);
				return null;
			}
			
			while(rs.next()) {
				Bus bus = new Bus();
				bus.setBusID(rs.getInt("busID"));
				bus.setBusName(rs.getString("busName"));
				bus.setBusType(rs.getString("busType"));
				bus.setTotalSeat(rs.getInt("totalSeat"));
				bus.setDeparturePoint(rs.getString("departurePoint"));
				bus.setArrivalPoint(rs.getString("arrivalPoint"));
				bus.setDepartureTime(rs.getTime("departureTime").toLocalTime());
				bus.setArrivalTime(rs.getTime("arrivalTime").toLocalTime());
				bus.setTicketPrice(rs.getDouble("ticketPrice"));
				
				list.add(bus);
			}
			DBUtility.closeConnection(conn);
			return list;
	}
	
	@Override
	public Bus getBusByID(int id) throws SQLException {
		Connection conn = null;

			conn = DBUtility.getConnection();
			
			String SELECT_QUERY = "SELECT * FROM Buses WHERE busID = ?";
			
			PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(CommanAmongAll.isResultSetEmpty(rs)) {
				DBUtility.closeConnection(conn);
				return null;
			}
			
			rs.next();
			
			Bus bus = new Bus();
			bus.setBusID(rs.getInt("busID"));
			bus.setBusName(rs.getString("busName"));
			bus.setBusType(rs.getString("busType"));
			bus.setTotalSeat(rs.getInt("totalSeat"));
			bus.setDeparturePoint(rs.getString("departurePoint"));
			bus.setArrivalPoint(rs.getString("arrivalPoint"));
			bus.setDepartureTime(rs.getTime("departureTime").toLocalTime());
			bus.setArrivalTime(rs.getTime("arrivalTime").toLocalTime());
			bus.setTicketPrice(rs.getDouble("ticketPrice"));
			
			DBUtility.closeConnection(conn);
			return bus;
//			System.out.println(CommanAmongAll.RED+"Something is wrong, please try again later"+CommanAmongAll.RESET);
	}
	
	@Override
	public void deleteBusByID(int id) throws SQLException {
		Connection conn = null;
		
			conn = DBUtility.getConnection();
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT busID FROM Bookings WHERE busID = "+id+" && status = 'Active' Limit 1");
			if(!CommanAmongAll.isResultSetEmpty(rs)) {
				System.out.println(CommanAmongAll.LIGHT_RED+"Cannot edit, this Bus is already booked!\n"+CommanAmongAll.RESET);
				return;
			}
			
			String DELETE_QUERY = "DELETE FROM Buses WHERE busID = ?";
			
			PreparedStatement ps = conn.prepareStatement(DELETE_QUERY);
			
			ps.setInt(1, id);
			
			int res = ps.executeUpdate();
			
			if(res==0) {
				System.out.println(CommanAmongAll.LIGHT_RED+"No bus with ID "+id+" is available!\n"+CommanAmongAll.RESET);;
			}else {
				System.out.println(CommanAmongAll.LIGHT_GREEN+"Bus with ID "+id+" is deleted!"+CommanAmongAll.RESET);
			}
			DBUtility.closeConnection(conn);
//			System.out.println(CommanAmongAll.RED+"Something is wrong, please try again later"+CommanAmongAll.RESET);
			
	}
	
	@Override
	public void editBusDetails(Bus bus) throws SQLException {
		Connection conn = null;

			conn = DBUtility.getConnection();
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT busID FROM Bookings WHERE busID = "+bus.getBusID()+"  && status = 'Active' Limit 1");
			if(!CommanAmongAll.isResultSetEmpty(rs)) {
				System.out.println(CommanAmongAll.LIGHT_RED+"Cannot edit, this Bus is already booked!\n"+CommanAmongAll.RESET);
				return;
			}
			
			String DELETE_QUERY = "UPDATE Buses SET busName = ?, busType = ?, totalSeat = ?, departurePoint = ?, arrivalPoint = ?, departureTime = ?, arrivalTime = ?, ticketPrice = ? WHERE busID = ?";
			
			PreparedStatement ps = conn.prepareStatement(DELETE_QUERY);
			
			ps.setString(1, bus.getBusName());
			ps.setString(2, bus.getBusType());
			ps.setInt(3, bus.getTotalSeat());
			ps.setString(4, bus.getDeparturePoint());
			ps.setString(5, bus.getArrivalPoint());
			ps.setTime(6, Time.valueOf(bus.getDepartureTime()));
			ps.setTime(7, Time.valueOf(bus.getArrivalTime()));
			ps.setDouble(8, bus.getTicketPrice());
			ps.setInt(9, bus.getBusID());
			
			int res = ps.executeUpdate();
			
			if(res==0) {
				System.out.println(CommanAmongAll.LIGHT_RED+"No bus with ID "+bus.getBusID()+" is available!\n"+CommanAmongAll.RESET);;
			}else {
				System.out.println(CommanAmongAll.LIGHT_GREEN+"Bus with ID "+bus.getBusID()+" is edited!"+CommanAmongAll.RESET);
			}
			DBUtility.closeConnection(conn);

//			System.out.println(CommanAmongAll.RED+"Something is wrong, please try again later\n"+CommanAmongAll.RESET);

	}
	public void printBus(Bus bus) {
	    int idWidth = "BusID".length();
	    int nameWidth = "BusName".length();
	    int typeWidth = "BusType".length();
	    int seatWidth = "TotalSeat".length();
	    int departWidth = "DeparturePoint".length();
	    int arriveWidth = "ArrivalPoint".length();
	    int departTimeWidth = "DepartureTime".length();
	    int arriveTimeWidth = "ArrivalTime".length();
	    int priceWidth = "TicketPrice".length();
	    
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
	    
		System.out.println("===================="+CommanAmongAll.TEAL+"BUS DETAILS"+CommanAmongAll.RESET+"==================\n");
	    String formatString = "| %1$-" + max + "s | %2$-" + max + "s |\n";
	    
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "Field", "Value");
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "BusID", bus.getBusID());
	    System.out.format(formatString, "BusName", bus.getBusName());
	    System.out.format(formatString, "BusType", bus.getBusType());
	    System.out.format(formatString, "TotalSeat", bus.getTotalSeat());
	    System.out.format(formatString, "DeparturePoint", bus.getDeparturePoint());
	    System.out.format(formatString, "ArrivalPoint", bus.getArrivalPoint());
	    System.out.format(formatString, "DepartureTime", bus.getDepartureTime());
	    System.out.format(formatString, "ArrivalTime", bus.getArrivalTime());
	    System.out.format(formatString, "TicketPrice", bus.getTicketPrice());
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	}
	
		@Override
		public int printListOfBuses(List<Bus> list) {
			
		System.out.println("\n"+CommanAmongAll.TEAL+"All BUSES"+CommanAmongAll.RESET+":-");
		if(list==null) {
			System.out.println(CommanAmongAll.GRAY+"No bus is present to show :\\"+CommanAmongAll.RESET);
			return 0;
		}
		
		int idWidth = "Bus ID".length();
		int nameWidth = "Bus Name".length();
		int typeWidth = "Bus Type".length();
		int seatWidth = "Total Seat".length();
		int departWidth = "Departure Point".length();
		int arriveWidth = "Arrival Point".length();
		int departTimeWidth = "Departure Time".length();
		int arriveTimeWidth = "Arrival Time".length();
		int priceWidth = "Ticket Price".length();
        
//        try {
        	
        for(int i=0; i<list.size(); i++) {
        	if((list.get(i).getBusID()+"").length()>idWidth) {
        		idWidth = (list.get(i).getBusID()+"").length();
        	}
        	if(list.get(i).getBusName().length()>nameWidth) {
        		nameWidth = list.get(i).getBusName().length();
        	}
        	if(list.get(i).getBusType().length()>typeWidth) {
        		typeWidth = list.get(i).getBusType().length();
        	}
        	if((list.get(i).getTotalSeat()+"").length()>seatWidth) {
        		seatWidth = (list.get(i).getTotalSeat()+"").length();
        	}
        	if(list.get(i).getDeparturePoint().length()>departWidth) {
        		departWidth = (list.get(i).getDeparturePoint()+"").length();
        	}
        	if(list.get(i).getArrivalPoint().length()>arriveWidth) {
        		arriveWidth = (list.get(i).getArrivalPoint()+"").length();
        	}
        	if((list.get(i).getDepartureTime()+"").length()>departTimeWidth) {
        		departTimeWidth = (list.get(i).getDepartureTime()+"").length();
        	}
        	if((list.get(i).getArrivalTime()+"").length()>arriveTimeWidth) {
        		arriveTimeWidth = (list.get(i).getArrivalTime()+"").length();
        	}
        }
        // Print the header row
        String formatString = "| %1$-" + idWidth + "s | %2$-" + nameWidth + "s | %3$-" + typeWidth + "s | %4$-" + seatWidth + "s | %5$-" + departWidth + "s | %6$-" + arriveWidth + "s | %7$-" + departTimeWidth + "s | %8$-" + arriveTimeWidth + "s | %9$-" + priceWidth + "s |\n";
        String separator = new String(new char[idWidth + nameWidth + typeWidth + seatWidth + departWidth + arriveWidth + departTimeWidth + arriveTimeWidth + priceWidth + 28]).replace('\0', '-');
        
        System.out.println(separator);
        System.out.format(formatString, "Bus ID", "Bus Name", "Bus Type", "Total Seat", "Departure Point", "Arrival Point", "Departure Time", "Arrival Time", "Ticket Price");

        // Print the separator row
        System.out.println(separator);

        // Print each row of bus data
        for (Bus bus : list) {
            System.out.format(formatString, bus.getBusID(), bus.getBusName(), bus.getBusType(), bus.getTotalSeat(), bus.getDeparturePoint(), bus.getArrivalPoint(), bus.getDepartureTime(), bus.getArrivalTime(), bus.getTicketPrice());
        }
        System.out.println(separator+"\n");
        return 1;
	}
}

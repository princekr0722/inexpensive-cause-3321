package dto;

import java.time.LocalTime;

public class Bus {
	/*
	 BusID, BusName, BusType, TotalSeat, AvailableSeats, DeparturePoint, 
	 ArrivalPoint, DepartureTime, ArrivalTime, TicketPrice
	 */
	private int busID;
	private String busName;
	private String busType;
	private int totalSeat;
	private String departurePoint;
	private String arrivalPoint;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private double ticketPrice;
	
	public Bus() {}
	
	public Bus(String busName, String busType, int totalSeat, String departurePoint,
			String arrivalPoint, LocalTime departureTime, LocalTime arrivalTime, double ticketPrice) {
		
		this.busName = busName;
		this.busType = busType;
		this.totalSeat = totalSeat;
		this.departurePoint = departurePoint;
		this.arrivalPoint = arrivalPoint;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.ticketPrice = ticketPrice;
	}

	public int getBusID() {
		return busID;
	}

	public void setBusID(int busID) {
		this.busID = busID;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	public String getDeparturePoint() {
		return departurePoint;
	}

	public void setDeparturePoint(String departurePoint) {
		this.departurePoint = departurePoint;
	}

	public String getArrivalPoint() {
		return arrivalPoint;
	}

	public void setArrivalPoint(String arrivalPoint) {
		this.arrivalPoint = arrivalPoint;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return busID + "\t" + busName + "\t" + busType + "\t" + totalSeat
				+ "\t" + departurePoint + "\t"
				+ arrivalPoint + "\t" + departureTime + "\t" + arrivalTime
				+ "\t" + ticketPrice;
	}
}

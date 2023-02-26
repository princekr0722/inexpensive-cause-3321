package ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import common.CommanAmongAll;
import cuurentUser.CurrentUser;
import dao.BookingsTableOperations;
import dao.BookingsTableOperationsImpl;
import dao.BusTableOperations;
import dao.BusTableOperationsImpl;
import dto.Bookings;
import dto.Bus;
import dto.TicketBookingReceipt;

public class TicketBookingSection {
	
	 static BusTableOperations bto = new BusTableOperationsImpl();
	 static BookingsTableOperations bkto = new BookingsTableOperationsImpl();
	
	public static void bookTicket(Scanner sc) {
		System.out.println("\n============="+CommanAmongAll.TEAL+"TICKET BOOKING SECTION"+CommanAmongAll.RESET+"==============\n");
		System.out.println(CommanAmongAll.LIGHT_BLUE+"Fill the your source and destination below:-\n"+CommanAmongAll.RESET);
		
		String start;
		String end;
		
		System.out.print("Enter Start Point: ");
		String input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserHomeSection.userHomeSection(sc, CurrentUser.currentUser);
			return;
		}
		
		while(input.length()<3 || input.length()>25 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Start Point: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserHomeSection.userHomeSection(sc, CurrentUser.currentUser);
				return;
			}
		}
		start = input;
		
		System.out.print("Enter End Point: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserHomeSection.userHomeSection(sc, CurrentUser.currentUser);
			return;
		}
		
		while(input.length()<3 || input.length()>25 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid End Point: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserHomeSection.userHomeSection(sc, CurrentUser.currentUser);
				return;
			}
		}
		end = input;
		
		try {
			List<Bus> list = bto.searchBusByStartAndEndPoint(start, end);
			
			int res = bto.printListOfBuses(list);
			if(res==0) {
				bookTicket(sc);
				return;
			}
			selectBus(sc, list);
		} catch (SQLException e) {
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			UserHomeSection.userHomeSection(sc, CurrentUser.currentUser);
		}
	}
	
	 static void selectBus(Scanner sc, List<Bus> list) {
		System.out.println(CommanAmongAll.GO_BACK_TEXT);
		System.out.print("Select Bus by ID: ");
		
		String input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserHomeSection.userHomeSection(sc, CurrentUser.currentUser);
			return;
		}
		
		while(input.length()<0 || !input.matches("\\d+")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"\nPlease Enter Valid ID: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserHomeSection.userHomeSection(sc, CurrentUser.currentUser);
				return;
			}
		}
		
		try {
			Bus bus = bto.getBusByID(Integer.parseInt(input));
			
			if(bus==null) {
				System.out.println(CommanAmongAll.LIGHT_RED+"No Match Found! Try Again...\n"+CommanAmongAll.RESET);
				selectBus(sc, list);
				return;
			}
			System.out.println();
			bto.printBus(bus);
			fillBookingdetails(bus, sc, list);
			return;
		} catch (NumberFormatException | SQLException e) {
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			UserHomeSection.userHomeSection(sc, CurrentUser.currentUser);
			return;
		}
	}
	
	 static void fillBookingdetails(Bus bus ,Scanner sc,List<Bus> list) {
		System.out.println(CommanAmongAll.GO_BACK_TEXT+"Fill in booking details below:-");
		
		LocalDate date = null;
		int noOfPassenger = 0;
		int availableSeats = 0;
		
		System.out.println("\nFormat of the date should be like this yyyy-mm-dd [ex: 2004-07-22]");
		System.out.print("Enter Date of Travel: ");
		String input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			int res = bto.printListOfBuses(list);
			if(res==0) {
				bookTicket(sc);
				return;
			}
			selectBus(sc, list);
			return;
		}
		boolean indicator = true;
		while(indicator) {
			try {
				date = LocalDate.parse(input);
				indicator = false;
				
				if(date.isBefore(LocalDate.now())) {
					indicator = true;
					System.out.println(CommanAmongAll.LIGHT_ORANGE+"You can't travel in past via bus"+CommanAmongAll.RESET);
					System.out.print("\nPlease Enter Valid Date of Travel: ");
					input = sc.nextLine(); 
					
					if(input.equals("..")) {
						System.out.println();
						int res = bto.printListOfBuses(list);
						if(res==0) {
							bookTicket(sc);
							return;
						}
						selectBus(sc, list);
						return;
					}
				}
				
				try {
					availableSeats = bkto.checkAvailableSeats(bus, date);
				} catch (SQLException e) {
					System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
					UserHomeSection.userHomeSection(sc, CurrentUser.currentUser);
					return;
				}
				
				if(availableSeats==0) {
					indicator = true;
					System.out.print(CommanAmongAll.LIGHT_RED+"SORRY! "+CommanAmongAll.RESET+"No seats are available on "+ date.toString()+"\n Please try different date: ");
					input = sc.nextLine();
					
					if(input.equals("..")) {
						System.out.println();
						int res = bto.printListOfBuses(list);
						if(res==0) {
							bookTicket(sc);
							return;
						}
						selectBus(sc, list);
						return;
					}
				}
			} catch (DateTimeParseException e) {
				indicator = true;
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nFormat of the date should be like this yyyy-mm-dd [ex: 2004-07-22]"+CommanAmongAll.RESET);
				System.out.print("Enter Valid Date of Travel : ");
				input = sc.nextLine(); 
				
				if(input.equals("..")) {
					System.out.println();
					int res = bto.printListOfBuses(list);
					if(res==0) {
						bookTicket(sc);
						return;
					}
					selectBus(sc, list);
					return;
				}
			}
		}
		
		System.out.println("\nTotal "+CommanAmongAll.LIGHT_GREEN+availableSeats+CommanAmongAll.RESET+" Seats are available on "+date.toString());
		
		System.out.print("\nEnter No. of Passangers : ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			int res = bto.printListOfBuses(list);
			if(res==0) {
				bookTicket(sc);
				return;
			}
			selectBus(sc, list);
			return;
		}
		
		indicator = true;
		while(indicator){
			if(input.length()==0 || !input.matches("\\d+") || input.equals("0")) {
				indicator = true;
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid No. of Passangers : "+CommanAmongAll.RESET);
				input = sc.nextLine();
				
				if(input.equals("..")) {
					System.out.println();
					int res = bto.printListOfBuses(list);
					if(res==0) {
						bookTicket(sc);
						return;
					}
					selectBus(sc, list);
					return;
				}
			}else if(Integer.parseInt(input)>availableSeats){
				indicator = true;
				System.out.print(CommanAmongAll.LIGHT_RED+"Sorry only "+availableSeats+" seats are left\n"
						+CommanAmongAll.LIGHT_ORANGE+"\nPlease Enter Valid No. of Passangers : "+CommanAmongAll.RESET);
				input = sc.nextLine();
				if(input.equals("..")) {
					System.out.println();
					int res = bto.printListOfBuses(list);
					if(res==0) {
						bookTicket(sc);
						return;
					}
					selectBus(sc, list);
					return;
				}
			}else {
				indicator = false;
			}
		}
		noOfPassenger = Integer.parseInt(input);
		
		Bookings booking = new Bookings(CurrentUser.currentUser.getCustomerID(), bus.getBusID(), LocalDate.now(), date, noOfPassenger, bus.getTicketPrice()*noOfPassenger, "Active");
		
		TicketBookingReceipt tbr = new TicketBookingReceipt(booking, bus);
		
		tbr.printEnteredDetails();
		
		proceedToPay(bus ,sc, tbr, list);
	}
	
	 static void proceedToPay(Bus bus ,Scanner sc, TicketBookingReceipt tbr, List<Bus> list) {
		System.out.println(CommanAmongAll.GO_BACK_TEXT);
		System.out.println(CommanAmongAll.LIGHT_BLUE+"Proceed To Payment?\n"+CommanAmongAll.RESET);
		System.out.println("1. Yes\n"
				+ "2. No, go back\n");
		System.out.print("Enter Input : ");
		String input = sc.nextLine();
		
		boolean indicator = true;
		
		while(indicator) {
			if(input.equals("1")) {
				indicator = false;
				PaymentSection.paymentSection(sc, tbr);
			}else if(input.equals("2")) {
				indicator = false;
				bto.printBus(bus);
				fillBookingdetails(bus ,sc, list);
			}else {
				indicator = true;
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input : "+CommanAmongAll.RESET);
				input = sc.nextLine();
			}
		}
	}
	
	

}

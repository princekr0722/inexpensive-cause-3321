package ui;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import admin.Admin;
import common.CommanAmongAll;
import dao.BookingsTableOperations;
import dao.BookingsTableOperationsImpl;
import dao.BusTableOperations;
import dao.BusTableOperationsImpl;
import dao.CustomerTableOperations;
import dao.CustomerTableOperationsImpl;
import dto.Bookings;
import dto.Bus;
import dto.Customers;

public class AdminHomeSection {
	public static void adminHomepageMenu(Scanner sc) {
		System.out.println("\n=================="+CommanAmongAll.TEAL+"ADMIN SECTION"+CommanAmongAll.RESET+"==================\n");
		System.out.println(CommanAmongAll.LIGHT_BLUE+"Admin can perform these tasks:-\n"+CommanAmongAll.RESET);
		System.out.println(
				  "1. Add a new bus\n"
				+ "2. View all buses\n"
				+ "3. View all customers\n"
				+ "4. View all bookings\n"
				+ "5. Admin Profile\n"
				+ "6. Log out\n"
				+ "0. Quit\n");
		
		System.out.print("Enter Input: ");
		String input = sc.nextLine();
		
		while(!(input.length()!=0 && input.matches("\\d") && (int)input.charAt(0)>=48 && (int)input.charAt(0)<=54)) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input: "+CommanAmongAll.RESET);
			input = sc.nextLine();
		}
		
		adminHomePageInputProcess(Integer.parseInt(input), sc);;
	}
	
	private static BusTableOperations bto = new BusTableOperationsImpl();
	private static CustomerTableOperations cto = new CustomerTableOperationsImpl();
	private static BookingsTableOperations bkto = new BookingsTableOperationsImpl();
	
	private static void adminHomePageInputProcess(int in, Scanner sc){
		switch (in) {
			case 1: {
				busCreation(sc);
				break;
			}
			case 2: {
				try {
					List<Bus> list = bto.viewAllBuses();
					if(bto.printListOfBuses(list)==0) {
						goBack(sc);
						return;
					}
					selectBus(sc);
				} catch (SQLException e) {
					System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
					adminHomepageMenu(sc);
					return;
				}
				break;
			}
			case 3: {
				try {
					List<Customers> list = cto.getAllCustomers();
					cto.printListOfCustomers(list);
					goBack(sc);
					return;
				} catch (SQLException e) {
					System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
					adminHomepageMenu(sc);
					return;
				}
			}
			case 4: {
				viewAllBooking(sc);
				break;
			}
			case 5: {
				Admin.admin.printAdmin();
				goBack(sc);
				return;
			}
			case 6: {
				CommanAmongAll.logOutMessage();
				
				LandingPageFunctionality.landingPageIntro(sc);
				LandingPageFunctionality.landingPageMenu(sc);
				break;
			}
			case 0: {
				CommanAmongAll.quit();
				break;
			}
		}
	}
	private static void goBack(Scanner sc) {
		System.out.print("Press .. To Go Back: ");
		String input = sc.nextLine();
		while(!input.equals("..")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter .. To Go Back: "+CommanAmongAll.RESET);
			input = sc.nextLine();
		}
		adminHomepageMenu(sc);
	}
	
	private static void viewAllBooking(Scanner sc) {
		try {
			List<Bookings> list = bkto.getListOfAllBookings();
			int res = bkto.printListOfBooking(list, "Bookings");
			
			if(res==0) {
				goBack(sc);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			adminHomepageMenu(sc);
			return;
		}
		
		System.out.println("1. View All Active Bookings\n"
				+ "2. View All Not-Active Bookings\n"
				+ ".. Go Back\n");
		
		System.out.print("Enter Input: ");
		String input = sc.nextLine();
		
		boolean indicator = true;
		while(indicator) {
			if(input.equals("1")) {
				indicator = false;
				viewAllActiveBooking(sc);
				return;
			}else if(input.equals("2")){
				indicator = false;
				viewAllNonActiveBooking(sc);
				return;
			}else if(input.equals("..")){
				indicator = false;
				adminHomepageMenu(sc);
				return;
			}else {
				indicator = true;
			}
		}
		
	}
	
	private static void viewAllActiveBooking(Scanner sc) {
		try {
			List<Bookings> list = bkto.getListOfAllActiveBookings();
			bkto.printListOfBooking(list, "Active Bookings");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			adminHomepageMenu(sc);
			return;
		}
		
		System.out.println("1. View All Bookings\n"
				+ "2. View All Non-Active Bookings\n"
				+ ".. Go Back\n");
		
		System.out.print("Enter Input: ");
		String input = sc.nextLine();
		
		boolean indicator = true;
		while(indicator) {
			if(input.equals("1")) {
				indicator = false;
				viewAllBooking(sc);
				return;
			}else if(input.equals("2")){
				indicator = false;
				viewAllNonActiveBooking(sc);
				return;
			}else if(input.equals("..")){
				indicator = false;
				adminHomepageMenu(sc);
				return;
			}else {
				indicator = true;
			}
		}
		
	}
	
	private static void viewAllNonActiveBooking(Scanner sc) {
		try {
			List<Bookings> list = bkto.getListOfAllNonActiveBookings();
			bkto.printListOfBooking(list, "Not-Active Bookings");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			adminHomepageMenu(sc);
			return;
		}
		
		System.out.println("1. View All Bookings\n"
				+ "2. View All Active Bookings\n"
				+ ".. Go Back\n");
		
		System.out.print("Enter Input: ");
		String input = sc.nextLine();
		
		boolean indicator = true;
		while(indicator) {
			if(input.equals("1")) {
				indicator = false;
				viewAllBooking(sc);
				return;
			}else if(input.equals("2")){
				indicator = false;
				viewAllActiveBooking(sc);
				return;
			}else if(input.equals("..")){
				indicator = false;
				adminHomepageMenu(sc);
				return;
			}else {
				indicator = true;
			}
		}
		
	}
	
	private static void busCreation(Scanner sc) {
		System.out.println("==================="+CommanAmongAll.TEAL+"ADD NEW BUS"+CommanAmongAll.RESET+"===================\n");
		
		String busName = null;
		String busType = null;
		int noOfSeats = 0;
		String departurePoint = null;
		String arrivalPoint = null;
		LocalTime dailyDepartureTime = null;
		LocalTime dailyArrivalTime = null;
		double ticketPrice = 0.0;
		
		System.out.println(CommanAmongAll.LIGHT_BLUE+"Fill the Bus details below"+CommanAmongAll.GRAY+" [Enter .. to go back]\n"+CommanAmongAll.RESET);
		
		System.out.print("Enter Bus Name : ");
		String input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(input.length()<3 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"Length must be greater than 2 and no special characters"+CommanAmongAll.RESET);
			System.out.print("\nEnter Bus Name : ");
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		busName = input;
		
		System.out.print("\nChoose Bus Type:-\n"
				+ "1. AC\n"
				+ "2. Non-AC\n");
		
		System.out.print("\nEnter Choice: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(!(input.length()!=0 && input.matches("\\d") && (int)input.charAt(0)>=49 && (int)input.charAt(0)<=50)) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"\nPlease Enter Valid Input: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		if(input.equals("1")) {
			busType = "AC";
		}else {
			busType = "Non-AC";
		}
		
		System.out.print("\nEnter No. of Seats in Bus: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(!(input.length()!=0 && input.matches("\\d+"))) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"\nPlease Enter Valid Input: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		noOfSeats = Integer.parseInt(input);
		
		System.out.print("\nEnter Departure Point: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(input.length()<3 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nLength must be greater than 2 and no special characters"+CommanAmongAll.RESET);
			System.out.print("Enter Departure Point: ");
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		departurePoint = input;
		
		System.out.print("\nEnter Arrival Point: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(input.length()<3 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nLength must be greater than 2 and no special characters"+CommanAmongAll.RESET);
			System.out.print("Enter Arrival Point: ");
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		arrivalPoint = input;
		
		
		System.out.println("\nFormat of the time should be like this hh-mm-ss [ex: 11:30:00]");
		System.out.print("Enter Daily Departure Time: ");
		input = sc.nextLine(); 
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		boolean indicator = true;
		
		while(indicator) {
			try {
				dailyDepartureTime = LocalTime.parse(input);
				indicator = false;
			} catch (DateTimeParseException e) {
				indicator = true;
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nFormat of the time should be like this hh-mm-ss [ex: 11:30:00]"+CommanAmongAll.RESET);
				System.out.print("Enter Daily Departure Time: ");
				input = sc.nextLine(); 
				
				if(input.equals("..")) {
					System.out.println();
					adminHomepageMenu(sc);
					return;
				}
			}
		}
		
		System.out.println("\nFormat of the time should be like this hh-mm-ss [ex: 11:30:00]");
		System.out.print("Enter Daily Arrival Time: ");
		input = sc.nextLine(); 
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		indicator = true;
		
		while(indicator) {
			try {
				dailyArrivalTime = LocalTime.parse(input);
				indicator = false;
			} catch (DateTimeParseException e) {
				indicator = true;
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nFormat of the time should be like this hh-mm-ss [ex: 11:30:00]"+CommanAmongAll.RESET);
				System.out.print("Enter Daily Arrival Time: ");
				input = sc.nextLine(); 
				
				if(input.equals("..")) {
					System.out.println();
					adminHomepageMenu(sc);
					return;
				}
			}
		}
		
		System.out.print("\nEnter Ticket Price: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(!(input.length()!=0 && input.matches("\\d+"))) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"\nPlease Enter Valid Input: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		ticketPrice = Double.parseDouble(input);
		
		Bus bus = new Bus(busName, busType, noOfSeats, departurePoint, arrivalPoint, dailyDepartureTime, dailyArrivalTime, ticketPrice);
		
		try {
			if(bto.addNewBus(bus)) {
				System.out.println(CommanAmongAll.LIGHT_GREEN+"\nNEW BUS ADDED!\n"+CommanAmongAll.RESET);
				adminHomepageMenu(sc);
			}
		} catch (SQLException e) {
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			adminHomepageMenu(sc);
		}
	}

	private static void selectBus(Scanner sc) {
		System.out.println(CommanAmongAll.GO_BACK_TEXT);
		System.out.print("Select Bus by ID: ");
		
		String input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(input.length()<0 || !input.matches("\\d+")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"\nPlease Enter Valid ID: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		
		try {
			Bus bus = bto.getBusByID(Integer.parseInt(input));
			
			if(bus==null) {
				System.out.println(CommanAmongAll.LIGHT_RED+"No Match Found! Try Again...\n"+CommanAmongAll.RESET);
				selectBus(sc);
				return;
			}
			System.out.println();
			bto.printBus(bus);
			individualBusOperation(bus,sc);
			return;
		} catch (NumberFormatException | SQLException e) {
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			adminHomepageMenu(sc);
		}
	}
	
	private static void individualBusOperation(Bus bus, Scanner sc) {
		System.out.println("1. Edit bus\n"
				+ "2. Delete bus\n"
				+ "0. Quit\n"
				+ ".. Go back\n\n");
		
		System.out.print("Enter Input: ");
		String input = sc.nextLine();
		
		boolean indicator = true;
		while(indicator) {
			if(input.equals("1")) {
				//edit
				busEditing(bus,sc);
				return;
			}else if(input.equals("2")) {
				//delete
				System.out.print(CommanAmongAll.LIGHT_RED+"ALERT!"+CommanAmongAll.RESET+" Do you really want to delete the Bus (yes/no): ");
				input = sc.nextLine().toLowerCase();
				if(input.equals("no") || input.equals("n")) {
					System.out.println(CommanAmongAll.LIGHT_BLUE+"Deletion Stopped!\n"+CommanAmongAll.RESET);
					try {
						bto.printListOfBuses(bto.viewAllBuses());
						selectBus(sc);
					} catch (SQLException e) {
						System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
						adminHomepageMenu(sc);
						return;
					}
				}
				
				try {
					bto.deleteBusByID(bus.getBusID());
					adminHomepageMenu(sc);
					return;
				} catch (SQLException e) {
					System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
					adminHomepageMenu(sc);
					return;
				}
				
			}else if(input.equals("0")) {
				CommanAmongAll.quit();
				return;
			}else if(input.equals("..")) {
				try {
					bto.printListOfBuses(bto.viewAllBuses());
					selectBus(sc);
					return;
				} catch (SQLException e) {
					System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
					adminHomepageMenu(sc);
					return;
				}
			}else {
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input: "+CommanAmongAll.RESET);
				input = sc.nextLine();
			}
		}
	}
	private static void busEditing(Bus bus,Scanner sc) {
		System.out.println("====================="+CommanAmongAll.TEAL+"EDIT BUS"+CommanAmongAll.RESET+"====================\n");
		
		String busName = null;
		String busType = null;
		int noOfSeats = 0;
		String departurePoint = null;
		String arrivalPoint = null;
		LocalTime dailyDepartureTime = null;
		LocalTime dailyArrivalTime = null;
		double ticketPrice = 0.0;
		
		System.out.println("Fill the Bus details below"+CommanAmongAll.GRAY+" (Enter .. to go back)\n");
		
		System.out.print("Enter Bus Name : ");
		String input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(input.length()<3 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"Length must be greater than 2 and no special characters"+CommanAmongAll.RESET);
			System.out.print("\nEnter Bus Name : ");
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		busName = input;
		
		System.out.print("\nChoose Bus Type:-\n"
				+ "1. AC\n"
				+ "2. Non-AC\n");
		
		System.out.print("\nEnter Choice: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(!(input.length()!=0 && input.matches("\\d") && (int)input.charAt(0)>=49 && (int)input.charAt(0)<=50)) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"\nPlease Enter Valid Input: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		if(input.equals("1")) {
			busType = "AC";
		}else {
			busType = "Non-AC";
		}
		
		System.out.print("\nEnter No. of Seats in Bus: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(!(input.length()!=0 && input.matches("\\d+"))) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"\nPlease Enter Valid Input: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		noOfSeats = Integer.parseInt(input);
		
		System.out.print("\nEnter Departure Point: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(input.length()<3 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nLength must be greater than 2 and no special characters"+CommanAmongAll.RESET);
			System.out.print("Enter Departure Point: ");
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		departurePoint = input;
		
		System.out.print("\nEnter Arrival Point: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(input.length()<3 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nLength must be greater than 2 and no special characters"+CommanAmongAll.RESET);
			System.out.print("Enter Arrival Point: ");
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		arrivalPoint = input;
		
		
		System.out.println("\nFormat of the time should be like this hh-mm-ss [ex: 11:30:00]");
		System.out.print("Enter Daily Departure Time: ");
		input = sc.nextLine(); 
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		boolean indicator = true;
		
		while(indicator) {
			try {
				dailyDepartureTime = LocalTime.parse(input);
				indicator = false;
			} catch (DateTimeParseException e) {
				indicator = true;
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nFormat of the time should be like this hh-mm-ss [ex: 11:30:00]"+CommanAmongAll.RESET);
				System.out.print("Enter Daily Departure Time: ");
				input = sc.nextLine(); 
				
				if(input.equals("..")) {
					System.out.println();
					adminHomepageMenu(sc);
					return;
				}
			}
		}
		
		System.out.println("\nFormat of the time should be like this hh-mm-ss [ex: 11:30:00]");
		System.out.print("Enter Daily Arrival Time: ");
		input = sc.nextLine(); 
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		indicator = true;
		
		while(indicator) {
			try {
				dailyArrivalTime = LocalTime.parse(input);
				indicator = false;
			} catch (DateTimeParseException e) {
				indicator = true;
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nFormat of the time should be like this hh-mm-ss [ex: 11:30:00]"+CommanAmongAll.RESET);
				System.out.print("Enter Daily Arrival Time: ");
				input = sc.nextLine(); 
				
				if(input.equals("..")) {
					System.out.println();
					adminHomepageMenu(sc);
					return;
				}
			}
		}
		
		System.out.print("\nEnter Ticket Price: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			adminHomepageMenu(sc);
			return;
		}
		
		while(!(input.length()!=0 && input.matches("\\d+"))) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"\nPlease Enter Valid Input: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				adminHomepageMenu(sc);
				return;
			}
		}
		ticketPrice = Double.parseDouble(input);
				
		bus.setBusName(busName);
		bus.setBusType(busType);
		bus.setTotalSeat(noOfSeats);
		bus.setDeparturePoint(departurePoint);
		bus.setArrivalPoint(arrivalPoint);
		bus.setDepartureTime(dailyDepartureTime);
		bus.setArrivalTime(dailyArrivalTime);
		bus.setTicketPrice(ticketPrice);
		
		try {
			bto.editBusDetails(bus);
			bto.printBus(bus);
			individualBusOperation(bus,sc);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			adminHomepageMenu(sc);
		}
	}

}



















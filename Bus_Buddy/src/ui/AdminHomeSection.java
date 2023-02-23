package ui;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import common.CommanAmongAll;
import dao.BusTableOperations;
import dao.BusTableOperationsImpl;
import dto.Bus;

public class AdminHomeSection {
	public static void adminHomepageMenu(Scanner sc) {
		System.out.println("\n=================="+CommanAmongAll.TEAL+"ADMIN SECTION"+CommanAmongAll.RESET+"==================\n");
		System.out.println("Admin can perform these tasks:-\n");
		System.out.println(
				  "1. Add a new bus\n"
				+ "2. View all buses\n"
				+ "3. View all customers\n"
				+ "4. View all bookings\n"
				+ "5. View date wise bookings and Revenue\n"
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
	
	private static void adminHomePageInputProcess(int in, Scanner sc){
		switch (in) {
			case 1: {
				busCreation(sc);
				break;
			}
			case 2: {
				try {
					List<Bus> list = bto.viewAllBuses();
					showAllBuses(list, sc);
				} catch (SQLException e) {
					System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
					adminHomepageMenu(sc);
				}
				break;
			}
			case 3: {
				System.out.println("track all customers");
				break;
			}
			case 4: {
				System.out.println("View all bookings");
				break;
			}
			case 5: {
				System.out.println("View date wise bookings and Revenue");
				break;
			}
			case 6: {
				CommanAmongAll.logOutMessage();
				
				LandingPageFunctionality.landingPageIntro();
				LandingPageFunctionality.landingPageMenu(sc);
				break;
			}
			case 0: {
				CommanAmongAll.quit();
				break;
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
		
		
		System.out.println("\nFormat of the date should be like this hh-mm-ss [ex: 11:30:00]");
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
				System.out.println(e);
				indicator = true;
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nFormat of the date should be like this hh-mm-ss [ex: 11:30:00]"+CommanAmongAll.RESET);
				System.out.print("Enter Daily Departure Time: ");
				input = sc.nextLine(); 
				
				if(input.equals("..")) {
					System.out.println();
					adminHomepageMenu(sc);
					return;
				}
			}
		}
		
		System.out.println("\nFormat of the date should be like this hh-mm-ss [ex: 11:30:00]");
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
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nFormat of the date should be like this hh-mm-ss [ex: 11:30:00]"+CommanAmongAll.RESET);
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

	private static void showAllBuses(List<Bus> list, Scanner sc) {
		
		if(list==null) {
			System.out.println(CommanAmongAll.GRAY+"No bus is present to show :\\"+CommanAmongAll.RESET);
			return;
		}
		
		int idWidth = "busID".length();
		int nameWidth = "busName".length();
		int typeWidth = "busType".length();
		int seatWidth = "totalSeat".length();
		int departWidth = "departurePoint".length();
		int arriveWidth = "arrivalPoint".length();
		int departTimeWidth = "departureTime".length();
		int arriveTimeWidth = "arrivalTime".length();
		int priceWidth = "ticketPrice".length();
        
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
        System.out.format(formatString, "busID", "busName", "busType", "totalSeat", "departurePoint", "arrivalPoint", "departureTime", "arrivalTime", "ticketPrice");

        // Print the separator row
        System.out.println(separator);

        // Print each row of bus data
        for (Bus bus : list) {
            System.out.format(formatString, bus.getBusID(), bus.getBusName(), bus.getBusType(), bus.getTotalSeat(), bus.getDeparturePoint(), bus.getArrivalPoint(), bus.getDepartureTime(), bus.getArrivalTime(), bus.getTicketPrice());
        }
        System.out.println(separator+"\n");
        selectBus(sc);
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
			System.err.println();
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
						showAllBuses(bto.viewAllBuses() , sc);
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
					showAllBuses(bto.viewAllBuses(), sc);
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
		
		
		System.out.println("\nFormat of the date should be like this hh-mm-ss [ex: 11:30:00]");
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
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nFormat of the date should be like this hh-mm-ss [ex: 11:30:00]"+CommanAmongAll.RESET);
				System.out.print("Enter Daily Departure Time: ");
				input = sc.nextLine(); 
				
				if(input.equals("..")) {
					System.out.println();
					adminHomepageMenu(sc);
					return;
				}
			}
		}
		
		System.out.println("\nFormat of the date should be like this hh-mm-ss [ex: 11:30:00]");
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
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nFormat of the date should be like this hh-mm-ss [ex: 11:30:00]"+CommanAmongAll.RESET);
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



















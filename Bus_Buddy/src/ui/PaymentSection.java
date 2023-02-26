package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import admin.Admin;
import common.CommanAmongAll;
import cuurentUser.CurrentUser;
import dao.BookingsTableOperations;
import dao.BookingsTableOperationsImpl;
import dao.CustomerTableOperations;
import dao.CustomerTableOperationsImpl;
import dto.TicketBookingReceipt;

public class PaymentSection {
	
	private static BookingsTableOperations bkto = new BookingsTableOperationsImpl();
	private static CustomerTableOperations cto = new CustomerTableOperationsImpl();
	
	public static void paymentSection(Scanner sc, TicketBookingReceipt tbr) {
		System.out.println("================="+CommanAmongAll.TEAL+"PAYMENT SECTION"+CommanAmongAll.RESET+"=================\n");
		System.out.println("Choose the method of payment:-\n");
		if(CurrentUser.currentUser.getTotalBalance() < tbr.getBus().getTicketPrice()*tbr.getBooking().getNoOfPassenger()) {
			System.out.println("1. Wallet ("+CommanAmongAll.LIGHT_RED+"₹"+CurrentUser.currentUser.getTotalBalance()+CommanAmongAll.RESET+")\n"
					+ "2. Card\n"
					+ ".. Go Back\n");
		}else {
			System.out.println("1. Wallet ("+CommanAmongAll.LIGHT_GREEN+"₹"+CurrentUser.currentUser.getTotalBalance()+CommanAmongAll.RESET+")\n"
					+ "2. Card\n"
					+ ".. Go Back\n");
		}
		
		System.out.print("Enter Input : ");
		String input = sc.nextLine();
		
		boolean indicator = true;
		
		while(indicator) {
			if(input.equals("1")) {
				indicator = false;
				int res = walletPayment(sc, tbr);
				if(res == 0) {
					System.out.println(CommanAmongAll.LIGHT_RED+"\nNot Sufficient Balance!"+CommanAmongAll.LIGHT_ORANGE+"\nPlease Try Different Payment Method\n"+CommanAmongAll.RESET);
					paymentSection(sc, tbr);
					return;
				}
			} else if(input.equals("2")) {
				indicator = false;
				cardPayment(sc, tbr);
			} else if(input.equals("..")) {
				indicator = false;
				TicketBookingSection.bookTicket(sc);
			} else {
				indicator = true;
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input : "+CommanAmongAll.RESET);
				input = sc.nextLine();
			}
		}
	}
	
	private static void cardPayment(Scanner sc, TicketBookingReceipt tbr) {
		System.out.print("\n"+CommanAmongAll.GO_BACK_TEXT);
		System.out.println(CommanAmongAll.LIGHT_BLUE+"Fill the Card Details below:-\n"+CommanAmongAll.RESET);
		
		String cardNo = null;
		String nameInCard = null;
		YearMonth expDate = null;
		String cvv = null;
		
		
		System.out.print("Enter Card No. : ");
		String input = sc.nextLine();
		
		if(input.equals("..")) {
			paymentSection(sc, tbr);
			return;
		}
		
		while(input.length()!=16 && !input.matches("\\d")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Card No. : "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				paymentSection(sc, tbr);
				return;
			}
		}
		cardNo = input;
		
		System.out.print("Enter Card Holder's Name : ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			paymentSection(sc, tbr);
			return;
		}
		
		while(input.length()<3|| input.length()>25 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"Length must be greater than 2 and less than 25 and no special characters"+CommanAmongAll.RESET);
			System.out.print("\nEnter Card Holder's Name : ");
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				paymentSection(sc, tbr);
				return;
			}
		}
		nameInCard = input.toLowerCase();
		
		System.out.println("\nFormat of the Expiry date should be like this yyyy-mm [ex: 2004-07]");
		System.out.print("Enter Card Expiry Date : ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			paymentSection(sc, tbr);
			return;
		}
		boolean indicator = true;
		while(indicator) {
			try {
				expDate = YearMonth.parse(input);
				indicator = false;
				
				if(expDate.isBefore(YearMonth.now())) {
					indicator = true;
					System.out.println(CommanAmongAll.LIGHT_RED+"This Card Is'nt Valid! Please Try Again..."+CommanAmongAll.RESET);
					
					System.out.print("Enter Valid Card Expiry Date : ");
					input = sc.nextLine();
					if(input.equals("..")) {
						paymentSection(sc, tbr);
						return;
					}
				}
			} catch (DateTimeParseException e) {
				indicator = true;
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"\nFormat of the Expiry date should be like this yyyy-mm [ex: 2004-07]"+CommanAmongAll.RESET);
				System.out.print("Enter Valid Date of Travel : ");
				input = sc.nextLine(); 
				
				if(input.equals("..")) {
					System.out.println();
					paymentSection(sc, tbr);
					return;
				}
			}
		}
		
		System.out.print("Enter CVV : ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			paymentSection(sc, tbr);
			return;
		}
		
		while(input.length()>4 && input.length()<3 && !input.matches("\\d")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid CVV : "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				paymentSection(sc, tbr);
				return;
			}
		}
		cardNo = input;
		
		proceedForBooking(sc, tbr);
	}
	
	private static int walletPayment(Scanner sc, TicketBookingReceipt tbr) {
		if(CurrentUser.currentUser.getTotalBalance() < tbr.getBus().getTicketPrice() * tbr.getBooking().getNoOfPassenger()) {
			return 0;
		}
		else {
			double payable = (tbr.getBus().getTicketPrice() * tbr.getBooking().getNoOfPassenger());
			double newBalance = CurrentUser.currentUser.getTotalBalance() - payable;
			try {
				CurrentUser.currentUser.setTotalBalance(newBalance);
				CurrentUser.setCurrentUser(CurrentUser.currentUser);
				
				try {
					Admin.setAdmin(new Admin());
					Admin.admin.setAsset((long)(Admin.admin.getAsset()+payable));
					Admin.setAdmin(new Admin());
				}catch (IOException ex) {
					System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
					paymentSection(sc, tbr);
					return 0;
				}
				
				cto.updateCustomerByID(CurrentUser.currentUser);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
				try {
					Admin.setAdmin(new Admin());
					Admin.admin.setAsset((long)(Admin.admin.getAsset()-payable));
					Admin.setAdmin(new Admin());
				}catch (IOException ex) {
					System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
					paymentSection(sc, tbr);
					return 0;
				}
				CurrentUser.currentUser.setTotalBalance(CurrentUser.currentUser.getTotalBalance() + payable);
				System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
				paymentSection(sc, tbr);
				return 0;
			}
			boolean res = proceedForBooking(sc, tbr);
			if(!res) {
				CurrentUser.currentUser.setTotalBalance(CurrentUser.currentUser.getTotalBalance() + payable);
				paymentSection(sc, tbr);
				return 0;
			}
			return 1;
		}
	}
	private static boolean proceedForBooking(Scanner sc, TicketBookingReceipt tbr) {
		try {
			bkto.bookABusTicket(tbr.getBooking());
			System.out.println(CommanAmongAll.LIGHT_GREEN+"\nTICKET BOOKED!"+CommanAmongAll.RESET);
			System.out.println(CommanAmongAll.LIGHT_YELLOW+"Thank You from Team Bus Buddy ^.^\n\n"+CommanAmongAll.RESET);
			
			LandingPageFunctionality.printLandingPageIntro();
			UserHomeSection.userHomeSection(sc, CurrentUser.currentUser);
			return true;
		} catch (SQLException e) {
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			paymentSection(sc, tbr);
			return false;
		}
	}
}

package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import admin.Admin;
import common.CommanAmongAll;
import cuurentUser.CurrentUser;
import dao.BookingsTableOperations;
import dao.BookingsTableOperationsImpl;
import dto.Bookings;
import dto.Customers;
import dto.TicketBookingReceipt;

public class UserHomeSection {
	private static BookingsTableOperations bkto = new BookingsTableOperationsImpl();
	
	public static void userHomeSection(Scanner sc, Customers customer) {
		System.out.println("\n==================="+CommanAmongAll.TEAL+"HOME SECTION"+CommanAmongAll.RESET+"==================\n");
		String userName = customer.getFirstName().toLowerCase();
		userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();
		
		System.out.println(CommanAmongAll.TEAL+"Hi "+CommanAmongAll.LIGHT_YELLOW+userName+CommanAmongAll.TEAL+", you can perform these tasks:-\n"+CommanAmongAll.RESET);
		System.out.println(
				  "1. Book a bus ticket\n"
				+ "2. View all booked tickets\n"
				+ "3. Your Profile\n"
				+ "4. Log out\n"
				+ "0. Quit\n");
		
		System.out.print("Enter Input: ");
		String input = sc.nextLine();
		
		boolean indicator = true;
		while(indicator) {
			if(input.equals("1")) {
				indicator = false;
				
				TicketBookingSection.bookTicket(sc);
			}else if(input.equals("2")) {
				indicator = false;
				//view all bookings
				viewCustomerBooking(sc, customer);
				return;
			}else if(input.equals("3")) {
				indicator = false;
				customer.printUser();
				goBack(sc, customer);
				return;
			}else if(input.equals("4")) {
				indicator = false;
				
				if(CurrentUser.logOut()) {
					CommanAmongAll.logOutMessage();
					LandingPageFunctionality.landingPageIntro(sc);
				}else {
					System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
					userHomeSection(sc, customer);
					return;
				}
			}else if(input.equals("0")) {
				indicator = false;
				CommanAmongAll.quit();
			}else {
				indicator = true;
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input: "+CommanAmongAll.RESET);
				input = sc.nextLine();
			}
		}
	}

	private static void goBack(Scanner sc, Customers customer) {
		System.out.print("\nPress .. To Go Back: ");
		String input = sc.nextLine();
		while(!input.equals("..")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter .. To Go Back: "+CommanAmongAll.RESET);
			input = sc.nextLine();
		}
		userHomeSection(sc, customer);
	}
	
	private static void viewCustomerBooking(Scanner sc, Customers customer) {
		List<Bookings> list = null;
		try {
			list = bkto.getListOfAllBookingsByCustomerID(customer.getCustomerID());
			if(bkto.printListOfBooking(list, "Your All Bookings")==0) {
				goBack(sc, customer);
				return;
			}
		} catch (SQLException e) {
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			userHomeSection(sc, customer);
			return;
		}
		System.out.println(CommanAmongAll.GO_BACK_TEXT);
		System.out.println(CommanAmongAll.LIGHT_BLUE+"Select Booking By ID:-\n"+CommanAmongAll.RESET);
		System.out.print("Enter Booking ID: ");
		String input = sc.nextLine();
		
		if(input.trim().equals("..")) {
			userHomeSection(sc, customer);
			return;
		}
		boolean indicator = true;
		while(indicator) {
			try {
				int bookingID = Integer.parseInt(input);
				int res = isValidBookingID(bookingID, list);
				if(res==-1) {
					indicator = true;
					System.out.println(CommanAmongAll.LIGHT_RED+"No Match Found!"+CommanAmongAll.RESET);
					System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input: "+CommanAmongAll.RESET);
					input = sc.nextLine();
					if(input.trim().equals("..")) {
						userHomeSection(sc, customer);
						return;
					}
					continue;
				}
				try {
					TicketBookingReceipt tbr = new TicketBookingReceipt(list.get(res));
					tbr.printEnteredDetails();
					
					cancelBookingMenu(sc, tbr, customer);
					return;
				} catch (SQLException e) {
					System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
					userHomeSection(sc, customer);
					return;
				}
			}catch (NumberFormatException e) {
				indicator = true;
				System.out.println(CommanAmongAll.LIGHT_RED+"No Match Found!"+CommanAmongAll.RESET);
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input: ");
				input = sc.nextLine();
				if(input.trim().equals("..")) {
					userHomeSection(sc, customer);
					return;
				}
			}
		}
	}
	private static int isValidBookingID(int id, List<Bookings> list) {
		
		for(int i=0; i<list.size(); i++) {
			Bookings b = list.get(i);
			if(b.getBookingID()==id) {
				return i;
			}
		}
		
		return -1;
	}
	
	private static void cancelBookingMenu(Scanner sc, TicketBookingReceipt tbr, Customers customer) {
		System.out.println("1. "+CommanAmongAll.LIGHT_RED+"Delete"+CommanAmongAll.RESET+" Ticket\n"
				+ ".. Go Back\n");
		
		System.out.print("Enter Input: ");
		String input = sc.nextLine();
		
		if(input.trim().toLowerCase().equals("..")){
			viewCustomerBooking(sc, customer);
			return;
		}
		
		boolean indicator = true;
		
		while(indicator) {
			if(input.trim().equals("1")) {
				System.out.print("\nDo you realy want to delete? (y/n): ");
				String yesNo = sc.nextLine();
				boolean i = true;
				while(i) {
					if(yesNo.trim().toLowerCase().equals("y") || yesNo.trim().toLowerCase().equals("yes")) {
						try {
							boolean res = bkto.cancelABusTicket(tbr.getBooking());
							if(res) {
								try {
									Admin.setAdmin(new Admin());
									Admin.admin.setAsset((long)(Admin.admin.getAsset()-tbr.getBooking().getTotalAmount()));
									Admin.setAdmin(new Admin());
								}catch (IOException e) {
									System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
									viewCustomerBooking(sc, customer);
									return;
								}
								
								CurrentUser.currentUser.setTotalBalance(CurrentUser.currentUser.getTotalBalance()+tbr.getBooking().getTotalAmount());
								System.out.println("Your wallet has been credited with "+CommanAmongAll.LIGHT_GREEN+"₹"+tbr.getBooking().getTotalAmount()+CommanAmongAll.RESET);
							}
						} catch (SQLException e) {
							System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
						}
						viewCustomerBooking(sc, customer);
						return;
					}else if(yesNo.trim().toLowerCase().equals("n") || yesNo.trim().toLowerCase().equals("no")){
						viewCustomerBooking(sc, customer);
						return;
					}else if(yesNo.trim().toLowerCase().equals("..")){
						viewCustomerBooking(sc, customer);
						return;
					}else {
						i = true;
						System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input: ");
						yesNo = sc.nextLine();
					}
				}
			}else if(input.trim().equals("..")) {
				
			}else {
				indicator = true;
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input: ");
				input = sc.nextLine();
				if(input.trim().toLowerCase().equals("..")){
					viewCustomerBooking(sc, customer);
					return;
				}
			}
		}
	}
}

package ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import common.CommanAmongAll;
import dao.CustomerTableOperations;
import dao.CustomerTableOperationsImpl;
import dto.Customers;

public class UserSignUpSection {
	private static CustomerTableOperations cto = new CustomerTableOperationsImpl();
	
	static void signUpForm(Scanner sc) {
		System.out.println("\n=================="+CommanAmongAll.TEAL+"SIGNUP SECTION"+CommanAmongAll.RESET+"=================\n");
		
//		int customerID = 0;
		String firstName = null;
		String lastName= null;
		LocalDate dob= null;
		String city= null;
		String state= null;
		int zipcode= 0;
		String phone= null;
		String email= null;
		String password= null;
		LocalDate dateEntered= LocalDate.now();
		
		System.out.println("Fill The Sign Up Details Correctly"+CommanAmongAll.GRAY+" [Enter .. to go back]\n");
		
		System.out.print("Enter First Name: ");
		String input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		
		while(input.length()<3|| input.length()>15 || !input.matches("[a-zA-Z]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"Length must be greater than 2 and less than 15 and no special characters");
			System.out.print("Please Enter Valid First Name: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserLoginSignupSection.logInOrSignUp(sc);
				return;
			}
		}
		firstName = input;
		
		System.out.print("\nEnter Last Name: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		
		while(input.length()<3|| input.length()>15 || !input.matches("[a-zA-Z]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"Length must be greater than 2 and less than 15 and no special characters");
			System.out.print("Please Enter Last Name: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserLoginSignupSection.logInOrSignUp(sc);
				return;
			}
		}
		lastName = input;
		
		System.out.println("\nFormat of the date should be like this yyyy-mm-dd [ex: 2004-07-22]");
		System.out.print("Enter DOB: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		boolean indicator = true;
		while(indicator) {
			try {
				dob = LocalDate.parse(input);
				indicator = false;
			} catch (DateTimeParseException e) {
				indicator = true;
				System.out.println(CommanAmongAll.LIGHT_ORANGE+"Format of the date should be like this yyyy-mm-dd [ex: 2004-07-22]");
				System.out.print("Please Enter Valid DOB: "+CommanAmongAll.RESET);
				input = sc.nextLine(); 
				
				if(input.equals("..")) {
					System.out.println();
					UserLoginSignupSection.logInOrSignUp(sc);
					return;
				}
			}
		}
		
		System.out.print("\nEnter Your City: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		
		while(input.length()<3 || input.length()>20 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"Length must be between 3-20 and no special characters");
			System.out.print("Please Enter Your Valid City: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserLoginSignupSection.logInOrSignUp(sc);
				return;
			}
		}
		city = input;
		

		System.out.print("\nEnter Your State: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		
		while(input.length()<3 || input.length()>20 || !input.matches("[a-zA-Z\\s]+")) {
			System.out.println(CommanAmongAll.LIGHT_ORANGE+"Length must be between 3-20 and no special characters");
			System.out.print("Enter Your Valid State Name: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserLoginSignupSection.logInOrSignUp(sc);
				return;
			}
		}
		state = input;
		
		
		System.out.print("\nEnter Your ZIP Code: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		
		while(input.length()!=6 || !input.matches("\\d+")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Your Valid ZIP Code : "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserLoginSignupSection.logInOrSignUp(sc);
				return;
			}
		}
		zipcode = Integer.parseInt(input);
		
		System.out.print("\nEnter Your Mobile No: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		
		while(input.length()!=10 || !input.matches("\\d+")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Your Valid Mobile No: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserLoginSignupSection.logInOrSignUp(sc);
				return;
			}
		}
		
		try {
			while(!cto.verifyMobile(input)) {
				System.out.println(CommanAmongAll.LIGHT_RED+"This Number Is Already Being Used!"+CommanAmongAll.RESET);
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Another Mobile No: "+CommanAmongAll.RESET);
				input = sc.nextLine();
				if(input.equals("..")) {
					System.out.println();
					UserLoginSignupSection.logInOrSignUp(sc);
					return;
				}
			}
		} catch (SQLException e2) {
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			UserLoginSignupSection.logInOrSignUp(sc);
		}
		
		phone = input;
		
		System.out.print("\nEnter Your Email Address: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		
		while(!input.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Your Valid Email Address: "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserLoginSignupSection.logInOrSignUp(sc);
				return;
			}
		}
		try {
			while(!cto.verifyEmail(input)) {
				System.out.println(CommanAmongAll.LIGHT_RED+"This Email Address Is Already Being Used!"+CommanAmongAll.RESET);
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Another Email Address: "+CommanAmongAll.RESET);
				input = sc.nextLine();
				if(input.equals("..")) {
					System.out.println();
					UserLoginSignupSection.logInOrSignUp(sc);
					return;
				}
			}
		} catch (SQLException e1) {
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			UserLoginSignupSection.logInOrSignUp(sc);
		}
		email = input.toLowerCase();
		
		System.out.println(CommanAmongAll.GRAY+"\n[Password must be of 8 to 16 character with atleast 1 Uppercase, 1 Lowercase and 1 Special Character]"+CommanAmongAll.RESET);
		System.out.print("Enter A Strong Password: ");
		input = sc.nextLine();
		
		if(input.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		
		while(!input.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$")) {
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Strong Password : "+CommanAmongAll.RESET);
			input = sc.nextLine();
			if(input.equals("..")) {
				System.out.println();
				UserLoginSignupSection.logInOrSignUp(sc);
				return;
			}
		}
		password = input;
		
		Customers customer = new Customers(firstName, lastName, dob, city, state, zipcode, phone, email, password, dateEntered);
		
		
		try {
			if(cto.addNewCustomer(customer)) {
				System.out.println(CommanAmongAll.LIGHT_GREEN+"\nACCOUNT CREATION SUCCESSFUL!\n"+CommanAmongAll.RESET);
				UserLoginSignupSection.logInOrSignUp(sc);
			}
		} catch (SQLException e) {
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			UserLoginSignupSection.logInOrSignUp(sc);
		}
	}
	
}

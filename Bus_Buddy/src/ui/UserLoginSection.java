package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import common.CommanAmongAll;
import cuurentUser.CurrentUser;
import dao.CustomerTableOperations;
import dao.CustomerTableOperationsImpl;
import dto.Customers;

public class UserLoginSection {
	private static final CustomerTableOperations cto = new CustomerTableOperationsImpl();
	
	static void logInAsUser(Scanner sc) {
		System.out.println("\n===================="+CommanAmongAll.TEAL+"USER LOGIN"+CommanAmongAll.RESET+"===================");
		System.out.println("Fill the log in details below"+CommanAmongAll.GRAY+" [Enter .. to go back]\n");
		
		System.out.print("Enter Username/Email: ");
		String identifier = sc.nextLine().toLowerCase();
		
		if(identifier.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		
		System.out.print("Enter Password: ");
		String userPass = sc.nextLine();
		
		if(userPass.equals("..")) {
			System.out.println();
			UserLoginSignupSection.logInOrSignUp(sc);
			return;
		}
		
		try {
			int customerID = cto.verifyLogin(identifier, userPass);
			
			if(customerID==0) {
				System.out.println(CommanAmongAll.LIGHT_RED+"\nWrong Credentials!\n"+CommanAmongAll.RESET);
				UserLoginSection.logInAsUser(sc);
				return;
			}
			
			Customers customer = cto.getCustomerByID(customerID);
			try {
//				CurrentUser.currentUser = customer;
				CurrentUser.setCurrentUser(customer);
				System.out.println(CommanAmongAll.LIGHT_GREEN+"\nHi "+customer.getFirstName().substring(0, 1).toUpperCase() + customer.getFirstName().substring(1).toLowerCase()+", You Logged in Successfully "+CommanAmongAll.LIGHT_YELLOW+"^,^"+CommanAmongAll.RESET);
				UserHomeSection.userHomeSection(sc, customer);
			
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
				UserLoginSignupSection.logInOrSignUp(sc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(CommanAmongAll.RED+"\nSomething is wrong, please try again later\n"+CommanAmongAll.RESET);
			UserLoginSignupSection.logInOrSignUp(sc);
		}
	}
}

package ui;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.Scanner;

import admin.Admin;
import common.CommanAmongAll;
import cuurentUser.CurrentUser;
import dao.BookingsTableOperationsImpl;
import dto.Customers;

public class Main {
	
	static {
		try {
	    	ObjectInput oi = new ObjectInputStream(new FileInputStream("adminInfo.txt"));
	        Admin c = (Admin) oi.readObject();
	        Admin.admin = c;
	        oi.close();
	    } catch (IOException | ClassNotFoundException e) {
	       try {
	    	   Admin.setAdmin(new Admin());
			} catch (IOException e1) {
				System.out.println(CommanAmongAll.RED+"\nFiles and Database are missing..."+CommanAmongAll.RESET);
			    System.out.println(CommanAmongAll.GRAY+"Contact the creator to know more:\n"+CommanAmongAll.LIGHT_BLUE+"https://www.linkedin.com/in/prince-kumar-7b9194247/");
			}
		}
		
	}
	
	static {
		//marking not active bookings
		try {
			BookingsTableOperationsImpl.markNotActiveBookings();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(CommanAmongAll.RED+"\nFiles and Database are missing..."+CommanAmongAll.RESET);
		    System.out.println(CommanAmongAll.GRAY+"Contact the creator to know more:\n"+CommanAmongAll.LIGHT_BLUE+"https://www.linkedin.com/in/prince-kumar-7b9194247/");
		}
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		try {
		    try {
		    	ObjectInput oi = new ObjectInputStream(new FileInputStream("currentUser.txt"));
		    	//one user is already logged in
		        Customers c = (Customers) oi.readObject();
		        CurrentUser.currentUser = c;
		        
		        LandingPageFunctionality.printLandingPageIntro();
		        UserHomeSection.userHomeSection(sc, c);
		        
		        oi.close();
		    } catch (EOFException e) {
		        // no user is logged in
		        LandingPageFunctionality.landingPageIntro(sc);
		    }
		    
		} catch (IOException | ClassNotFoundException e) {
		    System.out.println(CommanAmongAll.RED+"Files and Database are missing..."+CommanAmongAll.RESET);
		    System.out.println(CommanAmongAll.GRAY+"Contact the creator to know more:\n"+CommanAmongAll.LIGHT_BLUE+"https://www.linkedin.com/in/prince-kumar-7b9194247/");
		}
		sc.close();
	}
}

package ui;

import java.util.Scanner;

import admin.Admin;
import common.CommanAmongAll;

public class AdminLogInSection {
	
	private static final String ADMIN_USERNAME = Admin.admin.getAdminName();
	private static final String ADMIN_PASSWORD = Admin.admin.getAdminPassword();
	
	static void logInAsAdmin(Scanner sc) {
		System.out.println("\n==================="+CommanAmongAll.TEAL+"ADMIN LOGIN"+CommanAmongAll.RESET+"===================");
		System.out.println("Fill the log in details below"+CommanAmongAll.GRAY+" [Enter .. to go back]\n"+CommanAmongAll.RESET);
		
		System.out.print("Enter Admin Name: ");
		String adminName = sc.nextLine();
		
		if(adminName.equals("..")) {
			System.out.println();
			LandingPageFunctionality.landingPageMenu(sc);
			return;
		}
		
		System.out.print("Enter Password: ");
		String adminPass = sc.nextLine();
		
		if(adminPass.equals("..")) {
			System.out.println();
			LandingPageFunctionality.landingPageMenu(sc);
			return;
		}
		
		while(!(adminName.equals(ADMIN_USERNAME) && adminPass.equals(ADMIN_PASSWORD))) {
			System.out.println(CommanAmongAll.LIGHT_RED+"Wrong Credentials, Try Again...\n"+CommanAmongAll.RESET);
			
			System.out.print("Enter Admin Name: ");
			adminName = sc.nextLine();
			
			if(adminName.equals("..")) {
				System.out.println();
				LandingPageFunctionality.landingPageMenu(sc);
				return;
			}
			
			System.out.print("Enter Password: ");
			adminPass = sc.nextLine();
			
			if(adminPass.equals("..")) {
				System.out.println();
				LandingPageFunctionality.landingPageMenu(sc);
				return;
			}
		}
		System.out.println(CommanAmongAll.LIGHT_GREEN+"Login Successful!\n"+CommanAmongAll.RESET);
		AdminHomeSection.adminHomepageMenu(sc);
	}
	
}

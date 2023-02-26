package ui;

import java.util.Scanner;

import common.CommanAmongAll;

public class UserLoginSignupSection {
	static void logInOrSignUp(Scanner sc) {
		System.out.println("\n=================="+CommanAmongAll.TEAL+"LogIn / SignUp"+CommanAmongAll.RESET+"=================");
		
		System.out.print("\nProceed by choosing to:-\n"
				+ "1. Log In\n"
				+ "2. Sign Un\n"
				+ ".. Go back");
		
		System.out.print("\nEnter Choice: ");
		String input = sc.nextLine();
		
		boolean indicator = true;
		
		while(indicator) {
			if(input.equals("1")) {
				UserLoginSection.logInAsUser(sc);
				indicator = false;
			}else if(input.equals("2")) {
				UserSignUpSection.signUpForm(sc);
				indicator = false;
			}else if(input.equals("..")) {
				System.out.println();
				LandingPageFunctionality.landingPageIntro(sc);
				indicator = false;
				return;
			}else if(input.trim().equals("") || input.trim().equals("\n")){
				indicator = true;
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input: "+CommanAmongAll.RESET);
				input = sc.nextLine();
			}else {
				indicator = true;
				System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input: "+CommanAmongAll.RESET);
				input = sc.nextLine();
			}
		}
	}
}

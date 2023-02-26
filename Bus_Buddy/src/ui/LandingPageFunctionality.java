package ui;

import java.util.Scanner;

import common.CommanAmongAll;

public class LandingPageFunctionality {
	
	public static void printLandingPageIntro() {
		System.out.println("===================="+CommanAmongAll.YELLOW + "BUS BUDDY" + CommanAmongAll.RESET+"====================");
		System.out.println("==========="+CommanAmongAll.TEAL+"Ride in style, book a mile!"+CommanAmongAll.RESET+"===========");
		System.out.println();
		
		String aboutApp = "Bus Buddy is a online bus ticket booking platform,\nwhere travellers book their bus tickets with best \noffers.\n\n";
		for(int i=0; i<aboutApp.length(); i++) {
			System.out.print(aboutApp.charAt(i));
			try {Thread.sleep(10);} catch (InterruptedException e) {}
		}
		try {Thread.sleep(400);} catch (InterruptedException e) {}
	}
	
	static void landingPageIntro(Scanner sc) {
		System.out.println("===================="+CommanAmongAll.YELLOW + "BUS BUDDY" + CommanAmongAll.RESET+"====================");
		System.out.println("==========="+CommanAmongAll.TEAL+"Ride in style, book a mile!"+CommanAmongAll.RESET+"===========");
		System.out.println();
		
		String aboutApp = "Bus Buddy is a online bus ticket booking platform,\nwhere travellers book their bus tickets with best \noffers.\n\n";
		for(int i=0; i<aboutApp.length(); i++) {
			System.out.print(aboutApp.charAt(i));
			try {Thread.sleep(10);} catch (InterruptedException e) {}
		}
		try {Thread.sleep(400);} catch (InterruptedException e) {}
		landingPageMenu(sc);
	}
	
	static void landingPageMenu(Scanner sc) {
		System.out.println(CommanAmongAll.TEAL+"Proceed by choosing one of the following options:-\n"+CommanAmongAll.RESET);
		System.out.println(
				"1. Continue As User\n"
			  + "2. Continue As Admin\n"
			  + "0. Quit\n");
		System.out.print("Enter Input: ");
		String in = sc.nextLine();
		while(in.length()==0 || !in.matches("\\d") || (int)in.charAt(0)<48 || (int)in.charAt(0)>50){
			System.out.print(CommanAmongAll.LIGHT_ORANGE+"Please Enter Valid Input: "+CommanAmongAll.RESET);
			in = sc.nextLine();
		}
		landingPageInputProcess(Integer.parseInt(in), sc);
	}
	
	private static void landingPageInputProcess(int input, Scanner sc) {
		if(input==1) {
			UserLoginSignupSection.logInOrSignUp(sc);
		}else if(input==2) {
			AdminLogInSection.logInAsAdmin(sc);
		} else if(input==0) {
			CommanAmongAll.quit();
		}
	}
}

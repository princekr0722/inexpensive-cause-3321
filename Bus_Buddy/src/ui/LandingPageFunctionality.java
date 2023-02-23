package ui;

import java.util.Scanner;

import common.CommanAmongAll;

public interface LandingPageFunctionality {
	
	static void landingPageIntro() {
		System.out.println("===================="+CommanAmongAll.YELLOW + "Bus Buddy" + CommanAmongAll.RESET+"====================");
		System.out.println("==========="+CommanAmongAll.TEAL+"Ride in style, book a mile!"+CommanAmongAll.RESET+"===========");
		System.out.println();
		
		String aboutApp = "Bus Buddy is a online bus ticket booking platform,\nwhere travellers book their bus tickets with best \noffers.\n\n";
		for(int i=0; i<aboutApp.length(); i++) {
			System.out.print(aboutApp.charAt(i));
			try {Thread.sleep(10);} catch (InterruptedException e) {}
		}
		try {Thread.sleep(400);} catch (InterruptedException e) {}
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
			System.out.print("Please Enter Valid Input: ");
			in = sc.nextLine();
		}
		landingPageInputProcess(Integer.parseInt(in), sc);
	}
	
	private static void landingPageInputProcess(int input, Scanner sc) {
		if(input==1) {
			System.out.println(CommanAmongAll.RED+"Work in progress for User Login..."+CommanAmongAll.RESET);
		}else if(input==2) {
			AdminLogInSection.logInAsAdmin(sc);
		} else if(input==0) {
			CommanAmongAll.quit();
		}
	}
}

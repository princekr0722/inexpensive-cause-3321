package ui;

import java.util.Scanner;

public interface Main {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		LandingPageFunctionality.landingPageIntro();
		LandingPageFunctionality.landingPageMenu(sc);
		
		
		
		sc.close();
	}
}

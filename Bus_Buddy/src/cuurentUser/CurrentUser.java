package cuurentUser;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

import admin.Admin;
import common.CommanAmongAll;
import dto.Customers;

public class CurrentUser {
	public static Customers currentUser = null;
	
	public static void setCurrentUser(Customers c) throws IOException{
		
		
		FileOutputStream fos = new FileOutputStream("currentUser.txt");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(c);
		
		oos.close();
	}
	
	public static boolean logOut() {
		
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("currentUser.txt")))){
			
			bw.write("");
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}

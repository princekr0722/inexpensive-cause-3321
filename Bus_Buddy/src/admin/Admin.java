package admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import common.CommanAmongAll;

public class Admin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String ADMIN_NAME = "Tom";
	private final String ADMIN_PASSWORD = "Jerry";
	private long asset = 100000000;
	
	public static Admin admin = null;
	
	public static void setAdmin(Admin c) throws IOException{
		FileOutputStream file = new FileOutputStream("adminInfo.txt");
		ObjectOutput oo = new ObjectOutputStream(file);
		
		oo.writeObject(c);
		admin = c;
		oo.close();
	}

	public String getAdminName() {
		return ADMIN_NAME;
	}

	public String getAdminPassword() {
		return ADMIN_PASSWORD;
	}

	public long getAsset() {
		return asset;
	}

	public void setAsset(long asset) {
		this.asset = asset;
	}
	
	public void printAdmin() {
		int nameWidth = "Admin Name".length();
	    int passwordWidth = "Admin Password".length();
	    int assetWidth = "Asset".length();
	    
	    if (this.getAdminName().length() > nameWidth) {
	        nameWidth = this.getAdminName().length();
	    }
	    if (this.getAdminPassword().length() > nameWidth) {
	        nameWidth = this.getAdminPassword().length();
	    }
	    if ((this.getAsset()+"").length()+1 > assetWidth) {
	    	assetWidth = (this.getAsset()+"").length();
	    }
	    int max = 0;
	    if(nameWidth>max)max = nameWidth;
	    if(passwordWidth>max)max = passwordWidth;
	    if(assetWidth>max)max = assetWidth;
	    
		System.out.println(CommanAmongAll.TEAL+"ADMIN PROFILE:-"+CommanAmongAll.RESET+"\n");
	    String formatString = "| %1$-" + max + "s | %2$-" + max + "s |\n";
	    
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "Field", "Value");
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	    System.out.format(formatString, "Admin Name", this.getAdminName());
	    System.out.format(formatString, "Admin Password", this.getAdminPassword());
	    System.out.format(formatString, "Asset", "₹"+this.getAsset());
	    System.out.println(new String(new char[max * 2 + 7]).replace('\0', '-'));
	}
}

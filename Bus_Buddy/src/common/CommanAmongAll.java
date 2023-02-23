package common;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommanAmongAll {
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String GRAY = "\u001B[37m";
	public static final String TEAL = "\u001B[36m";
	public static final String LIGHT_RED = "\u001B[91m";
	public static final String LIGHT_GREEN = "\u001B[92m";
	public static final String LIGHT_YELLOW = "\u001B[93m";
	public static final String LIGHT_ORANGE = "\u001B[38;5;208m";
	public static final  String LIGHT_BLUE = "\u001B[94m";
	
	public static final String GO_BACK_TEXT = GRAY+"[Enter .. to go back]\n"+RESET;
	
	public static final void quit() {
		System.out.println("\nThank you for using our services, Good Day Ahead "+CommanAmongAll.YELLOW+":)"+CommanAmongAll.RESET);
		System.exit(1);
	}
	
	public static final void logOutMessage() {
		System.out.println(CommanAmongAll.LIGHT_GREEN+"Logout Successful!\n"+CommanAmongAll.RESET);
	}
	
	public static boolean isResultSetEmpty(ResultSet rs) throws SQLException {
		return (!rs.isBeforeFirst() && rs.getRow()==0);
	}
}

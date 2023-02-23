package dto;

public class Admin {
	private double debt;
	private double credit;
	private double revenue;
	private double profit;
	
	private final String adminName = "Tom";
	private final String password = "Jerry";
	public double getDebt() {
		return debt;
	}
	public void setDebt(double debt) {
		this.debt = debt;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public String getAdminName() {
		return adminName;
	}
	public String getPassword() {
		return password;
	}
}

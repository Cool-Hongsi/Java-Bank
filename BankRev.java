package Task2;

import java.util.ArrayList;

class Bank{
	
	public double balance;
	public double asset;
	ArrayList<Integer> NumberLoan = new ArrayList<>(); // same as ArrayList<Integer> NumberLoan = new ArrayList<Integer>();
	ArrayList<Integer> LoanedID = new ArrayList<>(); // same as ArrayList<Integer> NumberLoan = new ArrayList<Integer>();
	ArrayList<Double> LoanedAmount = new ArrayList<>(); // same as ArrayList<Double> NumberLoan = new ArrayList<Double>();
	
	public Bank(){
		this.balance = 0;
		this.asset = 0;
	}
	
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	public void setNumberOfLoaned(int numberOfLoaned) {
		this.NumberLoan.add(numberOfLoaned);
	}
	
	public void setLoanedID(int loanedID) {
		this.LoanedID.add(loanedID);
	}
	
	public void setAmount(double amount) {
		this.LoanedAmount.add(amount);
	}
	
	public double getBalance(){
		return this.balance;
	}
	
	public ArrayList<Integer> getNumberOfLoaned() {
		return this.NumberLoan;
	}
	
	public ArrayList<Integer> getLoanedID() {
		return this.LoanedID;
	}
	
	public ArrayList<Double> getAmount() {
		return this.LoanedAmount;
	}
}
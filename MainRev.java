package Task2;

import java.util.ArrayList;
import java.util.Scanner;

public class MainRev{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int i = 0, j = 0, k = 0, numberOfLoaned = 0, loanedID = 0;
		double balance = 0, amount = 0, changedNumberOfBanks, changedMinimum;
		ArrayList<Integer> unsafeBanks = new ArrayList<Integer>();
		
		String numberOfBanks = setNumberOfBanks(sc);
		changedNumberOfBanks = Double.parseDouble(numberOfBanks);
		
		do {
			if(changedNumberOfBanks <= 0) {
				System.out.print("Input Valid Banks : ");
				changedNumberOfBanks = sc.nextDouble();
			}
		}while(changedNumberOfBanks <= 0);
		
		Bank[] bank = new Bank[(int) changedNumberOfBanks];
		
		String minimumLimit = setMinimum(sc);
		changedMinimum = Integer.parseInt(minimumLimit);
		
		do {
			if(changedMinimum <= 0) {
				System.out.print("Input Valid Limit : ");
				changedMinimum = sc.nextDouble();
			}
		}while(changedMinimum <= 0);
		
		for(i=0; i<bank.length; i++) {
			bank[i] = new Bank();
		}
		
		for(i=0; i<bank.length; i++) {
			
			System.out.print("\nBank # " + i + "\n" + "-> Balance : ");
			balance = sc.nextDouble();
			do {
				if(balance < 0) {
					System.out.print("Input Valid Balance : ");
					balance = sc.nextDouble();
				}
			}while(balance <= 0);
			bank[i].setBalance(balance);
			
			System.out.print("-> Number of banks Loaned : ");
			numberOfLoaned = sc.nextInt();
			
			bank[i].setNumberOfLoaned(numberOfLoaned);
			
			if(numberOfLoaned >= 2) {
				for(j=0; j<numberOfLoaned; j++) {
					System.out.print("   -> Bank ID : ");
					loanedID = sc.nextInt();
					do {
						if(loanedID > changedNumberOfBanks-1) {
							System.out.print("Input Valid Banks : (0 ~ " + (int)(changedNumberOfBanks-1) + ") ");
							loanedID = sc.nextInt();
						}
					}while(loanedID > changedNumberOfBanks-1);
					
					bank[i].setLoanedID(loanedID);
					
					System.out.print("   -> Amount : ");
					amount = sc.nextDouble();
					bank[i].setAmount(amount);
				}
			}
			else if(numberOfLoaned == 1){
				System.out.print("   -> Bank ID : ");
				loanedID = sc.nextInt();
				do {
					if(loanedID > changedNumberOfBanks-1) {
						System.out.print("Input Valid Banks : (0 ~ " + (int)(changedNumberOfBanks-1) + ") ");
						loanedID = sc.nextInt();
					}
				}while(loanedID > changedNumberOfBanks-1);
				bank[i].setLoanedID(loanedID);
				
				System.out.print("   -> Amount : ");
				amount = sc.nextDouble();
				bank[i].setAmount(amount);
			}
			else {
				System.out.println();
			}
		}
		
		Result(bank, bank.length, i, j, changedMinimum, unsafeBanks);
		
		AdditionalResult(bank, bank.length, i, j, k, changedMinimum, unsafeBanks);
		
		sc.close();
	}
	
	public static boolean isNumber(String value) {
		try {
			Double.parseDouble(value);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static String setNumberOfBanks(Scanner sc) {
		String number;
		System.out.print("Number of banks : ");
		number = sc.next();
		
		if(isNumber(number) == false) {
			do {
				System.out.print("Input Valid Banks : ");
				number = sc.next();
			}while(isNumber(number) == false);
			return number;
		}
		else {
			return number;
		}
	}
	
	public static String setMinimum(Scanner sc) {
		String mini;
		System.out.print("Minimum asset limit : ");
		mini = sc.next();
		if(isNumber(mini) == false) {
			do {
				System.out.print("Input Valid Limit : ");
				mini = sc.next();
			}while(isNumber(mini) == false);
			return mini;
		}
		else {
			return mini;
		}
	}
	
	public static void Result(Bank[] bank, int length, int i, int j, double changedMinimum, ArrayList<Integer> unsafeBanks) {
		System.out.println("\n***** RESULT *****\n");
		System.out.println("The Minimum asset limit : " + changedMinimum + "\n");
		
		for(i=0; i<bank.length; i++) {
			System.out.print("Bank # " + i + " -> Balance : " + bank[i].getBalance() + " -> Number of banks loaned : " + 
					bank[i].getNumberOfLoaned().get(0));
			if(bank[i].getNumberOfLoaned().get(0) >= 2) {
				for(j=0; j<bank[i].getNumberOfLoaned().get(0); j++) {
					System.out.print(" -> Bank ID : " + bank[i].getLoanedID().get(j) + " -> Amount : " + 
							bank[i].getAmount().get(j));
				}
			}
			else if(bank[i].getNumberOfLoaned().size() == 1) {
				for(j=0; j<bank[i].getNumberOfLoaned().get(0); j++) {
					System.out.print(" -> Bank ID : " + bank[i].getLoanedID().get(j) + " -> Amount : " + 
						bank[i].getAmount().get(j));
				}
			}
			else {
				System.out.println();
			}
			System.out.println();
		}
		
		System.out.println();
		
		for(i=0; i<bank.length; i++) {
			if(bank[i].getNumberOfLoaned().size() != 0) {
				for(j=0; j<bank[i].getNumberOfLoaned().get(0); j++) {
					bank[i].asset += bank[i].getAmount().get(j);
				}
			}
			bank[i].asset += bank[i].getBalance();
			System.out.println("Bank # " + i + " Asset : " + bank[i].asset);
		}
		
		System.out.println();
		
		for(i=0; i<bank.length; i++) {
			if(bank[i].asset < changedMinimum) {
				unsafeBanks.add(i);
				System.out.print(" Unsafe banks are " + i);
			}
		}
	}
	
	public static void AdditionalResult(Bank[] bank, int length, int i, int j, int k, double changedMinimum, ArrayList<Integer> unsafeBanks) {
		for(i=0; i<bank.length; i++) {
			if(bank[i].getNumberOfLoaned().size() != 0) {
				for(j=0; j<bank[i].getNumberOfLoaned().get(0); j++) {
					if(unsafeBanks.size() != 0) {
						for(k=0; k<unsafeBanks.size(); k++) {
//							System.out.println(unsafeBanks.get(k));
//							System.out.println(bank[i].getLoanedID().get(j));
							if(unsafeBanks.get(k) == bank[i].getLoanedID().get(j)) {
//								System.out.println("Bank # " + i);
//								System.out.println(bank[i].getLoanedID().get(j));
//								System.out.println(bank[i].getAmount().get(j));
								if((bank[i].asset - bank[i].getAmount().get(j)) < changedMinimum) {
									System.out.println(" and " + i);
								}
							}
						}
					}
				}
			}
		}
	}
}
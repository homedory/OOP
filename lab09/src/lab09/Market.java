package lab09;

import java.util.Scanner;

public class Market {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Wallet myWallet = new Wallet("my wallet");
		int price;
		while(true) {
			try {
				myWallet.empty();
				System.out.print("Enter Price: ");
				price = scanner.nextInt();
				if(price < 0) {
					throw new NegativeException();
				}
				else if (price > 100) {
					throw new TooMuchExpenseException(price);
				}
				else if(myWallet.getBalance() < price) {
					throw new TooMuchExpenseException();					
				}
				myWallet.increaseIndex();
				myWallet.decreaseBalance(price);
				System.out.println("peace~~");
			}
			catch(NegativeException e) {
				System.out.println(e);
				System.out.println("\tat " + e.getStackTrace()[0]);
				System.out.println("oh, sorry!");
				
			}
			catch(TooMuchExpenseException e) {
				System.out.println(e);
				System.out.println("\tat " + e.getStackTrace()[0]);
				if(e.getMessage().equals("Over the limit!")) {
					System.out.println("you pay " + e.getInputNum());
				}
				System.out.println("oh, my!");
			}
			catch(Exception e) {
				System.out.println(e);
				System.out.println("\tat " + e.getStackTrace()[0]);
				if(e.getMessage().equals("Go Home")) {
					System.out.println("the end...");
					return;
				}
			}
			finally {
				System.out.println(myWallet);
				System.out.println("---transaction complete---\n");
			}
		}
	}

}

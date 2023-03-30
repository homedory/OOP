package manager;

import java.time.LocalDate;
import java.util.Random;

import account.Account;

public class AccountManager {

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2021, 12, 1);
		Account myAccount = new Account("Kim", 5, date);
		System.out.println(myAccount);
		Random rnd = new Random();
		boolean flag = true;
		
		while(myAccount.getBalance() <= 10000) {
			date = date.plusMonths(1);
			myAccount.receiveIncome(100);
			myAccount.receiveInterest();
			
			if((date.getMonthValue() == 1) && (date.isAfter(myAccount.getCreated().plusYears(1)))) {
				if(rnd.nextInt(10) == 0) {
					System.out.println("이벤트 당첨!");
					myAccount.receiveIncome(100);
				}
			}
			if(date.isAfter(myAccount.getCreated().plusYears(3)) && flag) {
				flag = false;
				myAccount.increaseYearlyInterest(2);
				System.out.println("가입후 3년이 지나서 이자율이 2% 인상되었습니다");
			}
		}
		System.out.print(myAccount);
		System.out.println(", 1억 모으기 끝: " + date);
	}

}

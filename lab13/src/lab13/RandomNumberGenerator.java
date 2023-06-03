package lab13;

import java.util.Random;

public class RandomNumberGenerator extends NumberGenerator{
	private Random random = new Random();
	private int number;
	
	public int getNumber() { //랜덤 숫자를 return
		return this.number;
	}

	public void execute() { //랜덤 숫자를 생성하고 생성할 때마다 observer들에게 notify
		for(int i = 0; i < 10; i++) {
			this.number = random.nextInt(50);
			notifyObservers();
		}
	}

}

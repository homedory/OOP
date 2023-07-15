package GameCafe;

import java.util.ArrayList;

public class CoffeeMachine extends Thread implements Observable{
	private ArrayList<Observer> observers;
	private int index;
	private String name;
	
	CoffeeMachine(int index, String name) { // CoffeeMachine 생성자
		this.observers = new ArrayList<Observer>();
		this.index = index;
		this.name = name;
	}
	
	public void run(){
		try {
			sleep(3000); // 20프로 진행할 때 마다 3초 소요
			for(int i = 1; i < 5; i++) { //20프로 완료되는데 3초씩 걸림
				System.out.println("Coffee with number: " + this.index + " is " + i * 20 +"% done.");
				sleep(3000);
			}
			for(int i = 0; i < observers.size(); i++) { //observer들에게 notify함 (update)
				observers.get(i).update(index, name);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void subscribe(Observer observer) { //observers에 observer 추가
		observers.add(observer);
	}
	
	@Override
	public void unsubscribe(Observer observer) { //observers에서 observer 삭제
		observers.remove(observer);
	}

}

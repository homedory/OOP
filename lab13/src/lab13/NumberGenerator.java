package lab13;

import java.util.ArrayList;

public abstract class NumberGenerator {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public abstract int getNumber();
	public abstract void execute();
	
	public void addObserver(Observer observer) { //observer를 observer arrayList에 추가 
		observers.add(observer);
	}
	
	public void deleteObservers(Observer observer) { //observer arrayList에서 해당 observer제거
		observers.remove(observer);
	}
	
	public void notifyObservers() { //observer arrayList에 있는 모든 observer를 update
		for(int i = 0; i < observers.size(); i++) {
			observers.get(i).update(this);
		}
	}
}

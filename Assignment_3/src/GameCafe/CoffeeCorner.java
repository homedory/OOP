package GameCafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class CoffeeCorner implements Observer{
	private int coffeeIndex = 0;
	private HashMap<String , Double> coffeeTypes;
	private ArrayList<CoffeeMachine> machines;
	
	CoffeeCorner(int machineAmount) {
		coffeeTypes = new HashMap<>();
		coffeeTypes.put("Capucino", 3.75); //커피메뉴 추가
		coffeeTypes.put("Americano", 2.5);
		coffeeTypes.put("Latte", 3.25);
		machines = new ArrayList<CoffeeMachine>(machineAmount); // 처음 사이즈를 머신개수만큼 초기화함
		for(int i = 0; i < machineAmount; i++) { //커피머신 개수 만큼 커피머신을 추가함
			CoffeeMachine coffeeMachine = new CoffeeMachine(0, "Machine " + (i + 1));
			machines.add(coffeeMachine);
		}
	}
	
	public int makeCoffee(String type) {
		this.coffeeIndex++;
		while (true) { // 만약 모든 machine들이 다 작동중이면 while문을 계속 돌면서 종료된 machine이 생길 때까지 기다림 
			for(int i = 0; i < machines.size(); i++) {
				CoffeeMachine machine = machines.get(i);
				if(!machine.isAlive()) { //machine이 작동 중이지 않을 때만 새로운 커피를 만들 수 있음
					machine = new CoffeeMachine(coffeeIndex, "Machine " + (i + 1)); // 만들려는 커피의 index와 현재 array에서 몇번째 machine인지로 machine이름을 정해줌
					machines.set(i, machine);  //새롭게 instance화한 machine을 arrayList의 기존 위치에 대체
					machine.subscribe(this);
					machine.start();
					return (i+1); //지금 만들어지는 커피가 몇번째 machine에서 만들어지는지 알기 위해 machine index를 넘겨줌
				}
				
			}
		}
	}
	
	public void listCoffeeTypes() { 
		for(Entry<String, Double> entry: coffeeTypes.entrySet()) { //hashmap안에있는 모든 요소들을 돌면서 출력함
			System.out.println(entry.getKey() + ", Price: " + entry.getValue());
		}
	}
	
	@Override
	public void update(int coffeeIndex, String machineName) {
		System.out.println("Coffee with number: " + coffeeIndex + " is ready for pickup at " + machineName +".");
	}
	
	
	//추가한 method
	public int getCoffeeIndex() { //CafeManager에서 coffeeindex 값을 알 수 있도록 추가
		return coffeeIndex;
	}
	
	public double getPrice(String coffeeType) { //커피가격을 알기 위해서 추가
		return coffeeTypes.get(coffeeType); //hashmap에서 coffeeType key값으로 가격 value를 가져옴
	}
	
}

package GameCafe;

public abstract class Game {
	private String name;
	protected int quality;
	private double price;
	
	Game(String name, double price , int quality) {
		this.name = name;
		this.price = price;
		this.quality = quality;
	}
	
	public String toString() {
		return "Name: " + name + ", Quality: " + getQuality() + ", Price: " + price;
	}
	
	public void repair() {
		quality = 100;
	}
	
	public abstract double getRepairCost();
	public abstract void lowerQuality();
	public abstract String getQuality();
	
	//추가로 만든 method
	
	public String getName() { //이름값을 return하는 getter method
		return this.name;
	}
		
	public double getPrice() {  //게임 가격을 return하는 getter method
		return this.price;
	}
		
	public double getRentingCost() { //대여 비용을 return하는 method
		return 0.5 * this.price;
	}
	
}

package Cafe;

public class Game {
	private String name;
	private String quality;
	private double price;
	
	public Game(String name, double price) {
		this.name = name;
		this.price = price;
		this.quality = "Good";
	}
	
	public String toString() {
		return "Name: " + name + ", Quality: " + quality + ", Price: " + price + ".";
	}
	
	public double getRepairCost() {
		if (quality.equals("Okay")) {
			return 0.2 * price;
		}
		else if(quality.equals("Bad")) {
			return 0.5 * price;
		}
		else { 
			return 0;
		}
	}
	
	public void repair() {
		quality = "Good";
	}
	
	public void lowerQuality() {
		if(quality.equals("Good")) { //Good인 경우 Okay로
			quality = "Okay";
		}
		else if(quality.equals("Okay")) { //Okay인 경우 Bad로
			quality = "Bad";
		}
	}
	
	//추가로 만든 method
	
	public String getName() { //이름값을 return하는 getter method
		return this.name;
	}
	
	public String getQuality() { //quality return하는 getter method
		return this.quality;
	}
	
	public double getPrice() {  //게임 가격을 return하는 getter method
		return this.price;
	}
	
	public double getRentingCost() { //대여 비용을 return하는 method
		return 0.5 * this.price;
	}
}

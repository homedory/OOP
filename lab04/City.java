package lab4;

import java.util.Random;

public class City {
	private String name;
	private int locationX;
	private int locationY;
	
	City(String name, int locationX, int locationY) {
		this.name = name;
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	City(String name) {
		this.name = name;
		
		Random rnd = new Random();
		this.locationX = rnd.nextInt(361);
		this.locationY = rnd.nextInt(361);
	}
	
	public String getName() {
		return name;
	}
	
	public int getLocationX() {
		return locationX;
	}
	
	public int getLocationY() {
		return locationY;
	}
	
	public boolean equals(City anotherCity) {
		if(this.name.equals(anotherCity.name) && this.locationX == anotherCity.locationY && this.locationY == anotherCity.locationY) {
			return true;
		}
		return false;
	}

	public String toString() {
		return this.name + ", " + this.locationX + ", " + this.locationY;
	}
	
	public static double distance(City city1, City city2)
	{	
		int xDis = city1.locationX - city2.locationX;
		int yDis = city1.locationY - city2.locationY;
		return Math.sqrt(Math.pow(xDis, 2) + Math.pow(yDis, 2));
	}
	
}

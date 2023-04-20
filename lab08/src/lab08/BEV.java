package lab08;

import java.time.LocalDate;

public class BEV extends Car {
	private static int carNum;
	private static int CO2emission;
	private static int GHGPERCAR = 25;
	
	BEV() {}
	
	BEV(String name, LocalDate date, int carNum) {
		this.name = name;
		this.date = date;
		BEV.carNum += carNum;
		CO2emission += carNum * GHGPERCAR;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof BEV)) return false;
		BEV bev = (BEV) obj;
		return (this.name.equals(bev.name) && this.date.equals(bev.date));
	}
	
	public String toString() {
		return "name: " + this.name + ", date: " + this.date + ", carNum: " + carNum;
	}
	
	int totalCO2() {
		System.out.println("BEV emit CO2 most when generating electric energy");
		return CO2emission;
	}

}

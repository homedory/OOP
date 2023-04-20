package lab08;

import java.time.LocalDate;

public class ICE extends Car{
	private static int carNum;
	private static int CO2emission;
	private static int GHGPERCAR = 35;
	
	ICE() {}
	
	ICE(String name, LocalDate date, int carNum) {
		this.name = name;
		this.date = date;
		ICE.carNum += carNum;
		CO2emission += carNum * GHGPERCAR;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof ICE)) return false;
		ICE ice = (ICE) obj;
		return(this.name.equals(ice.name) && this.date.equals(ice.date));
	}
	
	public String toString() {
		return "name: " + this.name + ", date: " + this.date + ", carNum: " + carNum;
	}
	
	int totalCO2() {
		System.out.println("ICE emit CO2 most when driving");
		return CO2emission;
	}
}

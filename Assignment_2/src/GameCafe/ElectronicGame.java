package GameCafe;

public class ElectronicGame extends Game {
	ElectronicGame(String name, double price, int quality) {
		super(name, price, quality);
	}

	public double getRepairCost() {
		return (100 - quality) * 0.06;
	}

	public void lowerQuality() {
		quality -= 20;
	}

	public String getQuality() {
		if(quality > 80) {
			return "Good";
		}
		else if(quality >= 60) {
			return "Okay";
		}
		else {
			return "Bad";
		}
	}
}

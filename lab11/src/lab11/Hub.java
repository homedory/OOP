package lab11;

public class Hub {
	private int number;
	private String description;
	private String area;
	private double price_per_box;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public double getPrice_per_box() {
		return price_per_box;
	}
	public void setPrice_per_box(double price_per_box) {
		this.price_per_box = price_per_box;
	}
	
	public String toString() {
		return "Box Number : " + this.number + "\nDescription : " + this.description +
				"\nArea : " + this.area + "\nPrice per box : " + this.price_per_box;
	}
}

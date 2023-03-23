package lab4;

public class CityTest {

	public static void main(String[] args) {
		City seoul = new City("Seoul", 23, 45);
		City paris = new City("Paris", 123, 41);
		City racoon = new City("Racoon City");
		City mega = new City("Mega City");
		
		System.out.println(seoul);
		System.out.println(paris);
		System.out.println(racoon);
		System.out.println(mega);
		
		System.out.print("Seoul-Paris : ");
		System.out.println(City.distance(seoul, paris));
		
		System.out.print("Seoul-Racoon City: ");
		System.out.println(City.distance(seoul, racoon));

		System.out.print("Paris-Mega City : ");
		System.out.println(City.distance(paris, mega));
	}

}

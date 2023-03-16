package lab03;

public class StaffManager {

	public static void main(String[] args) {
		Staff james = new Staff("James Wright", 29, "Accounting", 365, 15);
		Staff peter = new Staff("Peter Coolidge", 32, "R&D", 1095, 7);
		Staff amy = new Staff("Amy Smith", 27);
		
		System.out.println(james.toString());
		System.out.println(peter.toString());
		System.out.println(amy.toString());
		
		System.out.println("---\nSame Carrer?");
		
		System.out.print(peter.getName() + " and " + amy.getName());
		if(peter.sameCareer(amy)) {
			System.out.println(", Same.");
		}
		else {
			System.out.println(", Not exactly same.");
		}
		
		System.out.println("...A Few years later...");
		System.out.print(peter.getName() + " and " + amy.getName());
		amy.setCareer("R&D", 1095);
		
		if(peter.sameCareer(amy)) {
			System.out.println(", Same.");
		}
		else {
			System.out.println(", Not exactly same.");
		}
		
		james.vacation(10);
		amy.vacation(20);
		amy.vacation(1);
	}

}

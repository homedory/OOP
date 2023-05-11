package lab10;

public class Program {

	public static void main(String[] args) {
		Dog dog = new Dog();
		Tiger tiger = new Tiger();
		Turtle turtle = new Turtle();
		
		Animal[] animals = new Animal[3];
		animals[0] = dog;
		animals[1] = tiger;
		animals[2] = turtle;
		
		Person person = new Person() {
			private int hp = 100;
			
			public void control(Animal animal) {
				if(animal instanceof Tiger) {
					hp -= 80;
				}
				else if(animal instanceof Dog) {
					hp -= 10;
				}
			}
			public void showInfo() {
				System.out.println("Person HP: " + hp);
			}
		};
		
		showResult(animals, person);
	}
	
	private static void showResult(Animal[] animals, Person p) {
		for(int i = 0; i < animals.length; i++) {
			System.out.println("Animal" + (i+1) + ":" + animals[i].getName());
			if(animals[i] instanceof Barkable) {
				Barkable barkable = (Barkable) animals[i];
				System.out.println("Animal" + (i+1) + " barked " + barkable.bark());
			}
			p.control(animals[i]);
			System.out.println("You have overpowered the " + animals[i].getName());
			p.showInfo();
		}
	}

}

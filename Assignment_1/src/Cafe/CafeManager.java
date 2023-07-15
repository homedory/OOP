package Cafe;

import java.util.Scanner;

public class CafeManager {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Game[] games = new Game[3];
		games[0] = new Game("Battleship", 4.25);
		games[1] = new Game("Uno", 7);
		games[2] = new Game("Monopoly", 12);
		
		Cafe myCafe = new Cafe(games, 5);
		
		while(true) {
			myCafe.printCafeDetails();
			System.out.println("What would you like to do:");
			System.out.println("1: Rent a game, 2: Return a game, 3: Repair a game, 4: Buy a new game, 5: Quit");
			int input = scanner.nextInt();
			String gameName;
			double gamePrice;
			switch(input) {
				case 1: 
					System.out.println("Which game would you like to rent?");
					gameName = scanner.next();
					myCafe.rentOutGame(gameName);
					break;
				case 2:
					System.out.println("Which game would you like to return?");
					gameName = scanner.next();
					myCafe.returnGame(gameName);
					break;
				case 3:
					System.out.println("Which game would you like to repair?");
					gameName = scanner.next();
					myCafe.repairGame(gameName);
					break;
				case 4:
					System.out.println("What is the name of the game?");
					gameName = scanner.next();
					System.out.println("What is the price of the game?");
					gamePrice = scanner.nextDouble();
					Game game = new Game(gameName, gamePrice);
					myCafe.buyGame(game);
					break;
				case 5:
					return;
			default:
					System.out.println("Invalid input");
					break;
			}
		}
	}

}

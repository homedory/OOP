package GameCafe;

import java.io.IOException;
import java.util.Scanner;

public class CafeManager {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		CSVLoader csvLoader = new CSVLoader();
		
		Game[] games = null;
		
		while(games == null) { //Exception이 발생하면 LoadGames에서 null을 return하므로 null이 아닌 array가 return 될 때까지 반복
			System.out.println("What is the path of the gamesfile?");
			String gameFile = scanner.next();
			games = csvLoader.LoadGames(gameFile);
		}
		
		Cafe myCafe = new Cafe(games, 5);
		
		while(true) {
			myCafe.printCafeDetails();
			System.out.println("What would you like to do:");
			System.out.println("1: Rent a game, 2: Return a game, 3: Repair a game, 4: Buy a new game, 5: Save games");
			int input = scanner.nextInt();
			String gameName, gameType;
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
					System.out.println("What is the type of the game?");
					gameType = scanner.next();
					Game game = null;
					if(gameType.equals("Card")) {
						game = new CardGame(gameName, gamePrice, 100);
					}
					else if(gameType.equals("Board") ) {
						game = new BoardGame(gameName, gamePrice, 100);
					}
					else if(gameType.equals("Electronic")) {
						game = new ElectronicGame(gameName, gamePrice, 100);
					}
					myCafe.buyGame(game);
					break;
				case 5:
					try {
						System.out.println("What is the file you want to save?");
						String fileName = scanner.next();
						csvLoader.SaveGames(myCafe.getAllGames(), fileName);
						System.out.println("Games saved successfully.");
						return; 				//게임 저장이 완료되면 프로그램 종료
					}
					catch(IOException e) { //SaveGame에서 Exception이 발생해 throw 한 Exception을 catch
						System.out.println("Failed Saving games");
					}
					break;
			default:
					System.out.println("Invalid input");
					break;
			}
		}
		
	}

}

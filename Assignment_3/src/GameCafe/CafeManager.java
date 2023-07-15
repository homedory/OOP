package GameCafe;

import java.io.IOException;
import java.util.Scanner;

public class CafeManager {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		CSVLoader csvLoader = new CSVLoader();
		double money = 0;
		
		Game[] games = null;
		
		while(games == null) { //Exception이 발생하면 LoadGames에서 null을 return하므로 null이 아닌 array가 return 될 때까지 반복
			System.out.println("What is the path of the gamesfile?");
			String gameFile = scanner.next();
			games = csvLoader.LoadGames(gameFile);
		}
		
		GameCorner gameCorner = new GameCorner(games);
		CoffeeCorner coffeeCorner = new CoffeeCorner(2);
		
		
		while(true) {
			System.out.println("Money: " + money);  //잔고 출력
			gameCorner.printCafeDetails();
			System.out.println("What would you like to do:");
			System.out.println("1: Rent a game, 2: Return a game, 3: Repair a game, 4: Buy a new game, 5: Save games, 6: Buy coffee");
			int input = scanner.nextInt();
			String gameName, gameType;
			double gamePrice;
			switch(input) {
				case 1: 
					System.out.println("Which game would you like to rent?");
					gameName = scanner.next();
					money += gameCorner.rentOutGame(gameName);
					break;
				case 2:
					System.out.println("Which game would you like to return?");
					gameName = scanner.next();
					gameCorner.returnGame(gameName);
					break;
				case 3:
					System.out.println("Which game would you like to repair?");
					gameName = scanner.next();
					money -= gameCorner.repairGame(gameName, money); //현재 가지고 있는 돈보다 수리비용이 적은지 판단하기 위해 money argument 추가
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
					if(gamePrice > money) {
						System.out.println("Not enough funds for the game");
					}
					else {
						gameCorner.buyGame(game);
						money -= game.getPrice();
					}
					gameCorner.buyGame(game);
					break;
				case 5:
					try {
						System.out.println("What is the file you want to save?");
						String fileName = scanner.next();
						csvLoader.SaveGames(gameCorner.getAllGames(), fileName);
						System.out.println("Games saved successfully.");
						return; 				//게임 저장이 완료되면 프로그램 종료
					}
					catch(IOException e) { //SaveGame에서 Exception이 발생해 throw 한 Exception을 catch
						System.out.println("Failed Saving games");
					}
					break;
				case 6:
					System.out.println("What kind of coffee do you want?");
					coffeeCorner.listCoffeeTypes();
					String coffeeType = scanner.next();
					money += coffeeCorner.getPrice(coffeeType); //커피 가격만큼 잔고에 추가
					
				
					System.out.println("The coffee is being prepared on Machine " + coffeeCorner.makeCoffee(coffeeType) + "!");
					System.out.println("your number is " + coffeeCorner.getCoffeeIndex());
					break;
			default:
					System.out.println("Invalid input");
					break;
			}
		}
	}

}

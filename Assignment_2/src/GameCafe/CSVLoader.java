package GameCafe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVLoader {
	
	public Game[] LoadGames(String fileName) {
		
		Scanner inputStream = null;
		int gamesCount = 0;
		Game[] games = null;
		try {
			inputStream = new Scanner(new FileInputStream(fileName));
			
			while(inputStream.hasNextLine()) { //csv파일에있는 게임의 개수를 count
				String line = inputStream.nextLine();
				gamesCount++;
			}
			inputStream.close();
			
			games = new Game[gamesCount];
			
			inputStream = new Scanner(new FileInputStream(fileName));
			for(int i = 0; i < gamesCount; i++) {
				String line = inputStream.nextLine(); //csv파일에서 한 줄 씩 읽음
				String[] values = line.split(",");    //한 줄에서 ,를 기준으로 각 항목 구분 
				String gameType = values[0];
				String gameName = values[1];
				double gamePrice = Double.parseDouble(values[2]); //String을 double로 변환
				int gameQuality = Integer.parseInt(values[3]); 	  //String을 int로 변환
				
				if(gameType.equals("Card")) { //게임 type에 따라 게임을 만들어 게임 array에 저장 
					games[i] = new CardGame(gameName, gamePrice, gameQuality);
				}
				else if(gameType.equals("Board")) {
					games[i] = new BoardGame(gameName, gamePrice, gameQuality);
				}
				else if(gameType.equals("Electronic")) {
					games[i] = new ElectronicGame(gameName, gamePrice, gameQuality);
				}	
			}
			inputStream.close();
			return games;
		}
		catch(FileNotFoundException e) {
			System.out.println("File does not exist");
			return null;
		}
		catch(NumberFormatException e) {
			System.out.println("File is not well formatted");
			return null;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("File is empty");
			return null;
		}
		catch(IOException e) {
			System.out.println("IOException");
			return null;
		}
	}
	
	public void SaveGames(Game[] games, String fileName) throws IOException { //Exception 발생하면 main method에서 다시 method를 실행시키기 위해 exception throw 함
		PrintWriter outputStream = null;
		
		try {
			outputStream = new PrintWriter(new FileOutputStream(fileName, false)); //csv파일을 overwrite하기 위해 false를 parameter로 전달
			for(int i = 0; i < games.length; i++) {
				if(games[i] instanceof CardGame) {
					outputStream.print("Card,");
				}
				else if(games[i] instanceof BoardGame) {
					outputStream.print("Board,");
				}
				else if(games[i] instanceof ElectronicGame) {
					outputStream.print("Electronic,");
				}
				outputStream.println(games[i].getName() + "," + games[i].getPrice() + "," + games[i].quality);
			}
			outputStream.close();
		}
		catch(IOException e) {
			System.out.println("Problem opening file");
			throw e; //Exception이 발생하면 main method에서 다시 실행할 수 있도록 Exception을 throw
		}
	}
}

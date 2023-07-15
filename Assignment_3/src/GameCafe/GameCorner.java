package GameCafe;

public class GameCorner {
	private Game[] gamesInCafe;
	private Game[] rentedOutGames;
	
	public GameCorner(Game[] games) {
		gamesInCafe = new Game[games.length];
		for (int i = 0; i < games.length; i++) {
			gamesInCafe[i] = games[i];
		}
		rentedOutGames = new Game[0];
	}
	
	public double rentOutGame(String name) {
		int gameIdx = getIndexGamesInCafe(name);
		
		if(gameIdx == -1) { //찾는 게임이 카페에 없는 경우(gamesInCafe에 없는 경우)
			if(getIndexRentedOutGames(name) >= 0) { //이미 빌려간 게임에 있는지 확인
				System.out.println("Game was already rented out. You can rent it later");
			}
			else { //빌려간 게임에도 없는 경우
				System.out.println("There is no game which name is " + name);
			}
			return 0;
		}
		else if(gamesInCafe[gameIdx].getQuality().equals("Bad")) { //빌리려는 게임이 quality가 Bad인 경우, 대여불가
			System.out.println("Game's quality is Bad. Repair the game or Choose another game");
			return 0;
		}
		
		double rentingCost = gamesInCafe[gameIdx].getRentingCost();
		
		rentedOutGames = addGameInArray(rentedOutGames, gamesInCafe[gameIdx]); // rentedOutGames array에 게임 추가
		
		gamesInCafe = deleteGameInArray(gamesInCafe, gameIdx);  //gamesInCafe array에서 게임 제거
		
		System.out.println("Game rented successfully");
		
		return rentingCost; 
	}
	
	public void returnGame(String name) {
		int gameIdx = getIndexRentedOutGames(name);  //게임의 index 찾기
		
		if(gameIdx == -1) {  //해당하는 게임이 없는경우
			System.out.println("There is no game which name is " + name);
			return;
		}
		rentedOutGames[gameIdx].lowerQuality();  //반납하는 게임의 quality를 낮춤
		gamesInCafe = addGameInArray(gamesInCafe, rentedOutGames[gameIdx]); //gamesInCafe array에 게임 추가
		rentedOutGames = deleteGameInArray(rentedOutGames, gameIdx); // rentedOutGames array에서 게임 제거
		
		System.out.println("Game returned successfully");
	}
	
	public void buyGame(Game game) {
		gamesInCafe = addGameInArray(gamesInCafe, game); //gamesInCafe 배열에 새로운 게임 추가
		System.out.println("Game bought successfully");
	}
	
	public void printCafeDetails() {
		
		System.out.println("Games in cafe:");
		for (int i = 0; i <gamesInCafe.length; i++) { //카페에 있는 게임들 출력
			System.out.print(gamesInCafe[i]);
			if(gamesInCafe[i] instanceof BoardGame) {
				System.out.println(", type: Board");
			}
			else if(gamesInCafe[i] instanceof ElectronicGame) {
				System.out.println(", type: Electronic");
			}
			else if(gamesInCafe[i] instanceof CardGame) {
				System.out.println(", type: Card");
			}
		}
		System.out.println("Games rented out:");
		for (int i = 0; i < rentedOutGames.length; i++) { //빌려간 게임들 출력
			System.out.println(rentedOutGames[i]);
		}
	}
	
	public double repairGame(String name, double money) { //수리비용이 현재 가지고 있는 돈보다 적어서 수리 할 수 있는지 판단하기 위해 money argument 추가
		int gameIdx = getIndexGamesInCafe(name); //해당하는 게임 index 찾기
		
		if(getIndexRentedOutGames(name) >= 0) { //수리하려는 게임이 빌겨간 상태인 경우
			System.out.println("The game was rented out. You can repair it later.");
			return 0;
		}
		else if(gameIdx == -1) { // 찾는 게임이 없는 경우
			System.out.println("There is no game which name is " + name);
			return 0;
		}
		else if(gamesInCafe[gameIdx].getQuality().equals("Good")) { //게임의 quality가 이미 Good인 경우
			System.out.println("Game's quality is Good. You don't have to repair it.");
			return 0;
		}
		else if(gamesInCafe[gameIdx].getRepairCost() > money) { //수리 비용이 부족한 경우
			System.out.println("Money is not enough to repair the game");
			return 0;
		}
		
		double repairCost = gamesInCafe[gameIdx].getRepairCost(); 
		
		gamesInCafe[gameIdx].repair(); //게임 수리
		System.out.println("Repaired successfully, remaining money: " + (money - repairCost)); // 남아있는 잔고 출력
		
		return repairCost; //수리비용만큼 차감
	}
	
	private int getIndexGamesInCafe(String name) { 
		for (int i = 0; i < gamesInCafe.length; i++) {
			if(gamesInCafe[i].getName().equals(name)) { //게임의 index 찾기
				return i;
			}
		}
		return -1; //찾는 게임이 없는 경우 -1 return
	}
	
	private int getIndexRentedOutGames(String name) {
		
		for(int i = 0; i < rentedOutGames.length; i++) {
			if (rentedOutGames[i].getName().equals(name)) { //게임의 index 찾기
				return i;
			}
		}
		return -1; //찾는 게임이 없는 경우 -1 return
	}
	
	//추가로 만든 method
	
	private Game[] deleteGameInArray(Game[] games, int loc) { //지우려는 게임의 index를 loc 으로 전달받음
		int idx = 0;
		Game[] tmpArray = new Game[games.length - 1];  //이전 array 보다 length가 1만큼 작은 새로운 array를 선언 
		for (int i = 0; i < games.length; i++) {     // 지우려는 배열을 제외하고 전부 복사
			if(i != loc) { 							//지우려는 게임이 아니면 새로운 배열에 복사 
				tmpArray[idx] = games[i];
				idx++;
			}
		}
		return tmpArray;							//새로운 array return
	}
	
	private Game[] addGameInArray(Game[] games, Game game) { //추가하려는 게임을 game으로 전달받음
		Game[] tmpArray = new Game[games.length + 1];	//이전 array보다 length가 1만큼 큰 새로운 array를 선언 
		for (int i = 0; i < games.length; i++) {	// 새로운 array에 기존 array를 전부 복사
			tmpArray[i] = games[i];
		}
		tmpArray[games.length] = game;  //새로운 array의 마지막에 새로운 게임 추가
		return tmpArray;
	}
	
	public Game[] getAllGames() { //gamesInCafe와 rentedOutGames를 합친 array를 return하는 함수
		Game[] games = new Game[gamesInCafe.length + rentedOutGames.length];

		for(int i = 0; i < gamesInCafe.length; i++) { //games array에 gamesInCafe를 복사
			games[i] = gamesInCafe[i];
		}
		for(int i = 0; i < rentedOutGames.length; i++) { //games array에 gamsInCafe에 이어서 rentedOutGames 복사
			games[i + gamesInCafe.length] = rentedOutGames[i];
		}
		return games;
	}

}

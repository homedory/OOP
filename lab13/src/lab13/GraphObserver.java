package lab13;

public class GraphObserver implements Observer { //랜덤 숫자만큼 별을 찍는 역할을 하는 observer

	public GraphObserver(NumberGenerator generator) { //생성할 때 이 observer를 generator에 observer로 추가
		generator.addObserver(this);
	}
	
	public void update(NumberGenerator generator) { // generator로 부터 랜덤 숫자를 받아 숫자 만큼 별을 출력
		System.out.print("GraphObserver: ");
		for(int i = 0; i < generator.getNumber(); i++) {
			System.out.print("*");
		}
		System.out.println();
	}
	
}

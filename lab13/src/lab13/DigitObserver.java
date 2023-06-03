package lab13;

public class DigitObserver implements Observer { //랜덤 숫자를 출력하는 observer 역할을 하는 클래스
	
	public DigitObserver(NumberGenerator generator) { //생성할 때 generator의 arraylist에 이 observer 객체를 추가 
		generator.addObserver(this); 
	}

	public void update(NumberGenerator generator) { // generator로 부터 랜덤 숫자를 받아 출력
		System.out.println("DigitObserver: " + generator.getNumber());
	}
	
}

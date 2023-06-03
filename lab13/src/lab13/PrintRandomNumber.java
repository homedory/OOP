package lab13;

public class PrintRandomNumber {

	public static void main(String[] args) {
		NumberGenerator a = new RandomNumberGenerator(); //random number generator 인스턴스 생성
		DigitObserver b = new DigitObserver(a); //Digitobserver를 생성하고 a generator에 observer로 등록
		GraphObserver c = new GraphObserver(a); //Graphobserver를 생성하고 a generator에 observer로 등록
		
		a.execute(); //random number generator a 실행
	}

}

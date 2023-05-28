package lab12;

public class ProdCons {
	private Buffer buffer;
	private Producer[] producer;
	private Consumer[] consumer;
	
	public ProdCons() { //ProdCons constructor: buffer 사이즈를 5로하고, buffer를 이용해 producer, consumer를 만든다
		buffer = new Buffer(5);
		producer = new Producer[2]; 
		consumer = new Consumer[2];
		
		//consumer, producer 배열에 각각 2개씩 객체 생성
		producer[0] = new Producer(buffer);
		producer[1] = new Producer(buffer);
		consumer[0] = new Consumer(buffer);
		consumer[1] = new Consumer(buffer);
	}
	
	public class Producer extends Thread { //inner class, Buffer 클래스에서 Producer 사용하기 위해 public으로 변경 (serial number출력 위해서)
		private static int serial = 0;
		private int pNum;
		private final Buffer buffer;
		
		public Producer(Buffer buff) { //buffer 입력받는 생성자
			pNum = serial++;
			buffer = buff;
		}
		
		public int getNum() { //serial number인 pNum return
			return pNum;
		}
		
		public void produce() throws InterruptedException { //buffer에 배열 크기만큼 랜덤 숫자 저장
			for(int i = 0; i < buffer.getSize(); i++) {
				System.out.println("Producer#" + this.pNum + ":" + this);
				buffer.add(this, Math.random()*100);
			}
		}
		
		public void run() { //run() override, produce() 메서드 실행
			try {
				produce();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public class Consumer extends Thread { //inner class, Buffer 클래스에서 Consumer 사용하기 위해 public으로 변경 (serial number출력 위해서)
		private static int serial = 0;
		private int pNum;
		private final Buffer buffer;
		
		public Consumer(Buffer buff) { //buffer 입력받는 생성자
			pNum = serial++;
			this.buffer = buff;
		}
		
		public int getNum() { //serial number인 pNum return
			return pNum;
		}
		
		public void consume() throws InterruptedException { //배열의 크기만큼 buffer에서 숫자 제거
			for(int i = 0; i < buffer.getSize(); i++) {
				System.out.println("Consumer#" + this.pNum + ":" + this);
				buffer.remove(this);
			}
		}
		
		public void run() { //run() override, consume() 메서드 실행
			try {
				consume();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void startThread() { //모든 스레드 start
		producer[0].start();
		producer[1].start();
		consumer[0].start();
		consumer[1].start();
	}
}

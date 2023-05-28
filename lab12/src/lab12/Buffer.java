package lab12;

import lab12.ProdCons.Consumer;
import lab12.ProdCons.Producer; 

public class Buffer {
	private int loc = 0;
	private double[] data;
	
	public Buffer(int size) { //Buffer생성자
		data = new double[size];
		System.out.println("New buffer");
		for(int i = 0; i < size; i++) { //처음 Buffer상태를 출력
			System.out.printf("%.2f ", data[i]);
		}
		System.out.println();
	}
	
	public int getSize() { //buffer의 크기를 return
		return data.length;
	}
	
	public synchronized void add(Producer p, double toAdd) throws InterruptedException { //serial number 출력을 위해 Producer p를 parameter로 넘겨줌 
		while(loc >= data.length) { //if문을 사용하는 경우 index out of bound 오류가 발생할 수 있음
			System.out.println("Producer#" + p.getNum() + " try " + toAdd + "@ Buffer is full.\n");
			wait();
		}
		System.out.println("Producer#" + p.getNum() + " Adding item on " + loc + ": " + toAdd);
		data[loc++] = toAdd; 			//배열에 값 추가
		for(int i = 0; i < data.length; i++) { //현재 상태의 전체 배열 출력
			System.out.printf("%.2f ", data[i]);
		}
		System.out.println();
		notifyAll();
	}
	
	public synchronized double remove(Consumer c) throws InterruptedException { //serial number 출력을 위해 Consumer c를 parameter로 넘겨줌
		while(loc <= 0) { //if문을 사용하는 경우 index out of bound 오류가 발생할 수 있음
			System.out.println("Consumer#" + c.getNum() + " try " + data[loc] + " " + loc + " Buffer is empty.\n");
			wait();
		}
		double temp = data[--loc];
		System.out.println("Consumer#" + c.getNum() + " Removing item on " + loc + ": " + data[loc]);
		data[loc] = 0;            //배열의 값을 0으로 초기화
		for(int i = 0; i < data.length; i++) { //현재 상태의 전체 배열 출력
			System.out.printf("%.2f ", data[i]);
		}
		System.out.println();
		notifyAll();
		return temp;
	}
}

/* 
 * while 이 아니라 if문을 사용하는 경우 blocked 상태였던 thread가 다시 active되었을 때 바로
 * double temp = dat[--loc];로 넘어가면서 loc가 0이면 --loc이 0보다 작아지면서 index out of bound
 * 오류가 일어난다. whiㅣe문을 통해 다시 loc <= 0인지 확인하고 loc <= 0이라면 wait()를 통해 다시 blocked 상태로
 * 되어야 한다.  
 */ 

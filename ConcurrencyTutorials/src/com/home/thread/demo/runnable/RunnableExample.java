package com.home.thread.demo.runnable;

class Runner  implements Runnable {
	public void run() {
		for(int index=1; index<=10; index++) {
			System.out.println(index);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
}

public class RunnableExample {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runner());
		Thread t2 = new Thread(new Runner());
		t1.start();
		t2.start();
	}

}

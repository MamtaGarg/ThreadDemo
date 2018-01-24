package com.home.thread.demo.thread;

class Runner extends Thread {

	@Override
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

public class ThreadExample {

	public static void main(String[] args) {
		Runner runner1 = new Runner();
		runner1.start();
		
		Runner runner2 = new Runner();
		runner2.start();
	}

}

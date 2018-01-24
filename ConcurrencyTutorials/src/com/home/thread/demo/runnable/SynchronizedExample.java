package com.home.thread.demo.runnable;

public class SynchronizedExample {

	private int count = 0;
	
	private synchronized void increment() {
		count++;
	}
	
	public static void main(String[] args) {
		SynchronizedExample synchronizedExample = new SynchronizedExample();
		synchronizedExample.doWork();
	}
	
	private void doWork() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int index=1; index<=10000; index++) {
					increment();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int index=1; index<=10000; index++) {
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Count is : " +count);
	}

}

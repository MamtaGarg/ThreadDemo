package com.home.thread.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
	private int id;
	
	Processor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Starting : " + id + " ThreadName : " + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed : " + id + " ThreadName : " + Thread.currentThread().getName());
	}
}

public class ThreadPoolExample {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i=0; i<5; i++) {
			executor.submit(new Processor(i));
		}
		executor.shutdown();
		
		System.out.println("All tasks are successfully submitted.");
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All tasks are successfully completed.");
	}

}

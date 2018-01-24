package com.home.thread.reentrant.locks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();

	private void increment() {
		for(int i =0; i<10000; i++) {
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException {
		lock.lock();
		cond.await();

		System.out.println("First thread was waiting....");
		try {
			increment();
		} finally {
			lock.unlock();
		}
	}

	public void secondThread() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("Second Thread is acquiring the lock");
		lock.lock();
		System.out.println("Press return key!");
		new Scanner(System.in).nextLine();
		System.out.println("Return key is pressed.");
		
		cond.signal();
		try {
			increment();
			System.out.println("Second Thread increment() is finished!");
		} finally {
			lock.unlock();
		}
	}
	
	public void finished() {
		System.out.println("Total count is : " + count);
	}
	
}

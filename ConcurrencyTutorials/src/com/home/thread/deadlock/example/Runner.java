package com.home.thread.deadlock.example;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account a1 = new Account();
	private Account a2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
		while(true) {
			
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			
			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			 } finally {
				 if(gotFirstLock && gotSecondLock) {
					 return;
				 }
				 
				 if(gotFirstLock) {
					 firstLock.unlock();
				 }
				 if(gotSecondLock) {
					 secondLock.unlock();
				 }
				 //Thread.sleep(1);
			 }
		}
	}
	
	public void firstThread() throws InterruptedException {
		Random random = new Random();

		for (int i = 0; i < 10000; i++) {
			
			acquireLocks(lock1, lock2);

			try {
				a1.transfer(a1, a2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}

	}

	public void secondThread() throws InterruptedException {
		Random random = new Random();

		for (int i = 0; i < 10000; i++) {
			acquireLocks(lock2, lock1);

			try {
				a2.transfer(a1, a2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void finished() {
		System.out.println("Account 1 balance : " + a1.getBalance());
		System.out.println("Account 2 balance : " + a2.getBalance());
		System.out.println("Total balance is : "
				+ (a1.getBalance() + a2.getBalance()));
	}

}

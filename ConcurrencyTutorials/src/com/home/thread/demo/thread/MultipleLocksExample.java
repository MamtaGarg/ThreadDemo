package com.home.thread.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultipleLocksExample {

	private Random random = new Random();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();
	
	private void stageOne() {
		synchronized(lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(random.nextInt());
		}
	}
	
	private void stageTwo() {
		synchronized(lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list2.add(random.nextInt());
		}
	}
	
	private void process() {
		for(int i=0; i<1000; i++) {
			stageOne();
			stageTwo();
		}
	}
	
	private void callProcess() {
		System.out.println("Starting.......");
		long startTime = System.currentTimeMillis();
		Thread t1 = new Thread(() -> {
			process();
		});
		
		Thread t2 = new Thread(() -> {
			process();
		});

		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken : " + (endTime - startTime));
		System.out.println("List1 size : " + list1.size() + "; List2 Size : " + list2.size());
	}
	
	public static void main(String[] args) {
		MultipleLocksExample locks = new MultipleLocksExample();
		locks.callProcess();
	}

}

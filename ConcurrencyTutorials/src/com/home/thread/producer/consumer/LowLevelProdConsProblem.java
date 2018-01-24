package com.home.thread.producer.consumer;

import java.util.LinkedList;
import java.util.Random;

public class LowLevelProdConsProblem {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException {

		int value = 0;

		while (true) {
			synchronized (lock) {

				while (list.size() == LIMIT) {
					lock.wait();
				}
				System.out.println("Insert value : " +value);
				list.add(value++);
				lock.notify();
			}
		}

	}

	public void consume() throws InterruptedException {

		Random random = new Random();
		while (true) {
			synchronized (lock) {

				while (list.size() == 0) {
					lock.wait();
				}
				System.out.print("List size : " + list.size());
				int value = list.removeFirst();
				System.out.println(" ; value is : " + value);
				lock.notify();
			}
			
			Thread.sleep(random.nextInt(10000));
		}
	}

}

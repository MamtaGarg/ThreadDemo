package com.home.thread.demo.thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerExample {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	private static void produce() throws InterruptedException {
		Random random = new Random();
		while(true) {
			int element = random.nextInt(100);
			queue.put(element);
			System.out.println("Queue put : " + element);
		}
	}
	
	private static void consume() throws InterruptedException {
		Random random = new Random();
		while(true) {
			Thread.sleep(100);
			if(random.nextInt(10) == 0) {
				Integer element = queue.take();
				System.out.println("Taken value : " + element + "; Queue size is : " + queue.size());
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
			}
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
	}

}

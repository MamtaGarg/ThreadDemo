package com.home.thread.producer.consumer;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {
		
		synchronized(this) {
			System.out.println("Producer thread starting...");
			wait();
			System.out.println("Resumed.");
		}
		
		System.out.println("Producer has produced.");
	}
	
	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		System.out.println("Consumer is running....");

		synchronized(this) {
			System.out.println("Waiting for the return key.");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify();
		}
		
		//Thread.sleep(10000);
		System.out.println("Consumer has consumed.");
		
	}
}

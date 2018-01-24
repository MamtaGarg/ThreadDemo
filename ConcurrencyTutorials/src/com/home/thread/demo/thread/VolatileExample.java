package com.home.thread.demo.thread;

import java.util.Scanner;

class Volatile extends Thread {
	
	private boolean running = true; 
	
	public void run() {
		while(running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutDown() {
		running = false;
	}
}

public class VolatileExample {

	public static void main(String[] args) {
		Volatile v = new Volatile();
		v.start();
		System.out.println("Press enter to stop....");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		v.shutDown();
		
	}

}

package com.home.thread.semaphore;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection();

	private int connections = 0;

	private Semaphore sem = new Semaphore(10, true);

	public static Connection getInstance() {
		return instance;
	}

	public void connect() {
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		doConnect();
		sem.release();
	}

	public void doConnect() {
		synchronized (this) {
			connections++;
			System.out.println("Current connections : " + connections);
		}
		try {
			Thread.sleep(2);
			//int a = 10/0;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (this) {
			connections--;
		}
	}
}

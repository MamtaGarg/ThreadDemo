package com.home.thread.producer.consumer;

public class LowLevelSynchronizationExample {

		public static void main(String[] args) throws InterruptedException {

			LowLevelProdConsProblem lowLevelProdConsProblem = new LowLevelProdConsProblem();
			
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						lowLevelProdConsProblem.produce();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			
			Thread t2 = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						lowLevelProdConsProblem.consume();
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

package com.home.thread.callable.future;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
//		executor.submit(new Runnable() {
//			public void run() {
//				Random random = new Random();
//				int duration = random.nextInt(4000);
//				System.out.println("Starting...");
//				try {
//					Thread.sleep(duration);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println("Finished.");
//			}
//		});
		Future<?> future = executor.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				
				Random random = new Random();
				int duration = random.nextInt(4000);
				if(duration > 2000) {
					throw new IOException("Sleeping for too long time.");
				}
				System.out.println("Starting...");
				Thread.sleep(duration);
				System.out.println("Finished.");
				return null;
			}
			
		});
		//future.cancel(true);
		executor.shutdown();
		try {
			System.out.println("Result is : " + future.get());
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
		}
		
	}

}

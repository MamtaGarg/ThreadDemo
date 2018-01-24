package com.home.thread.demo.runnable;

public class RunnableConstructorExample {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int index=1; index<=10; index++) {
					System.out.println(index);
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
			
		}, "thread1");
		
		t1.start();
	}

}

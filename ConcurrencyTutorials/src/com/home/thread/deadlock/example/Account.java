package com.home.thread.deadlock.example;

public class Account {

	private  int balance = 10000;
	
	public void deposit(int amt) {
		balance += amt;
	}
	
	public void withDraw(int amt) {
		balance -= amt;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public static void transfer(Account a1, Account a2, int amt) {
		a1.withDraw(amt);
		a2.deposit(amt);
	}
}

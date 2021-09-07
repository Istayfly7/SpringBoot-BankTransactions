package com.rico.model;

public class Teller implements Runnable{
	private Bank bank;
	
	public Teller(Bank bank) {
		this.bank = bank;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		doWork();
	}
	
	private void doWork(){
		//3 transactions on accounts with id's between 0-5 (index of list)
		for(int i = 0; i<1; i++){
			int toAcc = (int)(Math.random() * 6);
			int fromAcc = (int)(Math.random() * 6);
			int amount = (int)(Math.random() * 501);
				
			try {
				if(toAcc != fromAcc)
					bank.transferAmount(toAcc, fromAcc, amount);
				else
					throw new InterruptedException("Attempted to transfer from the same account {" + (toAcc + 1) + ":" + (fromAcc + 1) + "}. Invalid Action!");
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	
}

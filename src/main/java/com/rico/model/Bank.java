package com.rico.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rico.entity.Account;
import com.rico.repository.TransactionRepository;

public class Bank {
	
	@Autowired
	private List<Account> accountsList;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	
	public List<Account> getAccounts() {
		return accountsList;
	}

	public void setAccounts(List<Account> accounts) {
		this.accountsList = accounts;
	}
	
	
	public void transferAmount(int toAcc, int fromAcc, double amount) 
			throws InterruptedException{
		
		accountsList.get(fromAcc).withdraw(amount, transactionRepository);
		accountsList.get(toAcc).deposit(amount, transactionRepository);
		
		String threadName = Thread.currentThread().getName();
		
		System.out.println(threadName + "\ttransfer done from "+ fromAcc + " to "+ toAcc);
	}
	
	
}

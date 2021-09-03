package com.rico.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rico.entity.Account;
import com.rico.repository.AccountRepository;
import com.rico.repository.TransactionRepository;

public class Bank {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private List<Account> accountsList = new ArrayList<>();
	
	public Bank() {
		accountRepository.findAll().forEach(accountsList::add);
	}
	
	public List<Account> getAccounts() {
		return accountsList;
	}

	public void setAccounts(List<Account> accounts) {
		this.accountsList = accounts;
	}
	
	
	public void transferAmount(int toAcc, int fromAcc, double amount) 
			throws InterruptedException{
		System.out.println("List: " + accountsList);
		
		accountsList.get(fromAcc).withdraw(amount, transactionRepository);
		accountsList.get(toAcc).deposit(amount, transactionRepository);
		
		String threadName = Thread.currentThread().getName();
		
		System.out.println(threadName + "\ttransfer done from "+ fromAcc + " to "+ toAcc);
	}
	
	
}

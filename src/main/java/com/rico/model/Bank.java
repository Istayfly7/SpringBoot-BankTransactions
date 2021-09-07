package com.rico.model;

import java.util.ArrayList;
import java.util.List;

import com.rico.entity.Account;
import com.rico.repository.AccountRepository;
import com.rico.repository.TransactionRepository;

public class Bank {
	
	private TransactionRepository transactionRepository;
	
	private AccountRepository accountRepository;
	
	private List<Account> accountsList = new ArrayList<>();
	
	public Bank() {
	}
	
	public Bank(AccountRepository accountRepository, TransactionRepository transactionRepository) {
		// TODO Auto-generated constructor stub
		accountRepository.findAll().forEach(accountsList::add);
		this.transactionRepository = transactionRepository;
		this.accountRepository = accountRepository;
	}

	public List<Account> getAccounts() {
		return accountsList;
	}

	public void setAccounts(List<Account> accounts) {
		this.accountsList = accounts;
	}
	
	
	public void transferAmount(int toAcc, int fromAcc, double amount) 
			throws InterruptedException{
		
		accountsList.get(fromAcc).withdraw(amount, transactionRepository, accountRepository);
		accountsList.get(toAcc).deposit(amount, transactionRepository, accountRepository);
		
		String threadName = Thread.currentThread().getName();
		
		System.out.println(threadName + "\ttransfer done from "+ (fromAcc+1) + " to "+ (toAcc+1));
	}
	
	
}

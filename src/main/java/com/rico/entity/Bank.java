package com.rico.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.springframework.beans.factory.annotation.Autowired;

import com.rico.entity.Account;

@Entity
public class Bank {

	@Id
	private int id;
	private String transType;
	private int amount;
	
	@Autowired
	@ManyToMany(mappedBy="bank", cascade=CascadeType.ALL)
	private List<Account> accounts;
	
	public Bank() {}

	public Bank(int id, String transactionType, int amount) {
		this.id = id;
		this.transType = transactionType;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTransactionType() {
		return transType;
	}

	public void setTransactionType(String transactionType) {
		this.transType = transactionType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", transactionType=" + transType + ", amount=" + amount + "]";
	}
	
	
	public void transferAmount(int toAcc, int fromAcc, double amount) 
			throws InterruptedException{
		
		accounts.get(fromAcc).withdraw(amount);
		accounts.get(toAcc).deposit(amount);
		
		String threadName = Thread.currentThread().getName();
		
		System.out.println(threadName + "\ttransfer done from "+ fromAcc + " to "+ toAcc);
	}
	
	
}

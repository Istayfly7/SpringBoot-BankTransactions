package com.rico.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="Accounts")
public class Account implements Comparable<Account>{

	@Id
	private int id;
	private String accName;
	private double balance;

	@Autowired
	@ManyToMany(mappedBy="account", cascade=CascadeType.ALL)
	private List<Bank> trans;
	
	public Account () {}

	public Account(int id, String accName, double balance) {
		this.id = id;
		this.accName = accName;
		this.balance = balance;
	}
	

	public List<Bank> getTrans() {
		return trans;
	}

	public void setTrans(List<Bank> trans) {
		this.trans = trans;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accName=" + accName + ", amount=" + balance + "]";
	}

	@Override
	public int compareTo(Account o) {
		// TODO Auto-generated method stub
		System.out.println("comparing "+ this + " with "+ o);
		
		int diff = this.id - o.id;
		return diff;
	}
	
	
	public synchronized boolean deposit(double amount) throws InterruptedException {
		
		double balance = this.balance;
		Thread.sleep(600);
		
		balance += amount;
		Thread.sleep(400);
		
		this.balance = balance;
		return true;
	}
	
	
	public synchronized boolean withdraw(double amount) throws InterruptedException {
		
		double balance = this.balance;
		Thread.sleep(600);
		
		if(amount > balance){
			throw new InterruptedException("Attempted to withdraw an amount more than the balance{Balance: " + balance + "\tAmount: " + amount + "}. Invalid Action!");
		}
		balance -= amount;
		Thread.sleep(400);
		
		this.balance = balance;
		return true;
	}
	
}

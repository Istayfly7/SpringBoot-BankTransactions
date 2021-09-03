package com.rico.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.rico.repository.TransactionRepository;
import com.rico.entity.Transaction;

@Entity
@Table(name="accounts")
public class Account implements Comparable<Account>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String accName;
	private double balance;

	@Autowired
	@ManyToMany(mappedBy="accountsList", cascade=CascadeType.ALL)
	private Set<Transaction> transList;
	
	public Account () {}

	public Account(int id, String accName, double balance) {
		this.id = id;
		this.accName = accName;
		this.balance = balance;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
	
	@Override
    public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj==null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(id, other.getId());
	}
	

	public Set<Transaction> getTransList() {
		return transList;
	}

	public void setTransList(Set<Transaction> transList) {
		this.transList = transList;
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
	
	
	public synchronized boolean deposit(double amount, TransactionRepository transactionRepository) throws InterruptedException {
		
		double balance = this.balance;
		Thread.sleep(600);
		
		balance += amount;
		Thread.sleep(400);
		
		this.balance = balance;
		Transaction trans = new Transaction(0, "Deposit", amount);
		transactionRepository.save(trans);
		return true;
	}
	
	
	public synchronized boolean withdraw(double amount, TransactionRepository transactionRepository) throws InterruptedException {
		
		double balance = this.balance;
		Thread.sleep(600);
		
		if(amount > balance){
			throw new InterruptedException("Attempted to withdraw an amount more than the balance{Balance: " + balance + "\tAmount: " + amount + "}. Invalid Action!");
		}
		balance -= amount;
		Thread.sleep(400);
		
		this.balance = balance;
		Transaction trans = new Transaction(0, "Withdraw", amount);
		System.out.println("Transactions: " + trans.toString());
		transactionRepository.save(trans);
		return true;
	}
	
}

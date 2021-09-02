package com.rico.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String transType;
	private double amount;
	
	@Autowired
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(
			joinColumns= 
				{@JoinColumn(name="account_id", referencedColumnName="id")},
			inverseJoinColumns= 
				{@JoinColumn(name="transaction_id", referencedColumnName="id")})
	private Set<Account> accountsList;
	
	public Transaction() {}

	public Transaction(int id, String transType, double amount) {
		this.id = id;
		this.transType = transType;
		this.amount = amount;
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
	
	public Set<Account> getAccountsList() {
		return accountsList;
	}

	public void setAccountsList(Set<Account> accountsList) {
		this.accountsList = accountsList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", transType=" + transType + ", amount=" + amount + "]";
	}
	
	
}

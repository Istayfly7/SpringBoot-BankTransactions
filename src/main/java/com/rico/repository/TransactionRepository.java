package com.rico.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rico.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	ArrayList<Transaction> findByTransType(String transType);

}

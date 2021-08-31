package com.rico.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rico.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

	ArrayList<Bank> findByTransType(String transType);

}

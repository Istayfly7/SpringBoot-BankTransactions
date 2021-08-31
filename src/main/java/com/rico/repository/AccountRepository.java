package com.rico.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rico.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	ArrayList<Account> findByAccName(String accName);

}

package com.rico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rico.model.Bank;
import com.rico.model.Teller;
import com.rico.repository.AccountRepository;
import com.rico.repository.TransactionRepository;

@RestController
@RequestMapping("/process-transactions")
public class TellerController {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@RequestMapping("/")
	public void process() {
		Bank bankRef = new Bank(accountRepository, transactionRepository);

		Runnable target = new Teller(bankRef);

		Thread t1 = new Thread(target,"Teller#1");
		t1.start();



		Thread t2 = new Thread(target,"Teller#2");
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
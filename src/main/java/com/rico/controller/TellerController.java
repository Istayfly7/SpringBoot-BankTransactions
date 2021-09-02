package com.rico.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rico.model.Bank;
import com.rico.model.Teller;

@RestController
@RequestMapping("/process-transactions/")
public class TellerController {

	public TellerController() {
		Bank bankRef = new Bank();

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
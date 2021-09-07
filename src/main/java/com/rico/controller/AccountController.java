package com.rico.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rico.entity.Account;
import com.rico.repository.AccountRepository;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<Account>> getAllAccounts(
			@RequestParam(required=false) String accName) {
		try {
			List<Account> accountsList = new ArrayList<>();
			
			if(accName==null)
				accountRepository.findAll().forEach(accountsList::add);
			else if(accName!=null)
				accountRepository.findByAccName(accName).forEach(accountsList::add);
			
			return new ResponseEntity<>(accountsList, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Account>> getAccountById(@PathVariable("id") int id){
		try {
			Optional<Account> acc = accountRepository.findById(id);
			
			if(acc.isPresent())
				return new ResponseEntity<>(acc, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<Account> createNewAccount(@RequestBody Account account){
		try {
			Account acc = accountRepository.save(account);
			return new ResponseEntity<>(acc, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save-default")
	public ResponseEntity<List<Account>> createDefaultAccounts(){
		try {
			Account acc1 = new Account(0, "George", 500.00);
			Account acc2 = new Account(0, "Abraham", 650.00);
			Account acc3 = new Account(0, "Thomas", 350.00);
			Account acc4 = new Account(0, "Benjamin", 1000.00);
			Account acc5 = new Account(0, "Theodore", 400.00);
			Account acc6 = new Account(0, "Barack", 750.00);
			
			List<Account> accountsList = Arrays.asList(acc1, acc2, acc3, acc4, acc5, acc6);
			for(Account account:accountsList)
				accountRepository.save(account);
			
			return new ResponseEntity<>(accountsList, HttpStatus.OK);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable(name="id") int acc_id){
		try {
			Optional<Account> _acc = accountRepository.findById(acc_id);
			
			if(_acc != null) {
				Account _acc_data = _acc.get();
			
				Account acc = new Account(_acc_data.getId(), account.getAccName(), account.getBalance());
				accountRepository.save(acc);
				return new ResponseEntity<>(acc, HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

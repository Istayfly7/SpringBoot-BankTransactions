package com.rico.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
}

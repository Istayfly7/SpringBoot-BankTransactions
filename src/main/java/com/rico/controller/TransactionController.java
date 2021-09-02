package com.rico.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rico.entity.Transaction;
import com.rico.repository.TransactionRepository;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionRepository transactionsRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<Transaction>> getAllTransactions(
			@RequestParam(required=false) String transType){
		try {
			List<Transaction> transList = new ArrayList<>();
			
			if(transType==null)
				transactionsRepository.findAll().forEach(transList::add);
			else if(transType!=null)
				transactionsRepository.findByTransType(transType).forEach(transList::add);
			
			return new ResponseEntity<>(transList, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Transaction>> getTransactionById(@PathVariable("id") int id){
		try {
			Optional<Transaction> t = transactionsRepository.findById(id);
			
			if(t.isPresent())
				return new ResponseEntity<>(t, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}

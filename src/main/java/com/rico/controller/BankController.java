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

import com.rico.entity.Bank;
import com.rico.repository.BankRepository;

@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	private BankRepository bankRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<Bank>> getAllBanks(
			@RequestParam(required=false) String transType){
		try {
			List<Bank> transList = new ArrayList<>();
			
			if(transType==null)
				bankRepository.findAll().forEach(transList::add);
			else if(transType!=null)
				bankRepository.findByTransType(transType).forEach(transList::add);
			
			return new ResponseEntity<>(transList, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Bank>> getAccountById(@PathVariable("id") int id){
		try {
			Optional<Bank> b = bankRepository.findById(id);
			
			if(b.isPresent())
				return new ResponseEntity<>(b, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}

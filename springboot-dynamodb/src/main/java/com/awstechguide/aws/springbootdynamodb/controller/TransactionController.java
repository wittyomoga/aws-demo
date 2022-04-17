package com.awstechguide.aws.springbootdynamodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awstechguide.aws.springbootdynamodb.domain.Transaction;
import com.awstechguide.aws.springbootdynamodb.service.TransactionService;

@RestController
@RequestMapping(TransactionService.SERVICE_NAME)
public class TransactionController {

	@Autowired
	TransactionService tranService;
	
	@GetMapping("/{trnId}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable String trnId){
		System.out.println("******"+trnId);
		Transaction trn = tranService.load(trnId);
		return new ResponseEntity<Transaction>(trn, HttpStatus.OK);
	}
	
	@PostMapping
	public String save(@RequestBody Transaction trn){
		tranService.save(trn);
		return "Record Inserted Successfully";
	}
	
}

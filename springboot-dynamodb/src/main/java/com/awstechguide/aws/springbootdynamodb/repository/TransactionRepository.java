package com.awstechguide.aws.springbootdynamodb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.awstechguide.aws.springbootdynamodb.domain.Transaction;
import com.awstechguide.aws.springbootdynamodb.service.TransactionService;

import lombok.extern.java.Log;

@Log
@Repository
public class TransactionRepository implements TransactionService{

	@Autowired
	DynamoDBMapper mapper;
	
	@Override
	public String save(Transaction trn) {
		mapper.save(trn);
		log.info("Record saved successfully "+ trn);
		return "SUCCESS";
	}

	@Override
	public Transaction load(String transactionId) {
		Transaction trn = new Transaction();
		trn.setTransactionId(transactionId);	
		Transaction tranc =mapper.load(trn);
		return tranc;
	}

	@Override
	public Transaction update(Transaction updatedTrn, String transactionId) {
		Transaction t = new Transaction();
		t.setTransactionId(transactionId);
		Transaction newTrn = mapper.load(t);
		newTrn=updatedTrn;
		mapper.save(newTrn);
		return null;
	}

	@Override
	public String delete(String transactionId) {
		Transaction t = new Transaction();
		t.setTransactionId(transactionId);
		Transaction result = mapper.load(t);
		mapper.delete(result);
		return "SUCCESS";
	}

	@Override
	public List<Transaction> query(String transactionId) {
		Transaction t = new Transaction();
		t.setTransactionId(transactionId);		
		DynamoDBQueryExpression<Transaction> exp = new DynamoDBQueryExpression<Transaction>()
				.withHashKeyValues(t)
				.withLimit(10);
		
		List<Transaction> res = mapper.query(Transaction.class, exp);
		
		return res;
	}

}
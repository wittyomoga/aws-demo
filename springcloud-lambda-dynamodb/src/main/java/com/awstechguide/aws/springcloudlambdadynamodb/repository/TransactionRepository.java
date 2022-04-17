package com.awstechguide.aws.springcloudlambdadynamodb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.awstechguide.aws.springcloudlambdadynamodb.domain.Transaction;
import com.awstechguide.aws.springcloudlambdadynamodb.service.TransactionService;

import lombok.extern.java.Log;

@Log
@Repository
public class TransactionRepository implements TransactionService{

	@Autowired
	DynamoDBMapper mapper;
	
	public void save(Transaction trn) {
		mapper.save(trn);
		log.info("Record saved successfully "+ trn);
	}
	
	public Transaction load(String transactionId) {
		Transaction trn = new Transaction();
		trn.setTransactionId(transactionId);	
		Transaction tranc =mapper.load(trn);
		log.info("Transaction: "+ tranc.toString());
		return tranc;
	}
}

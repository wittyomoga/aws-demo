package com.awstechguide.aws.springbootdynamodb.service;

import java.util.List;

import com.awstechguide.aws.springbootdynamodb.domain.Transaction;

public interface TransactionService {
	String SERVICE_NAME ="/transaction";

	String save(Transaction trn);
	Transaction load(String transactionId);
	Transaction update(Transaction updatedTrn, String transactionId);
	String delete(String transactionId);
	List<Transaction> query(String transactionId);
}

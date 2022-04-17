package com.awstechguide.aws.springcloudlambdadynamodb.service;

import com.awstechguide.aws.springcloudlambdadynamodb.domain.Transaction;

public interface TransactionService {
	String SERVICE_NAME ="/transaction";
	void save(Transaction trn);
	Transaction load(String transactionId);
}

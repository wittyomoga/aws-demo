package com.awstechguide.aws.springcloudlambdadynamodb.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBGeneratedUuid;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;
import com.awstechguide.aws.springcloudlambdadynamodb.model.CustomerTranslator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "Transaction")
public class Transaction {
	
	@DynamoDBHashKey(attributeName = "transactionid")
	@DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
	private String transactionId;
	
	@DynamoDBAttribute(attributeName = "date")
	private String date;
	
	@DynamoDBAttribute(attributeName = "transactionno")
	private Integer transactionNo;
	
	@DynamoDBAttribute(attributeName = "amount")
	private Integer amount;
	
	@DynamoDBVersionAttribute(attributeName = "version")
	private Long version;
	
	@DynamoDBAttribute(attributeName = "customer")
	@DynamoDBTypeConverted(converter = CustomerTranslator.class)
	private Customer customer;
	
	
}

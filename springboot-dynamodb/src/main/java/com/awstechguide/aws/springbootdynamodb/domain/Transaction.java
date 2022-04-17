package com.awstechguide.aws.springbootdynamodb.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBGeneratedUuid;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;
import com.awstechguide.aws.springbootdynamodb.model.CustomerTranslator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*using lombom here. Reducing boilerplate code results in better readability; 
 * less code means fewer errors. Project Lombok is becoming common in almost 
 * all major organizations and projects.*/

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
	private String amount;
	
	@DynamoDBVersionAttribute(attributeName = "version")
	private Long version;
	
	@DynamoDBAttribute(attributeName = "customer")
	@DynamoDBTypeConverted(converter = CustomerTranslator.class)
	private Customer customer;
	
	
}

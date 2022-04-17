package com.awstechguide.aws.springbootdynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.awstechguide.aws.springbootdynamodb.domain.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerTranslator implements DynamoDBTypeConverter<String, Customer>{

	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public String convert(Customer object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Customer unconvert(String object) {
		try {
			return mapper.readValue(object, Customer.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

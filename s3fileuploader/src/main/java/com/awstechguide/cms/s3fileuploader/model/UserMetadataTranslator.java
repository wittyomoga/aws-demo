package com.awstechguide.cms.s3fileuploader.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.awstechguide.cms.s3fileuploader.domain.UserMetadata;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserMetadataTranslator implements DynamoDBTypeConverter<String, UserMetadata>{

private static final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public String convert(UserMetadata object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserMetadata unconvert(String object) {
		try {
			return mapper.readValue(object, UserMetadata.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

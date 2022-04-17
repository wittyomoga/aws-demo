package com.awstechguide.cms.s3fileuploader.domain;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBGeneratedUuid;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.awstechguide.cms.s3fileuploader.model.UserMetadataTranslator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "S3FileMetadata")
public class FileMetadata {

	@DynamoDBHashKey(attributeName = "fileId")
	@DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
	private String fileId;
	
	@DynamoDBAttribute(attributeName = "filename")
	private String fileName;
	
	@DynamoDBAttribute(attributeName = "filetype")
	private String fileType;
	
	@DynamoDBAttribute(attributeName = "filesize")
	private Long fileSize;
	
	@DynamoDBAttribute(attributeName = "version")
	private Long fileVersion;
	
	@DynamoDBAttribute(attributeName = "uploadedon")
	private Date fileUploadDate;
	
	@DynamoDBAttribute(attributeName = "userdata")
	@DynamoDBTypeConverted(converter = UserMetadataTranslator.class)
	private UserMetadata fileUserdata;
}

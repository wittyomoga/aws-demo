package com.awstechguide.cms.s3fileuploader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfig {

	/*
	 * @Value("${cloud.aws.credentials.access-key}") private String accessKey;
	 * 
	 * @Value("${cloud.aws.credentials.secret-key}") private String accessSecret;
	 */

	@Value("${cloud.aws.region.static}")
	private String region;

	@Value("${cloud.aws.credentials.profile-name}")
	private String profileName;

	@Value("${cloud.aws.dynamodb.region}")
	private String amazonDynamoDBRegion;

	@Bean
	public AmazonS3 s3Client() {
		// AWSCredentials credentials = new BasicAWSCredentials(accessKey,
		// accessSecret);
		/*
		 * return AmazonS3ClientBuilder.standard().withCredentials(new
		 * AWSStaticCredentialsProvider(credentials)) .withRegion(region).build();
		 */

		return AmazonS3ClientBuilder.standard().withCredentials(new ProfileCredentialsProvider(profileName))
				.withRegion(region).build();
	}

	@Bean
	public DynamoDBMapperConfig dynamoDBMapperConfig() {
		return DynamoDBMapperConfig.DEFAULT;
	}

	@Bean
	public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
		return new DynamoDBMapper(amazonDynamoDB, config);
	}

	/* Create the clients for others like S3, same as amazonDynamoDB method */
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new ProfileCredentialsProvider(profileName))
				.withRegion(amazonDynamoDBRegion).build();
		return client;
	}

	/*
	 * @Bean public DynamoDB dynamoDB() { return new DynamoDB(amazonDynamoDB()); }
	 */

}

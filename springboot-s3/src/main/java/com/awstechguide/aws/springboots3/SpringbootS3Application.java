package com.awstechguide.aws.springboots3;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

@SpringBootApplication
public class SpringbootS3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootS3Application.class, args);
		/*
		 * create an user in IAM with programatic access and copy the Access key and
		 * secret access key here.
		 */
		
		BasicAWSCredentials credentials = new BasicAWSCredentials("Accesskey",
				"SecretAccessKey"); 
		 
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1).build();
		
		// final AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
		/*
		 * In above commented line AWS will pick aws credential from your system's
		 * users/.aws directory. there will be a file in .aws directory after you
		 * install AWS CLI and do 'aws configure'
		 */
		List<Bucket> buckets = s3.listBuckets();
		buckets.stream().forEach(bucket -> {
			System.out.println("bucket name: " + bucket.getName() + " Create date: " + bucket.getCreationDate()
					+ " get Owner: " + bucket.getOwner());
		});
	}

}

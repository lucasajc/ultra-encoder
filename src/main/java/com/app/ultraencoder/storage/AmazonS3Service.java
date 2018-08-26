package com.app.ultraencoder.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.app.ultraencoder.utils.*;
import java.io.File;

public class AmazonS3Service{
	
	public static void uploadFile(File file){
		System.out.format("Uploading %s to S3 bucket %s...\n", file, Constants.awsS3BucketName);
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(Constants.awsS3Key, Constants.awsS3KeySecret);
		
		AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Constants.awsS3ClientRegion)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
		
		try {
		    s3.putObject(Constants.awsS3BucketName, Constants.awsS3Key, file);
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    System.exit(1);
		}
	}
	
	
}
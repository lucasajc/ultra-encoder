package com.app.ultraencoder.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.app.ultraencoder.utils.*;
import java.io.File;


/**
 * @description implementation of Amazon S3 Service
 */
public class AmazonS3Service{
	
	 /**
	   * @description uploads a video file to the S3 bucket
	   * @param {File} file - the required video file
	   * @return {String} URL result of the file upload
	   */
	public static String uploadFile(File file){		
		
		AmazonS3 s3 = buildS3Client(Constants.awsS3Key, Constants.awsS3KeySecret);
		
		try {
				System.out.format("Uploading %s to S3 bucket %s...\n", file, Constants.awsS3BucketName);
			
				PutObjectResult response = s3.putObject(Constants.awsS3BucketName, file.getName(), file);
				
				s3.setObjectAcl(Constants.awsS3BucketName, file.getName(), CannedAccessControlList.PublicRead);
				s3.shutdown();
				
				if(response != null) {
					return "https://s3."+Constants.awsS3ClientRegion+".amazonaws.com/"+Constants.awsS3BucketName+"/"+file.getName();
				}
				else {
					return null;
				}
			
			} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    System.exit(1);
		}
		return null;
	}
	
	/**
	   * @description builds a S3 client object
	   * @param {String} key - the required AWS key
	   * @param {String} secret - the required AWS key secret
	   * @return {AmazonS3} the S3 client
	   */
	public static AmazonS3 buildS3Client(String key, String secret){
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(key,secret);

		return  AmazonS3ClientBuilder.standard()
                .withRegion(Constants.awsS3ClientRegion)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
		
	}
	
	/**
	   * @description changes a file access type at S3 bucket
	   * @param {String} key - the required AWS key
	   * @param {String} secret - the required AWS key secret
	   * @param {String} bucketName - the required S3 bucket name
	   * @param {String} fileName - the required file name
	   * @param {CannedAccessControlList} access - the required access type (e.g. public)
	   * @return {AmazonS3} the S3 client
	   */
	public static void changeFileAccessControl(String key, String secret, String bucketName, String fileName, CannedAccessControlList access){
		AmazonS3 s3 = buildS3Client(key,secret);
		s3.setObjectAcl(Constants.awsS3BucketName, fileName, access);		
	}
	
	
}
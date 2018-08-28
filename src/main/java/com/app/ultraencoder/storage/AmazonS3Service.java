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
import java.util.Calendar;
import java.util.Date;

public class AmazonS3Service{
	
	public static String uploadFile(File file){
		
		Date expireTime = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(expireTime); 
		c.add(Calendar.DATE, 1);
		expireTime = c.getTime();
		
		
		System.out.format("Uploading %s to S3 bucket %s...\n", file, Constants.awsS3BucketName);
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(Constants.awsS3Key, Constants.awsS3KeySecret);
		
		//BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAIFWRUUN3GBEWPICQ", "w1wYJFeQNV3VFOPKTpLgab6gAs3e3RKCGFenGIA3");
		
		AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Constants.awsS3ClientRegion)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                //.withCredentials(new EnvironmentVariableCredentialsProvider())
                .build();
		try {
			PutObjectResult response = s3.putObject(Constants.awsS3BucketName, file.getName(), file);
			s3.setObjectAcl(Constants.awsS3BucketName, file.getName(), CannedAccessControlList.PublicRead);
			
			/*
			GeneratePresignedUrlRequest generatePresignedUrlRequest = 
                    new GeneratePresignedUrlRequest(Constants.awsS3BucketName, file.getName())
                    .withMethod(HttpMethod.PUT)
                    .withExpiration(expireTime);
			
            URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
    
            return url.toString();
            */
			
			return "https://s3."+Constants.awsS3ClientRegion+".amazonaws.com/"+Constants.awsS3BucketName+"/"+file.getName();
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    System.exit(1);
		}
		return null;
	}
	
	
}
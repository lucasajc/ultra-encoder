package com.app.ultraencoder.model;

import java.io.File;

import org.springframework.web.client.RestClientException;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.app.ultraencoder.encoding.ZencoderService;
import com.app.ultraencoder.storage.AmazonS3Service;
import com.app.ultraencoder.utils.Constants;
import com.app.ultraencoder.utils.Utils;
import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.response.ZencoderCreateJobResponse;

public class ConvertVideoFile{
	
	/**
	   * @description convert video file service
	   * @param {File} file - the required video file
	   * @return {String} URL of the converted video
	   */
	public static String conversionService(File file) throws RestClientException, ZencoderClientException {
		
		String url = AmazonS3Service.uploadFile(file);
		
        if(url != null) {
        	
        	ZencoderCreateJobResponse response = ZencoderService.createJob(url);
        	InputOutputProgress progress;
            System.out.println(url);
     		ZencoderClient client = new ZencoderClient(Constants.zencoderKey);
     		
     		do {
     			progress = ZencoderService.queryJob(response, client);
     			System.out.println(progress.getOutputProgress().getState().toString()+"/"+progress.getOutputProgress().getProgress());
     		}while(!progress.getOutputProgress().getState().toString().equals(Constants.zencoderFinishedStatus));
     		
     		System.out.println("File successfully converted.");
     		
     		AmazonS3Service.changeFileAccessControl(Constants.awsS3Key, Constants.awsS3KeySecret, Constants.awsS3BucketName, Utils.getFileNameFromUrl(response.getOutputs().get(0).getUrl()), CannedAccessControlList.PublicRead);
            
     		return response.getOutputs().get(0).getUrl();
        }
        else {
        	System.out.println("Unable to upload the S3 bucket - PutObjectResult returned null");
        }
        
        return null;
	}
	
}
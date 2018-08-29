package com.app.ultraencoder.encoding;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestClientException;

import com.app.ultraencoder.model.InputOutputProgress;
import com.app.ultraencoder.utils.*;
import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.model.ContainerFormat;
import com.brightcove.zencoder.client.request.ZencoderCreateJobRequest;
import com.brightcove.zencoder.client.request.ZencoderOutput;
import com.brightcove.zencoder.client.response.ZencoderCreateJobResponse;
import com.brightcove.zencoder.client.response.ZencoderJobDetail;

/**
 * @description implementation of Amazon S3 Service
 */
public class ZencoderService{
	
	/**
	   * @description creates a job to make the video format conversion using Zencoder API
	   * @param {String} url - the required URL from the file upload
	   * @return {ZencoderCreateJobResponse} response of job creation request
	   */
	public static ZencoderCreateJobResponse createJob(String url) throws ZencoderClientException,RestClientException  {
		
		String newUrl = "";
		
		ZencoderClient client = new ZencoderClient(Constants.zencoderKey);
		ZencoderCreateJobRequest job = new ZencoderCreateJobRequest(); 
        job.setInput(url); 
        job.setTest(true); 
        List<ZencoderOutput> outputs = new ArrayList<ZencoderOutput>(); 
 
        ZencoderOutput output = new ZencoderOutput();
        output.setFormat(ContainerFormat.MP4);
        
        newUrl = Utils.changeFileFormat(url, Constants.mp4Format);
        output.setUrl(newUrl);
        outputs.add(output);
 
        job.setOutputs(outputs); 
        ZencoderCreateJobResponse response = client.createZencoderJob(job); 
 
        String jobId = response.getId(); 
        ZencoderJobDetail details = client.getZencoderJob(jobId); 
        String inputId = details.getInputMediaFile().getId(); 
        String outputId1 = response.getOutputs().get(0).getId(); 
 
        System.out.println("Job created. Input id: "+inputId+"/ Output id: "+outputId1);
        
        return response;
	}
	
	/**
	   * @description queries a job from Zencoder API
	   * @param {ZencoderCreateJobResponse} response - job data
	   * @param {ZencoderClient} client - client data
	   * @return {InputOutputProgress} input/output job progress
	   */
	public static InputOutputProgress queryJob(ZencoderCreateJobResponse response, ZencoderClient client) throws ZencoderClientException {
	
		String jobId = response.getId();
		ZencoderJobDetail details = client.getZencoderJob(jobId);
		String inputId = details.getInputMediaFile().getId();
		String outputId = response.getOutputs().get(0).getId();
		
		return new InputOutputProgress(client.getInputProgress(inputId),client.getOutputProgress(outputId));
	}
}
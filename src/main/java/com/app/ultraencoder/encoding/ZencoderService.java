package com.app.ultraencoder.encoding;

import java.util.ArrayList;
import java.util.List;

import com.app.ultraencoder.utils.*;
import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.model.ContainerFormat;
import com.brightcove.zencoder.client.request.ZencoderCreateJobRequest;
import com.brightcove.zencoder.client.request.ZencoderOutput;
import com.brightcove.zencoder.client.response.ZencoderCreateJobResponse;
import com.brightcove.zencoder.client.response.ZencoderInputOutputProgress;
import com.brightcove.zencoder.client.response.ZencoderJobDetail;

public class ZencoderService{
	
	public static void createJob(String url) throws ZencoderClientException {
		
		ZencoderClient client = new ZencoderClient(Constants.zencoderKey);
		ZencoderCreateJobRequest job = new ZencoderCreateJobRequest(); 
        job.setInput(url); 
        job.setTest(true); 
        List<ZencoderOutput> outputs = new ArrayList<ZencoderOutput>(); 
 
        ZencoderOutput output = new ZencoderOutput();
        output.setFormat(ContainerFormat.MP4);
        output.setUrl(url.replace(".mov", ".mp4"));
        outputs.add(output);
 
        job.setOutputs(outputs); 
        ZencoderCreateJobResponse response = client.createZencoderJob(job); 
 
        String jobId = response.getId(); 
        ZencoderJobDetail details = client.getZencoderJob(jobId); 
        String inputId = details.getInputMediaFile().getId(); 
        String outputId1 = response.getOutputs().get(0).getId(); 
 
        System.out.println(inputId);
        System.out.println(outputId1);
		
        ZencoderInputOutputProgress inputProgress = client.getInputProgress(inputId);
        ZencoderInputOutputProgress outputProgress1 = client.getOutputProgress(outputId1); 
	}
}
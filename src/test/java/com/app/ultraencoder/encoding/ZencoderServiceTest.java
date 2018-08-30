package com.app.ultraencoder.encoding;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.app.ultraencoder.model.InputOutputProgress;
import com.app.ultraencoder.utils.Constants;
import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.response.ZencoderCreateJobResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZencoderServiceTest {

	@Test
	public void createJobTest() {
		try {
			ZencoderCreateJobResponse response = ZencoderService.createJob("https://s3.sa-east-1.amazonaws.com/ultra-encoder/prpol-rerender2.mov");
			assertNotNull(response.getId());
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (ZencoderClientException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryJobTest() {
		
		ZencoderCreateJobResponse response;
		try {
			ZencoderClient client = new ZencoderClient(Constants.zencoderKey);
			response = ZencoderService.createJob("https://s3.sa-east-1.amazonaws.com/ultra-encoder/prpol-rerender2.mov");
			InputOutputProgress progress = ZencoderService.queryJob(response, client);
			assertNotNull(progress.getInputProgress().getState());
			assertNotNull(progress.getOutputProgress().getState());
		} catch (RestClientException | ZencoderClientException e) {
			e.printStackTrace();
		}
		
		
	}

}

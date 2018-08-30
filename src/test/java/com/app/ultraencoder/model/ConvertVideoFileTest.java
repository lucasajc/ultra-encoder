package com.app.ultraencoder.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.brightcove.zencoder.client.ZencoderClientException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConvertVideoFileTest {

	@Test
	public void conversionServiceTest() {
		File file = new File("prpol-rerender2.mov");
		try {
			assertNotNull(ConvertVideoFile.conversionService(file));
			assertEquals(ConvertVideoFile.conversionService(file),"prpol-rerender2.mp4");
		} catch (RestClientException | ZencoderClientException e) {
			e.printStackTrace();
		}
	}

}

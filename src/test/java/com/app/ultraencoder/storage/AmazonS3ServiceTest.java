package com.app.ultraencoder.storage;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.ultraencoder.utils.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmazonS3ServiceTest {

	@Test
	public void uploadFileTest() {
		File file = new File("prpol-rerender2.mov");
		
		assertNotNull(AmazonS3Service.uploadFile(file));
	}
	
	@Test
	public void buildS3ClientTest() {		
		assertNotNull(AmazonS3Service.buildS3Client(Constants.awsS3Key, Constants.awsS3KeySecret));
	}

}

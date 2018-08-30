package com.app.ultraencoder.utils;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {

	@Test
	public void convertTest() {
		MultipartFile multipartFile = null;
		File file = null;
		
		try {
			assertEquals(Utils.convert(multipartFile),file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void changeFileFormatTest() {
		String sampleText = "...///aaaa//bb/bcc//.././a./v/n./a.mov";
		String formatedText = "...///aaaa//bb/bcc//.././a./v/n./a.mp4";
		
		assertEquals(Utils.changeFileFormat(sampleText, "mp4"),formatedText);
	}
	
	@Test
	public void getFileNameFromUrl() {
		String sampleUrl = "...///aaaa//bb/bcc//.././a./v/n./sample.mov";
		
		assertEquals(Utils.getFileNameFromUrl(sampleUrl),"sample.mov");
	}

}

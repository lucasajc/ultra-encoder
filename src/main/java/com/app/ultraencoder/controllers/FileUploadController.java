package com.app.ultraencoder.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.app.ultraencoder.model.ConvertVideoFile;
import com.app.ultraencoder.utils.Utils;
import com.brightcove.zencoder.client.ZencoderClientException;

/**
 * @description controller of the File Upload application
 */
@Controller
public class FileUploadController {
	
	 /**
	   * @description mapping function that handles the video file upload to Amazon S3 bucket
	   * @param {MultipartFile} file - the required video file
	   * @return {String} name of the template (view)
	   */
	@PostMapping("/submit")
    public RedirectView handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttrs) throws IOException, ZencoderClientException {
		
		String newVideoUrl = ConvertVideoFile.conversionService(Utils.convert(file));
		
		System.out.println(newVideoUrl);
		
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl(newVideoUrl);
	    
	    return redirectView;
    }
}
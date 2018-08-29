package com.app.ultraencoder.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description general methods
 */
public class Utils{
	
	/**
	   * @description converts a 'MultipartFile' object to 'File'
	   * @param {MultipartFile} file - the required file
	   * @return {File} MultipartFile converted
	   */
	public static File convert(MultipartFile file) throws IOException {    
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(file.getBytes());
	    fos.close(); 
	    return convFile;
	}
	
	/**
	   * @description changes the file format extension
	   * @param {String} url - the required url
	   * @param {String} newFormat - the new format extension
	   * @return {String} formated url
	   */
	public static String changeFileFormat(String url, String newFormat){
		char urlChar = '0';
		int i = url.length() - 1;
		
		while((urlChar!='.')&&(i>=0)) {
			urlChar = url.charAt(i);
			i--;
		}
		if(url.length()>0) {
			return url.substring(0, i) + "." + newFormat;
		}
		else {
			return url;
		}
	    
	}
	
	/**
	   * @description gets the file name from a specified url
	   * @param {String} url - the required url
	   * @return {String} file name
	   */
	public static String getFileNameFromUrl(String url){
		char urlChar = '0';
		int i = url.length() - 1;
		
		while((urlChar!='/')&&(i>=0)) {
			urlChar = url.charAt(i);
			i--;
		}
		
		if(url.length()>0) {
			System.out.println(url.substring(i+2, url.length()));
			return url.substring(i+2, url.length());
		}
		else {
			return url;
		}
	    
	}
}
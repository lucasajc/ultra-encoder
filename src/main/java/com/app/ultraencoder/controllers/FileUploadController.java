package com.app.ultraencoder.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.ultraencoder.storage.AmazonS3Service;
import com.app.ultraencoder.utils.Utils;

@Controller
public class FileUploadController {
	@GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

	@PostMapping("/submit")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        AmazonS3Service.uploadFile(Utils.convert(file));

        return "index";
    }
}
package com.example.gnu.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.gnu.exception.ExtensionNotMatchException;
import com.example.gnu.exception.InvalidHeaderException;

@Controller
public class FileController {
	private final String STORE_DIR = "c:"+File.separator+"uploadedFile"+File.separator;
	@GetMapping("/fileUpload")
	public String fileUploadTest() {
		return "fileUploadTest";
	}

	@PostMapping("/uploadFile")
	public @ResponseBody String fileUploadHandler(@RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		String identifier = UUID.randomUUID().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMM");
		String today = sdf.format(new Date());
		String dir = STORE_DIR+File.separator+today+File.separator;
		File f = new File(dir);
		f.mkdirs();
		file.transferTo(new File(dir+identifier+"_"+file.getOriginalFilename()));
		return file.getOriginalFilename();
	}
	
	public boolean validator(MultipartFile file) throws ExtensionNotMatchException, InvalidHeaderException{
		if(!file.getOriginalFilename().matches("^.+\\.(?i)(jpg|gif|png)$")){
			throw new ExtensionNotMatchException("only allowed to jpg, gif, png");
		} else if(!file.getContentType().matches("^(image/).+$")){
			throw new InvalidHeaderException("only image files is accepted");
		}
		return true;
	}

}

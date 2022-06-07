package com.coding404.myweb.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding404.myweb.animal.AnimalService;
import com.coding404.myweb.command.animalVO;

@RestController
public class ajaxController {

	//animal서비스 영역으로 연결
	@Autowired
	AnimalService animalservice;
	
	@Value("${project.upload.path}")
	private String uploadpath;
	
	@GetMapping("/display")
	public byte[] display(@RequestParam("filepath") String filepath,
						  @RequestParam("filename") String filename,
						  @RequestParam("uuid") String uuid) {
		
		//System.out.println(filepath + "\\" + uuid + "_" + filename);
		
		
		
		File file = new File(uploadpath + "/" + filepath + "/" + uuid + "_" + filename);
		
		byte[] result = null;
		try {
			//경로에 파일을 읽어서 바이트 배열형으로 파일정보를 반환
			result = FileCopyUtils.copyToByteArray(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@GetMapping("/modalview")
	public animalVO modalview(@RequestParam("pk") String pk){
		System.out.println(pk);
		
		animalVO vo =  animalservice.modalview(pk);
		
		return vo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

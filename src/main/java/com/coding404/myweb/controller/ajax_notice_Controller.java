package com.coding404.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding404.myweb.command.NoticeVO;
import com.coding404.myweb.notice.NoticeService;

@RestController
public class ajax_notice_Controller {
	
	
	@Autowired
	@Qualifier("noticeService")
	private NoticeService noticeService;
	
	
	@GetMapping("/modalDetail")
	public NoticeVO modalDetail(@RequestParam("num") String num) {
		System.out.println(num);
		
		NoticeVO vo =noticeService.modalDetail(num);
		
		
		return vo;
		
	}
	
}
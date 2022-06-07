package com.coding404.myweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.HistoryVO;
import com.coding404.myweb.command.NoticeVO;
import com.coding404.myweb.command.UserVO;
import com.coding404.myweb.notice.NoticeService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	@Qualifier("noticeService")
	private NoticeService noticeService;
	
	
	//공지게시판등록 화면처리
	@GetMapping("/notice_reg")
	public String notice_reg(NoticeVO vo,
							 HttpSession session,
							 Model model,
			  				 RedirectAttributes RA) {
		
		
	
		if(session.getAttribute("userVO") == null) {
			RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
			return "redirect:/main";
		}
		
		UserVO sessionVO = (UserVO)session.getAttribute("userVO");
		String admin_id = sessionVO.getUser_id();
		
			if(!admin_id.equals("admin") ) {
				
				RA.addFlashAttribute("msg", "관리자만 작성 가능합니다.");
				
				return "redirect:/notice/notice";
			}
		
			model.addAttribute("noticeVO", new NoticeVO());
			

		return "notice/notice_reg";
	}
		

@PostMapping("/noticeForm")
	public String noticeForm(@Valid NoticeVO vo,
							 Errors errors,
							 RedirectAttributes RA,
							 Model model) {
		
		
		if(errors.hasErrors() ) { //유효성 검사 실패시 true
			
			List<FieldError> list = errors.getFieldErrors(); //유효성 검사 실패 목록확인 
					
			for(FieldError err : list) {
				
				if(err.isBindingFailure() ) { //자바측 에러인 경우 
					model.addAttribute("valid_" + err.getField(), "형식을 확인하세요"); //직접 에러메세지 생성
				} else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage() ); //유효성 검사 실패 메세지 
				}
				
			}
			
			model.addAttribute("noticeVO", vo);
		
			return "notice/notice_reg";
			}
		
		
		//글 작성
		int result = noticeService.regist(vo);
		
		if(result == 1) { //성공 
			RA.addFlashAttribute("msg", vo.getAd_list_title() + "이 정상 등록되었습니다");
		} else { //실패 
			RA.addFlashAttribute("msg", "등록 실패, 다시 작성해주세요");
		}
		return "redirect:/notice/notice";
	}



@PostMapping("/noticeDelete")
public String noticeDelete(@RequestParam("getNum") int getNum,
						   HttpSession session, 
						   RedirectAttributes RA) {
	
	
	if(session.getAttribute("userVO") == null) {
		RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
		return "redirect:/main";
	}
	
	
	UserVO sessionVO = (UserVO)session.getAttribute("userVO");
	String admin_id = sessionVO.getUser_id();
	
		if(!admin_id.equals("admin") ) {
			
			RA.addFlashAttribute("msg", "관리자만 삭제 가능합니다.");
			
			return "redirect:/notice/notice";
		}
	
	int result = noticeService.delete(getNum);
	
	if(result == 1) {
		RA.addFlashAttribute("msg", "삭제되었습니다");
	} else {
		RA.addFlashAttribute("msg", "삭제에 실패하였습니다");
	}
	
	
	return "redirect:/notice/notice";
}
	

@PostMapping("/noticeUpdateToReg")
public String noticeUpdateToReg(@RequestParam("getNum") int getNum,
							    HttpSession session,
							    RedirectAttributes RA,
								Model model) {
	
	
	if(session.getAttribute("userVO") == null) {
		RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
		return "redirect:/main";
	}
	
	UserVO sessionVO = (UserVO)session.getAttribute("userVO");
	String admin_id = sessionVO.getUser_id();
	
		if(!admin_id.equals("admin") ) {
			
			RA.addFlashAttribute("msg", "관리자만 수정 가능합니다.");
			
			return "redirect:/notice/notice";
		}
	
	
	
	NoticeVO vo = noticeService.updateToReg(getNum);
	
	model.addAttribute("noticeVO", vo);
	model.addAttribute("hide", "hide");

	
	System.out.println(getNum);
	System.out.println(vo.toString() );

	
	return "notice/notice_reg";
	
}



@PostMapping("/noticeUpdate")
public String noticeUpdate(@Valid NoticeVO vo,
		 				   Errors errors,
		 				   Model model,
		 				   RedirectAttributes RA) {
	
	
	
	
	if(errors.hasErrors() ) { //유효성 검사 실패시 true
		
		List<FieldError> list = errors.getFieldErrors(); //유효성 검사 실패 목록확인 
				
		for(FieldError err : list) {
			
			if(err.isBindingFailure() ) { //자바측 에러인 경우 
				model.addAttribute("valid_" + err.getField(), "형식을 확인하세요"); //직접 에러메세지 생성
			} else {
				model.addAttribute("valid_" + err.getField(), err.getDefaultMessage() ); //유효성 검사 실패 메세지 
			}
			
		}
		
		model.addAttribute("noticeVO", vo);
	
		return "notice/notice_reg";
		}
	
	System.out.println(vo.toString() );
	
	int result = noticeService.update(vo);
	
	if(result == 1) {
		RA.addFlashAttribute("msg", "수정되었습니다");
	} else {
		RA.addFlashAttribute("msg", "수정에 실패하였습니다");
	}
	
	
	return "redirect:/notice/notice";
	
	}


	
	
	
	//공지게시판 화면처리
	@GetMapping("/notice")
	public String notice(Model model, Criteria cri) {
		
		ArrayList<NoticeVO> list = noticeService.getList(cri);
		int total = noticeService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
	System.out.println(pageVO.toString() );
		
		return "notice/notice";
	}

}

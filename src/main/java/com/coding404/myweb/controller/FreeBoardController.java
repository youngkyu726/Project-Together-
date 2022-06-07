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

import com.coding404.myweb.command.CommentVO;
import com.coding404.myweb.command.FreeVO;
import com.coding404.myweb.command.UserVO;
import com.coding404.myweb.free.FreeService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.Criteria3;
import com.coding404.myweb.util.Page3VO;
import com.coding404.myweb.util.PageVO;

@Controller
@RequestMapping("/free")
public class FreeBoardController {

	@Autowired
	@Qualifier("freeService")
	private FreeService freeService;
	
	
	//자유게시판등록 화면처리
	@GetMapping("free_reg")
	public String free_reg(FreeVO vo,
						   HttpSession session,
			   			   RedirectAttributes RA,
			   			   Model model) {
		
		if(session.getAttribute("userVO") == null) {
			RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
			return "redirect:/main";
		}
		
		model.addAttribute("freeVO", vo);
		
		
		return "free/free_reg";
	}
	
	//자유게시판 화면처리
	@GetMapping("free_list")
	public String free_list(Model model, Criteria3 cri) {
		
		ArrayList<FreeVO> list = freeService.getList(cri);
		int total = freeService.getTotal(cri);
		Page3VO pageVO = new Page3VO(cri, total);
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
		return "free/free_list";
	}
	
	//자유게시판상세보기 화면처리
	@GetMapping("/free_detail")
	public String free_detail(@RequestParam("free_list_num") int free_list_num,
							  Model model, HttpSession session) {
		
		FreeVO freeVO = freeService.getDetail(free_list_num);
		freeService.viewsUpdate(free_list_num);
		model.addAttribute("freeVO", freeVO);
		
		ArrayList<CommentVO> list2 = freeService.getCommentList(free_list_num);
		model.addAttribute("list2", list2);
		
		
		
		
		return "free/free_detail";
	}
	

	
	//자유게시판 등록 폼
	@PostMapping("/freeForm")
	public String freeForm(@Valid FreeVO vo, Errors errors, 
						   HttpSession session,
						   RedirectAttributes RA,
						   Model model) {
		// valid, errors (같이붙여놔야함)
		if(session.getAttribute("userVO") == null) {
			RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
			return "redirect:/main";
		}
		
		if(errors.hasErrors() ) {
			List<FieldError> list = errors.getFieldErrors();
			
			for( FieldError err : list) {
				
				
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "형식을 확인하세요");
				}else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			};
			
			
			
			model.addAttribute("freeVO", vo);
			
			return "free/free_reg";
			
		}
		
		int result = freeService.regist(vo);
		
		if(result == 1 ) {
			RA.addFlashAttribute("msg", "게시글을 등록했습니다.");
		} else {
			RA.addFlashAttribute("msg", "게시글 등록에 실패했습니다.");
		}
		
		return "redirect:/free/free_list";
	}
	
	//수정
	@PostMapping("/updateForm")
	public String updateForm(@Valid FreeVO vo, Errors errors,
							 Model model,
							 HttpSession session,
							 RedirectAttributes RA) {
		
		int N = vo.getFree_list_num();
		
		if(session.getAttribute("userVO") == null) {
			RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
			return "redirect:/main";
		}
		
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		if(!user_id.equals(vo.getFree_list_writer())) {
			RA.addFlashAttribute("msg", "로그인정보와 일치하지 않습니다!");
			return "redirect:/free/free_detail?free_list_num="+N;
		}
		
		if(errors.hasErrors() ) {
			List<FieldError> list = errors.getFieldErrors();
			
			for( FieldError err : list) {
				
				
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "형식을 확인하세요");
				}else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			};
			
			
			model.addAttribute("freeVO", vo);
			
			return "free/free_detail";
			
		}
		
		
		int result = freeService.update(vo);
		
		if(result == 1 ) {
			RA.addFlashAttribute("msg", "게시글을 수정했습니다.");
		} else {
			RA.addFlashAttribute("msg", "게시글 수정에 실패했습니다.");
		}
		
		return "redirect:/free/free_detail?free_list_num="+N;
	}
	
	//삭제
	@PostMapping("/freeDelete")
	public String FreeDelete(FreeVO vo,
							 RedirectAttributes RA,
							 HttpSession session) {
		
		int N = vo.getFree_list_num();
		
		if(session.getAttribute("userVO") == null) {
			RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
			return "redirect:/main";
		}
		
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		if(!user_id.equals(vo.getFree_list_writer())) {
			RA.addFlashAttribute("msg", "로그인정보와 일치하지 않습니다!");
			return "redirect:/free/free_detail?free_list_num="+N;
		}
		
		
		int result = freeService.delete(vo.getFree_list_num());
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "게시글을 삭제 했습니다");
		} else {
			RA.addFlashAttribute("msg", "게시글삭제를 실패했습니다");
		}
		
		return "redirect:/free/free_list";
	}
	
	//댓글등록
	@PostMapping("/commentReg")
	public String commentReg(CommentVO vo,
							 RedirectAttributes RA,
							 HttpSession session
							 ) {
		
		int N = vo.getUser_list_num();
		System.out.println(vo);
		int result = freeService.CommentRegist(vo);
		
		
		return "redirect:/free/free_detail?free_list_num="+N;
	}
	
	//댓글 수정
	@PostMapping("/commentUpdate")
	public String commentUpdate(HttpSession session,
								CommentVO vo,
								RedirectAttributes RA,
								Model model
								) {
		
		if(session.getAttribute("userVO") == null) {
			RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
			return "redirect:/main";
		}
		int N = vo.getUser_list_num();

		UserVO userVO = (UserVO)session.getAttribute("userVO");
		String user_id = userVO.getUser_id();		
		
		if(!user_id.equals(vo.getComment_id())) {
			RA.addFlashAttribute("msg", "로그인정보와 일치하지 않습니다!");
			return "redirect:/free/free_detail?free_list_num="+N;
		}
		
		int result = freeService.commentUpdate(vo);
		
		
		return "redirect:/free/free_detail?free_list_num="+N;
	}
	
	
	//댓글 삭제
	@PostMapping("/commentDelete")
	public String commentDelete(@RequestParam("comment_num") int cn,
								CommentVO vo,
								RedirectAttributes RA,
								HttpSession session) {
		int N = vo.getUser_list_num();
		
		if(session.getAttribute("userVO") == null) {
			RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
			return "redirect:/main";
		}
		
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		
		if(!user_id.equals(vo.getComment_id())) {
			RA.addFlashAttribute("msg", "로그인정보와 일치하지 않습니다!");
			return "redirect:/free/free_detail?free_list_num="+N;
		}
		
		System.out.println(vo);
		
		int result = freeService.commentDelete(cn);		
		
		
		return "redirect:/free/free_detail?free_list_num="+N;
	}
	
}

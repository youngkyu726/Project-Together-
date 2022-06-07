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
import com.coding404.myweb.command.UserVO;
import com.coding404.myweb.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	
	//회원가입 화면처리
	@GetMapping("/sign")
	public String sign(UserVO vo, Model model) {
		
		model.addAttribute("user", new UserVO());
		
		return "user/sign";
	}
	
	//이력 화면처리
	@GetMapping("/history")
	public String history(HttpSession session,
						  Model model,
						  RedirectAttributes RA) {
		
		if(session.getAttribute("userVO") == null) {
			RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
			return "redirect:/main";
		}
		
		UserVO sessionVO = (UserVO)session.getAttribute("userVO");
		String user_id = sessionVO.getUser_id();
		
		ArrayList<HistoryVO> list = userService.getHistory(user_id);
		
		model.addAttribute("list", list);
		
		
		return "user/history";
	}
	
	//전체 이력 화면처리
	@GetMapping("/totalHistory")
	public String totalHistory(HttpSession session,
							   Model model,
							   RedirectAttributes RA) {
		
		if(session.getAttribute("userVO") == null) {
			RA.addFlashAttribute("msg", "관리자만 접근 가능합니다!");
			return "redirect:/main";
		}
		
		UserVO sessionVO = (UserVO)session.getAttribute("userVO");
		String user_id = sessionVO.getUser_id();
		
		if(!user_id.equals("admin")) {
			RA.addFlashAttribute("msg", "관리자만 접근 가능합니다!");
			return "redirect:/main";
		}
		
		ArrayList<HistoryVO> list = userService.getTotalHistory();
		
		model.addAttribute("list", list);
		
		
		return "user/totalHistory";
	}
	
	
	
	
	//마이페이지 화면처리
	@GetMapping("/mypage")
	public String mypage(HttpSession session,
						 RedirectAttributes RA,
						 Model model) {
	
		
		if(session.getAttribute("userVO") == null) {
			RA.addFlashAttribute("msg", "로그인 후에 이용하세요!");
			return "redirect:/main";
		}
		UserVO vo = (UserVO)session.getAttribute("userVO");
		String user_id= vo.getUser_id();
		int getFree = userService.getFree(user_id);
		int getAdopt = userService.getAdopt(user_id);
		int getAnimal = userService.getAnimal(user_id);
		model.addAttribute("getFree", getFree);
		model.addAttribute("getAdopt", getAdopt);
		model.addAttribute("getAnimal", getAnimal);
		
		
		
		return "user/mypage";
	}
	
	
	
	/////////////
	
	//회원가입
	@PostMapping("/signForm")
	public String signForm(@Valid UserVO vo, Errors errors, 
						   RedirectAttributes RA,
						   Model model) {
		
		
		//유효성 검사
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			
			for(FieldError err : list) {
				model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
			}
			
			model.addAttribute("user", vo);
			
			return "user/sign"; //실패시 회원가입 페이지로
		}
		
		
		//join
		int result = userService.join(vo);
		
		if(result == 1) { //성공
			RA.addFlashAttribute("msg", vo.getUser_id() + "님이 가입되었습니다.");
		} else { //실패
			RA.addFlashAttribute("msg", "회원가입에 실패했습니다.");
		}
		
		return "redirect:/main";
	}
	
	
	
	//회원수정
	@PostMapping("/userUpdate")
	public String userUpdate(@Valid UserVO vo, Errors errors,
							 RedirectAttributes RA,
							 Model model,
							 HttpSession session) {
		
		
		//유효성 검사
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			
			for(FieldError err : list) {
				model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
			}
			
			return "user/mypage"; //실패시 mypage로
		}
		
		
		//userUpdate
		int result = userService.userUpdate(vo);
		if(result == 1) { //성공 
			
			RA.addFlashAttribute("msg", vo.getUser_id() + "님의 정보가 변경되었습니다. 다시 로그인하세요");
			
			session.invalidate();
			
		} else { //실패
			RA.addFlashAttribute("msg", "회원정보변경에 실패했습니다.");
		}
		
		
		
		return "redirect:/main";
	}
	
	
	//이력관리 (입양취소)
	@PostMapping("/adopt_form")
	public String adopt_form(HistoryVO vo,
							 RedirectAttributes RA) {
		
		
		int result = userService.delAdopt_list(vo.getAdopt_list_num()); //이력 삭제
		
		if(result == 1) { //성공 
			RA.addFlashAttribute("msg", "해당 이력이 삭제되었습니다.");
			
		} else { //실패
			RA.addFlashAttribute("msg", "이력 삭제에 실패했습니다..");
		}
		
		System.out.println(vo.toString());
		
		userService.update_Animal(vo);  //동물리스트에 복구
		
		
		return "redirect:/user/totalHistory";
	}

}

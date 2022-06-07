package com.coding404.myweb.controller;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.animal.AnimalService;
import com.coding404.myweb.command.HistoryVO;
import com.coding404.myweb.command.UserVO;
import com.coding404.myweb.command.animalVO;

import com.coding404.myweb.util.Criteria2;
import com.coding404.myweb.util.Page2VO;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;



@Controller
@RequestMapping("/animal")
public class AnimalController {
	
	@Autowired
	@Qualifier("AnimalService")
	private AnimalService animalService;
	
	//동물등록 화면처리
	@GetMapping("/animal_Reg")
	public String animal_Reg(Model model,
							HttpSession session,
							RedirectAttributes ra) {
		
		UserVO vo = (UserVO)session.getAttribute("userVO");
		
		if(vo == null) {
			ra.addFlashAttribute("msg", "관리자만 사용가능한 기능입니다");
			return "redirect:/animal/animal_List";
		}
		
		
		if(vo.getUser_id().contains("admin")) {
			
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			String nowdate = sd.format(new Date());
			
			model.addAttribute("nowdate", nowdate);
			model.addAttribute("anivo", new animalVO());
			
			return "animal/animal_Reg";
		}else {
			ra.addFlashAttribute("msg", "관리자만 사용가능한 기능입니다");
			return "redirect:/animal/animal_List";
		}
		
		
	}
	
	//동물게시판 화면처리
	@GetMapping("/animal_List")
	public String animal_List(Model model , Criteria2 cri) {
		
		
		
		
		ArrayList<animalVO> list =  animalService.getdetail(cri);
		
		int total = animalService.getTotal(cri);
		Page2VO pageVO = new Page2VO(cri,total);
		
		
		model.addAttribute("animal" , list);
		model.addAttribute("pageVO", pageVO);
		
		
		
		
		return "animal/animal_List";
	}
	
	
	
	//등록
	@PostMapping("/RegForm")
	public String regform(@Valid animalVO vo,
						  Errors errors,
						 RedirectAttributes ra,
						 @RequestParam("file") MultipartFile f,
						 Model model) {
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String nowdate = sd.format(new Date());
		
		model.addAttribute("nowdate", nowdate);
		
		
		if(errors.hasErrors() ) {
			List<FieldError> list = errors.getFieldErrors();
			
			for( FieldError err : list) {
				
				
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "형식을 확인하세요");
				}else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			
			model.addAttribute("anivo", vo);
			
			return "animal/animal_Reg";
			
		}
		
		
		

			if(f.getContentType().contains("image") == false) { //이미지를 포함하고 있지 않은경우
				model.addAttribute("msg", "jpg,png,jpeg 이미지 형식만 등록가능");
				
				model.addAttribute("anivo", vo);
				
				
				return "animal/animal_Reg";
				
			}
		
		
		
		int result = animalService.regist(vo,f);
		
		if(result == 1 ) {
			ra.addFlashAttribute("msg", "동물을 등록했습니다.");
		} else {
			ra.addFlashAttribute("msg", "동물 등록에 실패했습니다.");
		}
		
	
		return "redirect:/animal/animal_List";
	}
	
	
	@PostMapping("/insertHistory")
	public String insertHistory(animalVO vo,
								@RequestParam("animal_num") String num,
								HttpSession session,
								RedirectAttributes ra) {

//		System.out.println(num);
		animalVO aniVO =  animalService.modalview(num);
		
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		if(userVO == null) {
			ra.addFlashAttribute("msg", "로그인후 이용가능합니다");
			return "redirect:/main";
		}
		String user_id = userVO.getUser_id();
		
		
		HistoryVO hisVO = new HistoryVO();
		hisVO.setUser_id(user_id);
		hisVO.setAdopt_list_name(aniVO.getANIMAL_NAME());
		hisVO.setAdopt_list_type(aniVO.getANIMAL_TYPE());
		hisVO.setAdopt_list_content(aniVO.getANIMAL_CONTENT());
		hisVO.setAnimal_num(aniVO.getANIMAL_NUM());
		
		
		
		animalService.insertHistory(hisVO);
		
		animalService.deleteAnimal(num);
		
		
		
		
		return "redirect:/user/history";
	}
	
	
	
	
	@PostMapping("/getpkUpdate")
	public String updateAnimal(@RequestParam("animal_num") String num,
								Model model,
								HttpSession session,
								RedirectAttributes ra) {
		
		UserVO uservo = (UserVO)session.getAttribute("userVO");
		
		if(uservo == null) {
			ra.addFlashAttribute("msg", "관리자만 사용가능한 기능입니다");
			return "redirect:/animal/animal_List";
		}
		
		
		if(uservo.getUser_id().contains("admin")) {
			
			
			animalVO vo = animalService.getupdateselect(num);
			model.addAttribute("anivo", vo);
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			String nowdate = sd.format(new Date());
			
			model.addAttribute("nowdate", nowdate);
			model.addAttribute("hide", "hide");
			
			return "animal/animal_Reg";
			
			
		}else {
			ra.addFlashAttribute("msg", "관리자만 사용가능한 기능입니다");
			return "redirect:/animal/animal_List";
		}
		
	}
	
	
	
	
	
	
	
	
	
	@PostMapping("/updateAnimal")
	public String updateAnimal(@Valid animalVO vo,
								  Errors errors,
								 RedirectAttributes ra,
								 @RequestParam("file") MultipartFile f,
								 Model model) {
		
		
		
		
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String nowdate = sd.format(new Date());
		
		model.addAttribute("nowdate", nowdate);
		model.addAttribute("hide", "hide");
		
		
		
		if(errors.hasErrors() ) {
			List<FieldError> list = errors.getFieldErrors();
			
			for( FieldError err : list) {
				
				
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "형식을 확인하세요");
				}else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			
			model.addAttribute("anivo", vo);
			
			return "animal/animal_Reg";
			
		}
		
		
		

		
		
		if(f.getContentType().contains("image") == false) { //이미지를 포함하고 있지 않은경우
				model.addAttribute("msg", "jpg,png,jpeg 이미지 형식만 등록가능");
				
				model.addAttribute("anivo", vo);
				
				
				return "animal/animal_Reg";
				
			}
		
			
			
		int result = animalService.updateanimal(vo, f);
		
		if(result == 1 ) {
			ra.addFlashAttribute("msg", "동물정보 수정에 성공했습니다.");
		} else {
			ra.addFlashAttribute("msg", "동물정보 수정에 실패했습니다");
		}
		
		return "redirect:/animal/animal_List";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

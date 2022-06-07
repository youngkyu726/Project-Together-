package com.coding404.myweb.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.animal.AnimalService;
import com.coding404.myweb.command.HistoryVO;
import com.coding404.myweb.command.RecVO;
import com.coding404.myweb.command.UserVO;
import com.coding404.myweb.command.animalVO;
import com.coding404.myweb.util.Criteria;

@Controller
@RequestMapping("/rec")
public class RecController {
	


	@Autowired
	@Qualifier("AnimalService")
	private AnimalService animalService;
	
	
	
	//동물게시판 화면처리
	@GetMapping("/rec_list")
	public String animal_List(Model model , Criteria cri,
							  RedirectAttributes ra,
							  HttpSession session) {
		
		UserVO vo = (UserVO)session.getAttribute("userVO");
		
		if(vo == null) {
			ra.addFlashAttribute("msg", "로그인 후 사용가능 합니다");
			return "redirect:/main";
		}
		
		String user_id = vo.getUser_id();
		
		ArrayList<RecVO> reclist = animalService.getrec(user_id);
		
		model.addAttribute("reclist", reclist);
		
	
		
		
		return "rec/rec_list";
	}
	
	
	@PostMapping("/insertHistory")
	public String insertHistory(animalVO vo,
								@RequestParam("animal_num") String num,
								HttpSession session) {

//		System.out.println(num);
		animalVO aniVO =  animalService.modalview(num);
		
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		if(userVO == null) {
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
	
	
}

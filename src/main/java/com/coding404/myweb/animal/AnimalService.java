package com.coding404.myweb.animal;

import java.util.ArrayList;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.HistoryVO;
import com.coding404.myweb.command.RecVO;
import com.coding404.myweb.command.animalVO;

import com.coding404.myweb.util.Criteria2;

public interface AnimalService {
	
	public int regist(animalVO vo , MultipartFile f); //등록
	
	public ArrayList<animalVO> getdetail(Criteria2 cri); //상세정보
	
	public ArrayList<RecVO> getrec(String User_id);
	
	public int getTotal(Criteria2 cri);
	
	
	public animalVO modalview(String pk); //모달
	
	public int insertHistory(HistoryVO vo); //이력 추가
	
	public int deleteAnimal(String num); //삭제
	
	public animalVO getupdateselect(String num);
	
	public int updateanimal(animalVO vo, MultipartFile f);// 업데이트
}

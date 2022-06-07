package com.coding404.myweb.user;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.coding404.myweb.command.FreeVO;
import com.coding404.myweb.command.HistoryVO;
import com.coding404.myweb.command.NoticeVO;
import com.coding404.myweb.command.UserVO;
import com.coding404.myweb.command.loginVO;

@Mapper
public interface UserMapper {
	
	public int join(UserVO vo); //회원가입
	
	public UserVO login(loginVO vo); //로그인

	public int userUpdate(UserVO vo); //회원정보수정
	
	public ArrayList<HistoryVO> getHistory(String user_id); //이력 list 조회
	public ArrayList<HistoryVO> getTotalHistory(); //총 이력 list 조회
	
	public ArrayList<NoticeVO> getNoticeNew(); //최신공지글 5개
	public ArrayList<FreeVO> getFreeNew(); //최신전체자유글 5개
	public ArrayList<FreeVO> getInfoNew(); //최신정보자유글 5개
	public ArrayList<FreeVO> getAfterNew(); //최신후기자유글 5개
	public ArrayList<FreeVO> getFunNew(); //최신유머자유글 5개
	
	public int getFree(String user_id); // 자유 게시글 수
	public int getAdopt(String user_id); // 이력 수
	public int getAnimal(String user_id); // 입양된 동물 수
	
	public int delAdopt_list(int adopt_list_num); //이력 삭제
	public int update_Animal(HistoryVO vo); //동물리스트 복구
	
}

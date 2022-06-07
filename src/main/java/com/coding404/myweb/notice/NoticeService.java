package com.coding404.myweb.notice;

import java.util.ArrayList;

import com.coding404.myweb.command.NoticeVO;
import com.coding404.myweb.util.Criteria;

public interface NoticeService {
	
	public int regist(NoticeVO vo);

	public ArrayList<NoticeVO> getList(Criteria cri);

	public int getTotal(Criteria cri);
	
	public int update(NoticeVO vo);
	
	public NoticeVO updateToReg(int getNum);
	
	public int delete(int getNum); //삭제 
	
	public NoticeVO modalDetail(String num);
		
		

}

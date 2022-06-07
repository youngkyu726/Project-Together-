package com.coding404.myweb.notice;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;


import com.coding404.myweb.command.NoticeVO;
import com.coding404.myweb.util.Criteria;

@Mapper
public interface NoticeMapper {

	public int regist(NoticeVO vo); //등록
	
	public ArrayList<NoticeVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	
	public int delete(int getNum); //삭제 
	
	public NoticeVO modalDetail(String num);

	public int update(NoticeVO vo);
	
	public NoticeVO updateToReg(int getNum);
	
}

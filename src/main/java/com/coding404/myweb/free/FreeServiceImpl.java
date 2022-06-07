package com.coding404.myweb.free;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding404.myweb.command.CommentVO;
import com.coding404.myweb.command.FreeVO;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.Criteria3;

@Service("freeService")
public class FreeServiceImpl implements FreeService{

	@Autowired
	private FreeMapper freeMapper;
	
	//등록
	@Override
	public int regist(FreeVO vo) {
		return freeMapper.regist(vo);
	}
	
	//목록
	@Override
	public ArrayList<FreeVO> getList(Criteria3 cri) {
		return freeMapper.getList(cri);
	}

	@Override
	public FreeVO getDetail(int free_list_num) {
		return freeMapper.getDetail(free_list_num);
	}

	@Override
	public int delete(int free_list_num) {
		return freeMapper.delete(free_list_num);
	}

	@Override
	public int update(FreeVO vo) {
		return freeMapper.update(vo);
	}

	@Override
	public int viewsUpdate(int free_list_num) {
		return freeMapper.viewsUpdate(free_list_num);
	}

	@Override
	public int CommentRegist(CommentVO vo) {
		return freeMapper.CommentRegist(vo);
	}

	@Override
	public ArrayList<CommentVO> getCommentList(int free_list_num) {
		return freeMapper.getCommentList(free_list_num);
	}

	@Override
	public int getTotal(Criteria3 cri) {
		return freeMapper.getTotal(cri);
	}

	@Override
	public int commentDelete(int comment_num) {
		return freeMapper.commentDelete(comment_num);
	}

	@Override
	public int commentUpdate(CommentVO vo) {
		return freeMapper.commentUpdate(vo);
	}




}

package com.coding404.myweb.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding404.myweb.command.FreeVO;
import com.coding404.myweb.command.HistoryVO;
import com.coding404.myweb.command.NoticeVO;
import com.coding404.myweb.command.UserVO;
import com.coding404.myweb.command.loginVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int join(UserVO vo) {         //회원가입
		return userMapper.join(vo);
	}

	@Override
	public UserVO login(loginVO vo) {    //로그인
		return userMapper.login(vo);
	}

	@Override
	public int userUpdate(UserVO vo) {    //회원정보수정
		return userMapper.userUpdate(vo);
	}

	@Override
	public ArrayList<HistoryVO> getHistory(String user_id) { //이력 list 조회
		return userMapper.getHistory(user_id);
	}

	@Override
	public ArrayList<HistoryVO> getTotalHistory() { //총 이력 list 조회
		return userMapper.getTotalHistory();
	}
	
	@Override
	public ArrayList<NoticeVO> getNoticeNew() {
		return userMapper.getNoticeNew();
	}

	@Override
	public ArrayList<FreeVO> getFreeNew() {
		return userMapper.getFreeNew();
	}

	@Override
	public ArrayList<FreeVO> getInfoNew() {
		return userMapper.getInfoNew();
	}

	@Override
	public ArrayList<FreeVO> getAfterNew() {
		return userMapper.getAfterNew();
	}

	@Override
	public ArrayList<FreeVO> getFunNew() {
		return userMapper.getFunNew();
	}

	@Override
	public int getFree(String user_id) {
		return userMapper.getFree(user_id);
	}

	@Override
	public int getAdopt(String user_id) {
		return userMapper.getAdopt(user_id);
	}

	@Override
	public int delAdopt_list(int adopt_list_num) {  //이력 삭제
		return userMapper.delAdopt_list(adopt_list_num);
	}

	@Override
	public int update_Animal(HistoryVO vo) {
		return userMapper.update_Animal(vo);
	}

	@Override
	public int getAnimal(String user_id) {
		
		return userMapper.getAnimal(user_id);
	}






	

}

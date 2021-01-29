package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVo;

public interface UserService {
	
	// 사용자 정보를 조회하는 로직 - 아이디로 받아서 vo를 반환
	UserVo getUser(String userid);
	

}

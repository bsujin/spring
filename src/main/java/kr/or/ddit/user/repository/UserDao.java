package kr.or.ddit.user.repository;

import kr.or.ddit.user.model.UserVo;

public interface UserDao {

	// 사용자 아이디로 사용자 조회
	// 인자가 사용자 아이디, 반환타입이 userVo
	UserVo getUser(String userid);
	
}

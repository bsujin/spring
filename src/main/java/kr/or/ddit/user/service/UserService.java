package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserService {
	
	// 사용자 정보를 조회하는 로직 - 아이디로 받아서 vo를 반환
	UserVo selectUser(String userid);
	
	List<UserVo> selectAllUser();

	Map<String, Object> selectPagingUser(PageVo vo);

	// 사용자 정보 수정
	int modifyUser(UserVo userVo);

	// 사용자 신규 등록
	int registerUser(UserVo userVo);

	// 사용자 삭제
	int deleteUser(String userid);
	
}

package kr.or.ddit.user.repository;

import kr.or.ddit.user.model.UserVo;

public interface UserDao {

	// ����� ���̵�� ����� ��ȸ
	// ���ڰ� ����� ���̵�, ��ȯŸ���� userVo
	UserVo getUser(String userid);
	
}

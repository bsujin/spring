package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVo;

public interface UserService {
	
	// ����� ������ ��ȸ�ϴ� ���� - ���̵�� �޾Ƽ� vo�� ��ȯ
	UserVo getUser(String userid);
	

}

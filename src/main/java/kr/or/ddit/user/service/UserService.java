package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserService {
	
	// ����� ������ ��ȸ�ϴ� ���� - ���̵�� �޾Ƽ� vo�� ��ȯ
	UserVo selectUser(String userid);
	
	List<UserVo> selectAllUser();

	Map<String, Object> selectPagingUser(PageVo vo);

	// ����� ���� ����
	int modifyUser(UserVo userVo);

	// ����� �ű� ���
	int registerUser(UserVo userVo);

	// ����� ����
	int deleteUser(String userid);
	
}

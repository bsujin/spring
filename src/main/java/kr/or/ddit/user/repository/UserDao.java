package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDao {

	// ����� ���̵�� ����� ��ȸ
	// ���ڰ� ����� ���̵�, ��ȯŸ���� userVo
	UserVo selectUser(String userid);
	
	//��ü ����� ���� ��ȸ
	List<UserVo> selectAllUser();
	
	//����¡ó��
	List<UserVo> selectPagingUser(PageVo vo);

	//����� ��ü �� ��ȸ
	int selectAllUserCnt();
	
	//����� ���� ����
	int modifyUser(UserVo userVo);
	
	//����� �ű� ���
	int registerUser(UserVo userVo);
	
	// ����� ���� 
	int deleteUser(String userid);
	
}

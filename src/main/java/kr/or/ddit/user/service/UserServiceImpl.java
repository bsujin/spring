package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	// spring ���¸� dao�� �����ϴ� ���
	// 1. setter �޼ҵ� �̿�
	// 2. Ŭ������ ���鶧 �����ڸ� ���� ������ ���� �� �ִ�
	
//	private UserDao userDao;
	// repository �� Ŭ���� ���� �������� (xml)
	@Resource(name="userDao")
	private UserDao userDao;
	
	public UserServiceImpl() {	}
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserVo getUser(String userid) {
		return userDao.getUser(userid);
	}
	
	
	// getter, setter ����
	// ������(spring) setter�� �����Ͽ� �����ش�
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
}

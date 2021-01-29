package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	// spring 형태를 dao를 주입하는 방식
	// 1. setter 메소드 이용
	// 2. 클래스를 만들때 생성자를 통해 주입을 받을 수 있다
	
//	private UserDao userDao;
	// repository 나 클래스 명에서 가져오기 (xml)
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
	
	
	// getter, setter 생성
	// 누군가(spring) setter에 주입하여 보내준다
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
}

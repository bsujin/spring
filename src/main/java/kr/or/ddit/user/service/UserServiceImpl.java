package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service("userService")
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
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}
	
	@Override
	public List<UserVo> selectAllUser() {
		// TODO Auto-generated method stub
		return userDao.selectAllUser();
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo pageVo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("pageVo", pageVo);
		resultMap.put("userList", userDao.selectPagingUser(pageVo));
		resultMap.put("pagination",(int)Math.ceil( (double)userDao.selectAllUserCnt() / pageVo.getPageSize()) );
		
		//방법2
//		int userCnt = userDao.selectAllUserCnt();
//		resultMap.put("pagination",(int)Math.ceil( (double)userCnt / pageVo.getPageSize()) );
		
//		방법1
//		resultMap.put("userCnt", userDao.selectAllUserCnt());
//		resultMap.put("pagination",(int)Math.ceil(Double.valueOf(resultMap.get("userCnt").toString()) /pageVo.getPageSize()));
//		resultMap.put("pagination",(int)Math.ceil( ((Integer)resultMap.get("userCnt")).doubleValue() / pageVo.getPageSize()) )
		
		return resultMap;
	}
	
	@Override
	public int modifyUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return userDao.modifyUser(userVo);
	}

	@Override
	public int registerUser(UserVo userVo) {
		
		return userDao.registerUser(userVo);
	}

	@Override
	public int deleteUser(String userid) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(userid);
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

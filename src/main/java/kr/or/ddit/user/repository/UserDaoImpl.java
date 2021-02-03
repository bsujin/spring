package kr.or.ddit.user.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

// <bean id="" class="">
// @Repository에서 별다른 설정을 하지 않으면 스프링 빈 이름으로 class 이름에서 
// 첫글자를 소문자로 한 문자열이 스프링 빈의 이름으로 설정된다
// UserDaoImpl ==> userDaoImpl

//nameing에 대한 룰도 중요하다
//UserDao / UserDaoImpl	==> @Resource(name="userDaoImpl")
//UserDaoI / UserDao	==> @Resource(name="userDao")

// 설정하는 방법이 있다("userDao") -> 등록하고 싶은 이름으로  
@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	
	@Override
	public UserVo selectUser(String userid) {
		return template.selectOne("users.selectUser", userid);
	}
	

	@Override
	public List<UserVo> selectAllUser() {
		// TODO Auto-generated method stub
		return template.selectList("users.selectAllUser");
	}

	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {
		return template.selectList("users.selectPagingUser",vo);
	}

	@Override
	public int selectAllUserCnt() {
		// TODO Auto-generated method stub
		return template.selectOne("users.selectAllUserCnt");
	}

	@Override
	public int modifyUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return template.update("users.modifyUser",userVo);
	}

	@Override
	public int registerUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return template.insert("users.registerUser",userVo);
	}

	@Override
	public int deleteUser(String userid) {
		// TODO Auto-generated method stub
		return template.delete("users.deleteUser",userid);
	}

	
	
	

}

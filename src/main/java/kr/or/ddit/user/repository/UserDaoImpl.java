package kr.or.ddit.user.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

// <bean id="" class="">
// @Repository���� ���ٸ� ������ ���� ������ ������ �� �̸����� class �̸����� 
// ù���ڸ� �ҹ��ڷ� �� ���ڿ��� ������ ���� �̸����� �����ȴ�
// UserDaoImpl ==> userDaoImpl

//nameing�� ���� �굵 �߿��ϴ�
//UserDao / UserDaoImpl	==> @Resource(name="userDaoImpl")
//UserDaoI / UserDao	==> @Resource(name="userDao")

// �����ϴ� ����� �ִ�("userDao") -> ����ϰ� ���� �̸�����  
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

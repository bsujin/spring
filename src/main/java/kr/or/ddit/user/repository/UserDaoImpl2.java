package kr.or.ddit.user.repository;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;

// <bean id="" class="">
// @Repository���� ���ٸ� ������ ���� ������ ������ �� �̸����� class �̸����� 
// ù���ڸ� �ҹ��ڷ� �� ���ڿ��� ������ ���� �̸����� �����ȴ�
// UserDaoImpl ==> userDaoImpl

//nameing�� ���� �굵 �߿��ϴ�
//UserDao / UserDaoImpl	==> @Resource(name="userDaoImpl")
//UserDaoI / UserDao	==> @Resource(name="userDao")

// �����ϴ� ����� �ִ�("userDao") -> ����ϰ� ���� �̸�����  

//@Repository("userDao2")
//public class UserDaoImpl2 implements UserDao{
//
//	@Resource(name="sqlSessionTemplate")
//	private SqlSessionTemplate template;
//	
//	
//	@Override
//	public UserVo selectUser(String userid) {
//		return template.selectOne("users.selectUser", userid);
//	}
//	
	
//	@Override
//	public UserVo selectUser(String userid) {
//		SqlSession sqlSession = MybatisUtill.getSqlSession();	xxx
//		UserVo user = sqlSession.selectOne("users.selectMember", userid);
//		sqlSession.close();xxx
//		return user;
	
//	 -> return sqlSession.selectOne("users.selectUser", userid);
//	}

//	@Override
//	public UserVo selectUser(String userid) {
//		// ������ �����ͺ��̽����� ��ȸ�� �ؾ��ϳ�, ���� �ʱ�ܰ�� ������ �Ϸ���� ����
//		// ����, Ȯ���Ϸ��� �ϴ� ����� ������ �����̳ʿ� ������ ���߱� ���� new �����ڸ� ���� ������ vo ��ü�� ��ȯ
//		
//		// UserVo user = new UserVo("brown", "����"); // ���ڰ� �ִ� ������ ����� 
////		return new UserVo("brown", "����");
//		return new UserVo("brown", "����", "brownPass");
//	}

	
	
	
	
	
	

//}

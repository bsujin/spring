package kr.or.ddit.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.ddit.user.model.UserVo;

public class UserVoValidator implements Validator{

	//기본적으로 메서드가 2개 선언되어 있다 
	// userVo의 객체 구현
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserVo.class.isAssignableFrom(clazz);
	}

	@Override					// error를 써주면 된다 - 리턴을해주는 것이 아니라 타입을 기술 
	public void validate(Object target, Errors errors) {
		UserVo userVo = (UserVo)target;
		
		// 검증로직을 기술
		// 에러로 판단되는 상황을 체크하여 errors에 추가 
		
		// userid 길이가 5글자 이상(5글자 허용)
		
		if( userVo.getUserid().length() < 5) {
//			 에러상황을 기술
			errors.rejectValue("userid", "length");
			
		}
		
	}

}

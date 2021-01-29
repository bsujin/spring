package kr.or.ddit.user.model;

public class UserVo {
	String userid;
	String usernm;

	public UserVo() {}

	// 인자가 있는 생성자 만들기 
	public UserVo(String userid, String usernm) {
		setUserid(userid);
		setUsernm(usernm);
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsernm() {
		return usernm;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	
	@Override
	public String toString() {
		return "UserVo [userid=" + userid + ", usernm=" + usernm + "]";
	}
	
	
}

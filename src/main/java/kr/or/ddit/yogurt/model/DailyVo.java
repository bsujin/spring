package kr.or.ddit.yogurt.model;

public class DailyVo {
	
	int cid; // 고객번호
	int pid;	//제품번호
	String dt;	//일자 (년월일 8글자제한)
	int cnt;	//수량
	
	public DailyVo() {}
	//인자가 있는 생성자를 만들면 기본생성자가 만들어지지 않으므로 꼭 만들어주기 ** 중요 
	
	public DailyVo(int cid, int pid, String dt, int cnt) {
		this.cid = cid;
		this.pid = pid;
		this.dt = dt;
		this.cnt = cnt;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "DailyVo [cid=" + cid + ", pid=" + pid + ", dt=" + dt + ", cnt=" + cnt + "]";
	}

}

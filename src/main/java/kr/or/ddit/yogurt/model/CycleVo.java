package kr.or.ddit.yogurt.model;

public class CycleVo {
	int cid;	//고객번호
	int pid;	//제품번호
	int day;	//요일
	int cnt;	//수량
	
	public CycleVo() {	}

	@Override
	public String toString() {
		return "CycleVo [cid=" + cid + ", pid=" + pid + ", day=" + day + ", cnt=" + cnt + "]";
	}
	
	public CycleVo(int cid, int pid, int day, int cnt) {
		this.cid = cid;
		this.pid = pid;
		this.day = day;
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
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	

}

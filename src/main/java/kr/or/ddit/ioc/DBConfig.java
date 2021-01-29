package kr.or.ddit.ioc;

public class DBConfig {

	// db에 연결하려면 필요한 4가지 
	private String url;
	private String driverClassName;
	private String username;
	private String password;
	
	// 설정 부분은 properties에 넣어준다 
	public DBConfig() {}

	public DBConfig(String url, String driverClassName, String username, String password) {
		super();
		this.url = url;
		this.driverClassName = driverClassName;
		this.username = username;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "DBConfig [url=" + url + ", driverClassName=" + driverClassName + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	
	
	
}


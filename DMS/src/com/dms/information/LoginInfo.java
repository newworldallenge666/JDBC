package com.dms.information;
/**
 * 记录登录信息类
 *
 *
 */
public class LoginInfo {
	private String password;
	private String id;
	private String userType;
	
	public LoginInfo() {
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}

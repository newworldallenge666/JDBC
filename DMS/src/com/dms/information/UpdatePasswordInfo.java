package com.dms.information;
/**
 * 更新密码时调用的类，对旧密码以及新密码进行操作
 *
 *
 */
public class UpdatePasswordInfo {
	
	private String ownerId;	
	private	String oldPwd;
	private String newPwd;
	private String CheckNewPwd;
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getoldPwd() {
		return oldPwd;
	}
	public void setoldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getnewPwd() {
		return newPwd;
	}
	public void setnewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getCheckNewPwd() {
		return CheckNewPwd;
	}
	public void setCheckNewPwd(String CheckNewPwd) {
		this.CheckNewPwd = CheckNewPwd;
	}
	
}

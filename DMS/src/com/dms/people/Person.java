package com.dms.people;

import com.dms.information.UpdatePasswordInfo;
import com.dms.information.UpdatePersonInfo;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.PasswordNotMatchException;
import com.dms.myexceptioin.UpdateDataException;
import com.dms.myexceptioin.UpdateSuccessException;
/**
 * 定义抽象类Person,所有人的基类,所有人的对象都要继承person
 * 
 *
 */
public abstract class Person {
	private String id;   //
	private	String name;
	private	String sex;
	private	String phone;
	
	public Person(){
		
	}
	
	/**
	 * 登录
	 * @throws PasswordNotMatchException
	 */
	public abstract void  Login() throws PasswordNotMatchException;
	
	/**
	 * 查询个人信息
	 * @return 
	 */
	public abstract Object PersonalInfo();
	/**
	 * 密码修改
	 * @throws PasswordNotMatchException
	 * @throws DataBaseException 
	 * @throws UpdateSuccessException 
	 */
	public abstract void PasswordUpdate(UpdatePasswordInfo updatePasswordInfo)throws PasswordNotMatchException, DataBaseException, UpdateSuccessException;
	
	/**
	 * 个人信息修改
	 * @throws UpdateDataException 
	 * @throws DataBaseException 
	 */
	public abstract void UpdatePersonalInfo(UpdatePersonInfo updatePersonInfo) throws UpdateDataException, DataBaseException;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	
}

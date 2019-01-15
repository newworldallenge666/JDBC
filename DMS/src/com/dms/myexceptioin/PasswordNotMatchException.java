package com.dms.myexceptioin;


/**
 * 密码不匹配异常
 * 
 *
 */
@SuppressWarnings("serial")
public class PasswordNotMatchException extends Exception {
	public PasswordNotMatchException(){
		super("密码不匹配错误");
	}
}

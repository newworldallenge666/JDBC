package com.dms.myexceptioin;
/**
 * 发生SQL异常错误
 * 
 *
 */
@SuppressWarnings("serial")
public class DataBaseException extends Exception {
	public DataBaseException(){
		super("数据库错误");
	}
}

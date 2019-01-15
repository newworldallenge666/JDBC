package com.dms.myexceptioin;
/**
 * 数据更新失败
 *
 *
 */
@SuppressWarnings("serial")
public class UpdateDataException extends Exception {
	public UpdateDataException(){
		super("数据更新失败!!!");
	}
}

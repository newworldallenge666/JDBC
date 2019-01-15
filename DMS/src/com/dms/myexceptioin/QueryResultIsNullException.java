package com.dms.myexceptioin;


/**
 * 查询不到结果跑出异常
 *
 *
 */
@SuppressWarnings("serial")
public class QueryResultIsNullException extends Exception {
	public QueryResultIsNullException() {
		super("查询结果为空");
	}
}

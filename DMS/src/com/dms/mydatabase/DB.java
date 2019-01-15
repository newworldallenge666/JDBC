package com.dms.mydatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.dms.myexceptioin.DataBaseException;

/**
 * 数据库连接操作
 * 
 */
public class DB {
	private static Connection conn = null;
	private static String url;
	private static String user;
	private static String password;
	private static String driver;

	static {
		//配置信息
		driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		url="jdbc:sqlserver://localhost:1433;DatabaseName=DMSystem";
		user="sa";
		password="root";
		try {	
			System.out.println("正在加载驱动中...");
			Class.forName(driver);
			System.out.println("驱动加载成功");
		} catch (ClassNotFoundException e) {
			System.out.println("驱动获取失败！");
			JOptionPane.showMessageDialog(null, "驱动获取失败！", "错误信息", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws DataBaseException {
		
		try {
			System.out.println("正在连接数据库...");
			conn = DriverManager.getConnection(url,user,password);
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "数据库连接异常!", "异常信息", JOptionPane.ERROR_MESSAGE);
			System.out.println("数据库连接异常!");
			e.printStackTrace();
//			System.out.println(e.getMessage());
//			System.out.println(e.getErrorCode());
			throw new DataBaseException();		
		}
		return conn;
	}

	/**
	 * 
	 * 
	 * 创建会话
	 * @return
	 * @throws DataBaseException
	 */
	public static Statement CreateStatement() throws DataBaseException {
        if(conn==null){
        	/*产生连接*/
        	getConnection();
        } 
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "会话建立失败!", "错误信息", JOptionPane.ERROR_MESSAGE);
			System.out.println("会话建立失败！");
//			System.out.println(e.getMessage());
//			System.out.println(e.getLocalizedMessage());
			throw new DataBaseException();
			// e.printStackTrace();
		}
		return stmt;
	}
	
	public static PreparedStatement CreatePrepare(String sql) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "PrepareStament 创建失败!", "错误信息", JOptionPane.ERROR_MESSAGE);
			System.out.println("PrepareStament 创建失败");
			e.printStackTrace();
		}
		return stmt;
	} 
	/**
	 * 关闭连接
	 */
	public static void Close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
}

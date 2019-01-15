package com.dms.queryresultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.dms.mydatabase.DB;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;

/**
 * 查询本栋楼所有宿舍信息
 * 
 */
public class AllDormitoryInfo {	
	private Vector<Object> dormitoryInfos = new Vector<Object>();
	
	public Vector<Object> getdormitoryInfos(){
		return dormitoryInfos;
	}
	
	public Vector<Object> getDormitoryInfos() {
		return dormitoryInfos;
	}

	public void setDormitoryInfos(Vector<Object> dormitoryInfos) {
		this.dormitoryInfos = dormitoryInfos;
	}

	public void getAllDormitoryInfo(String DormId) throws DataBaseException,QueryResultIsNullException {
		Statement stmt = DB.CreateStatement();
		Statement stmt2 = DB.CreateStatement();
		String sql = "select * from DMSystem..DormitoryInfo where DormId='"+DormId+"'";
//		System.out.println(sql);  //test
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);					
			if (rs != null && !rs.next()) {
				throw new QueryResultIsNullException();
			}		
			ResultSet rs2 = null;		
			String s, sql2;
			s = rs.getString(1);	
	        sql2 = "select * from DMSystem..Stu_DormInfo where DormId='" + s + "'";			
//	        System.out.println(sql2);	//test
			rs2 = stmt2.executeQuery(sql2);
			if (rs2 != null && !rs2.next()) {
				throw new QueryResultIsNullException();
			}
			else {
				do {
					Vector<Object> line = new Vector<Object>();
					for(int i=1; i<=6; i++){
						String string = rs2.getString(i);
						if(string==null){
							line.add("空");
						}else{
							line.add(string);
						}
					}
					dormitoryInfos.add(line);	
				}while(rs2.next());	
			}
		} catch (SQLException e) {
			// e.printStackTrace();
//			System.out.println(e.getMessage());
//			System.out.println(e.getErrorCode());
			throw new DataBaseException();
		}
	}

}

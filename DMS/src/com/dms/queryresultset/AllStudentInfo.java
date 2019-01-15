package com.dms.queryresultset;

import java.util.ArrayList;
/**
 * 所有学生的结果集
 *
 */
public class AllStudentInfo {
	
	private ArrayList<StudentInfo> allStudentInfo = new ArrayList<StudentInfo>();

	public ArrayList<StudentInfo> getAllStudentList() {
		return allStudentInfo;
	}

	public void setAllStudentList(ArrayList<StudentInfo> allStudentInfo) {
		this.allStudentInfo = allStudentInfo;
	}	
}

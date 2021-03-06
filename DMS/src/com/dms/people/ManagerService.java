package com.dms.people;

import java.sql.SQLException;
import java.util.Vector;

import com.dms.information.UpdatePasswordInfo;
import com.dms.information.UpdatePersonInfo;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.PasswordNotMatchException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.myexceptioin.UpdateDataException;
import com.dms.myexceptioin.UpdateSuccessException;
import com.dms.queryresultset.AllDormitoryInfo;
import com.dms.queryresultset.AllMaintanceRecord;
import com.dms.queryresultset.DormitoryBuildingInfo;
import com.dms.queryresultset.StudentInfo;

/*
 * 定义宿舍管理员服务
 */
public interface ManagerService {

	/**
	 * 
	 * 
	 * 获取所有学生的信息
	 * @param dmid 
	 * @return
	 * @throws QueryResultIsNullException
	 * @throws DataBaseException
	 */
	public Vector<Object> getAllStudentInfo(String dmid)
			throws QueryResultIsNullException, DataBaseException; // 得到所有学生的信息

	/**
	 * 
	 * 
	 * @param studentId
	 * @return
	 * @throws QueryResultIsNullException
	 * @throws DataBaseException
	 */
	public StudentInfo queryStudentInfoById(String studentId)
			throws QueryResultIsNullException, DataBaseException; // 查询学生信息by学生id

	public void queryStudentInfoByName(String studentName); // 查询学生信息by学生Name

	public void queryStudentInfoByDormitoryId(String dormitoryId); // 按宿舍查询学生信息

	// ***********************快递信息*************************************//
	
	public void getAllExpressUnSignIn(); // 得到所有未签收的快递信息

	public void getAllExpressSignIn(); // 得到所有已签收的快递信息

	public void queryExpressById(String ExpressId); // 查询快递by快递Id

	// ***************************维修信息********************************************//
	/**
	 * 
	 * 
	 * @param dormId
	 * @return 
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException 
	 */
	public AllMaintanceRecord getAllMaintenanceRecord(String dormId) throws DataBaseException, QueryResultIsNullException; // 得到所有维修信息
	
	public void getFixedInfo();

	public void queryMaintenanceInfoByDormitoryId(String DormitoryId);
	
	/**
	 * 
	 * 
	 * 查询舍管所在楼栋宿舍信息
	 * @param DMId
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	public DormitoryBuildingInfo getDormitory(String DMId)
			throws DataBaseException, QueryResultIsNullException;

	/**
	 * 
	 * 
	 * 得到本栋楼宿舍信息
	 * @param string 
	 * @return 
	 * @throws DataBaseException 
	 * @throws QueryResultIsNullException 
	 */
	public AllDormitoryInfo getAllDormitoryInfo(String DMid) throws DataBaseException, QueryResultIsNullException;
	
	/**
	 * 
	 * 
	 * 得到所有快递信息
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException 
	 */
	public Vector<Object> getAllExpressInfo(String dmid) throws DataBaseException, QueryResultIsNullException;
	
	public boolean updateExpressTransceiver(int Record, String Data);
	
	public boolean insetRecord(String cmid, String stuId, String data, String mark);
	/**
	 * 
	 * 
	 * 查询所有晚归记录
	 * @return 
	 * @throws DataBaseException 
	 * @throws QueryResultIsNullException 
	 */
	public Vector<Object> getAllCurfewRecord(String DMID) throws DataBaseException, QueryResultIsNullException;
	
	public void queryDormitoryInfoById(String dormitoryId);

	public void getAllDormitoryPropertyInfo();

	public void queryDormitoryPropertyInfoById(String dormitoryId);

	
	/**
	 * 
	 * 
	 * 个人密码修改
	 * @param id
	 * @throws PasswordNotMatchException
	 * @throws DataBaseException
	 * @throws UpdateSuccessException
	 */
	public void PasswordUpdate(UpdatePasswordInfo updatepassWordForm)
			throws PasswordNotMatchException, DataBaseException,
			UpdateSuccessException;

	/**
	 * 
	 * 
	 * 初始化管理员的个人信息
	 * @param manager
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	public void initPersonalInfo(Manager manager) throws DataBaseException,
			QueryResultIsNullException;

	/**
	 * 
	 * 
	 * 更新个人信息
	 * @param updatePersonInfo
	 * @throws UpdateDataException
	 * @throws DataBaseException
	 */
	void UpdatePersonalInfo(UpdatePersonInfo updatePersonInfo)
			throws UpdateDataException, DataBaseException;

	public boolean insertStu(String dormId2, String roomId, String bedId, String stuId, String data, String rmark) throws DataBaseException, QueryResultIsNullException;

	public boolean insertCurfew(String stuId, String stuName, String dormId2, String roomId, String bedId,
			String backTime, int backSum, String dMId, String reason)throws DataBaseException, QueryResultIsNullException, SQLException ;

	public boolean insertLCRecord(String stuId, String stuName, String data,String lC, String mark)throws DataBaseException, QueryResultIsNullException;

	public boolean outStu(String stuId, String rmark) throws DataBaseException;

	public String getStuName(String stuId) throws DataBaseException;
	
}

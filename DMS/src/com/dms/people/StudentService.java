package com.dms.people;

import java.util.Vector;

import com.dms.information.UpdatePasswordInfo;
import com.dms.information.UpdatePersonInfo;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.PasswordNotMatchException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.myexceptioin.UpdateDataException;
import com.dms.myexceptioin.UpdateSuccessException;

/**
 * 
 * 定义StudentService学生服务接口
 *
 */
public interface StudentService {
	
	public void initPersonalInfo(Student student) throws DataBaseException,
	QueryResultIsNullException;
	
	/**
	 * 个人密码修改
	 * @param updatepassWordForm
	 * @throws PasswordNotMatchException
	 * @throws DataBaseException 
	 * @throws UpdateSuccessException 
	 */
	public void PasswordUpdate(UpdatePasswordInfo updatepassWordForm) throws PasswordNotMatchException, DataBaseException, UpdateSuccessException;

	
	public void UpdatePersonalInfo(UpdatePersonInfo updatePersonInfo) throws UpdateDataException, DataBaseException;

	/**
	 * 查询学生个人详细的快递信息
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	public Vector<Object> getPersonalExpressInfo(String StuId) throws DataBaseException,QueryResultIsNullException;
	
	/**
	 *快递信息消息弹出宽显示的快递信息 
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	public Vector<Object> showPersonalExpressInfo(String StuId) throws DataBaseException,QueryResultIsNullException;

	/**
	 * 查询学生个人夜归记录
	 * @param StuId
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	public Vector<Object> getPersonalCurfewInfo(String StuId)throws DataBaseException,QueryResultIsNullException;

	/**
	 * 查询自己所在宿舍报修信息
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	public Vector<Object> getMaintainceRecord(String RoomId)throws DataBaseException,QueryResultIsNullException;

	public Vector<Object> getItem() throws DataBaseException;

	public boolean insertItemMa(String id,  String dormId2,	String roomId2, String bedNum2, String itemId, String rmark) throws DataBaseException;

	public boolean updateExpressTransceiver(int record, String data) throws DataBaseException;

}



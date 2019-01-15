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
 *学生信息类 
 * 
 *
 */
public  class Student extends Person {
	
	static StudentService studentService;
	
	static {
		studentService = new StudentServiceImpl();
	}	
	private String Home;//籍贯	
	private String grade; //年级	
	private String College;//学院	
	private String major;  //专业	
	private String classNumber;   //班级
	private String StuETime; //进入大学时间
	private String DormID; //宿舍栋数
	private String RoomID; //房号	
	private String BedNum; //床号
	public Student(String id) throws DataBaseException, QueryResultIsNullException{
		this.setId(id);
		initPersonalInfo();
	}
	
	public String getHome() {
		return Home;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public String getCollege() {
		return College;
	}
	
	public String getMajor() {
		return major;
	}
	
	public String getClassNumber() {
		return classNumber;
	}

	public String getStuETime() {
		return StuETime;
	}
	
	public String getDormID() {
		return DormID;
	}

	public String getRoomID() {
		return RoomID;
	}
	
	public String getBedNum(){
		return BedNum;
	}

	@Override
	public void Login() throws PasswordNotMatchException {
		// TODO 自动生成的方法存根	
		
	}
	@Override
	public void PasswordUpdate(UpdatePasswordInfo updatePasswordInfo) throws PasswordNotMatchException, DataBaseException, UpdateSuccessException {
		updatePasswordInfo.setOwnerId(this.getId());
		studentService.PasswordUpdate(updatePasswordInfo);
	}
	@Override
	public Object PersonalInfo() {
		return null;
		// TODO 自动生成的方法存根
	}
	
	public void setHome (String Home) {
		this.Home = Home;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public void setCollege(String College) {
		this.College = College;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
		
	public void setClassNumber(String class1) {
		classNumber = class1;
	}
	
	public void setStuETime(String set) {
		this.StuETime = set;
	}

	public void setDormID(String DormID) {
		this.DormID = DormID;
	}
	
	public void setRoomID(String RoomID) {
		this.RoomID = RoomID;
	}
	
	public void setBedNum(String BedNum){
		this.BedNum = BedNum;
	}

	
	/**
	 * 得到所有快递信息
	 * @return
	 * @throws QueryResultIsNullException 
	 * @throws DataBaseException 
	 */
	public Vector<Object> getPersonalExpressInfo() throws DataBaseException, QueryResultIsNullException {
			Vector<Object> vector = studentService.getPersonalExpressInfo(getId());
		return vector;
		
	}

	/**
	 * 弹出快递信息
	 * @return
	 * @throws QueryResultIsNullException 
	 * @throws DataBaseException 
	 */
	public Vector<Object> showPersonalExpressInfo() throws DataBaseException, QueryResultIsNullException {
			Vector<Object> vector = studentService.showPersonalExpressInfo(getId());
		return vector;
		
	}
	/**
	 * 拿到学生所在宿舍报修信息
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	public Vector<Object> getMaintenanceRecord() throws DataBaseException, QueryResultIsNullException {
		Vector<Object> vector = studentService.getMaintainceRecord(getRoomID());
	return vector;
	
}	
	@Override
	public void UpdatePersonalInfo(UpdatePersonInfo updatePersonInfo) throws UpdateDataException, DataBaseException {
		updatePersonInfo.setOwnerId(this.getId());
		studentService.UpdatePersonalInfo(updatePersonInfo);	
		try {
			initPersonalInfo();
		} catch (QueryResultIsNullException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	
	}
	
	
	private void initPersonalInfo() throws DataBaseException, QueryResultIsNullException{
		studentService.initPersonalInfo(this);
	}

	public Vector<Object> getPersonalCurfewInfo() throws DataBaseException, QueryResultIsNullException {
		return  studentService.getPersonalCurfewInfo(getId());
		
	}
	public Vector<Object> getItem() throws DataBaseException {
		// TODO Auto-generated method stub
		return studentService.getItem();
	}

	public boolean insertItemMa(String id,  String dormId2,	String roomId2, String bedNum2, String itemId, String rmark) throws DataBaseException {
		// TODO Auto-generated method stub
		return studentService.insertItemMa(id, dormId2, roomId2, bedNum2, itemId, rmark);
	}

	public boolean updateExpressTransceiver(int record, String data) throws DataBaseException {
		// TODO Auto-generated method stub
		return studentService.updateExpressTransceiver(record, data);
	}
}

package com.dms.people;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.dms.information.UpdatePasswordInfo;
import com.dms.information.UpdatePersonInfo;
import com.dms.mydatabase.DB;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.PasswordNotMatchException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.myexceptioin.UpdateDataException;
import com.dms.myexceptioin.UpdateSuccessException;
import com.dms.queryresultset.AllDormitoryInfo;
import com.dms.queryresultset.AllMaintanceRecord;
import com.dms.queryresultset.DormitoryBuildingInfo;
import com.dms.queryresultset.StudentInfo;

/*管理员服务接口的实现类*/
public class ManagerServiceImpl implements ManagerService {
	private Connection connection ;
	
	public ManagerServiceImpl(){
		 try {
			connection = DB.getConnection();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取所有学生的信息
	 * @param dmid 
	 * @return
	 * @throws QueryResultIsNullException
	 * @throws DataBaseException
	 */
	@Override
	public Vector<Object> getAllStudentInfo(String dmid)
			throws QueryResultIsNullException, DataBaseException {
		Statement stmt = DB.CreateStatement();
		String sql = "select * from View_Student_College_StuDormRoom where DormId = '" + dmid + "'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			if(rs!=null&&!rs.next()){
				throw new QueryResultIsNullException();
			}
			Vector<Object> vectors = new Vector<Object>();
			Vector<Object> vector = new Vector<Object>();
			for(int i=1;i<=9;i++){
				vector.add(rs.getObject(i));
			}
			vectors.add(vector);
			while(rs.next()){
				vector = new Vector<Object>();
				for(int i=1;i<=9;i++){
					vector.add(rs.getObject(i));
				}
				vectors.add(vector);
			}
			return vectors;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		
	}
	/**
	 * 
	 * 查询学生信息
	 * @param studentId
	 * @return
	 * @throws QueryResultIsNullException
	 * @throws DataBaseException
	 */
	@Override
	public StudentInfo queryStudentInfoById(String studentId)
			throws QueryResultIsNullException, DataBaseException {
		StudentInfo studentInfo = new StudentInfo();
		studentInfo.getStudentInfo();
		return studentInfo;

	}

	@Override
	public void queryStudentInfoByName(String studentName) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void queryStudentInfoByDormitoryId(String dormitoryId) {
		// TODO 自动生成的方法存根

	}
	// ***********************快递信息*************************************/
	/**
	 * 
	 * 得到所有未签收的快递信息
	 * @param studentId
	 * @return
	 * @throws QueryResultIsNullException
	 * @throws DataBaseException
	 */
	@Override
	public Vector<Object> getAllExpressInfo(String dmid) throws DataBaseException, QueryResultIsNullException {
		Statement stmt = DB.CreateStatement();
		String sql ="select * from ExpressTransceiver where DormId = '" + dmid + "'";
		ResultSet rs = null;
		Vector<Object> vector = null;
		Vector<Object> vectors = new Vector<Object>();
		try {
			rs = stmt.executeQuery(sql);
			if(rs!=null&&!rs.next()){
				throw new QueryResultIsNullException();
			}
			vector = new Vector<Object>();
			for(int i = 1 ;i<=11;i++){
					 
				vector.add(rs.getString(i));
				
			}
			vectors.add(vector);
			while(rs.next()){
				vector = new Vector<Object>();
				for(int i = 1 ;i<=11;i++){
					 
				vector.add(rs.getString(i));
				
			}
				vectors.add(vector);
			}
			} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		
		return vectors;

	}
	/**
	 * 
	 * 修改快递传递的记录与日期信息
	 * @param studentId
	 * @return
	 * @throws QueryResultIsNullException
	 * @throws DataBaseException
	 */
	public boolean updateExpressTransceiver(int Record, String Data){

		try {
			CallableStatement cst = connection.prepareCall("{call proc_ExpressTransceiver_Get(?,?)}");
			cst.setInt(1, Record);
			cst.setString(2, Data);
			int r = cst.executeUpdate();
			cst.close();
			if(r >0 ){
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	//插入记录
	public boolean insetRecord(String cmid, String stuId, String data, String mark){

		try {
			CallableStatement cst = connection.prepareCall("{call proc_ExpressTransceiver_Come(?,?,?,?)}");
			cst.setString(1, cmid);
			cst.setString(2, stuId);
			cst.setString(3, data);
			cst.setString(4, mark);
			int r = cst.executeUpdate();
			cst.close();
			if(r >0 ){
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	//查询所有未签收的快递信息
	@Override
	public void getAllExpressUnSignIn() {
		// TODO 自动生成的方法存根

	}
	//查询所有已签收的快递信息
	@Override
	public void getAllExpressSignIn() {
		// TODO 自动生成的方法存根

	}
	//通过快递Id查询快递
	@Override
	public void queryExpressById(String ExpressId) {
		// TODO 自动生成的方法存根

	}

	// ***************************维修信息********************************************//

	/**
	 * 
	 * @param dormId
	 * @return 
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException 
	 */
	// 得到所有维修信息
	@Override
	public AllMaintanceRecord getAllMaintenanceRecord(String dormId) throws DataBaseException, QueryResultIsNullException {
		AllMaintanceRecord allMaintanceRecord = new AllMaintanceRecord();
		allMaintanceRecord.getAllMaintanceRecord(dormId);
		return allMaintanceRecord;
	}

	@Override
	public void getFixedInfo() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void queryMaintenanceInfoByDormitoryId(String DormitoryId) {
		// TODO 自动生成的方法存根

	}
	
	@Override
	public AllDormitoryInfo getAllDormitoryInfo(String DormId)
			throws DataBaseException, QueryResultIsNullException {
		AllDormitoryInfo allDormitoryInfo = new AllDormitoryInfo();
		allDormitoryInfo.getAllDormitoryInfo(DormId);
		return allDormitoryInfo;

	}

	//通过宿舍id查询宿舍
	@Override
	public void queryDormitoryInfoById(String dormitoryId) {
		// TODO 自动生成的方法存根

	}
	/**
	 * 个人密码修改
	 * 
	 * @param id
	 * @throws PasswordNotMatchException
	 * @throws DataBaseException
	 * @throws UpdateSuccessException
	 */
	@Override
	public void PasswordUpdate(UpdatePasswordInfo updatePassWordForm)
			throws PasswordNotMatchException, DataBaseException,
			UpdateSuccessException {
		String oldPassword = updatePassWordForm.getoldPwd();
		Statement stmt = DB.CreateStatement();
		String sql = "select  * from DMSystem..DMAccountPassword where DMId = '"
				+ updatePassWordForm.getOwnerId() + "' and DMPassword ='"
				+ oldPassword + "'";
//		System.out.println(sql);
		try {
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				throw new PasswordNotMatchException();
			} else {
				String newPassword = updatePassWordForm.getnewPwd();
				String ownerId = updatePassWordForm.getOwnerId();
				String upsql = "update DMSystem..DMAccountPassword set DMPassword='"
						+ newPassword + "' where DMId= '" + ownerId + "'";
//				System.out.println(upsql); //test
				stmt.executeUpdate(upsql);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		throw new UpdateSuccessException();
	}

	
	@Override
	public void getAllDormitoryPropertyInfo() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void queryDormitoryPropertyInfoById(String dormitoryId) {
		// TODO 自动生成的方法存根

	}
	/**
	 * 初始化管理员的个人信息
	 * 
	 * @param manager
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	@Override
	public void initPersonalInfo(Manager manager) throws DataBaseException,
			QueryResultIsNullException {
		Statement stmt = DB.CreateStatement();
		System.out.println("*******************************欢迎登录宿舍管理系统********************************");
//		System.out.println(manager.getId());
		String sql = "select * from DMSystem..DormManage where DormManage.DMId='"+manager.getId()+"'";
//		System.out.println(sql);
		try {
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			if (!rs.next()) {
//				throw new QueryResultIsNullException();
			}
			// rs.next();
			manager.setName(rs.getString("DMName"));
			manager.setSex(rs.getString("DMsex"));
			manager.setDormId(rs.getString("DormId"));
			manager.setPhone(rs.getString("DMPhone"));
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new DataBaseException();
		}

	}
	/**
	 * 更新个人信息
	 * 
	 * @param updatePersonInfo
	 * @throws UpdateDataException
	 * @throws DataBaseException
	 */
	@Override
	public void UpdatePersonalInfo(UpdatePersonInfo updatePersonInfo)
			throws UpdateDataException, DataBaseException {
Connection connection = DB.getConnection();
		String sql = "update DormManage set DMPhone=? where DMId='"
				+ updatePersonInfo.getOwnerId()+"'";
	//	System.out.println(sql);
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, updatePersonInfo.getPhone());
			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UpdateDataException();

		}
	}
	/**
	 * 查询舍管所在楼栋宿舍信息
	 * 
	 * @param DMId
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	@Override
	public DormitoryBuildingInfo getDormitory(String DMID)
			throws DataBaseException, QueryResultIsNullException {
		Statement stmt = DB.CreateStatement();
		String sql = "select * from DMSystem..The_Info_Of_Dormitory_building where Dormid=(select DormId from DMSystem..DormManage where DMId='"
				+ DMID+"')";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs != null && !rs.next()) {
				throw new QueryResultIsNullException();
			}
			DormitoryBuildingInfo dormitoryBuildingInfo = new DormitoryBuildingInfo();
			dormitoryBuildingInfo.setDormId(rs.getString("DormID"));
			dormitoryBuildingInfo.setStuSex(rs.getString("StuSex"));
			dormitoryBuildingInfo.setCollege(rs.getString("College"));
			dormitoryBuildingInfo.setEtime(rs.getString("Etime"));
			dormitoryBuildingInfo.setLiveNum(rs.getString("LiveNum"));
			dormitoryBuildingInfo.setConNum(rs.getString("ConNum"));
			return dormitoryBuildingInfo;

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return null;

	}
	/**
	 * 查询所有夜归记录
	 * @return 
	 * @throws DataBaseException 
	 * @throws QueryResultIsNullException 
	 */
	@Override
	public Vector<Object> getAllCurfewRecord(String DMID) throws DataBaseException, QueryResultIsNullException {
		Statement stmt = DB.CreateStatement();
		String sql = "select * from DMSystem..Night_Back_Info where DormID='"+DMID +"' order by StuId asc";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			if(rs!=null&&!rs.next()){
				throw new QueryResultIsNullException();
			}
			Vector<Object> vectors = new Vector<Object>();
			Vector<Object> vector = new Vector<Object>();
			for(int i=1;i<=9;i++){
				vector.add(rs.getString(i));
			}
			vectors.add(vector);
			while(rs.next()){
				vector = new Vector<Object>();
				for(int i=1;i<=9;i++){
					vector.add(rs.getString(i));
				}
				vectors.add(vector);
			}
			return vectors;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		
	}
	

	public boolean insertLCRecord(String stuId, String stuName, String data,String lC, String mark)throws DataBaseException, QueryResultIsNullException{
		
		
		try {
			CallableStatement cst = connection.prepareCall("{call proc_LeaveComeStu(?,?,?,?,?)}");
			cst.setString(1, stuId);
			cst.setString(2, stuName);
			cst.setString(3, data);
			cst.setString(4, lC);
			cst.setString(5, mark);	
			
			int r = cst.executeUpdate();
			cst.close();
			if(r >0 ){
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}

	
	public boolean insertCurfew(String stuId, String stuName, String dormId2, String roomId, String bedId,
			String backTime, int backSum, String dMId, String reason) throws DataBaseException, QueryResultIsNullException, SQLException {

		PreparedStatement stmt1=null;
		String sql1="select * from DMSystem..Night_Back_Info where StuId='"+stuId+"' and StuName='"+stuName+"' and DormId='"+dormId2+"' and RoomId='"+roomId+"' and BedId='"+bedId+"' and BackTime='"+backTime+"' and BackSum="+backSum+" and DMId='"+dMId+"' and Reason='"+reason+"'";
		stmt1=DB.CreatePrepare(sql1);
//		System.out.println(sql1);
//		System.out.println(stmt1.executeQuery());
		if(stmt1.executeQuery() == null) {
			PreparedStatement stmt2 = null;
			String sql2=null;
			boolean rs2=false;
			sql2="INSERT INTO DMSystem..Night_Back_Info(StuId,StuName,DormId,RoomId,BedId,BackTime,BackSum,DMId,Reason) VALUES('"+stuId+"','"+stuName+"','"+dormId2+"','"+roomId+"','"+bedId+"','"+backTime+"',"+backSum+",'"+dMId+"','"+reason+"')";
			stmt2 = DB.CreatePrepare(sql2);
//			System.out.println(sql2);
			try {
				//boolean execute() throws SQLException在此 
				//PreparedStatement 对象中执行 SQL 语句，该语句可以是任何种类的 SQL 语句。
				//一些特别处理过的语句返回多个结果，execute 方法处理这些复杂的语句；executeQuery 和 executeUpdate 处理形式更简单的语句。
				//execute 方法返回一个 boolean 值，以指示第一个结果的形式。必须调用 getResultSet 或 getUpdateCount 方法来检索结果，
				//并且必须调用 getMoreResults 移动到任何后面的结果返回：如果第一个结果是 ResultSet 对象，则返回 true；如果第一个结果是更新计数或者没有结果，
				//则返回 false，意思就是如果是查询的话返回true，如果是更新或插入的话就返回false了；
				//execute()返回的是一个boolean值,代表两种不同的操作,getResultSet()返回的是结果集,而getUpdateCount()返回的是更新的记数。
				stmt2.execute();
				if(stmt2.executeUpdate()>0)
					rs2=true;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
//				System.out.println(e1.getMessage());
//				System.out.println(e1.toString());
				e1.printStackTrace();
			}
			return rs2;
			}
		else 
			return false;
		}
	
	public boolean outStu(String stuId, String rmark) throws DataBaseException{
		
		Statement stmt;
		stmt = DB.CreateStatement();
		String sql = "select StuId from DMSystem..StuAccountId where StuId='" +stuId + "'";
		ResultSet rs=null;
		try {
			rs = stmt.executeQuery(sql);	
			if(rs!=null && !rs.next()){
				return false;
			}
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			CallableStatement cst = connection.prepareCall("{call DMSystem..proc_StuInOut_SDM_out(?,?)}");
			cst.setString(1, stuId);
			cst.setString(2, rmark);
			int r = cst.executeUpdate();
			cst.close();
			if(r >0 ){
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public String getStuName(String stuId) throws DataBaseException{

		String Name = null;		
		Statement stmt;
		stmt = DB.CreateStatement();
		String sql = "select StuName from DMSystem..Student where StuId='" +stuId + "'";
		try {
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs!=null && !rs.next()){
			//System.out.print("学生姓名查询失败");			
				return null;
			}		
			Name = rs.getString(1);		
		//	System.out.print("学生姓名查询成功");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return Name;
		
	}
	
	public boolean insertStu(String dormId2, String roomId, String bedId, String stuId, String data, String rmark) throws DataBaseException, QueryResultIsNullException{
		Statement stmt;
		stmt = DB.CreateStatement();
		String sql = "select StuId from DMSystem..StuAccountId where StuId='" +stuId + "'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs!=null && rs.next()){
				return false;
			}
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			CallableStatement cst = connection.prepareCall("{call DMSystem..proc_StuInOut_SDM_in(?,?,?,?,?,?)}");
			cst.setString(1, stuId);
			cst.setString(2, dormId2);
			cst.setString(3, roomId);
			cst.setString(4, bedId);
			cst.setString(5, data);
			cst.setString(6, rmark);
			int r = cst.executeUpdate();
			cst.close();
			if(r >0 ){
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
}

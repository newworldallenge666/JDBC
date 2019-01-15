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

/**
 * 学生信息服务实现类
 * 
 * 
 */
public class StudentServiceImpl implements StudentService {

	@Override
	public void initPersonalInfo(Student student) throws DataBaseException,
			QueryResultIsNullException {
		Statement stmt = DB.CreateStatement();
		System.out.println("*******************************欢迎登录宿舍管理系统********************************");
		String sql = "select * from DMSystem..View_Student_College_StuDormRoom where StuId='"
				+ student.getId()+"'";	
		try {
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			if (!rs.next()) {
//				throw new QueryResultIsNullException();
			}
			student.setName(rs.getString("StuName"));
			student.setSex(rs.getString("StuSex"));
			student.setHome(rs.getString("StuHome"));
			student.setPhone(rs.getString("StuPhone"));
			student.setCollege(rs.getString("College"));
			student.setMajor(rs.getString("Major"));
			student.setClassNumber(rs.getString("StuClass"));
			student.setStuETime(rs.getString("StuETime"));
			student.setDormID(rs.getString("DormId"));
			student.setRoomID(rs.getString("RoomId"));

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块

			e.printStackTrace();
			throw new DataBaseException();
		}

	}

	/**
	 * 个人密码修改
	 * @param updatepassWordForm
	 * @throws PasswordNotMatchException
	 * @throws DataBaseException 
	 * @throws UpdateSuccessException 
	 */
	@Override
	public void PasswordUpdate(UpdatePasswordInfo updatePasswordInfo)
			throws PasswordNotMatchException, DataBaseException,
			UpdateSuccessException {
		String oldPassword = updatePasswordInfo.getoldPwd();
		Statement stmt = DB.CreateStatement();
		String sql = "select  * from DMSystem..StuAccountPassword where StuId='"
				+ updatePasswordInfo.getOwnerId() + "' and StuPassword ='"
				+ oldPassword + "'";
		//System.out.println(sql);
		try {
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				throw new PasswordNotMatchException();
			} else {
				String newPassword = updatePasswordInfo.getnewPwd();
				String ownerId = updatePasswordInfo.getOwnerId();
				String upsql = "update DMSystem..StuAccountPassword set StuPassword='"
						+ newPassword + "' where StuId='" + ownerId + "'";
//				System.out.println(upsql);
				stmt.executeUpdate(upsql);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		throw new UpdateSuccessException();

	}
	
	@Override
	public void UpdatePersonalInfo(UpdatePersonInfo updatePersonInfo)
			throws UpdateDataException, DataBaseException {
		Connection connection = DB.getConnection();
		String sql = "update DMSystem..Student set Stuphone=?,StuHome=? where StuId='"
				+ updatePersonInfo.getOwnerId()+"'";
		//System.out.println(sql);
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, updatePersonInfo.getPhone());
			pstmt.setString(2, updatePersonInfo.getHome());
			pstmt.execute();
		} catch (SQLException e) {

			e.printStackTrace();
			throw new UpdateDataException();

		}
	}
	/**
	 * 通过学生id查询学生个人详细的快递信息
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	@Override
	public Vector<Object> getPersonalExpressInfo(String StuId) throws DataBaseException,
			QueryResultIsNullException {
		Statement stmt = DB.CreateStatement();
		String sql = "select * from DMSystem..NewExpressInfo where StuId='"+StuId+"'";
		ResultSet rs = null;
		Vector<Object> vector = null;
		Vector<Object> vectors = new Vector<Object>();
		try {
			rs = stmt.executeQuery(sql);
			if (rs != null && !rs.next()) {
				throw new QueryResultIsNullException();
			}
			vector = new Vector<Object>();
			for (int i = 1; i <= 11; i++) {
				vector.add(rs.getString(i));
			}
			vectors.add(vector);
			while (rs.next()) {
				vector = new Vector<Object>();
				for (int i = 1; i <= 11; i++) {
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
	 *快递信息消息弹出宽显示的快递信息 
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 * 修改新快递信息表后就会弹出信息提示框
	 */
	@Override
	public Vector<Object> showPersonalExpressInfo(String StuId) throws DataBaseException,
			QueryResultIsNullException {
		Statement stmt = DB.CreateStatement();
		//数据项分别为快递记录号，快递公司，到件日期，备注
		String sql = "select RecordId,ExpressCompany,ReachDate,Comments from DMSystem..NewExpressInfo where StuId='"+StuId+"'";
		ResultSet rs = null;
		Vector<Object> vector = null;
		Vector<Object> vectors = new Vector<Object>();
		try {
//			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs != null && !rs.next()) {
				throw new QueryResultIsNullException();
			}
			vector = new Vector<Object>();
			//获取表的对应列的值并加入容器中
			vector.add(rs.getString("RecordId"));
			vector.add(rs.getString("ExpressCompany"));
			vector.add(rs.getString("ReachDate"));
			vector.add(rs.getString("Comments"));
			vectors.add(vector);
			while (rs.next()) {
				vector = new Vector<Object>();
				vector.add(rs.getString("RecordId"));
				vector.add(rs.getString("ExpressCompany"));
				vector.add(rs.getString("ReachDate"));
				vector.add(rs.getString("Comments"));
				vectors.add(vector);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		return vectors;

	}
	/**
	 * 查询学生个人夜归记录
	 * @param StuId
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	@Override
	public Vector<Object> getPersonalCurfewInfo(String StuId)
			throws DataBaseException, QueryResultIsNullException {
		Statement stmt = DB.CreateStatement();
		String sql = "select RecordId,BackTime,Reason,DMId from DMSystem..Night_Back_Info where StuId='"+StuId+"'";
		ResultSet rs = null;
		Vector<Object> vector = null;
		Vector<Object> vectors = new Vector<Object>();
		try {
//			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs != null && !rs.next()) {
				throw new QueryResultIsNullException();
			}
			vector = new Vector<Object>();
			for(int i=1;i<=4;i++){
				vector.add(rs.getString(i));
			}
			vectors.add(vector);
			while (rs.next()) {
				vector = new Vector<Object>();
				for(int i=1;i<=4;i++){
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
	 * 查询自己所在宿舍报修信息
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	@Override
	public Vector<Object> getMaintainceRecord(String RoomId) throws DataBaseException,
			QueryResultIsNullException {
		Statement stmt = DB.CreateStatement();
		String sql = "select * from RepairInfo_Of_MyDormitory where RoomId='"+RoomId+"'";
		ResultSet rs = null;
		Vector<Object> vector = null;
		Vector<Object> vectors = new Vector<Object>();
		try {
			//System.out.println(sql); //test
			rs = stmt.executeQuery(sql);
			if (rs != null && !rs.next()) {
				throw new QueryResultIsNullException();
			}
			vector = new Vector<Object>();
			for(int i=1;i<=11;i++){
				vector.add(rs.getString(i));
			}

			vectors.add(vector);
			while (rs.next()) {
				vector = new Vector<Object>();
				for(int i=1;i<=11;i++){
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

	@Override
	public Vector<Object> getItem() throws DataBaseException {
		// TODO Auto-generated method stub	
		Statement stmt = DB.CreateStatement();
		String sql = "select ItName from ItemInfo";
		ResultSet rs = null;	
		Vector<Object> vector = null;	
		Vector<Object> vectors = new Vector<Object>();	
		try {
		//System.out.println(sql);		
			rs = stmt.executeQuery(sql);
			if (rs != null && !rs.next()) {
				throw new QueryResultIsNullException();
			}		
			vector = new Vector<Object>();			
			vector.add(rs.getString(1));		
			vectors.add(vector);
			while (rs.next()) {
				vector = new Vector<Object>();
				vector.add(rs.getString(1));		
				vectors.add(vector);
			}		
		} catch (SQLException | QueryResultIsNullException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}

		return vectors;
		
	}
	@Override
	public boolean updateExpressTransceiver(int record, String data) throws DataBaseException {

		try {
			Connection connection = DB.getConnection();
			CallableStatement cst = connection.prepareCall("{call proc_ExpressTransceiver_Get(?,?)}");
			cst.setInt(1, record);
			cst.setString(2, data);
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

	@Override
	public boolean insertItemMa(String id, String dormId2, String roomId2, String bedNum2, String itemId, String rmark)
			throws DataBaseException {
		// TODO Auto-generated method stub
		return false;
	}

}

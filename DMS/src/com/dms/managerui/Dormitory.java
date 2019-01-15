package com.dms.managerui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.people.Manager;
import com.dms.queryresultset.DormitoryBuildingInfo;

public class Dormitory extends JPanel {
	/**
	 * 宿管个人信息板块
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtDormID;
	private JTextField txtStuSex;
	private JTextField txtCollege;
	private JTextField txtEtime;
	private JTextField txtConNum;
	private JTextField txtLiveNum;
	private JLabel labImage;
	private ImageIcon image;
	private Manager manager;

	public Dormitory(Manager m) {
		this.manager = m;
		setSize(800,600);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("宿舍楼信息");
        lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 25));
        lblNewLabel.setBounds(195, 101, 125, 40);
        add(lblNewLabel);
        
        JLabel labDormID = new JLabel("宿舍楼ID");
        labDormID.setFont(new Font("宋体", Font.PLAIN, 15));
        labDormID.setBounds(195, 164, 72, 30);
        add(labDormID);
        
        JLabel labStuSex = new JLabel("学生性别");
        labStuSex.setFont(new Font("宋体", Font.PLAIN, 15));
        labStuSex.setBounds(195, 206, 72, 30);
        add(labStuSex);
        
        JLabel labCollege = new JLabel("学院");
        labCollege.setFont(new Font("宋体", Font.PLAIN, 15));
        labCollege.setBounds(195, 251, 54, 30);
        add(labCollege);
        
        JLabel labEtime = new JLabel("入学时间");
        labEtime.setFont(new Font("宋体", Font.PLAIN, 15));
        labEtime.setBounds(195, 296, 72, 21);
        add(labEtime);
        
        JLabel labLiveNum = new JLabel("实住人数");
        labLiveNum.setFont(new Font("宋体", Font.PLAIN, 15));
        labLiveNum.setBounds(195, 344, 72, 30);
        add(labLiveNum);
        
        txtDormID = new JTextField();
        txtDormID.setBounds(292, 164, 288, 30);
        add(txtDormID);
        txtDormID.setColumns(10);
        
        txtStuSex = new JTextField();
        txtStuSex.setColumns(10);
        txtStuSex.setBounds(292, 206, 288, 30);
        add(txtStuSex);
        
        txtCollege = new JTextField();
        txtCollege.setColumns(10);
        txtCollege.setBounds(292, 251, 288, 30);
        add(txtCollege);
        
        txtEtime = new JTextField();
        txtEtime.setColumns(10);
        txtEtime.setBounds(292, 296, 288, 30);
        add(txtEtime);
        
        txtConNum = new JTextField();
        txtConNum.setColumns(10);
        txtConNum.setBounds(292, 387, 288, 30);
        add(txtConNum);
        
        JLabel labConNum = new JLabel("容纳人数");
        labConNum.setFont(new Font("宋体", Font.PLAIN, 15));
        labConNum.setBounds(195, 387, 72, 30);
        add(labConNum);
        
        txtLiveNum = new JTextField();
        txtLiveNum.setColumns(10);
        txtLiveNum.setBounds(292, 344, 288, 30);
        add(txtLiveNum);
        
        image = new ImageIcon("src\\MyImage\\Background.jpg");
		labImage = new JLabel(image);
		labImage.setBounds(0, 0, 800, 600);
		add(labImage);
		this.setOpaque(false);
	}
	public void  showInfo() throws DataBaseException, QueryResultIsNullException{
		Editable(false);
		DormitoryBuildingInfo dormitoryBuildingInfo= manager.getDormitory();
		 txtDormID.setText(dormitoryBuildingInfo.getDormId());
		 txtCollege.setText(dormitoryBuildingInfo.getCollege());
		 txtEtime.setText(dormitoryBuildingInfo.getEtime());
		 txtConNum.setText(dormitoryBuildingInfo.getConNum());
		 txtLiveNum.setText(dormitoryBuildingInfo.getLiveNum());
		 txtStuSex.setText(dormitoryBuildingInfo.getStuSex());
	}
	
	private void Editable(boolean statue){
		  txtStuSex.setEditable(statue);
		  txtDormID.setEditable(statue);
		  txtCollege.setEditable(statue);
		  txtLiveNum.setEditable(statue);
		  txtConNum.setEditable(statue);
		  txtEtime.setEditable(statue);
	}
}

package com.dms.studentui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.people.Student;

import sun.swing.table.DefaultTableCellHeaderRenderer;

import java.awt.Font;
import java.util.Vector;

public class NightBackInfo extends JPanel {
	/**
	 * 学生晚归板块
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Student student;
	private JLabel labImage;
	private ImageIcon image;
	private DefaultTableModel defaultTableModel;
	private Vector<Object> headVector;
	
	public NightBackInfo(Student student) {
		// TODO Auto-generated constructor stub
		this.student = student;
		setSize(800,600);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 153, 558, 298);
		add(scrollPane);
		
		table = new JTable();
		defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnCount(4);
		headVector = new Vector<Object>();
		headVector.add("记录号");
		headVector.add("晚归时间");
		headVector.add("晚归原因");
		headVector.add("值班宿管");
		defaultTableModel.setDataVector(null,headVector);
		table.setModel(defaultTableModel);
		//设置表数据居中显示		 
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cr); 
		//设置表头居中显示
		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		table.getTableHeader().setDefaultRenderer(hr);	
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel = new JLabel("我的晚归记录");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 25));
		lblNewLabel.setBounds(109, 79, 168, 39);
		add(lblNewLabel);
		
		image = new ImageIcon("src\\MyImage\\Background.jpg");
		labImage = new JLabel(image);
		labImage.setBounds(0, 0, 800, 600);
		add(labImage);
		this.setOpaque(false);
	}
	
	public void showInfo() throws DataBaseException, QueryResultIsNullException{
		defaultTableModel.setDataVector(student.getPersonalCurfewInfo(), headVector);
	}
	
}

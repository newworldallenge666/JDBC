package com.dms.studentui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.people.Student;

import sun.swing.table.DefaultTableCellHeaderRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;


public class ExpressInfo extends JPanel {
	/**
	 *快递信息查询板块 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableSKuaidi;
	private JTextField textField;
	private JLabel labImage;
	private ImageIcon image;
	private Student student;
	private DefaultTableModel defaultTableModel;
	private Vector<Object> headVector;
	private JFrame jframe;
	private JRadioButton radioButton;

	private TableRowSorter<TableModel> sorter;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_1;
	
	public ExpressInfo(JFrame jf, Student student) {
		this.student = student;
		this.jframe = jf;
		setSize(800,600);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 140, 670, 288);
		add(scrollPane);
		
		
		tableSKuaidi = new JTable();
		defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnCount(15);
		defaultTableModel.setRowCount(6);
		defaultTableModel.setValueAt(123, 1, 1);
		headVector = new Vector<Object>();
		headVector.add("记录号");
		headVector.add("公司名");
		headVector.add("宿舍楼");
		headVector.add("房间");
		headVector.add("学号");
		headVector.add("学生姓名");
		headVector.add("联系电话");
		headVector.add( "是否领取"); 
		headVector.add("到件日期");
		headVector.add("领取日期");
		headVector.add("备注");
		defaultTableModel.setDataVector(null,headVector);
		tableSKuaidi.setModel(defaultTableModel);
		scrollPane.setViewportView(tableSKuaidi);
		//设置表数据居中显示		 
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		tableSKuaidi.setDefaultRenderer(Object.class, cr); 
		//设置表头居中显示
		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		tableSKuaidi.getTableHeader().setDefaultRenderer(hr);

		tableSKuaidi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
		tableSKuaidi.setFont(new Font("宋体", Font.PLAIN, 15));
		
		sorter = new TableRowSorter<TableModel>(tableSKuaidi.getModel());
		tableSKuaidi.setRowSorter(sorter);	
		
		JLabel label = new JLabel("快递信息");
		label.setFont(new Font("微软雅黑", Font.BOLD, 25));
		label.setBounds(99, 50, 107, 40);
		add(label);
		
		radioButton = new JRadioButton("只显示未领取快递");
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = "否";
				if(radioButton.isSelected()==false){
					sorter.setRowFilter(null);
				}
				else{
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}
				
			}
		});
		radioButton.setOpaque(false);
		radioButton.setBounds(587, 456, 121, 23);
		add(radioButton);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 11));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = textField.getText();
				if(text.trim().length()==0)
					sorter.setRowFilter(null);
				else
					sorter.setRowFilter(RowFilter.regexFilter(text));
				
			}
		});
		textField.setBounds(102, 480, 122, 30);
		add(textField);
		textField.setColumns(10);
		

		final JRadioButton rdbtnNewRadioButton = new JRadioButton("快递公司");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(235, 480, 73, 30);
		add(rdbtnNewRadioButton);
		
		final JRadioButton radioButton_1 = new JRadioButton("日期");
		buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(312, 480, 57, 30);
		add(radioButton_1);
		
		final JRadioButton radioButton_2 = new JRadioButton("记录号");
		buttonGroup.add(radioButton_2);
		radioButton_2.setBounds(368, 480, 61, 30);
		add(radioButton_2);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = textField.getText();
				if(text.trim().length()==0) {
					sorter.setRowFilter(null);
					JOptionPane.showMessageDialog(jframe, "请输入查询信息" , "系统信息", JOptionPane.WARNING_MESSAGE);
				}
				else if(!rdbtnNewRadioButton.isSelected()&&!radioButton_1.isSelected()&&!radioButton_2.isSelected()){
					JOptionPane.showMessageDialog(jframe, "请选择查询方式" , "系统信息", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(rdbtnNewRadioButton.isSelected() == true){
						sorter.setRowFilter(RowFilter.regexFilter(text, 1));
					}else if(radioButton_1.isSelected() == true){
						sorter.setRowFilter(RowFilter.regexFilter(text, 8));
					}else if(radioButton_2.isSelected()== true){
						sorter.setRowFilter(RowFilter.regexFilter(text, 0));
					}
				}
			}
		});
		button.setBounds(275, 515, 60, 30);
		add(button);
		
		image = new ImageIcon("src\\MyImage\\Background.jpg");
		labImage = new JLabel(image);
		labImage.setBounds(0, 0, 800, 600);
		add(labImage);
		this.setOpaque(false);
	}
	
	public void showInfo() throws DataBaseException, QueryResultIsNullException{
		defaultTableModel.setDataVector(student.getPersonalExpressInfo(), headVector);
	}
	
	public boolean updateRecord() throws DataBaseException{
		int Record;
		String Data = "";	
		Record = Integer.parseInt( textField.getText() );	
		Data = textField_1.getText();	
		return student.updateExpressTransceiver(Record, Data);
		
	}
}

package com.dms.studentui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.dms.mydatabase.DB;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.people.Student;

import sun.swing.table.DefaultTableCellHeaderRenderer;

public class RepairInfo extends JPanel {
	/**
	 * 报修信息板块
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableSMR;
	private Student student;
	private DefaultTableModel defaultTableModel;
	private Vector<Object> headVector;
	private JTextField textField_1;

	private JLabel labImage;
	private JRadioButton radioButton;

	private TableRowSorter<TableModel> sorter;
	
	private JButton button_2;
	private JButton add;
	private JButton confirmadd;
	private JButton delete;
	private int currentRows;
	private int currentColumns;
	@SuppressWarnings("rawtypes")
	private Vector newRow;
	@SuppressWarnings("unused")
	private JFrame jframe;
	protected String DormId;
	private String stuId;
	private String stuname;
	private String dormid;
	private String roomid;
	private String bedid;
	private String itname;
	private String repaireDate;
	private String isreplace;
	private String repaireMan;
	private String comment;
	private int recordId;
	
	@SuppressWarnings("static-access")
	public RepairInfo(JFrame jf, Student stu) {
		this.student = stu;
		this.jframe = jf;
		setSize(800,600);
		setLayout(null);
		
		JLabel label = new JLabel("维修信息");
		label.setFont(new Font("微软雅黑", Font.BOLD, 25));
		label.setBounds(112, 53, 100, 34);
		add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 113, 673, 308);
		add(scrollPane);
		
		tableSMR = new JTable();
		scrollPane.setViewportView(tableSMR);
		defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnCount(11);
//		defaultTableModel.setValueAt(123, 1, 1);
		
		
		headVector = new Vector<Object>();
		headVector.add("记录号");
		headVector.add("学号");
		headVector.add("姓名");
		headVector.add("宿舍楼号");
		headVector.add("宿舍号");
		headVector.add("床号");
		headVector.add("物品名称");
		headVector.add("维修日期");
		headVector.add("是否维修");
		headVector.add("维修员");
		headVector.add("备注");
		defaultTableModel.setDataVector(null, headVector);
		tableSMR.setModel(defaultTableModel);
		 //设置表数据居中显示		 
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		tableSMR.setDefaultRenderer(Object.class, cr); 
		//设置表头居中显示
		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		tableSMR.getTableHeader().setDefaultRenderer(hr);
		
		currentRows=tableSMR.getRowCount(); 
		currentColumns=tableSMR.getColumnCount();
//		for(int i=0;i<currentRows;i++)
//			defaultTableModel.isCellEditable(i, 0); //设置不可编辑
			
		tableSMR.setAutoResizeMode(tableSMR.AUTO_RESIZE_OFF);  //自动调整表格列的宽度，不更改其他列的大小，而是更改整个表格的大小
		scrollPane.setViewportView(tableSMR);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		sorter = new TableRowSorter<TableModel>(tableSMR.getModel());
		
		tableSMR.setRowSorter(sorter);
		
		
		radioButton = new JRadioButton("只显示未维修");
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = "否";
				if(radioButton.isSelected()==false){
					sorter.setRowFilter(RowFilter.regexFilter(""));
				}
				else{
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}
			}
		});
		
		radioButton.setOpaque(false);
		
		radioButton.setBounds(430, 467, 100, 30);
		add(radioButton);
		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String text = textField_1.getText();
				if(text.trim().length()==0)
					sorter.setRowFilter(null);
				else
					sorter.setRowFilter(RowFilter.regexFilter(text));
		 		
			}
		});
		textField_1.setBounds(112, 468, 100, 30);
		add(textField_1);
		textField_1.setColumns(10);

		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = textField_1.getText();
				if(text.trim().length()==0)
					sorter.setRowFilter(null);
				else
					sorter.setRowFilter(RowFilter.regexFilter(text));
		 	
			}
		});
		button.setBounds(222, 467, 57, 30);
		add(button);
		
		add=new JButton("添加");
		add.addActionListener(new ActionListener() {
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stuId="";stuname=student.getName();dormid=student.getDormID();roomid=student.getRoomID();
				bedid="";itname="";repaireDate="";isreplace="";comment="";repaireMan="";
//				JOptionPane.showMessageDialog(null, "请输入完整数据", "提示", JOptionPane.WARNING_MESSAGE);
				add.setEnabled(false);
				confirmadd.setEnabled(true);
				currentColumns=tableSMR.getColumnCount();
				currentRows=tableSMR.getRowCount();
				newRow=new Vector();	
				for(int i=0;i<currentColumns;i++)   //小于当前列数
				{
					if(i==0) {
						recordId=Integer.parseInt(tableSMR.getValueAt(currentRows-1, 0).toString());
//						System.out.println(recordId);
						newRow.add(i, recordId+1);
					}
					else if(i==3||i==4) {
						 String DormId = student.getDormID();
						 String RoomId = student.getRoomID();
						 if(i==3)
							 newRow.add(i,DormId);
						 else
							 newRow.add(i,RoomId);
					}
					else
						newRow.add(""); 
				}
				((DefaultTableModel) tableSMR.getModel()).addRow(newRow);
			}
		});
		add.setBounds(112,510,60,30);
		add(add);
		
		confirmadd=new JButton("确认添加");
		confirmadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(input()) {
					currentRows=tableSMR.getRowCount()-1; 
					currentColumns=tableSMR.getColumnCount();
					recordId=Integer.parseInt(tableSMR.getValueAt(currentRows, 0).toString());
					for(int i=1;i<currentColumns;i++)
					{
						if(tableSMR.getModel().getValueAt(currentRows, i)=="") {
							JOptionPane.showMessageDialog(null, "请输入完整数据", "输入信息不完整", JOptionPane.WARNING_MESSAGE);
							break;
						}
					
					}
					
						confirmadd.setEnabled(false);
						add.setEnabled(true);
						String sql;
						sql="INSERT INTO DMSystem..RepairInfo_Of_MyDormitory(RecordNum,StuId,StuName,DormId,roomId,BedId,ItName,RepaireDate,isReplace,RepaireMan,Comment) VALUES('"+recordId+"','"+stuId+"','"+stuname+"','"+dormid+"','"+roomid+"','"+bedid+"','"+itname+"','"+repaireDate+"','"+isreplace+"','"+repaireMan+"','"+comment+"')";
						PreparedStatement stmt = DB.CreatePrepare(sql);
						try {
							stmt.execute();	
							JOptionPane.showMessageDialog(null, "添加成功", "信息提示", JOptionPane.WARNING_MESSAGE);
							add.setEnabled(true);
							confirmadd.setEnabled(false);
							try {
								showInfo();
							} catch (DataBaseException | QueryResultIsNullException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "添加失败", "信息提示", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
						}		
				}
			}
		});
		defaultTableModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int currentrow=tableSMR.getEditingRow();
				int currentcolumn;
				if(currentrow==tableSMR.getRowCount()-1) {
					currentcolumn=tableSMR.getEditingColumn();
//					System.out.println(currentrow+","+currentcolumn);
					switch(currentcolumn)
					{
						case 1:stuId=tableSMR.getValueAt(currentRows, 1).toString();break;
						case 2:stuname=tableSMR.getValueAt(currentRows, 2).toString();break;
						case 3:dormid=tableSMR.getValueAt(currentRows, 3).toString();break;
						case 4:roomid=tableSMR.getValueAt(currentRows, 4).toString();break;
						case 5:bedid=tableSMR.getValueAt(currentRows, 5).toString();break;
						case 6:itname=tableSMR.getValueAt(currentRows, 6).toString();break;
						case 7:repaireDate=tableSMR.getValueAt(currentRows, 7).toString();break;
						case 8:isreplace=tableSMR.getValueAt(currentRows, 8).toString();break;
						case 9:repaireMan=tableSMR.getValueAt(currentRows, 9).toString();break;
						case 10:comment=tableSMR.getValueAt(currentRows, 10).toString();break;
					}			
				}
			}
		});
		confirmadd.setBounds(180,510,85,30);
		add(confirmadd);
		
		delete=new JButton("删除");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectrow=tableSMR.getSelectedRow();
				int rowcount = defaultTableModel.getRowCount() - 1;
				int id=Integer.parseInt(tableSMR.getValueAt(selectrow, 0).toString());
//				System.out.println("id="+id);
				if(rowcount>=0)
				{
					defaultTableModel.removeRow(selectrow);
					defaultTableModel.setRowCount(rowcount);
					String sql="delete from DMSystem..RepairInfo_Of_MyDormitory where RecordNum='"+id+"'";	
					PreparedStatement stmt=DB.CreatePrepare(sql);
					try {
						stmt.execute();
						JOptionPane.showMessageDialog(null, "删除成功" , "提示信息" , JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
				else {
					JOptionPane.showMessageDialog(null, "删除失败", "失败信息", JOptionPane.WARNING_MESSAGE);
				}
				sorter.setRowFilter(RowFilter.regexFilter(""));	
				add.setEnabled(true);
				confirmadd.setEnabled(false);
			}
		});
		delete.setBounds(155, 550, 60, 30);
		add(delete);
		
		button_2 = new JButton("刷新");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sorter.setRowFilter(RowFilter.regexFilter(""));
			}
		});
		button_2.setBounds(565, 467, 60, 30);
		add(button_2);
		

		ImageIcon image = new ImageIcon("src\\MyImage\\Background.jpg");
		labImage = new JLabel(image);
		labImage.setBounds(0, 0, 800, 600);
		add(labImage);
		this.setOpaque(false);	
	}
	
	private boolean input() {
		currentRows=tableSMR.getRowCount()-1; 
		if(stuId.equals("")||bedid.equals("")||itname.equals("")||repaireDate.equals("")||repaireMan.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入完整数据", "输入信息不完整", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public void showInfo() throws DataBaseException, QueryResultIsNullException{
		@SuppressWarnings("rawtypes")
		Vector vect=new Vector();
		vect=student.getMaintenanceRecord();
//		System.out.println(vect); //test
		defaultTableModel.setDataVector(vect, headVector);
	}
	
	public Vector<Object> getItem() throws DataBaseException{
		
		return student.getItem();
	}	
}

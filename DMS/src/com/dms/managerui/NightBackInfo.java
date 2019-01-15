package com.dms.managerui;

import java.awt.Font;
import java.util.Vector;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.dms.mydatabase.DB;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.people.Manager;

import sun.swing.table.DefaultTableCellHeaderRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NightBackInfo extends JPanel {
	/**
	 * 晚归记录板块
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	@SuppressWarnings("unused")
	private String MANAGER_ID;
	private JLabel labImage;
	private ImageIcon image;
	private DefaultTableModel defaultTableModel;
	private Vector<String> headvector ;
	private Manager manager;
	
	private TableRowSorter<TableModel> sorter;
	
	@SuppressWarnings("unused")
	private JFrame jframe;
	private JTextField tfFind;
	private JTextField tfStuId;
	private JTextField tfStuName;
	private JTextField tfBackTime;
	private JTextField tfReason;
	private JTextField tfBedId;
	private JTextField tfRoomId;
	private JButton btnRefresh;
	@SuppressWarnings("unused")
	private JButton btnAddRecord;
	private JButton add;
	private JButton confirmadd;
	private JButton delete;
	private int recordid;
	private String stuid;
	private String stuname;
	private String dormid;
	private String roomid;
	private String bedid;
	private String backtime;
	private String dmid;
	private String reason;
	private int currentRows;
	private int currentColumns;
	
	@SuppressWarnings("static-access")
	public NightBackInfo (JFrame jf, Manager m){
		this.jframe = jf;
		this.manager = m;
		setSize(800,600);
	     setLayout(null);
	     
	     JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	     scrollPane.setBounds(90, 189, 612, 216);
	     add(scrollPane);
	     
	     table = new JTable();
	     table.addMouseListener(new MouseAdapter() {
	     	@Override
	     	public void mouseClicked(MouseEvent e) {
	     		
	     		Integer n = table.getSelectedRow();
				tfStuId.setText((String)table.getValueAt(n, 1));
				tfStuName.setText((String) table.getValueAt(n, 2));			
				tfBackTime.setText((String) table.getValueAt(n, 6));						
				tfReason.setText((String) table.getValueAt(n, 8));	
				tfBedId.setText((String)table.getValueAt(n, 5));
				tfRoomId.setText((String)table.getValueAt(n, 4));
				}	     
	     });
	     scrollPane.setViewportView(table);
	     headvector = new Vector<String>();
	     headvector.add("记录号");
	     headvector.add("学号");
	     headvector.add("姓名");
	     headvector.add("宿舍楼栋");
	     headvector.add("房间号");
	     headvector.add("床号");
	     headvector.add("夜归时间");
	     headvector.add("值班宿管");
	     headvector.add("晚归原因");
	     
	     defaultTableModel = new DefaultTableModel();    
	     defaultTableModel.setColumnCount(9);   
	     defaultTableModel.setDataVector(null,headvector);
	     table.setModel(defaultTableModel);
	     table.setFont(new Font("宋体", Font.PLAIN, 15));
	     //设置表数据居中显示		 
		 DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		 cr.setHorizontalAlignment(JLabel.CENTER);
		 table.setDefaultRenderer(Object.class, cr); 
		 //设置表头居中显示
		 DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		 hr.setHorizontalAlignment(JLabel.CENTER);
		 table.getTableHeader().setDefaultRenderer(hr);
	     
	 	 table.setAutoResizeMode(table.AUTO_RESIZE_OFF);  //自动调整表格列的宽度，不更改其他列的大小，而是更改整个表格的大小
		 sorter = new TableRowSorter<TableModel>(table.getModel());
		 table.setRowSorter(sorter);
		
			
	     JLabel label = new JLabel("夜归记录表");
	     label.setFont(new Font("微软雅黑", Font.BOLD, 25));
	     label.setBounds(90, 119, 155, 34);
	     add(label);
	     
		 
		 JLabel LStuId = new JLabel("学号");
		 LStuId.setBounds(90, 395, 30, 30);
		 add(LStuId);
		 
		 tfStuId = new JTextField();
		 tfStuId.setBounds(120, 395, 180, 30);
		 add(tfStuId);
		 tfStuId.setColumns(11);
		 
		 JLabel LName = new JLabel("姓名");
		 LName.setBounds(90, 425, 30, 30);
		 add(LName);
		 
		 tfStuName = new JTextField();
		 tfStuName.setBounds(120, 425, 180, 30);
		 add(tfStuName);
//		 tfStuName.setColumns(10);
		 
		 JLabel LRoomId=new JLabel("宿舍号");
		 LRoomId.setBounds(80,455,36,30);
		 add(LRoomId);
		 
		 tfRoomId=new JTextField();
		 tfRoomId.setBounds(120,455,180,30);
		 add(tfRoomId);
		 
		 JLabel LBackTime = new JLabel("夜归时间");
		 LBackTime.setBounds(65, 485, 60, 30);
		 add(LBackTime);
		 
		 tfBackTime = new JTextField();
		 tfBackTime.setBounds(120, 485, 180, 30);
		 add(tfBackTime);
		 
		 JLabel LBedId=new JLabel("床位");
		 LBedId.setBounds(90,515,30,30);
		 add(LBedId);
		 
		 tfBedId=new JTextField();
		 tfBedId.setBounds(120, 515, 180, 30);
		 add(tfBedId);
		 
		 JLabel LReason = new JLabel("原因");
		 LReason.setBounds(90, 545, 30, 30);
		 add(LReason);
		 
		 tfReason = new JTextField();
		 tfReason.setBounds(120, 545, 180, 30);
		 add(tfReason);
		 
		 add=new JButton("添加");
		 add.addActionListener(new ActionListener() {
			
			@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Vector newRow;
				int selectrow=table.getSelectedRow();
				
				stuid="";stuname="";dormid="";roomid="";bedid="";backtime="";reason="";
				add.setEnabled(false);
					confirmadd.setEnabled(true);
					currentRows=table.getRowCount(); 
					currentColumns=table.getColumnCount();
					newRow=new Vector();
					for(int i=0;i<currentColumns;i++)   //小于当前列数
					{
						if(i==0) {
							recordid=Integer.parseInt(table.getValueAt(currentRows-1, 0).toString());
//							System.out.println(recordId);
							newRow.add(i, recordid+1);
						}
						else if(i==3||i==7) {
							 dormid = manager.getDormId();
							 dmid = manager.getId();
							 if(i==3)
								 newRow.add(i,dormid);
							 else if(i==7)
								 newRow.add(i,dmid);
						}
						else
							newRow.add(""); 
					}
					((DefaultTableModel) table.getModel()).addRow(newRow);					
			}
		});
		 add.setBounds(310, 545, 60, 30);
		 add(add);
		 
		 confirmadd=new JButton("确认添加");
		 confirmadd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(input()) {
						currentRows=table.getRowCount()-1; 
						currentColumns=table.getColumnCount();
						confirmadd.setEnabled(false);
						add.setEnabled(true);						
							String sql="INSERT INTO DMSystem..Night_Back_Info(StuId,StuName,DormId,RoomId,BedId,BackTime,DMId,Reason) VALUES('"+stuid+"','"+stuname+"','"+dormid+"','"+roomid+"','"+bedid+"','"+backtime+"','"+dmid+"','"+reason+"')";
	//								System.out.println(sql);
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
		 confirmadd.setBounds(370,545,85,30);
		 add(confirmadd);
		 defaultTableModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int currentrow=table.getEditingRow();
				int currentcolumn;
				if(currentrow==table.getRowCount()-1) {
					currentcolumn=table.getEditingColumn();
//					System.out.println(currentrow+","+currentcolumn);
					switch(currentcolumn)
					{
						case 1:stuid=table.getValueAt(currentRows, 1).toString();break;
						case 2:stuname=table.getValueAt(currentRows, 2).toString();break;
						case 3:dormid=table.getValueAt(currentRows, 3).toString();break;
						case 4:roomid=table.getValueAt(currentRows, 4).toString();break;
						case 5:bedid=table.getValueAt(currentRows, 5).toString();break;
						case 6:backtime=table.getValueAt(currentRows, 6).toString();break;
						case 7:dmid=table.getValueAt(currentRows, 7).toString();break;
						case 8:reason=table.getValueAt(currentRows, 8).toString();break;
					}			
				}
			}
		});
		 delete=new JButton("删除");
			delete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int selectrow=table.getSelectedRow();
					int rowcount = defaultTableModel.getRowCount() - 1;
					int recordid=Integer.parseInt(table.getValueAt(selectrow, 0).toString());
//					System.out.println("id="+id);
					if(rowcount>=0)
					{
						defaultTableModel.removeRow(selectrow);
						defaultTableModel.setRowCount(rowcount);
						String sql="delete from DMSystem..Night_Back_Info where RecordId="+recordid;	
						PreparedStatement stmt=DB.CreatePrepare(sql);
						try {
							stmt.execute();
							JOptionPane.showMessageDialog(null, "删除成功" , "提示信息" , JOptionPane.WARNING_MESSAGE);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						sorter.setRowFilter(RowFilter.regexFilter(""));	
					}	
					else {
						JOptionPane.showMessageDialog(null, "删除失败", "失败信息", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			delete.setBounds(460, 545, 60, 30);
			add(delete);
			
		 tfFind = new JTextField();
		 tfFind.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {

					String text = tfFind.getText();
					if(text.trim().length()==0)
						sorter.setRowFilter(RowFilter.regexFilter(""));
					else
						sorter.setRowFilter(RowFilter.regexFilter(text));
			 		
			 	}
			 });
			 tfFind.setBounds(375, 435, 150, 30);
			 add(tfFind);
			 
			 final JButton btnFind = new JButton("查询");
			 btnFind.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {

					String text = tfFind.getText();
					if(text.trim().length()==0)
						sorter.setRowFilter(RowFilter.regexFilter(""));
					else
						sorter.setRowFilter(RowFilter.regexFilter(text));
			 	}
			 });
			 btnFind.setBounds(525, 435, 60, 30);
			 add(btnFind);
			 
			 JButton btnReset = new JButton("重置");
			 btnReset.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		tfStuId.setText("");
			 		tfStuName.setText("");
			 		tfBackTime.setText("");
			 		tfReason.setText("");	
			 		tfBedId.setText("");
			 		tfRoomId.setText("");
			 	}
			 });
			 btnReset.setBounds(405, 465, 60, 30);
			 add(btnReset);
	
		 btnRefresh = new JButton("刷新");
		 btnRefresh.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		sorter.setRowFilter(RowFilter.regexFilter(""));
		 	}
		 });
		 btnRefresh.setBounds(465, 465, 60, 30);
		 add(btnRefresh);
		 
	     image = new ImageIcon("src\\MyImage\\Background.jpg");
		 labImage = new JLabel(image);
		 labImage.setBounds(0, 0, 800, 600);
		 add(labImage);
		 this.setOpaque(false);
		}   
	private boolean input() {
		currentRows=table.getRowCount()-1; 
		if(stuid.equals("")||stuname.equals("")||dormid.equals("")||roomid.equals("")||backtime.equals("")||reason.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入完整数据", "输入信息不完整", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
		public void showInfo() throws DataBaseException, QueryResultIsNullException{
			Vector<Object> vector = manager.getAllCurfewRecord();
			defaultTableModel.setDataVector(vector, headvector);
		}

}

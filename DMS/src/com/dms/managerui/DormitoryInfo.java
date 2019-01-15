package com.dms.managerui;

import java.awt.Font;
import java.util.Vector;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;

import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.people.Manager;
import com.dms.queryresultset.AllDormitoryInfo;

import sun.swing.table.DefaultTableCellHeaderRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DormitoryInfo extends JPanel {
	/**
	 * 宿舍
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableDormitoryInfo;
	private JTextField Find;
	private Manager manager;
	private JLabel labImage;
	private ImageIcon image;
	private DefaultTableModel defaultTableModel;
	private Vector<Object> headvector;
	private TableRowSorter<TableModel> sorter;
	private JTextField tfDormid;
	private JTextField tfRoom;
	@SuppressWarnings("unused")
	private JFrame jframe;
	private AllDormitoryInfo allDormitoryInfo = null;
	private JButton butSelect;
	private JButton RefreshBtn;

	public DormitoryInfo(JFrame jf, Manager m) {
		this.jframe = jf;
		this.manager = m;
		setSize(800,600);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		scrollPane.setBounds(130, 130, 470, 300);
		add(scrollPane);		
		tableDormitoryInfo = new JTable();
		
		tableDormitoryInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer n = tableDormitoryInfo.getSelectedRow();
				tfDormid.setText((String)tableDormitoryInfo.getValueAt(n, 0));
				tfRoom.setText((String) tableDormitoryInfo.getValueAt(n, 1));
		}});
		
		scrollPane.setViewportView(tableDormitoryInfo);

		defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnCount(6);
		defaultTableModel.setRowCount(10);
		defaultTableModel.setValueAt(123, 1, 1);
		
		//头部信息
		headvector = new Vector<Object>();
		headvector.add("宿舍楼Id");
		headvector.add("宿舍号");
		headvector.add("舍长");
		headvector.add("舍员1");
		headvector.add("舍员2");
		headvector.add("舍员3");
		
		defaultTableModel.setDataVector(null,headvector);		
		tableDormitoryInfo.setModel(defaultTableModel);
		//设置表数据居中显示		 
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		tableDormitoryInfo.setDefaultRenderer(Object.class, cr); 
		//设置表头居中显示
		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		tableDormitoryInfo.getTableHeader().setDefaultRenderer(hr);
		
		tableDormitoryInfo.setFont(new Font("宋体", Font.PLAIN, 15));	
		tableDormitoryInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  	
		sorter = new TableRowSorter<TableModel>(tableDormitoryInfo.getModel());
		tableDormitoryInfo.setRowSorter(sorter);
		
		JLabel labDormitoryInfo = new JLabel("具体的宿舍楼信息表");
		labDormitoryInfo.setFont(new Font("微软雅黑", Font.BOLD, 22));
		labDormitoryInfo.setBounds(72, 63, 222, 34);
		add(labDormitoryInfo);
		
		JLabel label = new JLabel("宿舍楼");
		label.setBounds(270, 457, 54, 30);
		add(label);
		
		tfDormid = new JTextField();
		tfDormid.setEditable(false);
		tfDormid.setBounds(310, 457, 35, 30);
		add(tfDormid);
		tfDormid.setColumns(10);
		
		JLabel LRoom = new JLabel("房间");
		LRoom.setBounds(375, 457, 54, 30);
		add(LRoom);
		
		tfRoom = new JTextField();
		tfRoom.setEditable(false);
		tfRoom.setBounds(400, 457, 40, 30);
		add(tfRoom);
		
		JLabel label1=new JLabel("输入表中任何列值的信息进行查询");
		label1.setBounds(130,495,185,30);
		add(label1); 
		
		Find = new JTextField();
		Find.setFont(new Font("宋体", Font.PLAIN, 15));
		Find.setColumns(6);
		Find.setBounds(320, 495, 130, 30);
		add(Find);
		Find.setFont(new Font("Myriad", Font.PLAIN, 14));
		Find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String text = Find.getText();
				if(text.trim().length()==0)
					sorter.setRowFilter(RowFilter.regexFilter(""));
				else
					sorter.setRowFilter(RowFilter.regexFilter(text));
			}
		});

		butSelect = new JButton("查 询");
		butSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				String text = Find.getText();
				if(text.trim().length()==0)
					sorter.setRowFilter(RowFilter.regexFilter(""));
				else
					sorter.setRowFilter(RowFilter.regexFilter(text));	
			}
		});
		butSelect.setFont(new Font("宋体", Font.PLAIN, 13));
		butSelect.setBounds(455, 495, 65, 30);
		add(butSelect);
		RefreshBtn = new JButton("刷 新");
		RefreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
					sorter.setRowFilter(RowFilter.regexFilter(""));
			}
		});
		RefreshBtn.setFont(new Font("宋体", Font.PLAIN, 13));
		RefreshBtn.setBounds(360, 533, 65, 30);
		add(RefreshBtn);
		
		image = new ImageIcon("src\\MyImage\\Background.jpg");
		labImage = new JLabel(image);
		labImage.setBounds(0, 0, 800, 600);
		add(labImage);	
		this.setOpaque(false);
	}
	
	public void showInfo() throws DataBaseException, QueryResultIsNullException {
		
		allDormitoryInfo = manager.getAllDormitoryInfo();
		defaultTableModel.setDataVector(allDormitoryInfo.getdormitoryInfos(), headvector);
	}	
}

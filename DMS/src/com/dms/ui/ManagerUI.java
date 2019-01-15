package com.dms.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.dms.managerui.About;
import com.dms.managerui.ChangePassword;
import com.dms.managerui.Dormitory;
import com.dms.managerui.DormitoryBuildingInfo;
import com.dms.managerui.DormitoryInfo;
import com.dms.managerui.First;
import com.dms.managerui.Help;
import com.dms.managerui.NightBackInfo;
import com.dms.managerui.PerInfo;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.people.Manager;

import java.awt.Toolkit;

public class ManagerUI extends JFrame {

	/**
	 * 管理员界面
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout card;
	private JPanel contentPane = null;
	private JPanel paneFirst;
	private PerInfo paneMInfo;	//管理员信息
	private JPanel paneChangPWD;	//修改密码
	
	private JPanel paneDormitory;	//宿舍基本信息
	private JPanel paneDormitoryInfo;	//宿舍学生具体信息
	
	private JPanel paneView_Student_College;	//学生信息查询
	private JPanel paneNightBack;  //夜归信息
	
	private JPanel panemhelptext;
	private JPanel paneAbout;
	
	@SuppressWarnings("unused")
	private JPanel paneMStuInfo;
	@SuppressWarnings("unused")
	private JPanel paneSelectStudnet;	
	private String MANAGER_ID;
	private Manager manager=null;
	
	public ManagerUI(String MANAGER_ID) throws DataBaseException, QueryResultIsNullException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerUI.class.getResource("/MyImage/Icon.png")));
		manager = new Manager(MANAGER_ID);
		this.MANAGER_ID=MANAGER_ID;	
		setTitle("宿舍管理系统");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 805, 650);
	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		menuBar.setMargin(new Insets(4, 4, 4, 4));
		menuBar.setForeground(new Color(30, 144, 255));
		menuBar.setBackground(SystemColor.activeCaption);
		setJMenuBar(menuBar);
		menuBar.setSize(940, 200);
		menuBar.setBorderPainted(true);
		
		JMenu mnNewMenu = new JMenu("   用户管理  ");
		mnNewMenu.setBackground(new Color(192, 192, 192));
		mnNewMenu.setFont(new Font("华文行楷", Font.PLAIN, 15));
		menuBar.add(mnNewMenu);
		 
		JMenuItem ManagerInfo = new JMenuItem("  个人信息   ");   
		ManagerInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((PerInfo) paneMInfo).showInfo();
				} catch (DataBaseException e1) {
					JOptionPane.showMessageDialog(null, "系统异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (QueryResultIsNullException e1) {
					JOptionPane.showMessageDialog(null, "数据异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
				card.show(contentPane,"管理员资料");
				
			}
		});		
		mnNewMenu.add(ManagerInfo);
		
		JMenuItem PassWordUpdate = new JMenuItem("  密码修改   ");       // PasswordUpdate  密码修改
		PassWordUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane,"密码修改");
			}
		});
		mnNewMenu.add(PassWordUpdate);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		mnNewMenu.add(separator);
		
		JMenuItem menuItem_4 = new JMenuItem("  退出系统   ");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					this.finalize();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				Login l = new Login();
				l.setVisible(true);
				setVisible(false);
			}
		});
		mnNewMenu.add(menuItem_4);
		
		JMenu Fix = new JMenu("    寝室管理      ");                       //Fix 宿舍维修
		Fix.setFont(new Font("华文行楷", Font.PLAIN, 15));
		menuBar.add(Fix);
		
		JMenuItem Indemnity = new JMenuItem("  宿舍楼信息    ");    //Indemnity   赔偿表
		Indemnity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane,"宿舍楼信息");	
				try {
					((Dormitory)paneDormitory).showInfo();
				} catch (DataBaseException e1) {
					card.show(contentPane,"宿舍楼信息");
					JOptionPane.showMessageDialog(null, "系统异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (QueryResultIsNullException e1) {
					card.show(contentPane,"宿舍楼信息");
					JOptionPane.showMessageDialog(null, "数据异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		Fix.add(Indemnity);
		
		JMenuItem repairs = new JMenuItem(" 宿舍分配信息   ");                      //repair 报修
		repairs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "宿舍分配信息");
				try {
					((DormitoryInfo) paneDormitoryInfo).showInfo();
				} catch (DataBaseException e1) {
					JOptionPane.showMessageDialog(null, "数据异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (QueryResultIsNullException e1) {
					JOptionPane.showMessageDialog(null, "没有结果", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
				 
			}
		});
		Fix.add(repairs);
		
		
		JMenu StudentInfo = new JMenu("   信息查询    "); 
		StudentInfo.setFont(new Font("华文行楷", Font.PLAIN, 15));
		menuBar.add(StudentInfo);
		
		JMenuItem menuItem_3 = new JMenuItem("学生信息查询");               
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "学生信息");
				try {
					((DormitoryBuildingInfo)paneView_Student_College).showInfo();
				} catch (QueryResultIsNullException e1) {
					JOptionPane.showMessageDialog(null, "没有结果", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (DataBaseException e1) {
					JOptionPane.showMessageDialog(null, "数据异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		StudentInfo.add(menuItem_3);
		
	
		
		JMenuItem mntmNewMenuItem = new JMenuItem("学生晚归信息");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane,"学生晚归信息");
				try {
					((NightBackInfo)paneNightBack).showInfo();
				} catch (DataBaseException e1) {
					JOptionPane.showMessageDialog(null, "数据异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (QueryResultIsNullException e1) {
					JOptionPane.showMessageDialog(null, "没有结果", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		StudentInfo.add(mntmNewMenuItem);
		
		JMenu menu = new JMenu("     帮助     ");
		menu.setFont(new Font("华文行楷", Font.PLAIN, 15));
		menuBar.add(menu);
		
		JMenuItem menuItem_2 = new JMenuItem("使用帮助");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "使用帮助");
			}
		});
		menu.add(menuItem_2);
		
		JMenuItem menuItem_5 = new JMenuItem("关于");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "关于");
			}
		});
		
		menu.add(menuItem_5);
		
		JLabel lblNewLabel_1 = new JLabel("                                                        ");
		menuBar.add(lblNewLabel_1);
		
		JLabel label = new JLabel("  欢迎您：");
		menuBar.add(label);
		
		JLabel lblNewLabel_2 = new JLabel(manager.getName());
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 12));
		lblNewLabel_2.setForeground(SystemColor.textHighlight);
		
		menuBar.add(lblNewLabel_2);
		
		card = new CardLayout(0,0);
		contentPane = new JPanel(card);
		
		paneFirst = new First();
		contentPane.add(paneFirst,"首界面");
		
		paneMInfo = new PerInfo(manager,this.MANAGER_ID);
		contentPane.add(paneMInfo,"管理员资料");
		
		paneChangPWD = new ChangePassword(manager);
		contentPane.add(paneChangPWD,"密码修改");
		
		paneDormitory = new Dormitory(manager);
		contentPane.add(paneDormitory,"宿舍楼信息");
		
		paneDormitoryInfo = new DormitoryInfo(this,manager);
		contentPane.add(paneDormitoryInfo,"宿舍分配信息");


		paneView_Student_College = new DormitoryBuildingInfo(this,manager);
		contentPane.add(paneView_Student_College,"学生信息");
		
		paneNightBack = new NightBackInfo(this,manager);
		contentPane.add(paneNightBack,"学生晚归信息");

		
		panemhelptext = new Help();
		contentPane.add(panemhelptext,"使用帮助");
		
		paneAbout = new About();
		contentPane.add(paneAbout,"关于");
		
		paneAbout = new About();
				
		this.getContentPane().add(contentPane);
		card.show(contentPane, "首界面");
		setVisible(true);

	}
}

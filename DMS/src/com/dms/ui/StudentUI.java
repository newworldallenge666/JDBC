package com.dms.ui;

import java.awt.CardLayout;
import java.awt.Font;
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

import com.dms.expressmsg.ExpressMassageRemind;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.people.Student;
import com.dms.studentui.About;
import com.dms.studentui.ChangPassword;
import com.dms.studentui.ExpressInfo;
import com.dms.studentui.First;
import com.dms.studentui.Help;
import com.dms.studentui.NightBackInfo;
import com.dms.studentui.PerInfo;
import com.dms.studentui.RepairInfo;

import java.awt.Toolkit;


public class StudentUI extends JFrame {

	/**
	 * 学生界面
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane=null;
	@SuppressWarnings("unused")
	private String STUDENT_ID;
	private CardLayout card;
	private JPanel paneStuInfo;
	private JPanel paneStuFirst;
	private JPanel paneStuPassword;
	private JPanel paneSKuaidi;
	private JPanel paneSCurfew;	//夜归
	private JPanel paneSMR;	//维修
	private JPanel paneSAbout;
	private JPanel paneshelp;
	private Student student = null;
	public StudentUI(){}
	public StudentUI(String STUDENT_ID) throws DataBaseException, QueryResultIsNullException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StudentUI.class.getResource("/MyImage/Icon.png")));
		this.STUDENT_ID = STUDENT_ID;
		student = new Student(STUDENT_ID);
		Thread thread = new Thread(new ExpressMassageRemind(student));
		thread.start();
		setTitle("宿舍管理系统");
		setResizable(false);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setBounds(200, 50, 805, 650);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUserInfo = new JMenu("    用户管理    ");
		mnUserInfo.setFont(new Font("华文楷体", Font.PLAIN, 15));
		menuBar.add(mnUserInfo);
		
		JMenuItem miStuInfo = new JMenuItem("    个人信息    ");
		miStuInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((PerInfo) paneStuInfo).showInfo();
				} catch (DataBaseException e1) {
					JOptionPane.showMessageDialog(null, "系统异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (QueryResultIsNullException e1) {
					JOptionPane.showMessageDialog(null, "数据异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
				card.show(contentPane, "个人信息");
			}
		});
		mnUserInfo.add(miStuInfo);
		
		JMenuItem miPassword = new JMenuItem("    修改密码   ");
		miPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "修改密码");
				
			}
		});
		mnUserInfo.add(miPassword);
		
		JSeparator separator = new JSeparator();
		mnUserInfo.add(separator);
		
		JMenuItem menuItem = new JMenuItem("    退出系统   ");
		menuItem.addActionListener(new ActionListener() {
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
		
		mnUserInfo.add(menuItem);
		
		JMenu mnRepair = new JMenu("   信息查询   ");
		mnRepair.setFont(new Font("华文楷体", Font.PLAIN, 15));
		mnRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "报修信息");
				try {
					((RepairInfo)paneSMR).showInfo();
				} catch (QueryResultIsNullException e1) {
					JOptionPane.showMessageDialog(null, "没有结果", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (DataBaseException e1) {
					JOptionPane.showMessageDialog(null, "数据异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		menuBar.add(mnRepair);
		
		JMenuItem menuItem_1 = new JMenuItem("   报修信息   ");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "报修信息");
				try {
					((RepairInfo)paneSMR).showInfo();
				} catch (QueryResultIsNullException e1) {
					JOptionPane.showMessageDialog(null, "没有结果", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (DataBaseException e1) {
					JOptionPane.showMessageDialog(null, "数据异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		mnRepair.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("   快递信息   ");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "快递信息");
				try {
					((ExpressInfo)paneSKuaidi).showInfo();
				} catch (QueryResultIsNullException e1) {
					JOptionPane.showMessageDialog(null, "没有结果", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (DataBaseException e1) {
					JOptionPane.showMessageDialog(null, "数据异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		mnRepair.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("   晚归信息   ");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "晚归信息");
				try {
					((NightBackInfo)paneSCurfew).showInfo();
				} catch (QueryResultIsNullException e1) {
					JOptionPane.showMessageDialog(null, "没有结果", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (DataBaseException e1) {
					JOptionPane.showMessageDialog(null, "数据异常", "系统信息", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		mnRepair.add(menuItem_3);
		
		JMenu menu = new JMenu("    帮助     ");
		menu.setFont(new Font("华文楷体", Font.PLAIN, 15));
		menuBar.add(menu);
		
		JMenuItem menuItem_4 = new JMenuItem("使用帮助");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "使用帮助");
			}
		});
		menu.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("关于");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "关于");
			}
		});
		menu.add(menuItem_5);
		
		JLabel label = new JLabel("                                                                  ");
		menuBar.add(label);
		
		JLabel label_1 = new JLabel("欢迎您：");
		menuBar.add(label_1);
		
		JLabel lblNewLabel = new JLabel(student.getName());
		menuBar.add(lblNewLabel);
		
		
		card = new CardLayout(0,0);
		contentPane = new JPanel(card);
		
		
		paneStuFirst = new First();
		contentPane.add(paneStuFirst,"欢迎页");
		
		
		paneStuInfo = new PerInfo(student);
		contentPane.add(paneStuInfo,"个人信息");
		
		paneStuPassword = new ChangPassword(student);
		contentPane.add(paneStuPassword,"修改密码");
		
		paneSMR = new RepairInfo(this, student);
		contentPane.add(paneSMR,"报修信息");
		
		paneSKuaidi = new ExpressInfo(this, student);
		contentPane.add(paneSKuaidi,"快递信息");
		
		paneSCurfew = new NightBackInfo(student);
		contentPane.add(paneSCurfew,"晚归信息");
		
		paneshelp = new Help();
		contentPane.add(paneshelp,"使用帮助");
		
		paneSAbout = new About();
		contentPane.add(paneSAbout,"关于");
		
		this.getContentPane().add(contentPane);
		card.show(contentPane, "欢迎页");
		setVisible(true);
	}
}

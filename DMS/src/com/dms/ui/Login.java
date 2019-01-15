package com.dms.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.dms.information.LoginInfo;
import com.dms.mydatabase.DB;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;


public class Login extends JFrame implements FocusListener,KeyListener,ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel Login_Pane;
	private JTextField Account_Field;
	private JPasswordField Pwd_Field;
	private JFrame sr = this;
	private JButton Login_Btn ;
	private JLabel Login_Img;
	private ImageIcon image;
	private JRadioButton Type_Student;
	private JRadioButton Type_Manager;
	private ButtonGroup Radio_Group;
	private String selected="Student";
	/**
	 * 登录系统
	 */
	public Login() {
	
		initialize();
	}
	
	public void initialize(){
		//全体设置
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/MyImage/Icon.png")));
		setTitle("Dormitory Management System");
		setBounds(800, 300, 600, 500);
		Login_Pane = new JPanel();
     	Login_Pane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(Login_Pane);
		Login_Pane.setLayout(null);
		
		Account_Field = new JTextField();
		Pwd_Field = new JPasswordField();
		
		Account_Field.setFont(new Font("Myriad", Font.PLAIN, 17));
		Pwd_Field.setFont(new Font("Myriad", Font.PLAIN, 17));
		
//		Account_Field.setColumns(10);
		setResizable(false);
		
		//设置圆角边框
	    RoundBorder TextField_Border=new RoundBorder(new Color(255,20,147));
	 
		Account_Field.setOpaque(false);
		Pwd_Field.setOpaque(false);
		
		Account_Field.setBorder(TextField_Border);
		Pwd_Field.setBorder(TextField_Border);
		
		Account_Field.setBounds(240, 130, 190, 30);
		Pwd_Field.setBounds(240, 185, 190, 30);
		Account_Field.setForeground(Color.GRAY);
		Pwd_Field.setForeground(Color.GRAY);
		Account_Field.setText("Input your account");
		Pwd_Field.setText("Input your password");
		Account_Field.setSelectedTextColor(new Color(135,206,250));
		Pwd_Field.setSelectedTextColor(new Color(135,206,250));
		
		Login_Pane.add(Account_Field);
		Login_Pane.add(Pwd_Field);
		
		JLabel Account_Label = new JLabel("Account:");
		Account_Label.setFont(new Font("Myriad", Font.BOLD, 17));
		Account_Label.setBounds(155, 130, 85, 25);
		Login_Pane.add(Account_Label);

		JLabel Pwd_Label = new JLabel("Password:");
		Pwd_Label.setFont(new Font("Myriad", Font.BOLD, 17));
		Pwd_Label.setBounds(155, 185, 85, 25);
		Login_Pane.add(Pwd_Label);
		
		Type_Student=new JRadioButton("Student");
		Type_Manager=new JRadioButton("Manager");
		Type_Student.setSelected(true);	//默认选择学生
		Type_Student.setFont(new Font("Myriad", Font.PLAIN, 14));
		Type_Manager.setFont(new Font("Myriad", Font.PLAIN, 14));
		
		Type_Student.setBounds(248, 240, 90, 30);
		Type_Manager.setBounds(338, 240, 90, 30);
		
		Radio_Group=new ButtonGroup();
		Radio_Group.add(Type_Student);
		Radio_Group.add(Type_Manager);
		
		Login_Pane.add(Type_Student);
		Login_Pane.add(Type_Manager);
		
		Login_Btn = new JButton("Login");
		Login_Btn.setFont(new Font("Myriad", Font.PLAIN, 16));
		Login_Btn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green)); //设置按钮UI
		Login_Btn.setBounds(293, 280, 68, 30);
		Login_Btn.setBackground(new Color(238,130,238));
		Login_Pane.add(Login_Btn);
				
		image = new ImageIcon("src\\MyImage\\Login.jpg");
		Login_Img = new JLabel(image);
		Login_Img.setBounds(0, 0, image.getIconWidth(),image.getIconHeight());
		Login_Pane.add(Login_Img);
		Login_Pane.setOpaque(false);
		
		Pwd_Field.addFocusListener(this);
		Account_Field.addFocusListener(this);
		Account_Field.addKeyListener(this);
		Type_Manager.addKeyListener(this);
		Type_Student.addKeyListener(this);
		Pwd_Field.addKeyListener(this);
		Login_Btn.addActionListener(this);
	}
	//有参构造函数通过用户的登录状态进行判断
	public void login(LoginInfo loginInfo) throws DataBaseException,QueryResultIsNullException {
		
		String sql = null;
		if (loginInfo.getUserType().equals("Student")) {
			sql = "select * from DMSystem..StuAccountPassword  where StuId ='"
					+ loginInfo.getId() + "' and StuPassword = '"
					+ loginInfo.getPassword() + "'";
		}
		if (loginInfo.getUserType().equals("Manager")) {
			sql = "select * from DMSystem..DMAccountPassword  where DMId ='"
					+ loginInfo.getId() + "' AND DMPassword ='"
					+ loginInfo.getPassword() + "'";
		}
		Statement stmt = DB.CreateStatement();
//		System.out.println(sql); //test
		try {
			ResultSet rs = stmt.executeQuery(sql); 
			if (!rs.next()) {
				throw new QueryResultIsNullException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
}

	public static void main(String[] args) throws Exception {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login Login_Instance = new Login();
					Login_Instance.setVisible(true);
					UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels(); //获取所有的显示样式
					final String plafName;
					plafName = infos[1].getClassName();
					UIManager.setLookAndFeel(plafName);
					SwingUtilities.updateComponentTreeUI(Login_Instance);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private boolean judge(String str1,String str2)
	{
		if(str1.equals("Input your account"))
		{
			if(str2.equals("")||str2.equals("Input your password"))
				JOptionPane.showMessageDialog(sr,"请输入账号和密码" , "输入信息不完全", JOptionPane.ERROR_MESSAGE);
			else 
				JOptionPane.showMessageDialog(sr,"请输入账号" , "输入信息不完全", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(str2.equals("Input your password"))
		{
			if(!str1.equals("")||str1.equals("Input your account"))
				JOptionPane.showMessageDialog(sr,"请输入密码" , "输入信息不完全", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(sr,"请输入账号和密码" , "输入信息不完全", JOptionPane.ERROR_MESSAGE);
			return false;
		}	
		else if(str1.equals(""))
		{
			if(str2.equals("Input your password"))
				JOptionPane.showMessageDialog(sr,"请输入账号和密码" , "输入信息不完全", JOptionPane.ERROR_MESSAGE);
			else 
				JOptionPane.showMessageDialog(sr,"请输入账号" , "输入信息不完全", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(str2.equals(""))
		{
			if(str1.equals("Input your account"))
				JOptionPane.showMessageDialog(sr,"请输入账号和密码" , "输入信息不完全", JOptionPane.ERROR_MESSAGE);
			else 
				JOptionPane.showMessageDialog(sr,"请输入密码" , "输入信息不完全", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	/**
	*监听JTextField状态，设置光标以及提示语句
	*/
	@SuppressWarnings("deprecation")
	@Override
	public void focusGained(FocusEvent e) 
	{
		// TODO Auto-generated method stub
		JTextComponent obj=(JTextComponent) e.getSource();
		obj.setEditable(true);
		if(e.getSource().equals(Account_Field)&&Account_Field.getText().equals("Input your account")) {
			Account_Field.setText("");
			Account_Field.setForeground(Color.BLACK);
		}
		if(e.getSource().equals(Pwd_Field)&&Pwd_Field.getText().equals("Input your password")) {
			Pwd_Field.setText("");
			Pwd_Field.setForeground(Color.BLACK);
		}
		obj.setCaretColor(Color.green);
		//设置光标位置为文本内容最后面
		obj.setCaretPosition(obj.getText().length());
	}
	//设置文本框不可编辑后设置光标的可用性为false以及提示功能
	@SuppressWarnings("deprecation")
	@Override
	public void focusLost(FocusEvent e) {

		// TODO Auto-generated method stub
		JTextField textfield=(JTextField) e.getSource();
		textfield.setEditable(false); 
		textfield.getCaret().setVisible(false);
		if(e.getSource().equals(Account_Field)) {
			if("".equals(Account_Field.getText())) {
				Account_Field.setText("Input your account");
				Account_Field.setForeground(Color.GRAY);
			}
		}
		if(e.getSource().equals(Pwd_Field)) {
			if("".equals(Pwd_Field.getText())) {
				Pwd_Field.setText("Input your password");
				Pwd_Field.setForeground(Color.GRAY);
			}
		}
	}
	/**
	*监听键盘点击事件
	*/
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//检查输入框内容
		String str1=Account_Field.getText().trim();
		@SuppressWarnings("deprecation")
		String str2=Pwd_Field.getText().trim();
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(judge(str1, str2))
				Login_Btn.doClick(); 
		}
	}	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	*设置登录按钮的监听
	*/
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//检查账号和密码输入
		String str1=Account_Field.getText().trim();
		String str2=Pwd_Field.getText().trim();
		if(judge(str1, str2))
		{
			LoginInfo loginInfo = new LoginInfo(); 	//创建记录登录状态的LoginInfo对象
			loginInfo.setId(Account_Field.getText()); //用户名为id，学生为学号
			loginInfo.setPassword(Pwd_Field.getText().trim());
			if(Type_Manager.isSelected())
				selected="Manager";
			else
				selected="Student";
			loginInfo.setUserType(selected);
			try {
					login(loginInfo);
					if (loginInfo.getUserType().equals("Student")) {
						System.out.println("正在登录系统...");
						new StudentUI(loginInfo.getId());
						setVisible(false);
					}
					if (loginInfo.getUserType().equals("Manager")) {
						System.out.println("正在登录系统...");
						new ManagerUI(loginInfo.getId());
						setVisible(false);
					}
				} catch (DataBaseException e1) {
						JOptionPane.showMessageDialog(sr, selected+ "系统异常", "System Information", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					} catch (QueryResultIsNullException e1) {
						JOptionPane.showMessageDialog(sr, selected + "用户不存在或密码错误", "System Information", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
			}
	}
}

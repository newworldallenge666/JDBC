package com.dms.studentui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Help extends JPanel {
	
	/**
	 * 说明文档板块
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon image;
	JLabel labImage;
	
	public Help() {
		setSize(800,600);
		setLayout(null);
		
		JLabel label_17 = new JLabel("一、用户管理");
		label_17.setBounds(91, 73, 216, 27);
		add(label_17);
		
		JLabel label = new JLabel("1、个人信息");
		label.setBounds(102, 97, 153, 33);
		add(label);
		
		JLabel label_1 = new JLabel("点击“修改”便可修改自己的手机号码和籍贯，而且仅可修改这两项。");
		label_1.setBounds(111, 119, 500, 33);
		add(label_1);
		
		JLabel label_2 = new JLabel("2、修改密码");
		label_2.setBounds(101, 145, 216,33);
		add(label_2);
		
		JLabel label_3 = new JLabel("输入新旧密码确认，若输入的旧密码和旧密码相等，则修改成功，否则失败。");
		label_3.setBounds(111, 170,450, 33);
		add(label_3);
		
		JLabel label_4 = new JLabel("二、信息查询");
		label_4.setBounds(91, 197, 216, 33);
		add(label_4);
		
		JLabel label_5 = new JLabel("1、报修信息");
		label_5.setBounds(101, 223, 216, 33);
		add(label_5);
		
		JLabel label_6 = new JLabel("如果想调整表格列宽查看完整数据，可将鼠标放在列与列之间，然后长按鼠标左键调整。");
		label_6.setBounds(111, 250, 500, 33);
		add(label_6);
		
		JLabel label_8 = new JLabel("下面的文本框可以输入任何列的内容，点击“查询”按钮便可显示相应的消息。选中“只显示未");
		label_8.setBounds(91, 274, 500, 33);
		add(label_8);
		
		JLabel label_9 = new JLabel("维修”可以只显示未维修的信息。点击“刷新”按钮便可显示登录用户宿舍的所有的维修信息。");
		label_9.setBounds(91, 298, 500, 33);
		add(label_9);
		
		
		JLabel label_13 = new JLabel("2、快递信息");
		label_13.setBounds(101, 330, 216, 33);
		add(label_13);
		
		JLabel label_12 = new JLabel("每次登录之后会弹出新快递信息的提醒窗口，后面会根据下面设定的时间段定时进行提醒。");
		label_12.setBounds(111, 354, 500, 33);
		add(label_12);
		
		JLabel label_18 = new JLabel("你也可以选择取消定时提醒。主面板会显示登录用户的所有快递信息，可通过填写包含快递");
		label_18.setBounds(91, 378, 500, 33);
		add(label_18);
		
		JLabel label_15 = new JLabel("公司名、日期、记录号的任何信息对快件信息进行筛选。如查询11月的所有快件信息，输入");
		label_15.setBounds(91, 402, 520, 33);
		add(label_15);
		
		JLabel label_19 = new JLabel("“11月”后选择“日期”，点击“查询”按钮后会显示日期为11月的所有快件信息，以此类推。");
		label_19.setBounds(91, 426, 540, 33);
		add(label_19);
	
		
		JLabel label_21 = new JLabel("3、晚归信息");
		label_21.setBounds(101, 450, 216, 33);
		add(label_21);
		
		JLabel label_22 = new JLabel("主面板会显示登录用户的所有夜归记录。");
		label_22.setBounds(101, 474, 520, 33);
		add(label_22);
		
		JLabel label_23 = new JLabel("三、关于");
		label_23.setBounds(91, 498, 216, 33);
		add(label_23);
		
		JLabel label_24 = new JLabel("显示本系统的相关开发信息。");
		label_24.setBounds(101,522, 500, 33);
		add(label_24);
		
	
		ImageIcon image = new ImageIcon("src\\MyImage\\Background.jpg");
		labImage = new JLabel(image);
		labImage.setBounds(0, 0, 800, 600);
		add(labImage);
		this.setOpaque(false);
		
	}
}

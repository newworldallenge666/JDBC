package com.dms.managerui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Help extends JPanel {

	/**
	 * 说明文档板块
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labImage;
	private ImageIcon image;
	
	public Help() {
		setSize(800,600);
		setLayout(null);
		
		JLabel label = new JLabel("一、用户信息");
		label.setBounds(69, 32, 536, 29);
		add(label);
		
		JLabel label_3 = new JLabel("1、个人信息");
		label_3.setBounds(89, 57, 536, 29);
		add(label_3);
		
		JLabel label_1 = new JLabel("点击“修改”按钮，就可以修改登录用户的电话号码，且只可修改电话号码。");
		label_1.setBounds(112, 85, 536, 29);
		add(label_1);
		

		JLabel label_2 = new JLabel("2、密码修改");
		label_2.setBounds(89, 112, 536, 29);
		add(label_2);
		
		JLabel label_4 = new JLabel("输入新旧密码确认，若输入的旧密码和旧密码相等，则修改成功，否则失败。");
		label_4.setBounds(112, 138, 536, 29);
		add(label_4);
		
			
		JLabel label_5 = new JLabel("二、寝室管理");
		label_5.setBounds(69, 165, 536, 29);
		add(label_5);
		
		JLabel label_6 = new JLabel("1、宿舍楼信息");
		label_6.setBounds(89, 192, 536, 29);
		add(label_6);
		
		JLabel label_7 = new JLabel("点击即可查看本登录用户所管宿舍楼的信息。");
		label_7.setBounds(112, 217, 536, 29);
		add(label_7);
		
		JLabel label_8 = new JLabel("2、宿舍分配信息");
		label_8.setBounds(89, 245, 536, 29);
		add(label_8);
		
		JLabel label_9 = new JLabel("点击此选项卡后，主面板显示的是本登录用户所管宿舍楼的所有宿舍信息。选中某一行后该选");
		label_9.setBounds(112, 271, 536, 29);
		add(label_9);
		
		JLabel label_10 = new JLabel("中行的宿舍房间信息会显示出来，在“查询”按钮左边的文本框填写随机列的字段，点击查询即可查");
		label_10.setBounds(99, 291, 536, 29);
		add(label_10);
		
		JLabel label_18 = new JLabel("询包含此关键字的结果集。点击“刷新”按钮即可显示所有的宿舍信息。");
		label_18.setBounds(99, 311, 536, 29);
		add(label_18);
		
		JLabel label_11 = new JLabel("三、信息查询");
		label_11.setBounds(69, 330, 536, 29);
		add(label_11);
		
		JLabel label_12 = new JLabel("1、学生信息查询");
		label_12.setBounds(89, 357, 536, 29);
		add(label_12);
		
		JLabel label_13 = new JLabel("在文本框中写随机列的字段，即可筛选相应的消息。");
		label_13.setBounds(112, 383, 536, 29);
		add(label_13);
		
		JLabel label_14 = new JLabel("2、夜归信息");
		label_14.setBounds(89, 411, 536, 29);
		add(label_14);
		
		JLabel label_15 = new JLabel("此主面板显示本登录用户所管宿舍楼所有的晚归信息。可通过点击选中某一行，在下面的文本框中查看");
		label_15.setBounds(112, 437, 550, 29);
		add(label_15);
		
		JLabel label_16 = new JLabel("此晚归信息，重置按钮可清除所有的文本框内容。同时可以通过添加按钮实现学生夜归记录的添加。添");
		label_16.setBounds(112, 457, 550, 29);
		add(label_16);
		
		JLabel label_20 = new JLabel("加最后一列内容后，光标消失后才可点击确认添加，不然会提示信息填写不完整。查询与刷新功能与上同。");
		label_20.setBounds(112, 477, 580, 29);
		add(label_20);
		
		JLabel label_17 = new JLabel("四、关于");
		label_17.setBounds(69, 505, 540, 29);
		add(label_17);
		
		JLabel label_19 = new JLabel("显示本系统的相关开发信息。");
		label_19.setBounds(112, 533, 540, 29);
		add(label_19);
		
		image = new ImageIcon("src\\MyImage\\Background.jpg");
		labImage = new JLabel(image);
		labImage.setBounds(0, 0, 800, 600);
		add(labImage);
		this.setOpaque(false);
		
	}
}

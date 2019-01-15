package com.dms.studentui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JPanel {
		
	/**
	 * “关于”界面板块
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labImage;
	private ImageIcon image;

	public About() {
		setBackground(new Color(255, 255, 255));
		setSize(800,600);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/MyImage/Icon.png")));
		lblNewLabel.setBounds(135, 240, 200, 232);
		add(lblNewLabel);
		
		JLabel label = new JLabel("In The End");
		label.setFont(new Font("宋体", Font.BOLD, 35));
		label.setBounds(285, 58, 305, 72);
		add(label);
		
		JLabel lblNewLabel_1 = new JLabel("Dormitory Management System 2.0");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 30));
		lblNewLabel_1.setBounds(158, 160, 600, 70);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Develop By WeiQin");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 25));
		lblNewLabel_2.setBounds(390, 281, 386, 72);
		add(lblNewLabel_2);
		
		JButton btn=new JButton("支持开发者");
		btn.setFont(new Font("楷体", Font.BOLD, 22));
		btn.setBackground(new Color(0,191,255));
		btn.setBounds(440, 366, 150, 50);
		add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame f=new JFrame();
				f.setVisible(true);
				 ImageIcon img = new ImageIcon("src\\MyImage\\MyPayment.png");//这是背景图片
				  JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
				  f.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
				  imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置
				  Container cp=f.getContentPane();
				  cp.setLayout(new BorderLayout());
				  ((JPanel)cp).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
				  f.setBounds(400, 250, 310, 400);
				  f.setTitle("支持开发者");
			}
		});
		
		image = new ImageIcon("src\\MyImage\\Background.jpg");
		labImage = new JLabel(image);
		labImage.setBounds(0, 0, 800, 600);
		add(labImage);
		this.setOpaque(false);
		
	}

}

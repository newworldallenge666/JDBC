package com.dms.managerui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class About extends JPanel {

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
		
		JLabel label_1 = new JLabel("Thanks^_^");
		label_1.setFont(new Font("楷体", Font.BOLD, 30));
		label_1.setBounds(433, 366, 295, 72);
		add(label_1);
		
		image = new ImageIcon("src\\MyImage\\Background.jpg");
		labImage = new JLabel(image);
		labImage.setBounds(0, 0, 800, 600);
		add(labImage);
		this.setOpaque(false);
		
	}

}

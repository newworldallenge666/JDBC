package com.dms.managerui;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class First extends JPanel {

	/**
	 * 初始化界面
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labImage;
	private ImageIcon image;
	public First() {
		setSize(800,600);
		setLayout(null);	
	
		image = new ImageIcon("src\\MyImage\\Welcome.jpg");
		labImage = new JLabel(image);
		labImage.setBounds(0, 0, 800, 600);
		add(labImage);
		this.setOpaque(false);
		
	}
}
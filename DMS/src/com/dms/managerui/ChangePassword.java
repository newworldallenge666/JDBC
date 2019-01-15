package com.dms.managerui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.*;

import com.dms.information.UpdatePasswordInfo;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.PasswordNotMatchException;
import com.dms.myexceptioin.UpdateSuccessException;
import com.dms.people.Manager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ChangePassword extends JPanel {
	/**
	 * 修改密码面板
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField oldPwd; 
	private JPasswordField newPwd;
	private JPasswordField CheckNewPwd;
	private JButton btnOK;
	private JLabel LImage;
	private ImageIcon image;
	private Manager manager;
	

	public ChangePassword(Manager m) {
		this.manager = m;
		setSize(800,600);
        setLayout(null);
        
        JLabel LoldPwd = new JLabel("旧密码");
        LoldPwd.setFont(new Font("宋体", Font.PLAIN, 15));
        LoldPwd.setBounds(255, 197, 54, 15);
        add(LoldPwd);
        
        JLabel LnewPwd = new JLabel("新密码");
        LnewPwd.setFont(new Font("宋体", Font.PLAIN, 15));
        LnewPwd.setBounds(255, 267, 54, 15);
        add(LnewPwd);
        
        JLabel LCheckNewPwd = new JLabel("确认新密码");
        LCheckNewPwd.setFont(new Font("宋体", Font.PLAIN, 15));
        LCheckNewPwd.setBounds(255, 325, 93, 20);
        add(LCheckNewPwd);
        
        btnOK = new JButton("确认");
        btnOK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		UpdatePasswordInfo updatePasswordInfo = new UpdatePasswordInfo();
        		@SuppressWarnings("deprecation")
				String oldpwd=oldPwd.getText().trim();
        		@SuppressWarnings("deprecation")
				String newpwd=newPwd.getText().trim();
        		@SuppressWarnings("deprecation")
				String checknewpwd=CheckNewPwd.getText().trim();
        		if(oldpwd.equals("")||newpwd.equals("")||checknewpwd.equals(""))
        			JOptionPane.showMessageDialog(null, "请输入完整信息！", "修改密码失败", JOptionPane.WARNING_MESSAGE);
        		else if(oldpwd.equals(newpwd))
        			JOptionPane.showMessageDialog(null, "新旧密码相同！", "修改密码失败", JOptionPane.WARNING_MESSAGE);
        		else {
	        		updatePasswordInfo.setoldPwd(oldpwd);
	        		updatePasswordInfo.setnewPwd(newpwd);
	        		updatePasswordInfo.setCheckNewPwd(checknewpwd);
	        		if(updatePasswordInfo.getnewPwd().equals(updatePasswordInfo.getCheckNewPwd())){
	        			try {
							manager.PasswordUpdate(updatePasswordInfo);
						} catch (PasswordNotMatchException e) {
							JOptionPane.showMessageDialog(null, "旧密码错误,修改失败!", "系统信息", JOptionPane.WARNING_MESSAGE);
							//e.printStackTrace();
						} catch (DataBaseException e) {
							// TODO 自动生成的 catch 块
							JOptionPane.showMessageDialog(null, "数据库错误,修改失败!", "系统信息", JOptionPane.WARNING_MESSAGE);
							//e.printStackTrace();
						} catch (UpdateSuccessException e) {
							JOptionPane.showMessageDialog(null, "密码修改成功！", "系统信息", JOptionPane.WARNING_MESSAGE);
						}
	        		}else{
	        			JOptionPane.showMessageDialog(null, "请正确确认新密码！", "系统信息", JOptionPane.WARNING_MESSAGE);
	        		}
        		}
        	}
        });
        btnOK.setFont(new Font("宋体", Font.PLAIN, 15));
        btnOK.setBounds(370, 385, 100, 35);
        add(btnOK);
        
        oldPwd = new JPasswordField();
        oldPwd.setBounds(356, 186, 185, 35);
        add(oldPwd);
        
        newPwd = new JPasswordField();
        newPwd.setBounds(356, 253, 185, 35);
        add(newPwd);
        
        CheckNewPwd = new JPasswordField();
        CheckNewPwd.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
					btnOK.doClick(); 
				}
        	}
        });
        CheckNewPwd.setBounds(356, 314, 185, 35);
        add(CheckNewPwd);
        
        JLabel LChangePwd = new JLabel("修改密码");
        LChangePwd.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        LChangePwd.setBounds(320, 121, 115, 34);
        add(LChangePwd);
        
        image = new ImageIcon("src\\MyImage\\Background.jpg");
		LImage = new JLabel(image);
		LImage.setBounds(0, 0, 800, 600);
		add(LImage);
		this.setOpaque(false);
	}

}

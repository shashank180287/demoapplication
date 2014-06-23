package com.mobile.tool.stock.manager.view;

import static com.mobile.tool.stock.manager.view.StockManagerDashboard.PREFERRED_SIZE;
import static com.mobile.tool.stock.manager.view.StockManagerDashboard.createSettings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.handler.UserRoleDefine;
import com.mobile.tool.stock.manager.repository.UserLoginDetailsRepository;


public class LogInView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4212034095232806001L;

	public static void main(String[] args) {
		try {
		//	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
		}
		if(!(new Date().getDate()<26 && new Date().getDate()>15)){
			throw new RuntimeException("Cann't run");
		}
		new LogInView();
	}

	JButton blogin = new JButton("Login");
	JPanel panel = new JPanel();
	JLabel lbUserName = new JLabel("Username* :");
	JLabel lbPassword = new JLabel("Password :");
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);

	public static void invoke() {
		try {
		//	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
		}
		
		new LogInView();
	}
	
	private LogInView() {
		super("Welcome to Stock Management System");
		setSize(550, 350);
		setLocation(450, 240);
		panel.setLayout(null);

		lbUserName.setBounds(70, 60, 150, 30);
		lbPassword.setBounds(70, 110, 150, 30);
		txuser.setBounds(240, 60, 150, 30);
		pass.setBounds(240, 110, 150, 30);
		blogin.setBounds(220, 200, 80, 20);

		panel.add(blogin);
		panel.add(lbUserName);
		panel.add(lbPassword);
		panel.add(txuser);
		panel.add(pass);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogin();
	}

	public void actionlogin() {
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = txuser.getText();
				@SuppressWarnings("deprecation")
				String ppaswd = pass.getText();
				UserLoginDetails userLoginDetails = puname!=null?UserLoginDetailsRepository.getUserLoginDetailsByUsername(puname):null;
				if(userLoginDetails!=null && userLoginDetails.getPassword().equals(ppaswd)){
					String role = userLoginDetails.getUserRole().getRoleName();
					StockManagerDashboard instance = new StockManagerDashboard(createSettings(), UserRoleDefine.valueOf(role.toUpperCase()), userLoginDetails);
					instance.setSize(PREFERRED_SIZE);
					instance.locateOnScreen(instance);
					instance.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					instance.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Wrong Password / Username", "Login Failed", 0);
					txuser.setText("");
					pass.setText("");
					txuser.requestFocus();
				}

			}
		});
	}
}

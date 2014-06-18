package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.repository.CustomerRecordsRepository;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;
import com.mobile.tool.stock.manager.repository.UserLoginDetailsRepository;
import com.mobile.tool.stock.manager.repository.UserRoleRepository;

public class AddNewUserLoginDetailsListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, usernameLabel, passwordLabel, confirmPasswordLabel, userTypeLabel, userCodeLabel;
	JTextField usernameText, passwordText, userCodeText;
	JPasswordField confirmPasswordText;
	JComboBox<String> userTypeCombo;
	
	JButton createButton, clearButton, searchUserButton;
	WindowAdapter adapter;
	private AdminRoleMouseListener listener;
	
	public AddNewUserLoginDetailsListener(AdminRoleMouseListener listener)
			throws HeadlessException {
		super();
		this.listener = listener;
	}

	public void createAndShowGUI() {
		setVisible(true);
		setSize(600, 400);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("User Login Details:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		confirmPasswordLabel = new JLabel("Confirm Password:");
		userTypeLabel = new JLabel("Role:");
		userCodeLabel = new JLabel("User:");
		
		usernameText = new JTextField();
		passwordText = new JTextField();
		confirmPasswordText = new JPasswordField();
		userTypeCombo = new JComboBox<String>(new String[]{"Admin", "Employee", "Customer"});
		userTypeCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!"Admin".equalsIgnoreCase(userTypeCombo.getSelectedItem().toString())){
					userCodeText.setEnabled(true);
					searchUserButton.setEnabled(true);
					searchUserButton.addActionListener(new SearchUserButtonListener( AddNewUserLoginDetailsListener.this, userTypeCombo.getSelectedItem().toString()));
				}else{
					userCodeText.setEnabled(false);
					searchUserButton.setEnabled(false);
				}
				userCodeText.setText("");
				createButton.setEnabled(true);
				clearButton.setEnabled(true);
			}
		});
		userCodeText = new JTextField();
		userCodeText.setEnabled(false);
		
		createButton = new JButton("Create");
		createButton.setEnabled(false);
		clearButton = new JButton("Clear");
		clearButton.setEnabled(false);
		searchUserButton = new JButton("..");
		searchUserButton.setEnabled(false);

		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedRole = userTypeCombo.getSelectedItem().toString();
				UserLoginDetails userLoginDetails = new UserLoginDetails(usernameText.getText(), passwordText.getText(), 
						UserRoleRepository.getUserRolesByRoleName(selectedRole));
				UserLoginDetailsRepository.addUserLoginDetails(userLoginDetails);
				if("Customer".equalsIgnoreCase(selectedRole)){
					CustomerRecord customerRecord = CustomerRecordsRepository.getCustomerRecordByCustomerCode(userCodeText.getText());
					customerRecord.setUserLoginDetails(userLoginDetails);
					CustomerRecordsRepository.updateCustomerUserLoginDetails(customerRecord);
				}else if("Employee".equalsIgnoreCase(selectedRole)){
					EmployeeRecord employeeRecord = EmployeeRecordsRepository.getEmployeeRecordByEmployeeCode(userCodeText.getText());
					employeeRecord.setUserLoginDetail(userLoginDetails);
					EmployeeRecordsRepository.updateEmployeeUserLoginDetails(employeeRecord);
				}
				
				listener.reloadUserDetailsData();
				dispose();
			}
		});
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usernameText.setText("");
				passwordText.setText("");
				confirmPasswordText.setText("");
				userCodeText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		usernameLabel.setBounds(80, 70, 200, 30);
		passwordLabel.setBounds(80, 110, 200, 30);
		confirmPasswordLabel.setBounds(80, 150, 200, 30);
		userTypeLabel.setBounds(80, 190, 200, 30);
		userCodeLabel.setBounds(80, 230, 200, 30);

		usernameText.setBounds(300, 70, 200, 30);
		passwordText.setBounds(300, 110, 200, 30);
		confirmPasswordText.setBounds(300, 150, 200, 30);
		userTypeCombo.setBounds(300, 190, 200, 30);
		userCodeText.setBounds(300, 230, 160, 30);
		searchUserButton.setBounds(470, 230, 30, 30);
		createButton.setBounds(50, 260, 100, 30);
		clearButton.setBounds(170, 260, 100, 30);

		add(headline);
		add(usernameLabel);
		add(usernameText);
		add(passwordLabel);
		add(passwordText);
		add(confirmPasswordLabel);
		add(confirmPasswordText);
		add(userTypeLabel);
		add(userTypeCombo);
		add(userCodeLabel);
		add(userCodeText);
		add(confirmPasswordLabel);
		add(createButton);
		add(clearButton);
		add(searchUserButton);
		
		adapter = new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
			}
			
		};
		addWindowListener(adapter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		createAndShowGUI();

	}

	public void setUserCodeText(String categoryName) {
		this.userCodeText.setText(categoryName);
		this.revalidate();
	}
}

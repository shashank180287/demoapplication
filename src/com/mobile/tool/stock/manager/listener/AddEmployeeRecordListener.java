package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;

public class AddEmployeeRecordListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, firstNameLabel, mobileNumberLabel, lastNameLabel, genderLabel, qualificationLabel, professionLabel,  ageLabel, maritalStatusLabel, jobDescriptionLabel, emailLabel, jobTitleLabel, contactAddressLabel, managerLabel;
	JTextField firstNameText, mobileNoText, lastNameText, qualificationText, professionText, ageText, jobDescriptionText, emailText, jobTitleText, managerText;
	JTextArea contactAddressText;
	JComboBox<String> genderComboBox, maritalComboBox;
	JButton createButton, clearButton, searchManagerButton;
	WindowAdapter adapter;
	
	private AdminRoleMouseListener listener;
	
	public AddEmployeeRecordListener(AdminRoleMouseListener listener) {
		this.listener = listener;
	}
	
	public void createAndShowGUI() {
		setVisible(true);
		setSize(700, 800);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Employee Record:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		firstNameLabel = new JLabel("First Name:");
		mobileNumberLabel = new JLabel("Mobile Number:");
		lastNameLabel = new JLabel("Last Name:");
		genderLabel = new JLabel("Gender:");
		qualificationLabel = new JLabel("Qualification:");
		professionLabel = new JLabel("Profession:");
		ageLabel = new JLabel("Age:");
		maritalStatusLabel = new JLabel("Marital Status:");
		jobDescriptionLabel = new JLabel("Job Description:");
		emailLabel = new JLabel("Email:");
		jobTitleLabel = new JLabel("Job Title:");
		contactAddressLabel = new JLabel("Contact Address:");
		managerLabel = new JLabel("Manager:");
		
		
		firstNameText = new JTextField();
		mobileNoText = new JTextField();
		String[] genderOptions = new String[]{"Male", "Female"};
		genderComboBox = new JComboBox<String>(genderOptions);
		String[] maritalStatusOptions = new String[]{"Married", "Unmarried"};
		maritalComboBox = new JComboBox<String>(maritalStatusOptions);
		contactAddressText = new JTextArea();
		jobDescriptionText = new JTextField();
		emailText = new JTextField();
		jobTitleText = new JTextField();
		managerText = new JTextField();
		lastNameText = new JTextField();
		qualificationText = new JTextField();
		mobileNoText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		professionText = new JTextField();
		ageText = new JTextField();
		ageText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		createButton = new JButton("Create");
		clearButton = new JButton("Clear");
		searchManagerButton = new JButton("..");
		searchManagerButton.addActionListener(new SearchEmployeerButtonListener(this));
		
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeRecord employeeRecord = new EmployeeRecord(null, firstNameText.getText(), lastNameText.getText(),
						(mobileNoText.getText()!=null? Integer.valueOf(mobileNoText.getText()):0), genderComboBox.getSelectedItem().toString(),
						qualificationText.getText(), professionText.getText(), (ageText.getText()!=null?Integer.valueOf(ageText.getText()):0), 
						maritalComboBox.getSelectedItem().toString(), jobDescriptionText.getText(), EmployeeRecordsRepository.getEmployeeRecordByEmployeeCode(managerText.getText()), 
						emailText.getText(), new Date(System.currentTimeMillis()), jobTitleText.getText(), contactAddressText.getText(), null, null, null, null);
				EmployeeRecordsRepository.addNewEmployeeRecord(employeeRecord);
				listener.reloadEmployeeTableData();
				dispose();
			}
		});
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				firstNameText.setText("");
				mobileNoText.setText("");
				lastNameText.setText("");
				qualificationText.setText("");
				professionText.setText("");
				ageText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		firstNameLabel.setBounds(80, 70, 200, 30);
		lastNameLabel.setBounds(80, 110, 200, 30);
		mobileNumberLabel.setBounds(80, 150, 200, 30);
		genderLabel.setBounds(80, 190, 200, 30);
		qualificationLabel.setBounds(80, 230, 200, 30);
		professionLabel.setBounds(80, 270, 200, 30);
		ageLabel.setBounds(80, 310, 200, 30);
		maritalStatusLabel.setBounds(80, 350, 200, 30);
		jobDescriptionLabel.setBounds(80, 390, 200, 30);
		emailLabel.setBounds(80, 430, 200, 30);
		jobTitleLabel.setBounds(80, 470, 200, 30);
		contactAddressLabel.setBounds(80, 510, 200, 30);
		managerLabel.setBounds(80, 590, 200, 30);
		
		firstNameText.setBounds(300, 70, 200, 30);
		lastNameText.setBounds(300, 110, 200, 30);
		mobileNoText.setBounds(300, 150, 200, 30);
		genderComboBox.setBounds(300, 190, 200, 30);
		qualificationText.setBounds(300, 230, 200, 30);
		professionText.setBounds(300, 270, 200, 30);
		ageText.setBounds(300, 310, 200, 30);
		maritalComboBox.setBounds(300, 350, 200, 30);
		jobDescriptionText.setBounds(300, 390, 200, 30);
		emailText.setBounds(300, 430, 200, 30);
		jobTitleText.setBounds(300, 470, 200, 30);
		contactAddressText.setBounds(300, 510, 200, 60);
		managerText.setBounds(300, 590, 160, 30);
		searchManagerButton.setBounds(470, 590, 30, 30);
		createButton.setBounds(50, 630, 100, 30);
		clearButton.setBounds(170, 630, 100, 30);

		add(headline);
		add(firstNameLabel);
		add(firstNameText);
		add(mobileNumberLabel);
		add(mobileNoText);
		add(lastNameLabel);
		add(lastNameText);
		add(genderLabel);
		add(qualificationText);
		add(qualificationLabel);
		add(professionText);
		add(professionLabel);
		add(ageText);
		add(ageLabel);
		add(genderComboBox);
		add(maritalStatusLabel);
		add(jobDescriptionText);
		add(jobDescriptionLabel);
		add(emailText);
		add(jobTitleLabel); 
		add(contactAddressLabel); 
		add(managerLabel);
		add(jobTitleText);
		add(managerText);
		add(maritalComboBox);
		add(emailLabel);
		add(contactAddressText);
		add(searchManagerButton);
		add(createButton);
		add(clearButton);
		
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

	public void setManagerText(String employeeCode) {
		this.managerText.setText(employeeCode);
		this.revalidate();
	}
}

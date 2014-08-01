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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.mobile.tool.stock.manager.designgrid.ui.AddEmployeeQualification;
import com.mobile.tool.stock.manager.entity.EmployeeProfessionalDetails;
import com.mobile.tool.stock.manager.entity.EmployeeQualificationDetails;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.repository.EmployeeProfessionDetailsRepository;
import com.mobile.tool.stock.manager.repository.EmployeeQualificationDetailsRepository;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;

public class AddEmployeeRecordListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, firstNameLabel, mobileNumberLabel, lastNameLabel,
			genderLabel, stateOfOriginLabel, localGovtLabel, ageLabel,
			maritalStatusLabel, jobDescriptionLabel, emailLabel, jobTitleLabel,
			contactAddressLabel, managerLabel, religionLabel,
			idProofDocNameLabel, idProofDocNoLabel, reference1Label,
			reference2Label, reference3Label;
	JTextField managerText;
	JComboBox<String> genderComboBox, maritalComboBox, idProofDocNameComboBox;
	JButton createButton, clearButton, searchManagerButton,
			addQualificationButton, addProfessionButton;
	WindowAdapter adapter;
	List<EmployeeQualificationDetails> employeeQualificationDetailsList;
	List<EmployeeProfessionalDetails> employeeProfessionDetailsList;
	
	private AdminRoleMouseListener listener;

	public AddEmployeeRecordListener(AdminRoleMouseListener listener) {
		this.listener = listener;
	}

	public void createAndShowGUI() {
		setVisible(true);
		setSize(1400, 800);
		setLayout(null);
		setTitle("Stock Management");

		employeeQualificationDetailsList = new ArrayList<>();
		
		headline = new JLabel("Employee Record:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		firstNameLabel = new JLabel("First Name:");
		mobileNumberLabel = new JLabel("Mobile Number:");
		lastNameLabel = new JLabel("Last Name:");
		genderLabel = new JLabel("Gender:");
		stateOfOriginLabel = new JLabel("State Of Origin:");
		localGovtLabel = new JLabel("Local Govt.:");
		ageLabel = new JLabel("Age:");
		maritalStatusLabel = new JLabel("Marital Status:");
		jobDescriptionLabel = new JLabel("Job Description:");
		emailLabel = new JLabel("Email:");
		jobTitleLabel = new JLabel("Job Title:");
		contactAddressLabel = new JLabel("Contact Address:");
		managerLabel = new JLabel("Manager:");
		religionLabel = new JLabel("Religion:");
		idProofDocNameLabel = new JLabel("Id Proof:");
		idProofDocNoLabel = new JLabel("Id Proof Doc Number:");
		reference1Label = new JLabel("Reference(1):");
		reference2Label = new JLabel("Reference(2):");
		reference3Label = new JLabel("Reference(3):");
		
		final JTextField firstNameText = new JTextField();
		final JTextField mobileNoText = new JTextField();
		String[] genderOptions = new String[] { "Male", "Female" };
		genderComboBox = new JComboBox<String>(genderOptions);
		String[] maritalStatusOptions = new String[] { "Married", "Unmarried" };
		maritalComboBox = new JComboBox<String>(maritalStatusOptions);
		final JTextArea contactAddressText = new JTextArea();
		final JTextField jobDescriptionText = new JTextField();
		final JTextField emailText = new JTextField();
		final JTextField jobTitleText = new JTextField();
		managerText = new JTextField();
		managerText.setEditable(false);
		final JTextField lastNameText = new JTextField();
		final JTextField stateOfOriginText = new JTextField();
		mobileNoText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField LocalGovtText = new JTextField();
		final JTextField ageText = new JTextField();
		ageText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField religionText = new JTextField();
		idProofDocNameComboBox = new JComboBox<String>(new String[] {
				"National ID", "Driver’s License", "Voter’s Card", "Passport" });
		final JTextField idProofDocNoText = new JTextField();
		final JTextField reference1Text = new JTextField();
		final JTextField reference2Text = new JTextField();
		final JTextField reference3Text = new JTextField();
		
		createButton = new JButton("Create");
		clearButton = new JButton("Clear");
		searchManagerButton = new JButton("..");
		searchManagerButton
				.addActionListener(new SearchEmployeerButtonListener(this));
		addQualificationButton = new JButton("Qualification");
		addQualificationButton
				.addActionListener(new AddEmployeeQualification(this));
		addProfessionButton = new JButton("Experience");
		addProfessionButton
				.addActionListener(new AddEmployeeProfession(this));

		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeRecord employeeRecord = new EmployeeRecord(
						null,
						firstNameText.getText(),
						lastNameText.getText(),
						(!StringUtils.isEmpty(mobileNoText.getText()) ? Long
								.parseLong(mobileNoText.getText()) : 0),
						genderComboBox.getSelectedItem().toString(),
						stateOfOriginText.getText(),
						LocalGovtText.getText(),
						(!StringUtils.isEmpty(ageText.getText()) ? Integer
								.valueOf(ageText.getText()) : 0),
						maritalComboBox.getSelectedItem().toString(),
						jobDescriptionText.getText(),
						StringUtils.isEmpty(managerText.getText()) ? null
								: EmployeeRecordsRepository
										.getEmployeeRecordByEmployeeCode(managerText
												.getText()), emailText
								.getText(),
						new Date(System.currentTimeMillis()), jobTitleText
								.getText(), contactAddressText.getText(), null,
						reference1Text.getText(), reference2Text.getText(), reference3Text.getText(), religionText.getText(),
						idProofDocNameComboBox.getSelectedItem().toString(),
						idProofDocNoText.getText());
				String employeeCode = EmployeeRecordsRepository.addNewEmployeeRecord(employeeRecord);
				EmployeeRecord empRecordAfterInsertion = EmployeeRecordsRepository.getEmployeeRecordByEmployeeCode(employeeCode);
				
				if(employeeQualificationDetailsList!=null){
					for (EmployeeQualificationDetails employeeQualificationDetails : employeeQualificationDetailsList) {
						employeeQualificationDetails.setEmpolyee(empRecordAfterInsertion);
						EmployeeQualificationDetailsRepository.addEmployeeQualificationDetails(employeeQualificationDetails);
					}
				}
				
				if(employeeProfessionDetailsList!=null){
					for (EmployeeProfessionalDetails employeeProfessionalDetails : employeeProfessionDetailsList) {
						employeeProfessionalDetails.setEmpolyee(empRecordAfterInsertion);
						EmployeeProfessionDetailsRepository.addEmployeeProfessionalDetails(employeeProfessionalDetails);
					}
				}
				
				listener.reloadEmployeeTableData();
				firstNameText.setText("");
				lastNameText.setText("");
				mobileNoText.setText("");
				stateOfOriginText.setText("");
				LocalGovtText.setText("");
				ageText.setText("");
				jobDescriptionText.setText("");
				emailText.setText("");
				jobTitleText.setText("");
				contactAddressText.setText("");
				managerText.setText("");
				religionText.setText("");
				idProofDocNoText.setText("");
				reference1Text.setText("");
				reference2Text.setText("");
				reference3Text.setText("");
				dispose();
			}
		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				firstNameText.setText("");
				mobileNoText.setText("");
				lastNameText.setText("");
				stateOfOriginText.setText("");
				LocalGovtText.setText("");
				ageText.setText("");
				jobDescriptionText.setText("");
				emailText.setText("");
				jobTitleText.setText("");
				contactAddressText.setText("");
				managerText.setText("");
				religionText.setText("");
				idProofDocNoText.setText("");
				reference1Text.setText("");
				reference2Text.setText("");
				reference3Text.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		firstNameLabel.setBounds(80, 70, 200, 30);
		lastNameLabel.setBounds(80, 110, 200, 30);
		mobileNumberLabel.setBounds(80, 150, 200, 30);
		genderLabel.setBounds(80, 190, 200, 30);
		stateOfOriginLabel.setBounds(80, 230, 200, 30);
		localGovtLabel.setBounds(80, 270, 200, 30);
		ageLabel.setBounds(80, 310, 200, 30);
		maritalStatusLabel.setBounds(80, 350, 200, 30);
		jobDescriptionLabel.setBounds(80, 390, 200, 30);
		emailLabel.setBounds(80, 430, 200, 30);
		jobTitleLabel.setBounds(80, 470, 200, 30);
		contactAddressLabel.setBounds(80, 510, 200, 30);
		managerLabel.setBounds(80, 590, 200, 30);
		religionLabel.setBounds(700, 70, 200, 30);
		idProofDocNameLabel.setBounds(700, 110, 200, 30);
		idProofDocNoLabel.setBounds(700, 150, 200, 30);
		reference1Label.setBounds(700, 190, 200, 30);
		reference2Label.setBounds(700, 230, 200, 30);
		reference3Label.setBounds(700, 270, 200, 30);
		
		firstNameText.setBounds(300, 70, 200, 30);
		lastNameText.setBounds(300, 110, 200, 30);
		mobileNoText.setBounds(300, 150, 200, 30);
		genderComboBox.setBounds(300, 190, 200, 30);
		stateOfOriginText.setBounds(300, 230, 200, 30);
		LocalGovtText.setBounds(300, 270, 200, 30);
		ageText.setBounds(300, 310, 200, 30);
		maritalComboBox.setBounds(300, 350, 200, 30);
		jobDescriptionText.setBounds(300, 390, 200, 30);
		emailText.setBounds(300, 430, 200, 30);
		jobTitleText.setBounds(300, 470, 200, 30);
		contactAddressText.setBounds(300, 510, 200, 60);
		managerText.setBounds(300, 590, 160, 30);
		religionText.setBounds(940, 70, 200, 30);
		idProofDocNameComboBox.setBounds(940, 110, 200, 30);
		idProofDocNoText.setBounds(940, 150, 200, 30);
		reference1Text.setBounds(940, 190, 200, 30);
		reference2Text.setBounds(940, 230, 200, 30);
		reference3Text.setBounds(940, 270, 200, 30);
		searchManagerButton.setBounds(470, 590, 30, 30);
		addQualificationButton.setBounds(50, 630, 100, 30);
		addProfessionButton.setBounds(170, 630, 100, 30);
		createButton.setBounds(290, 630, 100, 30);
		clearButton.setBounds(410, 630, 100, 30);

		add(headline);
		add(firstNameLabel);
		add(firstNameText);
		add(mobileNumberLabel);
		add(mobileNoText);
		add(lastNameLabel);
		add(lastNameText);
		add(genderLabel);
		add(stateOfOriginText);
		add(stateOfOriginLabel);
		add(LocalGovtText);
		add(localGovtLabel);
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
		add(reference1Label);
		add(reference2Label);
		add(reference3Label);
		add(jobTitleText);
		add(managerText);
		add(maritalComboBox);
		add(emailLabel);
		add(contactAddressText);
		add(religionLabel);
		add(religionText);
		add(idProofDocNameLabel);
		add(idProofDocNameComboBox);
		add(idProofDocNoLabel);
		add(idProofDocNoText);
		add(reference1Text);
		add(reference2Text);
		add(reference3Text);
		add(addProfessionButton);
		add(searchManagerButton);
		add(addQualificationButton);
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

	public void setEmployeeQualificationDetailsList(
			List<EmployeeQualificationDetails> employeeQualificationDetailsList) {
		this.employeeQualificationDetailsList = employeeQualificationDetailsList;
	}
	
	public void addEmployeeQualificationDetailsList(
			EmployeeQualificationDetails employeeQualificationDetails) {
		this.employeeQualificationDetailsList.add(employeeQualificationDetails);
	}

	public void setEmployeeProfessionDetailsList(
			List<EmployeeProfessionalDetails> employeeProfessionDetailsList) {
		this.employeeProfessionDetailsList = employeeProfessionDetailsList;
	}
	
	public void addEmployeeProfessionDetailsList(
			EmployeeProfessionalDetails employeeProfessionDetails) {
		this.employeeProfessionDetailsList.add(employeeProfessionDetails);
	}
	
}

package com.mobile.tool.stock.manager.ui.listener.updater;

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
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

import com.mobile.tool.stock.manager.entity.EmployeePayrollDetails;
import com.mobile.tool.stock.manager.repository.EmployeePayrollDetailsRepository;
import com.mobile.tool.stock.manager.view.HumanResourceTab;

public class UpdateEmployeePayrollDetailsListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, employeeCodeLabel, departmentLabel, sickLeavesLabel, casualLeavesLabel, annualLeavesLabel, studyLeavesLabel, 
					otherLabel, daysOfLeavesLabel, leaveStartDateLabel, leaveEndDateLabel, loanNameLabel, intertestRateLabel,
					gradeLevelLabel, allowancesLabel, initialSalaryLabel, upgradeSalaryLabel, employmentTypeLabel;
	JButton updateButton, cancelButton;
	WindowAdapter adapter;
	JFrame frame;

	private HumanResourceTab tab;
	private EmployeePayrollDetails employeePayrollDetails;

	public UpdateEmployeePayrollDetailsListener(HumanResourceTab tab, EmployeePayrollDetails employeePayrollDetails) {
		this.tab = tab;
		this.employeePayrollDetails = employeePayrollDetails;
	}

	@SuppressWarnings("unchecked")
	public void createAndShowGUI() {
		setVisible(true);
		setSize(1200, 600);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Payroll Record:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		employeeCodeLabel = new JLabel("Employee Code:");
		departmentLabel = new JLabel("Department:");
		sickLeavesLabel = new JLabel("Sick Leaves:");
		casualLeavesLabel = new JLabel("Casual Leaves:");
		annualLeavesLabel = new JLabel("Annual Leaves:");
		studyLeavesLabel = new JLabel("Study Leaves:");
		otherLabel = new JLabel("Other:");
		daysOfLeavesLabel = new JLabel("Days Of Leaves:");
		leaveStartDateLabel = new JLabel("Leaves Start Date:");
		leaveEndDateLabel = new JLabel("Leaves End Date:");
		loanNameLabel = new JLabel("Loan Name:");
		intertestRateLabel = new JLabel("Interest Rate:");
		gradeLevelLabel = new JLabel("Grade Level:");
		allowancesLabel = new JLabel("Allowance:");
		initialSalaryLabel = new JLabel("Initial Salary:");
		upgradeSalaryLabel = new JLabel("Upgrade Salary:");
		employmentTypeLabel = new JLabel("Employment Type:");


		final JTextField employeeCodeText = new JTextField();
		employeeCodeText.setEditable(false);
		employeeCodeText.setText(employeePayrollDetails.getEmployeeRecord().getEmployeeCode());
		final JTextField departmentText = new JTextField();
		departmentText.setText(employeePayrollDetails.getDepartment());
		final JTextField sickLeavesText = new JTextField();
		sickLeavesText.setText(employeePayrollDetails.getSickLeaves()+"");
		sickLeavesText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField casualLeavesText = new JTextField();
		casualLeavesText.setText(employeePayrollDetails.getCasualLeaves()+"");
		casualLeavesText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField annualLeavesText = new JTextField();
		annualLeavesText.setText(employeePayrollDetails.getAnnualLeaves()+"");
		annualLeavesText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField studyLeavesText = new JTextField();
		studyLeavesText.setText(employeePayrollDetails.getStudyLeaves()+"");
		studyLeavesText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField otherText = new JTextField();
		otherText.setText(employeePayrollDetails.getOther()+"");
		otherText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField daysOfLeavesText = new JTextField();
		daysOfLeavesText.setText(employeePayrollDetails.getDaysOfLeaves()+"");
		daysOfLeavesText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField loanNameText = new JTextField();
		loanNameText.setText(employeePayrollDetails.getLoanName());
		final JTextField intertestRateText = new JTextField();
		intertestRateText.setText(employeePayrollDetails.getIntertestRate()+"");
		intertestRateText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField gradeLevelText = new JTextField();
		gradeLevelText.setText(employeePayrollDetails.getGradeLevel());
		final JTextField allowancesText = new JTextField();
		allowancesText.setText(employeePayrollDetails.getAllowances()+"");
		allowancesText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField initialSalaryText = new JTextField();
		initialSalaryText.setText(employeePayrollDetails.getInitialSalary()+"");
		initialSalaryText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField upgradeSalaryText = new JTextField();
		upgradeSalaryText.setText(employeePayrollDetails.getUpgradeSalary()+"");
		upgradeSalaryText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JDatePickerImpl leaveStartDatePicker = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		((DateModel<Date>)leaveStartDatePicker.getModel()).setValue(employeePayrollDetails.getLeaveStartDate());
		final JDatePickerImpl leaveEndDatePicker = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		((DateModel<Date>)leaveEndDatePicker.getModel()).setValue(employeePayrollDetails.getLeaveEndDate());

		String[] petStrings = {"Permanent", "Contractor"};
		final JComboBox<String> employmentTypeComboBox = new JComboBox<String>(petStrings);
		employmentTypeComboBox.setSelectedItem(employeePayrollDetails.getEmploymentType());

		updateButton = new JButton("Update");
		cancelButton = new JButton("Cancel");

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				employeePayrollDetails.setDepartment(departmentText.getText());
				employeePayrollDetails.setSickLeaves((sickLeavesText.getText()!=null && sickLeavesText.getText().length()>0?Double.parseDouble(sickLeavesText.getText()):0.0));
				employeePayrollDetails.setCasualLeaves((casualLeavesText.getText()!=null && casualLeavesText.getText().length()>0?Double.parseDouble(casualLeavesText.getText()):0.0));
				employeePayrollDetails.setAnnualLeaves((annualLeavesText.getText()!=null && annualLeavesText.getText().length()>0?Double.parseDouble(annualLeavesText.getText()):0.0));
				employeePayrollDetails.setStudyLeaves((studyLeavesText.getText()!=null && studyLeavesText.getText().length()>0?Double.parseDouble(studyLeavesText.getText()):0.0));
				employeePayrollDetails.setOther((otherText.getText()!=null && otherText.getText().length()>0?Double.parseDouble(otherText.getText()):0.0));
				employeePayrollDetails.setDaysOfLeaves((daysOfLeavesText.getText()!=null && daysOfLeavesText.getText().length()>0?Double.parseDouble(daysOfLeavesText.getText()):0.0));
				employeePayrollDetails.setLeaveStartDate((Date)leaveStartDatePicker.getModel().getValue());
				employeePayrollDetails.setLeaveEndDate((Date)leaveEndDatePicker.getModel().getValue());
				employeePayrollDetails.setLoanName(loanNameText.getText());
				employeePayrollDetails.setIntertestRate((intertestRateText.getText()!=null && intertestRateText.getText().length()>0?Double.parseDouble(intertestRateText.getText()):0.0));
				employeePayrollDetails.setGradeLevel(gradeLevelText.getText());
				employeePayrollDetails.setAllowances((allowancesText.getText()!=null && allowancesText.getText().length()>0?Double.parseDouble(allowancesText.getText()):0.0));
				employeePayrollDetails.setInitialSalary((initialSalaryText.getText()!=null && initialSalaryText.getText().length()>0?Double.parseDouble(initialSalaryText.getText()):0.0));
				employeePayrollDetails.setUpgradeSalary((upgradeSalaryText.getText()!=null && upgradeSalaryText.getText().length()>0?Double.parseDouble(upgradeSalaryText.getText()):0.0));
				employeePayrollDetails.setEmploymentType(employmentTypeComboBox.getSelectedItem().toString());

				EmployeePayrollDetailsRepository.updateEmployeePayrollDetails(employeePayrollDetails);

				tab.reloadPayrollTableDataForEmployeeCode(employeePayrollDetails.getEmployeeRecord().getEmployeeCode());

				employeeCodeText.setText("");
				departmentText.setText("");
				sickLeavesText.setText("");
				casualLeavesText.setText("");
				annualLeavesText.setText("");
				studyLeavesText.setText("");
				otherText.setText("");
				daysOfLeavesText.setText("");
				loanNameText.setText("");
				intertestRateText.setText("");
				gradeLevelText.setText("");
				allowancesText.setText("");
				initialSalaryText.setText("");
				upgradeSalaryText.setText("");
				dispose();
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		headline.setBounds(80, 30, 400, 30);
		employeeCodeLabel.setBounds(80, 70, 200, 30);
		departmentLabel.setBounds(80, 110, 200, 30);
		sickLeavesLabel.setBounds(80, 150, 200, 30);
		casualLeavesLabel.setBounds(80, 190, 200, 30);
		annualLeavesLabel.setBounds(80, 230, 200, 30);
		studyLeavesLabel.setBounds(80, 270, 200, 30);
		otherLabel.setBounds(80, 310, 200, 30);
		daysOfLeavesLabel.setBounds(80, 350, 200, 30);
		leaveStartDateLabel.setBounds(80, 390, 200, 30);
		leaveEndDateLabel.setBounds(80, 430, 200, 30);
		loanNameLabel.setBounds(550, 70, 200, 30);
		intertestRateLabel.setBounds(550, 110, 200, 30);
		gradeLevelLabel.setBounds(550, 150, 200, 30);
		allowancesLabel.setBounds(550, 190, 200, 30);
		initialSalaryLabel.setBounds(550, 230, 200, 30);
		upgradeSalaryLabel.setBounds(550, 270, 200, 30);
		employmentTypeLabel.setBounds(550, 310, 200, 30);

		employeeCodeText.setBounds(300, 70, 200, 30);
		departmentText.setBounds(300, 110, 200, 30);
		sickLeavesText.setBounds(300, 150, 200, 30);
		casualLeavesText.setBounds(300, 190, 200, 30);
		annualLeavesText.setBounds(300, 230, 200, 30);
		studyLeavesText.setBounds(300, 270, 200, 30);
		otherText.setBounds(300, 310, 200, 30);
		daysOfLeavesText.setBounds(300, 350, 200, 30);
		leaveStartDatePicker.setBounds(300, 390, 200, 30);
		leaveEndDatePicker.setBounds(300, 430, 200, 30);
		loanNameText.setBounds(770, 70, 200, 30);
		intertestRateText.setBounds(770, 110, 200, 30);
		gradeLevelText.setBounds(770, 150, 200, 30);
		allowancesText.setBounds(770, 190, 200, 30);
		initialSalaryText.setBounds(770, 230, 200, 30);
		upgradeSalaryText.setBounds(770, 270, 200, 30);
		employmentTypeComboBox.setBounds(770, 310, 200, 30);

		updateButton.setBounds(50, 470, 100, 30);
		cancelButton.setBounds(170, 470, 100, 30);

		add(headline);
		add(employeeCodeLabel);
		add(departmentLabel);
		add(sickLeavesLabel);
		add(casualLeavesLabel);
		add(annualLeavesLabel);
		add(studyLeavesLabel);
		add(otherLabel);
		add(daysOfLeavesLabel);
		add(leaveStartDateLabel);
		add(leaveEndDateLabel);
		add(loanNameLabel);
		add(intertestRateLabel);
		add(gradeLevelLabel);
		add(allowancesLabel);
		add(initialSalaryLabel);
		add(upgradeSalaryLabel);
		add(employmentTypeLabel);
		add(employeeCodeText);
		add(departmentText);
		add(sickLeavesText);
		add(casualLeavesText);
		add(annualLeavesText);
		add(studyLeavesText);
		add(otherText);
		add(daysOfLeavesText);
		add(loanNameText);
		add(intertestRateText);
		add(gradeLevelText);
		add(allowancesText);
		add(initialSalaryText);
		add(upgradeSalaryText);
		add(leaveStartDatePicker);
		add(leaveEndDatePicker);
		add(employmentTypeComboBox);
		add(updateButton);
		add(cancelButton);

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

}

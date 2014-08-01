package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.EmployeeProfessionalDetails;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

public class AddEmployeeProfessionDetails extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, companyLabel, designationLabel, startDateLabel, endDateLabel, responsibityLabel;
	JButton createButton, cancelButton;
	WindowAdapter adapter;
	
	private AddEmployeeProfession listener;
	
	public AddEmployeeProfessionDetails(AddEmployeeProfession listener) {
		this.listener = listener;
	}
	
	public void createAndShowGUI() {
		setVisible(true);
		setSize(700, 350);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Employee Experience:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		companyLabel = new JLabel("Company:");
		designationLabel = new JLabel("Job Title:");
		startDateLabel = new JLabel("Start Date:");
		endDateLabel = new JLabel("End Date:");
		responsibityLabel = new JLabel("Responsibilities:");	
		
		final JTextField companyText = new JTextField();
		final JTextField designationText = new JTextField();
		final JDatePickerImpl startDatePicker = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		final JDatePickerImpl endDatePicker = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		final JTextField resposibitiesText = new JTextField();
		
		createButton = new JButton("Create");
		cancelButton = new JButton("Cancel");
		
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.addEmployeeProfessionalDetails(new EmployeeProfessionalDetails(0, null, companyText.getText(), 
						designationText.getText(), (Date) startDatePicker.getModel().getValue(), (Date) endDatePicker.getModel().getValue(),
						resposibitiesText.getText()));
				listener.reloadEmployeeProfessionalDetails();;
				companyText.setText("");
				designationText.setText("");
				resposibitiesText.setText("");
				dispose();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				companyText.setText("");
				designationText.setText("");
				resposibitiesText.setText("");
				dispose();
			}
		});

		headline.setBounds(80, 30, 400, 30);
		companyLabel.setBounds(80, 70, 200, 30);
		designationLabel.setBounds(80, 110, 200, 30);
		startDateLabel.setBounds(80, 150, 200, 30);
		endDateLabel.setBounds(80, 190, 200, 30);
		responsibityLabel.setBounds(80, 230, 200, 30);
		
		companyText.setBounds(300, 70, 200, 30);
		designationText.setBounds(300, 110, 200, 30);
		startDatePicker.setBounds(300, 150, 200, 30);
		endDatePicker.setBounds(300, 190, 200, 30);
		resposibitiesText.setBounds(300, 230, 200, 30);
		createButton.setBounds(50, 280, 100, 30);
		cancelButton.setBounds(170, 280, 100, 30);

		add(headline);
		add(companyLabel);
		add(designationLabel);
		add(startDateLabel);
		add(endDateLabel);
		add(responsibityLabel);
		add(companyText);
		add(designationText);
		add(startDatePicker);
		add(endDatePicker);
		add(resposibitiesText);
		add(createButton);
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
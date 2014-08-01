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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.VisitorEnquiryDetail;
import com.mobile.tool.stock.manager.repository.VisitorEnquiryDetailRepository;

public class AddVisitorEnquiryDetailListener extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7055821904254908209L;

	JLabel headline, visitorNameLabel, visitorMobileNoLabel, visitorAddressLabel, visitorPurposeLabel, commentLabel;
	JButton createButton, clearButton;
	WindowAdapter adapter;
	JFrame frame;

	private AdminRoleMouseListener adminListener;
	private EmployeeRoleMouseListener employeeListener;

	public AddVisitorEnquiryDetailListener(AdminRoleMouseListener adminListener) {
		super();
		this.adminListener = adminListener;
		this.employeeListener = null;
	}

	public AddVisitorEnquiryDetailListener(
			EmployeeRoleMouseListener employeeListener) {
		super();
		this.adminListener = null;
		this.employeeListener = employeeListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		createAndShowGUI();
	}

	public void createAndShowGUI() {
		setVisible(true);
		setSize(700, 400);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Add Visitor Enquiry:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		visitorNameLabel = new JLabel("Visitor Name:");
		visitorMobileNoLabel = new JLabel("Mobile Number:");
		visitorAddressLabel = new JLabel("Address:");
		visitorPurposeLabel = new JLabel("Visiting Purpose:");
		commentLabel = new JLabel("Comment:");

		final JTextField visitorNameText = new JTextField();
		final JTextField visitorMobileNoText = new JTextField();
		visitorMobileNoText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextArea visitorAddressText = new JTextArea();
		final JTextField visitingPurposeText = new JTextField();
		final JTextField commentText = new JTextField();

		createButton = new JButton("Create");
		clearButton = new JButton("Clear");

		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VisitorEnquiryDetailRepository.addVisitorEnquiryDetail(new VisitorEnquiryDetail(0, visitorNameText.getText(), 
						((visitorMobileNoText.getText()!=null)? Long.parseLong(visitorMobileNoText.getText()):null), visitorAddressText.getText(), 
						visitingPurposeText.getText(), new Date(System.currentTimeMillis()), commentText.getText()));

				if(adminListener!=null)
					adminListener.reloadVisitorEnquiryDetailTableData();
				else if(employeeListener!=null)
					employeeListener.reloadVisitorEnquiryDetailTableData();

				visitorNameText.setText("");
				visitorMobileNoText.setText("");
				visitorAddressText.setText("");
				visitingPurposeText.setText("");
				commentText.setText("");
				dispose();
			}
		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				visitorNameText.setText("");
				visitorMobileNoText.setText("");
				visitorAddressText.setText("");
				visitingPurposeText.setText("");
				commentText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		visitorNameLabel.setBounds(80, 70, 200, 30);
		visitorMobileNoLabel.setBounds(80, 110, 200, 30);
		visitorAddressLabel.setBounds(80, 150, 200, 30);
		visitorPurposeLabel.setBounds(80, 230, 200, 30);
		commentLabel.setBounds(80, 270, 200, 30);

		visitorNameText.setBounds(300, 70, 200, 30);
		visitorMobileNoText.setBounds(300, 110, 200, 30);
		visitorAddressText.setBounds(300, 150, 200, 60);
		visitingPurposeText.setBounds(300, 230, 200, 30);
		commentText.setBounds(300, 270, 200, 30);
		createButton.setBounds(50, 310, 100, 30);
		clearButton.setBounds(170, 310, 100, 30);

		add(headline);
		add(visitorNameLabel);
		add(visitorMobileNoLabel);
		add(visitorAddressLabel);
		add(visitorPurposeLabel);
		add(commentLabel);
		add(visitorNameText);
		add(visitorMobileNoText);
		add(visitorAddressText);
		add(visitingPurposeText);
		add(commentText);
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

}
package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.repository.CustomerRecordsRepository;

public class AddCustomerRecordListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, firstNameLabel, mobileNoLabel, lastNameLabel, emailLabel, addressLabel, websiteLabel,  genderLabel, descriptionLabel;
	JComboBox<String> genderComboBox;
	JButton createButton, clearButton;
	WindowAdapter adapter;
	JFrame frame;

	private AdminRoleMouseListener listener;
	
	public AddCustomerRecordListener(AdminRoleMouseListener listener) {
		this.listener = listener;
	}
	
	public void createAndShowGUI() {
		setVisible(true);
		setSize(700, 600);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Customer Record:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		firstNameLabel = new JLabel("First Name:");
		mobileNoLabel = new JLabel("Mobile Number:");
		lastNameLabel = new JLabel("Last Name:");
		emailLabel = new JLabel("Email:");
		addressLabel = new JLabel("Address:");
		websiteLabel = new JLabel("Website:");
		genderLabel = new JLabel("Gender:");
		descriptionLabel = new JLabel("Description:");
			
		final JTextField firstNameText = new JTextField();
		final JTextField mobileNoText = new JTextField();
		String[] petStrings = {"Male", "Female"};
		genderComboBox = new JComboBox<String>(petStrings);
		
		final JTextField descriptionText = new JTextField();
		final JTextField lastNameText = new JTextField();
		final JTextField emailText = new JTextField();
		mobileNoText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextArea addressText = new JTextArea();
		final JTextField websiteText = new JTextField();
		createButton = new JButton("Create");
		clearButton = new JButton("Clear");
		
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerRecord customerRecord = new CustomerRecord(null, firstNameText.getText(), lastNameText.getText(), Long.parseLong(mobileNoText.getText()), 
						emailText.getText(), genderComboBox.getSelectedItem().toString(), addressText.getText(), null, 
						websiteText.getText(), descriptionText.getText(), null);
				CustomerRecordsRepository.addCustomerRecord(customerRecord);
				listener.reloadCustomerTableData();
				firstNameText.setText("");
				mobileNoText.setText("");
				lastNameText.setText("");
				emailText.setText("");
				descriptionText.setText("");
				addressText.setText("");
				websiteText.setText("");
				dispose();
			}
		});
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				firstNameText.setText("");
				mobileNoText.setText("");
				lastNameText.setText("");
				emailText.setText("");
				descriptionText.setText("");
				addressText.setText("");
				websiteText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		firstNameLabel.setBounds(80, 70, 200, 30);
		lastNameLabel.setBounds(80, 110, 200, 30);
		mobileNoLabel.setBounds(80, 150, 200, 30);
		emailLabel.setBounds(80, 190, 200, 30);
		descriptionLabel.setBounds(80, 230, 200, 30);
		websiteLabel.setBounds(80, 270, 200, 30);
		genderLabel.setBounds(80, 310, 200, 30);
		addressLabel.setBounds(80, 350, 200, 30);
		firstNameText.setBounds(300, 70, 200, 30);
		lastNameText.setBounds(300, 110, 200, 30);
		mobileNoText.setBounds(300, 150, 200, 30);
		emailText.setBounds(300, 190, 200, 30);
		descriptionText.setBounds(300, 230, 200, 30);
		websiteText.setBounds(300, 270, 200, 30);
		genderComboBox.setBounds(300, 310, 200, 30);
		addressText.setBounds(300, 350, 200, 60);
		createButton.setBounds(50, 440, 100, 30);
		clearButton.setBounds(170, 440, 100, 30);

		add(headline);
		add(firstNameLabel);
		add(firstNameText);
		add(mobileNoLabel);
		add(mobileNoText);
		add(lastNameLabel);
		add(lastNameText);
		add(emailLabel);
		add(emailText);
		add(addressLabel);
		add(addressText);
		add(websiteLabel);
		add(websiteText);
		add(genderLabel);
		add(genderComboBox);
		add(descriptionLabel);
		add(descriptionText);
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

}

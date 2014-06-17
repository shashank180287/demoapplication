package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.VendorRecord;
import com.mobile.tool.stock.manager.repository.VendorCategoryRepository;
import com.mobile.tool.stock.manager.repository.VendorRecordRepository;

public class AddNewVendorRecordListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, nameLabel, categoryLabel, mobileNoLabel, titleLabel, emailLabel, contactAddressLabel, permanentAddressLabel, websiteLabel, descriptionLabel;
	JTextField nameText, mobileNoText, titleText, emailText, websiteText, descriptionText, categoryText;
	JTextArea contactAddressText, permanentAddressText;
	JButton createButton, clearButton, searchCategoryButton;
	WindowAdapter adapter;
	JFrame frame;
	private AdminRoleMouseListener listener;
	
	public AddNewVendorRecordListener(AdminRoleMouseListener listener)
			throws HeadlessException {
		super();
		this.listener = listener;
	}

	public void createAndShowGUI() {
		setVisible(true);
		setSize(750, 650);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Vendor Record:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		nameLabel = new JLabel("Name:");
		mobileNoLabel = new JLabel("Mobile Number:");
		titleLabel = new JLabel("Title:");
		emailLabel = new JLabel("Email:");
		contactAddressLabel = new JLabel("Contact Address:");
		websiteLabel = new JLabel("Website:");
		permanentAddressLabel = new JLabel("Permanent Address:");
		descriptionLabel = new JLabel("Description:");
		categoryLabel = new JLabel("Category:");
		
		nameText = new JTextField();
		mobileNoText = new JTextField();
		permanentAddressText = new JTextArea();
		categoryText = new JTextField();
		descriptionText = new JTextField();
		titleText = new JTextField();
		emailText = new JTextField();
		mobileNoText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		contactAddressText = new JTextArea();
		websiteText = new JTextField();
		createButton = new JButton("Create");
		clearButton = new JButton("Clear");
		searchCategoryButton = new JButton("..");
		searchCategoryButton.addActionListener(new SearchVendorCategoryButtonListener(this));
		
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VendorRecord vendorRecord = new VendorRecord(null, VendorCategoryRepository.getVendorCategoryByCategoryName(categoryText.getText()), 
						nameText.getText(), titleText.getText(), descriptionText.getText(), Integer.parseInt(mobileNoText.getText()), emailText.getText(), contactAddressText.getText(), 
						permanentAddressText.getText(), websiteText.getText(), null);
				VendorRecordRepository.addVendorRecord(vendorRecord);
				listener.reloadVendorTableData();
				dispose();
			}
		});
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameText.setText("");
				mobileNoText.setText("");
				titleText.setText("");
				emailText.setText("");
				contactAddressText.setText("");
				websiteText.setText("");
				permanentAddressText.setText("");
				categoryText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		nameLabel.setBounds(80, 70, 200, 30);
		titleLabel.setBounds(80, 110, 200, 30);
		mobileNoLabel.setBounds(80, 150, 200, 30);
		emailLabel.setBounds(80, 190, 200, 30);
		descriptionLabel.setBounds(80, 230, 200, 30);
		websiteLabel.setBounds(80, 270, 200, 30);
		permanentAddressLabel.setBounds(80, 310, 200, 30);
		contactAddressLabel.setBounds(80, 390, 200, 30);
		categoryLabel.setBounds(80, 470, 200, 30);
		nameText.setBounds(300, 70, 200, 30);
		titleText.setBounds(300, 110, 200, 30);
		mobileNoText.setBounds(300, 150, 200, 30);
		emailText.setBounds(300, 190, 200, 30);
		descriptionText.setBounds(300, 230, 200, 30);
		websiteText.setBounds(300, 270, 200, 30);
		permanentAddressText.setBounds(300, 310, 200, 60);
		contactAddressText.setBounds(300, 390, 200, 60);
		categoryText.setBounds(300, 470, 160, 30);
		searchCategoryButton.setBounds(470, 470, 30, 30);
		createButton.setBounds(50, 540, 100, 30);
		clearButton.setBounds(170, 540, 100, 30);

		add(headline);
		add(nameLabel);
		add(nameText);
		add(mobileNoLabel);
		add(mobileNoText);
		add(titleLabel);
		add(titleText);
		add(emailLabel);
		add(emailText);
		add(contactAddressLabel);
		add(contactAddressText);
		add(websiteLabel);
		add(websiteText);
		add(permanentAddressLabel);
		add(permanentAddressText);
		add(descriptionLabel);
		add(descriptionText);
		add(categoryLabel);
		add(categoryText);
		add(createButton);
		add(clearButton);
		add(searchCategoryButton);
		
		adapter = new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
//				frame.setEnabled(true);
				super.windowClosing(e);
			}
			
		};
		addWindowListener(adapter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		this.frame.setEnabled(false);
		createAndShowGUI();

	}
	
	public void setVendorCategoryText(String categoryName) {
		this.categoryText.setText(categoryName);
		this.revalidate();
	}
}

package com.mobile.tool.stock.manager.ui.listener.updater;

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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.listener.AdminRoleMouseListener;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.CustomerRecordsRepository;
import com.mobile.tool.stock.manager.ui.listener.StockManagerActionListener;

public class UpdateCustomerRecordListener extends JFrame implements StockManagerActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, firstNameLabel, mobileNoLabel, lastNameLabel, emailLabel, addressLabel, websiteLabel,  genderLabel, descriptionLabel;
	JComboBox<String> genderComboBox;
	JButton updateButton, cancelButton;
	WindowAdapter adapter;
	JFrame frame;
	StockManagementTableModel tableModel;
	JTable table;
	AdminRoleMouseListener listener;
	
	public UpdateCustomerRecordListener(JTable table, StockManagementTableModel tableModel, AdminRoleMouseListener listener) {
		this.listener = listener;
		this.table = table;
		this.tableModel = tableModel;
	}
	
	public void createAndShowGUI() {
		if(table.getSelectedRow()!=-1){
			Object customerCode = tableModel.getValueAt(table.getSelectedRow(), 0);
			final String customerCodeStr = (customerCode!=null)?customerCode.toString():null;
			Object firstName = tableModel.getValueAt(table.getSelectedRow(), 1);
			String firstNameStr = (firstName!=null)?firstName.toString():"";
			Object lastName = tableModel.getValueAt(table.getSelectedRow(), 2);
			String lastNameStr = (lastName!=null)?lastName.toString():"";
			Object mobileNumber = tableModel.getValueAt(table.getSelectedRow(), 3);
			String mobileNumberStr = (mobileNumber!=null)?mobileNumber.toString():"";
			Object email = tableModel.getValueAt(table.getSelectedRow(), 4);
			String emailStr = (email!=null)?email.toString():"";
			Object gender = tableModel.getValueAt(table.getSelectedRow(), 5);
			String genderStr = (gender!=null)?gender.toString():"";
			Object address = tableModel.getValueAt(table.getSelectedRow(), 6);
			String addressStr = (address!=null)?address.toString():"";
			Object website = tableModel.getValueAt(table.getSelectedRow(), 8);
			String websiteStr = (website!=null)?website.toString():"";
			Object description = tableModel.getValueAt(table.getSelectedRow(), 9);
			String descriptionStr = (description!=null)?description.toString():"";
			
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
			firstNameText.setText(firstNameStr);
			final JTextField mobileNoText = new JTextField();
			mobileNoText.setText(mobileNumberStr);
			String[] petStrings = {"Male", "Female"};
			genderComboBox = new JComboBox<String>(petStrings);
			genderComboBox.setSelectedItem(genderStr);
			
			final JTextField descriptionText = new JTextField();
			descriptionText.setText(descriptionStr);
			final JTextField lastNameText = new JTextField();
			lastNameText.setText(lastNameStr);
			final JTextField emailText = new JTextField();
			emailText.setText(emailStr);
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
			addressText.setText(addressStr);
			final JTextField websiteText = new JTextField();
			websiteText.setText(websiteStr);
			
			updateButton = new JButton("Update");
			cancelButton = new JButton("Cancel");
			
			updateButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					CustomerRecord customerRecord = new CustomerRecord(customerCodeStr, firstNameText.getText(), lastNameText.getText(), Long.parseLong(mobileNoText.getText()), 
							emailText.getText(), genderComboBox.getSelectedItem().toString(), addressText.getText(), null, 
							websiteText.getText(), descriptionText.getText(), null);
					CustomerRecordsRepository.updateCustomerRecord(customerRecord);
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
			
			cancelButton.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
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
			updateButton.setBounds(50, 440, 100, 30);
			cancelButton.setBounds(170, 440, 100, 30);
	
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
			add(updateButton);
			add(cancelButton);
			
			adapter = new WindowAdapter() {
				
				@Override
				public void windowClosing(WindowEvent e) {
					super.windowClosing(e);
				}
				
			};
			addWindowListener(adapter);
		} else {
			JOptionPane.showMessageDialog(this,"Please select one record.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		createAndShowGUI();

	}

	@Override
	public String getActionName() {
		return "Update Customer";
	}

}
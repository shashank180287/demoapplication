package com.mobile.tool.stock.manager.ui.listener.updater;

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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.VendorRecord;
import com.mobile.tool.stock.manager.listener.AdminRoleMouseListener;
import com.mobile.tool.stock.manager.listener.SearchVendorCategoryButtonListener;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.VendorCategoryRepository;
import com.mobile.tool.stock.manager.repository.VendorRecordRepository;
import com.mobile.tool.stock.manager.ui.listener.StockManagerActionListener;

public class UpdateVendorRecordListener extends JFrame implements StockManagerActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, nameLabel, categoryLabel, mobileNoLabel, titleLabel, emailLabel, contactAddressLabel, permanentAddressLabel, websiteLabel, descriptionLabel;
	JTextField categoryText;
	JButton updateButton, cancelButton, searchCategoryButton;
	WindowAdapter adapter;
	JFrame frame;
	private StockManagementTableModel tableModel;
	private JTable table;
	private AdminRoleMouseListener listener;
	
	public UpdateVendorRecordListener(StockManagementTableModel tableModel,
			JTable table, AdminRoleMouseListener listener)
			throws HeadlessException {
		super();
		this.tableModel = tableModel;
		this.table = table;
		this.listener = listener;
	}

	public void createAndShowGUI() {
		if(table.getSelectedRow()!=-1){
			Object vendorCode = tableModel.getValueAt(table.getSelectedRow(), 0);
			final String vendorCodeStr = (vendorCode!=null)?vendorCode.toString():null;
			Object category = tableModel.getValueAt(table.getSelectedRow(), 1);
			String categoryStr = (category!=null)?category.toString():"";
			Object name = tableModel.getValueAt(table.getSelectedRow(), 2);
			String nameStr = (name!=null)?name.toString():"";
			Object title = tableModel.getValueAt(table.getSelectedRow(), 3);
			String titleStr = (title!=null)?title.toString():"";
			Object mobileNumber = tableModel.getValueAt(table.getSelectedRow(), 4);
			String mobileNumberStr = (mobileNumber!=null)?mobileNumber.toString():"";
			Object email = tableModel.getValueAt(table.getSelectedRow(), 5);
			String emailStr = (email!=null)?email.toString():"";
			Object description = tableModel.getValueAt(table.getSelectedRow(), 6);
			String descriptionStr = (description!=null)?description.toString():"";
			Object contactAddress = tableModel.getValueAt(table.getSelectedRow(), 7);
			String contactAddressStr = (contactAddress!=null)?contactAddress.toString():"";
			Object permanentAddress = tableModel.getValueAt(table.getSelectedRow(), 8);
			String permanentAddressStr = (permanentAddress!=null)?permanentAddress.toString():"";
			Object website = tableModel.getValueAt(table.getSelectedRow(), 9);
			String websiteStr = (website!=null)?website.toString():"";

			
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
			
			final JTextField nameText = new JTextField();
			nameText.setText(nameStr);
			final JTextField mobileNoText = new JTextField();
			mobileNoText.setText(mobileNumberStr);
			final JTextArea permanentAddressText = new JTextArea();
			permanentAddressText.setText(permanentAddressStr);
			categoryText = new JTextField();
			categoryText.setEditable(false);
			categoryText.setText(categoryStr);
			final JTextField descriptionText = new JTextField();
			descriptionText.setText(descriptionStr);
			final JTextField titleText = new JTextField();
			titleText.setText(titleStr);
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
			final JTextArea contactAddressText = new JTextArea();
			contactAddressText.setText(contactAddressStr);
			final JTextField websiteText = new JTextField();
			websiteText.setText(websiteStr);
			updateButton = new JButton("Update");
			cancelButton = new JButton("Cancel");
			searchCategoryButton = new JButton("..");
			searchCategoryButton.addActionListener(new SearchVendorCategoryButtonListener(this));
			
			updateButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VendorRecord vendorRecord = new VendorRecord(vendorCodeStr, VendorCategoryRepository.getVendorCategoryByCategoryName(categoryText.getText()), 
							nameText.getText(), titleText.getText(), descriptionText.getText(), Long.parseLong(mobileNoText.getText()), emailText.getText(), contactAddressText.getText(), 
							permanentAddressText.getText(), websiteText.getText(), null);
					VendorRecordRepository.updateVendorRecord(vendorRecord);
					listener.reloadVendorTableData();
					nameText.setText("");
					mobileNoText.setText("");
					titleText.setText("");
					emailText.setText("");
					contactAddressText.setText("");
					websiteText.setText("");
					descriptionText.setText("");
					permanentAddressText.setText("");
					categoryText.setText("");
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
			updateButton.setBounds(50, 540, 100, 30);
			cancelButton.setBounds(170, 540, 100, 30);
	
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
			add(updateButton);
			add(cancelButton);
			add(searchCategoryButton);
			
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
	
	public void setVendorCategoryText(String categoryName) {
		this.categoryText.setText(categoryName);
		this.revalidate();
	}

	@Override
	public String getActionName() {
		return "Update Vendor";
	}
}

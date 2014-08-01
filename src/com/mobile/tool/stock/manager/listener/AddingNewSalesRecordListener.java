package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.entity.ProductSupplyDetails;
import com.mobile.tool.stock.manager.entity.SalesRecord;
import com.mobile.tool.stock.manager.entity.TransectionMode;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.report.InvoiceDesign;
import com.mobile.tool.stock.manager.repository.CustomerRecordsRepository;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;
import com.mobile.tool.stock.manager.repository.ProductRecordRepository;
import com.mobile.tool.stock.manager.repository.ProductSupplyDetailsRepository;
import com.mobile.tool.stock.manager.repository.SaleRecordsRepository;
import com.mobile.tool.stock.manager.repository.TransectionModeRepository;
import com.mobile.tool.stock.manager.utils.SendingMailsUtility;
import com.mobile.tool.stock.manager.view.StockDashboardTab;

public class AddingNewSalesRecordListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;
	JTextField customerText, productText;
	JLabel headline, productLabel, noOfItemsLabel, saleTitleLabel, salesDescLabel, salesAmtLabel, confirmAmtLabel,  modeLabel, customerLabel, commentLabel;
	JComboBox<String> modeComboBox;
	JButton createButton, clearButton, searchProductButton, searchCustomerButton;
	WindowAdapter adapter;
	JFrame frame;
	List<TransectionMode> transectionModes;
	
	private UserLoginDetails userLoginDetails;
	private EmployeeRoleMouseListener listener;
	
	public AddingNewSalesRecordListener(EmployeeRoleMouseListener listener, UserLoginDetails userLoginDetails) {
		this.listener = listener;
		this.userLoginDetails = userLoginDetails;
	}
	
	public void createAndShowGUI() {
		setVisible(true);
		setSize(700, 600);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Sales Record:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		productLabel = new JLabel("Product:");
		noOfItemsLabel = new JLabel("Total Items:");
		saleTitleLabel = new JLabel("Sales Title:");
		salesDescLabel = new JLabel("Sales Description:");
		salesAmtLabel = new JLabel("Sales Amount:");
		confirmAmtLabel = new JLabel("Confirm Amount:");
		modeLabel = new JLabel("Mode:");
		customerLabel = new JLabel("Customer:");
		commentLabel = new JLabel("Comment:");
			
		productText = new JTextField();
		productText.setEditable(false);
		final JTextField noOfItemsText = new JTextField();
		transectionModes = TransectionModeRepository.getAllTransectionModes();
		String[] petStrings = getTransectionModes(transectionModes);
		modeComboBox = new JComboBox<String>(petStrings);
		
		customerText = new JTextField();
		customerText.setEditable(false);
		final JTextField commentText = new JTextField();
		final JTextField salesTitleText = new JTextField();
		final JTextField salesDescText = new JTextField();
		noOfItemsText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		final JTextField salesAmtText = new JTextField();
		final JTextField confirmAmtText = new JTextField();
		salesAmtText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || (c == KeyEvent.VK_PERIOD)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		confirmAmtText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || (c == KeyEvent.VK_PERIOD)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		createButton = new JButton("Create");
		clearButton = new JButton("Clear");
		searchProductButton = new JButton("..");
		searchProductButton.addActionListener(new SearchProductsButtonListener(this));
		searchCustomerButton = new JButton("..");
		searchCustomerButton.addActionListener(new SearchCustomerButtonListener(this));
		
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductRecord product = ProductRecordRepository.getProductRecordByCode(productText.getText());
				TransectionMode mode = getSelectedTransectionMode(modeComboBox.getSelectedItem().toString());
				CustomerRecord customer = CustomerRecordsRepository.getCustomerRecordByCustomerCode(customerText.getText());
				EmployeeRecord employee = EmployeeRecordsRepository.getEmployeeRecordByUsername(userLoginDetails.getUsername());
				SalesRecord salesRecord = new SalesRecord(null, product, (noOfItemsText.getText()!=null? Integer.valueOf(noOfItemsText.getText()):0), 
						salesTitleText.getText(), salesDescText.getText(),  (salesAmtText.getText()!=null?Float.valueOf(salesAmtText.getText()):0.0F), 
						(confirmAmtText.getText()!=null? Float.valueOf(confirmAmtText.getText()):0.0F), mode , customer , employee , commentText.getText(), new Date(System.currentTimeMillis()));
				SaleRecordsRepository.addSalesRecord(salesRecord);
				ProductSupplyDetails productSupplyDetails = ProductSupplyDetailsRepository.getLastProductSupplyByProductCode(salesRecord.getProduct().getProductCode());
				productSupplyDetails.setCurrentItemCount(productSupplyDetails.getCurrentItemCount()-salesRecord.getNoOfItems());
				ProductSupplyDetailsRepository.updateProductSupplyDetails(productSupplyDetails);
				
				InvoiceDesign.printInvoiceBill(salesRecord);
				
				String filePath = "D:\\";
				try{
					filePath = URLDecoder.decode(StockDashboardTab.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
					filePath = filePath + "/tmp/";
					if(!new File(filePath).exists()){
						filePath = "D:\\";
					}
				}catch(Exception e1){
					filePath = "D:\\";
				}
				
				SendingMailsUtility.sendMailUsingGmailSMTP(salesRecord.getCustomer().getEmail(), filePath + "demo.pdf");
				SendingMailsUtility.sendMailUsingGmailSMTP(salesRecord.getEmployee().getEmail(), filePath + "demo.pdf");
				listener.reloadTableData();
				
				productText.setText("");
				noOfItemsText.setText("");
				salesTitleText.setText("");
				salesDescText.setText("");
				salesAmtText.setText("");
				confirmAmtText.setText("");
				customerText.setText("");
				commentText.setText("");
				
				dispose();
			}

			private TransectionMode getSelectedTransectionMode(String selectedItem) {
				for (TransectionMode transectionMode : transectionModes) {
					if(transectionMode.getChannelName().equalsIgnoreCase(selectedItem))
						return transectionMode;
				}
				return null;
			}
		});
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				productText.setText("");
				noOfItemsText.setText("");
				salesTitleText.setText("");
				salesDescText.setText("");
				salesAmtText.setText("");
				confirmAmtText.setText("");
				customerText.setText("");
				commentText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		productLabel.setBounds(80, 70, 200, 30);
		noOfItemsLabel.setBounds(80, 110, 200, 30);
		saleTitleLabel.setBounds(80, 150, 200, 30);
		salesDescLabel.setBounds(80, 190, 200, 30);
		salesAmtLabel.setBounds(80, 230, 200, 30);
		confirmAmtLabel.setBounds(80, 270, 200, 30);
		modeLabel.setBounds(80, 310, 200, 30);
		customerLabel.setBounds(80, 350, 200, 30);
		commentLabel.setBounds(80, 390, 200, 30);
		productText.setBounds(300, 70, 160, 30);
		noOfItemsText.setBounds(300, 110, 200, 30);
		salesTitleText.setBounds(300, 150, 200, 30);
		salesDescText.setBounds(300, 190, 200, 30);
		salesAmtText.setBounds(300, 230, 200, 30);
		confirmAmtText.setBounds(300, 270, 200, 30);
		modeComboBox.setBounds(300, 310, 200, 30);
		customerText.setBounds(300, 350, 160, 30);
		commentText.setBounds(300, 390, 200, 30);
		searchProductButton.setBounds(470, 70, 30, 30);
		searchCustomerButton.setBounds(470, 350, 30, 30);
		createButton.setBounds(50, 440, 100, 30);
		clearButton.setBounds(170, 440, 100, 30);

		add(headline);
		add(productLabel);
		add(productText);
		add(searchProductButton);
		add(noOfItemsLabel);
		add(noOfItemsText);
		add(saleTitleLabel);
		add(salesTitleText);
		add(salesDescLabel);
		add(salesDescText);
		add(salesAmtLabel);
		add(salesAmtText);
		add(confirmAmtLabel);
		add(confirmAmtText);
		add(modeLabel);
		add(modeComboBox);
		add(customerLabel);
		add(customerText);
		add(searchCustomerButton);
		add(commentLabel);
		add(commentText);
		add(createButton);
		add(clearButton);
		
		adapter = new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
//				frame.setEnabled(true);
				super.windowClosing(e);
			}
			
		};
		addWindowListener(adapter);
	}

	private String[] getTransectionModes(List<TransectionMode> allTransectionModes) {
		List<String> transections = new ArrayList<>();
		for (TransectionMode transectionMode : allTransectionModes) {
			transections.add(transectionMode.getChannelName());
		}
		String[] trasectionArray = new String[transections.size()];
		transections.toArray(trasectionArray);
		return trasectionArray;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		this.frame.setEnabled(false);
		createAndShowGUI();

	}
	
	public void setProductText(String productCode) {
		this.productText.setText(productCode);
		this.revalidate();
	}
	
	public void setCustomerText(String customerCode) {
		this.customerText.setText(customerCode);
		this.revalidate();
	}
}

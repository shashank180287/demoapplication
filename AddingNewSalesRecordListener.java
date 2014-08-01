package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.entity.ProductSupply;
import com.mobile.tool.stock.manager.entity.SalesRecord;
import com.mobile.tool.stock.manager.entity.TransectionMode;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.report.InvoiceDesign;
import com.mobile.tool.stock.manager.repository.CustomerRecordsRepository;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;
import com.mobile.tool.stock.manager.repository.ProductRecordRepository;
import com.mobile.tool.stock.manager.repository.ProductSupplyRepository;
import com.mobile.tool.stock.manager.repository.SaleRecordsRepository;
import com.mobile.tool.stock.manager.repository.TransectionModeRepository;
import com.mobile.tool.stock.manager.utils.SendingMailsUtility;

public class AddingNewSalesRecordListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, productLabel, noOfItemsLabel, saleTitleLabel, salesDescLabel, salesAmtLabel, amtPaidLabel,  balanceLabel, modeLabel, customerLabel, commentLabel;
	JTextField productText, noOfItemsText, salesTitleText, salesDescText, salesAmtText, amtPaidText, balanceText, customerText, commentText;
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
		setSize(700, 650);
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
		amtPaidLabel = new JLabel("Amount Paid:");
		balanceLabel = new JLabel("Balance");
		modeLabel = new JLabel("Mode:");
		customerLabel = new JLabel("Customer:");
		commentLabel = new JLabel("Comment:");
			
		productText = new JTextField();
		noOfItemsText = new JTextField();
		transectionModes = TransectionModeRepository.getAllTransectionModes();
		String[] petStrings = getTransectionModes(transectionModes);
		modeComboBox = new JComboBox<String>(petStrings);
		
		customerText = new JTextField();
		commentText = new JTextField();
		salesTitleText = new JTextField();
		salesDescText = new JTextField();
		noOfItemsText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		salesAmtText = new JTextField();
		amtPaidText = new JTextField();
		balanceText = new JTextField();
		salesAmtText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		amtPaidText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		balanceText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		balanceText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				if(salesAmtText.getText()!=null && amtPaidText.getText()!=null){
					float saleAmount = Float.valueOf(salesAmtText.getText());
					float amountPaid = Float.valueOf(amtPaidText.getText());
					balanceText.setText((amountPaid-saleAmount)+"");
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
						(amtPaidText.getText()!=null? Float.valueOf(amtPaidText.getText()):0.0F), (balanceText.getText()!=null? Float.valueOf(balanceText.getText()):0.0F), mode , customer , employee , commentText.getText(), new Date(System.currentTimeMillis()));
				SaleRecordsRepository.addSalesRecord(salesRecord);
				ProductSupply productSupply = ProductSupplyRepository.getProductSupplyByProductCode(salesRecord.getProduct().getProductCode());
				productSupply.setCurrentItemCount(productSupply.getCurrentItemCount()-salesRecord.getNoOfItems());
				ProductSupplyRepository.updateProductSupply(productSupply);
				
				InvoiceDesign.printInvoiceBill(salesRecord);
				SendingMailsUtility.sendMailUsingGmailSMTP(salesRecord.getCustomer().getEmail(), "D:\\demo.pdf");
				SendingMailsUtility.sendMailUsingGmailSMTP(salesRecord.getEmployee().getEmail(), "D:\\demo.pdf");
				listener.reloadTableData();
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
				amtPaidText.setText("");
				balanceText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		productLabel.setBounds(80, 70, 200, 30);
		noOfItemsLabel.setBounds(80, 110, 200, 30);
		saleTitleLabel.setBounds(80, 150, 200, 30);
		salesDescLabel.setBounds(80, 190, 200, 30);
		salesAmtLabel.setBounds(80, 230, 200, 30);
		amtPaidLabel.setBounds(80, 270, 200, 30);
		balanceLabel.setBounds(80, 310, 200, 30);
		modeLabel.setBounds(80, 350, 200, 30);
		customerLabel.setBounds(80, 390, 200, 30);
		commentLabel.setBounds(80, 430, 200, 30);
		
		productText.setBounds(300, 70, 160, 30);
		noOfItemsText.setBounds(300, 110, 200, 30);
		salesTitleText.setBounds(300, 150, 200, 30);
		salesDescText.setBounds(300, 190, 200, 30);
		salesAmtText.setBounds(300, 230, 200, 30);
		amtPaidText.setBounds(300, 270, 200, 30);
		balanceText.setBounds(300, 310, 200, 30);
		modeComboBox.setBounds(300, 350, 200, 30);
		customerText.setBounds(300, 390, 160, 30);
		commentText.setBounds(300, 430, 200, 30);
		searchProductButton.setBounds(470, 70, 30, 30);
		searchCustomerButton.setBounds(470, 390, 30, 30);
		createButton.setBounds(50, 480, 100, 30);
		clearButton.setBounds(170, 480, 100, 30);

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
		add(amtPaidLabel);
		add(amtPaidText);
		add(balanceLabel);
		add(balanceText);
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
				super.windowClosing(e);
			}
			
		};
		addWindowListener(adapter);
	}

	private String[] getTransectionModes(List<TransectionMode> allTransectionModes) {
		List<String> transections = new ArrayList<String>();
		for (TransectionMode transectionMode : allTransectionModes) {
			transections.add(transectionMode.getChannelName());
		}
		String[] trasectionArray = new String[transections.size()];
		transections.toArray(trasectionArray);
		return trasectionArray;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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

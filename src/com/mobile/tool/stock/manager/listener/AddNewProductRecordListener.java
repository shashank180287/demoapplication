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
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.repository.ProductCategoryRepository;
import com.mobile.tool.stock.manager.repository.ProductRecordRepository;
import com.mobile.tool.stock.manager.repository.VendorRecordRepository;

public class AddNewProductRecordListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, nameLabel, categoryLabel, descriptionLabel, unitPriceLabel, bulkPriceLabel, orderCountLabel, vendorLabel;
	JTextField nameText, descriptionText, categoryText, unitPriceText, bulkPriceText, orderCountText, vendorText;
	JButton createButton, clearButton, searchCategoryButton, searchVendorButton;
	WindowAdapter adapter;
	JFrame frame;
	private AdminRoleMouseListener listener;
	
	public AddNewProductRecordListener(AdminRoleMouseListener listener)
			throws HeadlessException {
		super();
		this.listener = listener;
	}

	public void createAndShowGUI() {
		setVisible(true);
		setSize(600, 500);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Product Record:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		nameLabel = new JLabel("Name:");
		unitPriceLabel = new JLabel("Unit Price:");
		bulkPriceLabel = new JLabel("Bulk Price:");
		orderCountLabel = new JLabel("Order Count:");
		vendorLabel = new JLabel("vendor:");
		descriptionLabel = new JLabel("Description:");
		categoryLabel = new JLabel("Category:");
		
		nameText = new JTextField();
		unitPriceText = new JTextField();
		categoryText = new JTextField();
		descriptionText = new JTextField();
		bulkPriceText = new JTextField();
		orderCountText = new JTextField();
		unitPriceText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)  || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		bulkPriceText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)  || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		orderCountText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		vendorText = new JTextField();
		createButton = new JButton("Create");
		clearButton = new JButton("Clear");
		searchCategoryButton = new JButton("..");
		searchCategoryButton.addActionListener(new SearchProductCategoryButtonListener(this));
		searchVendorButton = new JButton("..");
		searchVendorButton.addActionListener(new SearchVendorButtonListener(this));
		
		
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductRecord productRecord = new ProductRecord(null, ProductCategoryRepository.getProductCatagoryByCategoryName(categoryText.getText()), 
						nameText.getText(), descriptionText.getText(), Double.parseDouble(unitPriceText.getText()), Double.parseDouble(bulkPriceText.getText()), 
						Integer.parseInt(orderCountText.getText()), VendorRecordRepository.getVendorRecordByCode(vendorText.getText()), new Date(System.currentTimeMillis()));
				ProductRecordRepository.addProductRecord(productRecord);
				listener.reloadProductTableData();
				dispose();
			}
		});
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameText.setText("");
				unitPriceText.setText("");
				bulkPriceText.setText("");
				orderCountText.setText("");
				descriptionText.setText("");
				vendorText.setText("");
				categoryText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		nameLabel.setBounds(80, 70, 200, 30);
		bulkPriceLabel.setBounds(80, 110, 200, 30);
		unitPriceLabel.setBounds(80, 150, 200, 30);
		orderCountLabel.setBounds(80, 190, 200, 30);
		descriptionLabel.setBounds(80, 230, 200, 30);
		vendorLabel.setBounds(80, 270, 200, 30);
		categoryLabel.setBounds(80, 310, 200, 30);
		nameText.setBounds(300, 70, 200, 30);
		bulkPriceText.setBounds(300, 110, 200, 30);
		unitPriceText.setBounds(300, 150, 200, 30);
		orderCountText.setBounds(300, 190, 200, 30);
		descriptionText.setBounds(300, 230, 200, 30);
		vendorText.setBounds(300, 270, 160, 30);
		categoryText.setBounds(300, 310, 160, 30);
		searchCategoryButton.setBounds(470, 310, 30, 30);
		searchVendorButton.setBounds(470, 270, 30, 30);
		createButton.setBounds(50, 360, 100, 30);
		clearButton.setBounds(170, 360, 100, 30);

		add(headline);
		add(nameLabel);
		add(nameText);
		add(unitPriceLabel);
		add(unitPriceText);
		add(bulkPriceLabel);
		add(bulkPriceText);
		add(orderCountLabel);
		add(orderCountText);
		add(vendorLabel);
		add(vendorText);
		add(descriptionLabel);
		add(descriptionText);
		add(categoryLabel);
		add(categoryText);
		add(createButton);
		add(clearButton);
		add(searchCategoryButton);
		add(searchVendorButton);
		
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
	
	public void setVendorText(String categoryName) {
		this.vendorText.setText(categoryName);
		this.revalidate();
	}

	public void setProductCategoryText(String categoryName) {
		this.categoryText.setText(categoryName);
		this.revalidate();
	}
}

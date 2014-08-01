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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.ProductSupplyDetails;
import com.mobile.tool.stock.manager.repository.ProductRecordRepository;

public class AddNewProductSupplyDetailsListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, productLabel, noOfItemsLabel;
	JTextField productText;
	JButton createButton, clearButton, searchProductButton;
	WindowAdapter adapter;
	
	private AddNewProductSupplyListener listener;
	
	public AddNewProductSupplyDetailsListener(AddNewProductSupplyListener listener) {
		this.listener = listener;
	}
	
	public void createAndShowGUI() {
		setVisible(true);
		setSize(700, 250);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Product Supply:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		productLabel = new JLabel("Product:");
		noOfItemsLabel = new JLabel("Total Items:");
			
		productText = new JTextField();
		productText.setEditable(false);
		final JTextField noOfItemsText = new JTextField();
		noOfItemsText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		createButton = new JButton("Create");
		clearButton = new JButton("Clear");
		searchProductButton = new JButton("..");
		searchProductButton.addActionListener(new SearchProductsButtonListener(this));
		
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.addProductSupplyDetail(new ProductSupplyDetails(0, null, ProductRecordRepository.getProductRecordByCode(productText.getText()), 
						0, 0, (noOfItemsText.getText()!=null)?Integer.parseInt(noOfItemsText.getText()):0, 0));
				listener.reloadProductSupplyDetailsData();
				productText.setText("");
				noOfItemsText.setText("");
				dispose();
			}
		});
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				productText.setText("");
				noOfItemsText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		productLabel.setBounds(80, 70, 200, 30);
		noOfItemsLabel.setBounds(80, 110, 200, 30);
		productText.setBounds(300, 70, 160, 30);
		noOfItemsText.setBounds(300, 110, 200, 30);
		searchProductButton.setBounds(470, 70, 30, 30);
		createButton.setBounds(50, 150, 100, 30);
		clearButton.setBounds(170, 150, 100, 30);

		add(headline);
		add(productLabel);
		add(productText);
		add(searchProductButton);
		add(noOfItemsLabel);
		add(noOfItemsText);
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
	
	public void setProductText(String productCode) {
		this.productText.setText(productCode);
		this.revalidate();
	}
}

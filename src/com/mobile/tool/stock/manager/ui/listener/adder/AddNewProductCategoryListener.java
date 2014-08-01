package com.mobile.tool.stock.manager.ui.listener.adder;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.ProductCatagory;
import com.mobile.tool.stock.manager.listener.AdminRoleMouseListener;
import com.mobile.tool.stock.manager.repository.ProductCategoryRepository;
import com.mobile.tool.stock.manager.ui.listener.StockManagerActionListener;

public class AddNewProductCategoryListener extends JFrame implements StockManagerActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, categoryNameLabel, categoryDescLabel;
	JButton createButton, clearButton;
	WindowAdapter adapter;
	private AdminRoleMouseListener listener;
	
	public AddNewProductCategoryListener(AdminRoleMouseListener listener)
			throws HeadlessException {
		super();
		this.listener = listener;
	}

	public void createAndShowGUI() {
		setVisible(true);
		setSize(600, 250);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Product Category Details:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		categoryNameLabel = new JLabel("Category Name:");
		categoryDescLabel = new JLabel("Description:");
		
		final JTextField nameText = new JTextField();
		final JTextField descriptionText = new JTextField();
		
		createButton = new JButton("Create");
		createButton.setEnabled(true);
		clearButton = new JButton("Clear");
		clearButton.setEnabled(true);
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductCatagory productCatagory = new ProductCatagory(0, nameText.getText(), descriptionText.getText());
				ProductCategoryRepository.addProductCategoryDetails(productCatagory);
				
				listener.reloadProductCategoryDetailsTable();
				nameText.setText("");
				descriptionText.setText("");
				dispose();
			}
		});
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameText.setText("");
				descriptionText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		categoryNameLabel.setBounds(80, 70, 200, 30);
		categoryDescLabel.setBounds(80, 110, 200, 30);

		nameText.setBounds(300, 70, 200, 30);
		descriptionText.setBounds(300, 110, 200, 30);
		createButton.setBounds(50, 150, 100, 30);
		clearButton.setBounds(170, 150, 100, 30);

		add(headline);
		add(categoryNameLabel);
		add(nameText);
		add(categoryDescLabel);
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

	@Override
	public String getActionName() {
		return "Add Product Category";
	}
}

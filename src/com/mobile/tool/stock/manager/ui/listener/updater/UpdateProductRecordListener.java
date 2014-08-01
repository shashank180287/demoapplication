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
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.listener.AdminRoleMouseListener;
import com.mobile.tool.stock.manager.listener.SearchProductCategoryButtonListener;
import com.mobile.tool.stock.manager.listener.SearchVendorButtonListener;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.ProductCategoryRepository;
import com.mobile.tool.stock.manager.repository.ProductRecordRepository;
import com.mobile.tool.stock.manager.repository.VendorRecordRepository;
import com.mobile.tool.stock.manager.ui.listener.StockManagerActionListener;

public class UpdateProductRecordListener extends JFrame implements StockManagerActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, nameLabel, categoryLabel, descriptionLabel, unitPriceLabel, bulkPriceLabel, orderCountLabel, vendorLabel;
	JButton updateButton, cancelButton, searchCategoryButton, searchVendorButton;
	JTextField categoryText, vendorText;
	WindowAdapter adapter;
	private StockManagementTableModel tableModel;
	private JTable table;
	private AdminRoleMouseListener listener;
	
	public UpdateProductRecordListener(StockManagementTableModel tableModel,
			JTable table, AdminRoleMouseListener listener)
			throws HeadlessException {
		super();
		this.tableModel = tableModel;
		this.table = table;
		this.listener = listener;
	}

	public void createAndShowGUI() {
		if(table.getSelectedRow()!=-1){
			Object productCode = tableModel.getValueAt(table.getSelectedRow(), 0);
			final String productCodeStr = (productCode!=null)?productCode.toString():null;
			Object category = tableModel.getValueAt(table.getSelectedRow(), 1);
			String categoryStr = (category!=null)?category.toString():"";
			Object name = tableModel.getValueAt(table.getSelectedRow(), 2);
			String nameStr = (name!=null)?name.toString():"";
			Object description = tableModel.getValueAt(table.getSelectedRow(), 3);
			String descStr = (description!=null)?description.toString():"";
			Object unitPrice = tableModel.getValueAt(table.getSelectedRow(), 4);
			String unitPriceStr = (unitPrice!=null)?unitPrice.toString():"";
			Object bulkPrice = tableModel.getValueAt(table.getSelectedRow(), 5);
			String bulkPriceStr = (bulkPrice!=null)?bulkPrice.toString():"";
			Object orderCount = tableModel.getValueAt(table.getSelectedRow(), 6);
			String orderCountStr = (orderCount!=null)?orderCount.toString():"";
			Object vendor = tableModel.getValueAt(table.getSelectedRow(), 7);
			String vendorStr = (vendor!=null)?vendor.toString():"";
			
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
			
			final JTextField nameText = new JTextField();
			nameText.setText(nameStr);
			final JTextField unitPriceText = new JTextField();
			unitPriceText.setText(unitPriceStr);
			categoryText = new JTextField();
			categoryText.setText(categoryStr);
			categoryText.setEditable(false);
			final JTextField descriptionText = new JTextField();
			descriptionText.setText(descStr);
			final JTextField bulkPriceText = new JTextField();
			bulkPriceText.setText(bulkPriceStr);
			final JTextField orderCountText = new JTextField();
			orderCountText.setText(orderCountStr);
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
			vendorText.setText(vendorStr);
			vendorText.setEditable(false);
			updateButton = new JButton("Update");
			cancelButton = new JButton("Cancel");
			searchCategoryButton = new JButton("..");
			searchCategoryButton.addActionListener(new SearchProductCategoryButtonListener(this));
			searchVendorButton = new JButton("..");
			searchVendorButton.addActionListener(new SearchVendorButtonListener(this));
			
			
			updateButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ProductRecord productRecord = new ProductRecord(productCodeStr, ProductCategoryRepository.getProductCatagoryByCategoryName(categoryText.getText()), 
							nameText.getText(), descriptionText.getText(), Double.parseDouble(unitPriceText.getText()), Double.parseDouble(bulkPriceText.getText()), 
							Integer.parseInt(orderCountText.getText()), VendorRecordRepository.getVendorRecordByCode(vendorText.getText()), new Date(System.currentTimeMillis()));
					ProductRecordRepository.updateProductRecord(productRecord);
					listener.reloadProductTableData();
					nameText.setText("");
					unitPriceText.setText("");
					bulkPriceText.setText("");
					orderCountText.setText("");
					descriptionText.setText("");
					vendorText.setText("");
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
			updateButton.setBounds(50, 360, 100, 30);
			cancelButton.setBounds(170, 360, 100, 30);
	
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
			add(updateButton);
			add(cancelButton);
			add(searchCategoryButton);
			add(searchVendorButton);
			
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
	
	public void setVendorText(String categoryName) {
		this.vendorText.setText(categoryName);
		this.revalidate();
	}

	public void setProductCategoryText(String categoryName) {
		this.categoryText.setText(categoryName);
		this.revalidate();
	}

	@Override
	public String getActionName() {
		return "Update Product";
	}
}

package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.ProductSupply;
import com.mobile.tool.stock.manager.entity.ProductSupplyDetails;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.ProductSupplyDetailsRepository;
import com.mobile.tool.stock.manager.repository.ProductSupplyRepository;

public class AddNewProductSupplyListener extends JFrame implements
		ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, supplierLabel, supplyDescLabel;
	JButton createButton, clearButton, addProductDetailsButton;
	WindowAdapter adapter;

	private JTable productSupplyDetailTable;
	private StockManagementTableModel productSupplyDetailTableData;
	private List<ProductSupplyDetails> productSupplyDetails;
	private JScrollPane productSupplyDetailTablePane;
	
	private AdminRoleMouseListener listener;

	public AddNewProductSupplyListener(AdminRoleMouseListener listener) {
		this.listener = listener;
	}

	public void createAndShowGUI() {
		setVisible(true);
		setSize(700, 500);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Product Supply:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		supplierLabel = new JLabel("Supplier:");
		supplyDescLabel = new JLabel("Supply Description:");

		final JTextField supplierText = new JTextField();
		final JTextField supplyDescText = new JTextField();

		productSupplyDetails = new ArrayList<>();
		productSupplyDetailTableData = new StockManagementTableModel(
				getJTableModel(), new String[] { "Product Code",
						"Number of Items" });
		productSupplyDetailTable = new JTable(productSupplyDetailTableData);
		productSupplyDetailTable.setShowGrid(true);
		productSupplyDetailTablePane = new JScrollPane(
				productSupplyDetailTable);

		createButton = new JButton("Create");
		createButton.setEnabled(false);
		clearButton = new JButton("Clear");
		addProductDetailsButton = new JButton("Add Product");
		addProductDetailsButton
				.addActionListener(new AddNewProductSupplyDetailsListener(this));

		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductSupply productSupply = new ProductSupply(null, new Date(System.currentTimeMillis()),
					supplyDescText.getText(), supplierText.getText());	
				productSupply = ProductSupplyRepository.addProductSupply(productSupply);

				for (ProductSupplyDetails productSupplyDetail : productSupplyDetails) {
					ProductSupplyDetails lastProductSupply = ProductSupplyDetailsRepository.getLastProductSupplyByProductCode(productSupplyDetail.getProduct().getProductCode());
					productSupplyDetail.setProductSupply(productSupply);
					int totalItems=0;
					int currentItemCount=0;
					int totalSupplied=0;
					int currentStock = productSupplyDetail.getCurrentStock();
					if(lastProductSupply!=null){
						totalItems = lastProductSupply.getTotalItems();
						currentItemCount = lastProductSupply.getCurrentItemCount();
						totalSupplied = lastProductSupply.getTotalSupplied();
					}
					productSupplyDetail.setCurrentItemCount(currentItemCount+currentStock);
					productSupplyDetail.setTotalSupplied(totalSupplied+1);
					productSupplyDetail.setTotalItems(totalItems+currentStock);
				}	
				ProductSupplyDetailsRepository.addProductSupplyDetailsList(productSupplyDetails);

				listener.reloadProductSupplyData();
				supplierText.setText("");
				supplyDescText.setText("");
				for (int i = 1; i <= productSupplyDetailTableData.getRowCount(); i++) {
					productSupplyDetailTableData.removeRow(i-1);
				}
				productSupplyDetailTable = null;;
				productSupplyDetailTableData = null;
				productSupplyDetails = null;
				dispose();
			}
		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				supplierText.setText("");
				supplyDescText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		supplierLabel.setBounds(80, 70, 200, 30);
		supplyDescLabel.setBounds(80, 110, 200, 30);
		supplierText.setBounds(300, 70, 200, 30);
		supplyDescText.setBounds(300, 110, 200, 30);
		productSupplyDetailTablePane.setBounds(80, 150, 500, 200);
		addProductDetailsButton.setBounds(50, 370, 100, 30);
		createButton.setBounds(170, 370, 100, 30);
		clearButton.setBounds(290, 370, 100, 30);

		add(headline);
		add(supplierLabel);
		add(supplierText);
		add(addProductDetailsButton);
		add(supplyDescLabel);
		add(supplyDescText);
		add(createButton);
		add(clearButton);
		add(productSupplyDetailTablePane);

		adapter = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
			}

		};
		addWindowListener(adapter);
	}

	private String[][] getJTableModel() {
		List<String[]> tableModelData = new ArrayList<>();
		for (ProductSupplyDetails productSupplyDetail : productSupplyDetails) {
			tableModelData.add(new String[] {
					productSupplyDetail.getProduct().getProductCode(),
					productSupplyDetail.getCurrentStock() + "" });
		}
		String[][] tableModel = new String[tableModelData.size()][];
		tableModelData.toArray(tableModel);
		return tableModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		createAndShowGUI();

	}

	public void reloadProductSupplyDetailsData() {
		this.productSupplyDetailTableData.refreshTableData(getJTableModel(),
				new String[] { "Product Code", "Number of Items" });
		if(this.productSupplyDetails.size()>0 && !this.createButton.isEnabled()){
			this.createButton.setEnabled(true);
		}
	}

	public void addProductSupplyDetail(
			ProductSupplyDetails productSupplyDetail) {
		this.productSupplyDetails.add(productSupplyDetail);
	}
}

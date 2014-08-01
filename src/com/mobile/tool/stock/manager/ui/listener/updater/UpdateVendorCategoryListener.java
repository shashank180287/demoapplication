package com.mobile.tool.stock.manager.ui.listener.updater;

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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.VendorCategory;
import com.mobile.tool.stock.manager.listener.AdminRoleMouseListener;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.VendorCategoryRepository;
import com.mobile.tool.stock.manager.ui.listener.StockManagerActionListener;

public class UpdateVendorCategoryListener  extends JFrame implements StockManagerActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, categoryNameLabel, categoryDescLabel;
	JButton updateButton, cancelButton;
	WindowAdapter adapter;
	private StockManagementTableModel tableModel;
	private JTable table;
	private AdminRoleMouseListener listener;
	
	public UpdateVendorCategoryListener(StockManagementTableModel tableModel,
			JTable table, AdminRoleMouseListener listener)
			throws HeadlessException {
		super();
		this.tableModel = tableModel;
		this.table = table;
		this.listener = listener;
	}

	public void createAndShowGUI() {
		if(table.getSelectedRow()!=-1){
			Object id = tableModel.getValueAt(table.getSelectedRow(), 0);
			final String idStr = (id!=null)?id.toString():"0";
			Object name = tableModel.getValueAt(table.getSelectedRow(), 1);
			String nameStr = (name!=null)?name.toString():"";
			Object desc = tableModel.getValueAt(table.getSelectedRow(), 2);
			String descStr = (desc!=null)?desc.toString():"";
			setVisible(true);
			setSize(600, 250);
			setLayout(null);
			setTitle("Stock Management");

			headline = new JLabel("Vendor Category Details:");
			headline.setForeground(Color.blue);
			headline.setFont(new Font("Serif", Font.BOLD, 20));

			categoryNameLabel = new JLabel("Category Name:");
			categoryDescLabel = new JLabel("Description:");

			final JTextField nameText = new JTextField();
			nameText.setText(nameStr);
			final JTextField descriptionText = new JTextField();
			descriptionText.setText(descStr);
			
			updateButton = new JButton("Update");
			updateButton.setEnabled(true);
			cancelButton = new JButton("Cancel");
			cancelButton.setEnabled(true);
			updateButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VendorCategory vendorCategory = new VendorCategory( Integer.parseInt(idStr),
							nameText.getText(), descriptionText.getText());
					VendorCategoryRepository
							.updateVendorCategoryDetails(vendorCategory);

					listener.reloadVendorCategoryDetailsTable();
					nameText.setText("");
					descriptionText.setText("");
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
			categoryNameLabel.setBounds(80, 70, 200, 30);
			categoryDescLabel.setBounds(80, 110, 200, 30);

			nameText.setBounds(300, 70, 200, 30);
			descriptionText.setBounds(300, 110, 200, 30);
			updateButton.setBounds(50, 150, 100, 30);
			cancelButton.setBounds(170, 150, 100, 30);

			add(headline);
			add(categoryNameLabel);
			add(nameText);
			add(categoryDescLabel);
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
		return "Update Vendor Category";
	}
}

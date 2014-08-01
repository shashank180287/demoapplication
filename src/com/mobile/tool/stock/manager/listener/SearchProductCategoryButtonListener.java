package com.mobile.tool.stock.manager.listener;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mobile.tool.stock.manager.entity.ProductCatagory;
import com.mobile.tool.stock.manager.repository.ProductCategoryRepository;
import com.mobile.tool.stock.manager.ui.listener.adder.AddNewProductRecordListener;
import com.mobile.tool.stock.manager.ui.listener.updater.UpdateProductRecordListener;

public class SearchProductCategoryButtonListener extends JFrame implements
		ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	DefaultTableModel model;
	JTable customerTable;
	String searchCategoryColumns[] = {"Category Name", "Description"};
	private List<ProductCatagory> categoryRecords;
	
	private AddNewProductRecordListener addListener;
	private UpdateProductRecordListener updateListener;
	
	public SearchProductCategoryButtonListener(AddNewProductRecordListener listener) {
		this.addListener = listener;
	}

	public SearchProductCategoryButtonListener(UpdateProductRecordListener listener) {
		this.updateListener = listener;
	}
	
	public void createAndShowGUI() {
		categoryRecords = ProductCategoryRepository.getAllProductCategories();
		model = new DefaultTableModel(getTableModelArray(categoryRecords), searchCategoryColumns);
		customerTable = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		customerTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2 && categoryRecords!=null && categoryRecords.size()>=row) {
		            if(addListener!=null)
		            	addListener.setProductCategoryText(categoryRecords.get(row).getCategoryName());
		            else
		            	updateListener.setProductCategoryText(categoryRecords.get(row).getCategoryName());
		            closeWindow();
		        }
		    }
		});
		JScrollPane pane = new JScrollPane(customerTable);
		add(pane);
		setVisible(true);
		setSize(500, 400);
		setLayout(new FlowLayout());
	}

	private String[][] getTableModelArray(List<ProductCatagory> allPrductCategory) {
		List<String[]> categories = new ArrayList<>();
		for (ProductCatagory productCategory : allPrductCategory) {
			categories.add(new String[]{productCategory.getCategoryName(), productCategory.getCategoryDescription()});
		}
		String[][] categoriesArray = new String[categories.size()][];
		categories.toArray(categoriesArray);
		return categoriesArray;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		createAndShowGUI();

	}
	
	public void closeWindow() {
		setVisible(false);
		dispose();
	}
}

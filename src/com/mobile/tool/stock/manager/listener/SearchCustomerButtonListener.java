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

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.repository.CustomerRecordsRepository;

public class SearchCustomerButtonListener extends JFrame implements
		ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	DefaultTableModel model;
	JTable customerTable;
	String searchCustomerColumns[] = { "Customer Code", "Name", "Mobile Number" };
	private List<CustomerRecord> customerRecords;
	
	private final AddingNewSalesRecordListener listener;
	
	public SearchCustomerButtonListener(AddingNewSalesRecordListener listener) {
		this.listener = listener;
	}
	
	public void createAndShowGUI() {
		customerRecords = CustomerRecordsRepository.getAllCustomerRecords();
		model = new DefaultTableModel( getTableModelArray(customerRecords), searchCustomerColumns);
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
		        if (me.getClickCount() == 2 && customerRecords!=null && customerRecords.size()>=row) {
		            listener.setCustomerText(customerRecords.get(row).getCustomerCode());
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

	private String[][] getTableModelArray(List<CustomerRecord> allProductRecords) {
		List<String[]> customers = new ArrayList<>();
		for (CustomerRecord customerRecord : allProductRecords) {
			customers.add(new String[]{customerRecord.getCustomerCode(), customerRecord.getFirstName()+" "+customerRecord.getLastName(),
					customerRecord.getMobileNumber()+""});
		}
		String[][] customersArray = new String[customers.size()][];
		customers.toArray(customersArray);
		return customersArray;
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

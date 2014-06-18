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

import com.mobile.tool.stock.manager.entity.VendorRecord;
import com.mobile.tool.stock.manager.repository.VendorRecordRepository;

public class SearchUserButtonListener extends JFrame implements
		ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	DefaultTableModel model;
	JTable userTable;
	String searchUserColumns[] = {"User Code", "Name", "Mobile Number", "Email"};
	
	private final AddNewUserLoginDetailsListener listener;
	private String userType;
	
	public SearchUserButtonListener(AddNewUserLoginDetailsListener listener, String userType) {
		this.listener = listener;
		this.userType = userType;
	}
	
	public void createAndShowGUI() {
		
		vendorRecords = VendorRecordRepository.getAllVendorRecords();
		model = new DefaultTableModel(getTableModelArray(vendorRecords), searchUserColumns);
		userTable = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		userTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2 && vendorRecords!=null && vendorRecords.size()>=row) {
		            listener.setVendorText(vendorRecords.get(row).getVendorCode());
		            closeWindow();
		        }
		    }
		});
		JScrollPane pane = new JScrollPane(userTable);
		add(pane);
		setVisible(true);
		setSize(500, 400);
		setLayout(new FlowLayout());
	}

	private String[][] getTableModelArray(List<VendorRecord> vendorRecords) {
		List<String[]> vendors = new ArrayList<String[]>();
		for (VendorRecord vendorRecord : vendorRecords) {
			vendors.add(new String[]{vendorRecord.getVendorCode(), vendorRecord.getName(), vendorRecord.getMobilenumber()+"", vendorRecord.getEmail()});
		}
		String[][] vendorsArray = new String[vendors.size()][];
		vendors.toArray(vendorsArray);
		return vendorsArray;
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

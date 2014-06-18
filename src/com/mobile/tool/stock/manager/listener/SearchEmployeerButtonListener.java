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

import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;

public class SearchEmployeerButtonListener extends JFrame implements
		ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	DefaultTableModel model;
	JTable employeeTable;
	String searchEmployeeColumns[] = {"Code", "Name", "Mobile Number", "Email"};
	private List<EmployeeRecord> employeeRecords;
	
	private final AddEmployeeRecordListener listener;
	
	public SearchEmployeerButtonListener(AddEmployeeRecordListener listener) {
		this.listener = listener;
	}
	
	public void createAndShowGUI() {
		employeeRecords = EmployeeRecordsRepository.getAllEmployeeRecords();
		model = new DefaultTableModel(getTableModelArray(employeeRecords), searchEmployeeColumns);
		employeeTable = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		employeeTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2 && employeeRecords!=null && employeeRecords.size()>=row) {
		            listener.setManagerText(employeeRecords.get(row).getEmployeeCode());
		            closeWindow();
		        }
		    }
		});
		JScrollPane pane = new JScrollPane(employeeTable);
		add(pane);
		setVisible(true);
		setSize(500, 400);
		setLayout(new FlowLayout());
	}

	private String[][] getTableModelArray(List<EmployeeRecord> employeeRecords) {
		List<String[]> employees = new ArrayList<String[]>();
		for (EmployeeRecord employeeRecord : employeeRecords) {
			employees.add(new String[]{employeeRecord.getEmployeeCode(), employeeRecord.getFirstname()+" "+employeeRecord.getLastname(), employeeRecord.getMobilenumber()+"", employeeRecord.getEmail()});
		}
		String[][] employeesArray = new String[employees.size()][];
		employees.toArray(employeesArray);
		return employeesArray;
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

package com.mobile.tool.stock.manager.listener;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.repository.CustomerRecordsRepository;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;

public class SearchUserButtonListener extends JFrame implements
		ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	DefaultTableModel model;
	JTable userTable;
	String searchUserColumns[] = {"User Code", "Name", "Mobile Number", "Email"};
	static boolean oneWindowOpen=false;
	
	private final AddNewUserLoginDetailsListener listener;
	private String userType;
	
	public SearchUserButtonListener(AddNewUserLoginDetailsListener listener, String userType) {
		this.listener = listener;
		this.userType = userType;
	}
	
	public void createAndShowGUI() {
		if(!oneWindowOpen){
			if("Customer".equalsIgnoreCase(userType)){
				final List<CustomerRecord> customerRecords = CustomerRecordsRepository.getAllCustomerRecords();
				model = new DefaultTableModel( getTableModelArrayForCustomer(customerRecords), searchUserColumns);
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
				        if (me.getClickCount() == 2 && customerRecords!=null && customerRecords.size()>=row) {
				            listener.setUserCodeText(customerRecords.get(row).getCustomerCode());
				            oneWindowOpen =false;
				            closeWindow();
				        }
				    }
				});
			}else{
				final List<EmployeeRecord> employeeRecords = EmployeeRecordsRepository.getAllEmployeeRecords();
				model = new DefaultTableModel(getTableModelArrayForEmployee(employeeRecords), searchUserColumns);
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
				        if (me.getClickCount() == 2 && employeeRecords!=null && employeeRecords.size()>=row) {
				            listener.setUserCodeText(employeeRecords.get(row).getEmployeeCode());
				            oneWindowOpen = false;
				            closeWindow();
				        }
				    }
				});
			}
			oneWindowOpen = true;
			JScrollPane pane = new JScrollPane(userTable);
			add(pane);
			setVisible(true);
			setSize(500, 400);
			setLayout(new FlowLayout());
		}
		
		WindowAdapter adapter = new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				oneWindowOpen = false;
			}
			
		};
		addWindowListener(adapter);
	}

	
	private String[][] getTableModelArrayForCustomer(List<CustomerRecord> allProductRecords) {
		List<String[]> customers = new ArrayList<>();
		for (CustomerRecord customerRecord : allProductRecords) {
			customers.add(new String[]{customerRecord.getCustomerCode(), customerRecord.getFirstName()+" "+customerRecord.getLastName(),
					customerRecord.getMobileNumber()+"", customerRecord.getEmail()});
		}
		String[][] customersArray = new String[customers.size()][];
		customers.toArray(customersArray);
		return customersArray;
	}
	
	private String[][] getTableModelArrayForEmployee(List<EmployeeRecord> employeeRecords) {
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

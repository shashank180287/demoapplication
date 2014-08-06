package com.mobile.tool.stock.manager.view;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.looks.LookUtils;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;

public class EmployeePersonDetailsDashboard {

    public JComponent build(String employeeCode) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(Borders.DIALOG_BORDER);
		panel.add(buildHorizontalSplit(employeeCode));
		return panel;
    }

	private JComponent buildHorizontalSplit(String employeeCode) {
		JComponent lowerPanel = new JScrollPane(buildPersonalDetailsTable(employeeCode));
		return lowerPanel;
	}
	
	private JTable buildPersonalDetailsTable(String employeeCode) {
		StockManagementTableModel payrollTableModel = new StockManagementTableModel(getTableModel(EmployeeRecordsRepository.getEmployeeRecordByEmployeeCode(employeeCode)), new String[]{"Attribute", "Value"});
		JTable table = new JTable(payrollTableModel);
		int tableFontSize = table.getFont().getSize();
		int minimumRowHeight = tableFontSize + 6;
		int defaultRowHeight = LookUtils.IS_LOW_RESOLUTION ? 17 : 18;
		table.setRowHeight(Math.max(minimumRowHeight, defaultRowHeight));
		return table;
	}

	public static String[][] getTableModel(EmployeeRecord employeeRecord) {
		 String[][] tableModelArray = new String[][]{{"Employee Code" , employeeRecord.getEmployeeCode()}, 
				 {"Name", employeeRecord.getFirstname() +" "+ employeeRecord.getLastname()},
				 {"MobileNumber", employeeRecord.getMobilenumber()+""} , 
				 {"Gender", employeeRecord.getGender()},
				 {"Age",  employeeRecord.getAge()+""},
				 {"Marital Status",  employeeRecord.getMaritalStatus()},
				 {"Manager",  (employeeRecord.getManager()!=null)?employeeRecord.getManager().getFirstname()+" "+ employeeRecord.getManager().getLastname():""},
				 {"Email",  employeeRecord.getEmail()},
				 {"Employeed On",  employeeRecord.getEmployeed().toString()},
				 {"Job Title",  employeeRecord.getJobTitle()},
				 {"Contact Address",  employeeRecord.getContactAddress()},
				 {"Username",  (employeeRecord.getUserLoginDetail()!=null?employeeRecord.getUserLoginDetail().getUsername():"")}
				 };
		return tableModelArray;
	}
}

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

public class EmployeeAdditionalDetailsDashboard {
	
    public JComponent build(String employeeCode) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(Borders.DIALOG_BORDER);
		panel.add(buildHorizontalSplit(employeeCode));
		return panel;
    }

	private JComponent buildHorizontalSplit(String employeeCode) {
		JComponent lowerPanel = new JScrollPane(buildAdditionalDetailsTable(employeeCode));
		return lowerPanel;
	}
	
	private JTable buildAdditionalDetailsTable(String employeeCode) {
		StockManagementTableModel payrollTableModel = new StockManagementTableModel(getTableModel(EmployeeRecordsRepository.getEmployeeRecordByEmployeeCode(employeeCode)), new String[]{"Attribute", "Value"});
		JTable table = new JTable(payrollTableModel);
		int tableFontSize = table.getFont().getSize();
		int minimumRowHeight = tableFontSize + 6;
		int defaultRowHeight = LookUtils.IS_LOW_RESOLUTION ? 17 : 18;
		table.setRowHeight(Math.max(minimumRowHeight, defaultRowHeight));
		return table;
	}

	public static String[][] getTableModel(EmployeeRecord employeeRecord) {
		 String[][] tableModelArray = new String[][]{
				 {"State Of Origin" , ""},//employeeRecord.getStateOfOrigin()}, 
				 {"Local Government", ""},//employeeRecord.getLocalGovt()},
				 {"Job Description", employeeRecord.getJobDescription()} , 
				 {"Religion", ""},//employeeRecord.getReligion()},
				 {"Id Proof Document", ""},//employeeRecord.getIdProofDocName()},
				 {"Id Proof Document No", ""},//employeeRecord.getIdProofDocNo()},
				 {"Reference 1", employeeRecord.getReference1()},
				 {"Reference 2", employeeRecord.getReference2()},
				 {"Reference 3", employeeRecord.getReference3()},
				 };
		return tableModelArray;
	}
}

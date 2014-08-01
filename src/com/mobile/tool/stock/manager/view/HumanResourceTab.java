package com.mobile.tool.stock.manager.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

<<<<<<< HEAD
import javax.swing.JButton;
=======
>>>>>>> origin/master
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
<<<<<<< HEAD
=======
import javax.swing.table.TableModel;
>>>>>>> origin/master

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.looks.LookUtils;
import com.jgoodies.uif_lite.component.UIFSplitPane;
<<<<<<< HEAD
import com.mobile.tool.stock.manager.entity.EmployeePayrollDetails;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.handler.EmployeeDetailsOptionHandler;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.EmployeePayrollDetailsRepository;
import com.mobile.tool.stock.manager.ui.listener.adder.AddEmployeePayrollDetailsListener;
import com.mobile.tool.stock.manager.ui.listener.updater.UpdateEmployeePayrollDetailsListener;

public class HumanResourceTab {

=======
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;

public class HumanResourceTab {
    
>>>>>>> origin/master
	private UserLoginDetails userLoginDetails;
	private StockManagementTableModel payrollTableModel;
	private JPanel btnPnl;
    /**
     * Builds the panel.
     */
<<<<<<< HEAD
    public JComponent build() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(Borders.DIALOG_BORDER);
		panel.add(buildHorizontalSplit(true));
		return panel;
    }

	public Component build(UserLoginDetails userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(Borders.DIALOG_BORDER);
		panel.add(buildHorizontalSplit(false));
		return panel;
	}
=======
    public JComponent build(UserLoginDetails userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(Borders.DIALOG_BORDER);
		panel.add(buildHorizontalSplit());
		return panel;
    }

>>>>>>> origin/master
	/**
	 * Builds and returns the horizontal split using stripped split panes.
	 * <p>
	 * 
	 * Nesting split panes often leads to duplicate borders. However, a
	 * look&feel should not remove borders completely - unless he has good
	 * knowledge about the context: the surrounding components in the component
	 * tree and the border states.
	 */
<<<<<<< HEAD
	private JComponent buildHorizontalSplit(boolean isUserAdmin) {
		JComponent upperPanel = new JScrollPane(buildEmployeeTable(isUserAdmin));
		upperPanel.setPreferredSize(new Dimension(100, 200));

		JComponent middlePanel = new JScrollPane(buildButtonPanel());
		middlePanel.setPreferredSize(new Dimension(100, 50));

		JComponent lowerPanel = new JScrollPane(buildPayrollTable());
		lowerPanel.setPreferredSize(new Dimension(100, 100));

		UIFSplitPane pane = UIFSplitPane.createStrippedSplitPane(
				JSplitPane.VERTICAL_SPLIT, upperPanel, middlePanel);

		return UIFSplitPane.createStrippedSplitPane(
				JSplitPane.VERTICAL_SPLIT, pane, lowerPanel);
	}

=======
	private JComponent buildHorizontalSplit() {
		JComponent upperPanel = new JScrollPane(buildEmployeeTable());
		upperPanel.setPreferredSize(new Dimension(100, 400));
		
		JComponent middlePanel = new JScrollPane(buildButtonPanel());
		middlePanel.setPreferredSize(new Dimension(100, 100));
		
		JComponent lowerPanel = new JScrollPane(buildPayrollTable());
		lowerPanel.setPreferredSize(new Dimension(100, 300));
		
		UIFSplitPane pane = UIFSplitPane.createStrippedSplitPane(
				JSplitPane.VERTICAL_SPLIT, upperPanel, middlePanel);
		
		return UIFSplitPane.createStrippedSplitPane(
				JSplitPane.VERTICAL_SPLIT, pane, lowerPanel);
	}
	
>>>>>>> origin/master
	private Component buildButtonPanel() {
		btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPnl.add(new JLabel("Please select employee record to see his/her payroll details"));
		return btnPnl;
	}
<<<<<<< HEAD

	/**
	 * Builds and returns a sample table.
	 */
	private JTable buildEmployeeTable(boolean isUserAdmin) {
		String[][] tableData = new String[][]{};
		final StockManagementTableModel tableModel = new StockManagementTableModel(tableData, new String[]{});
		if(isUserAdmin){
			EmployeeDetailsOptionHandler.handleEmployeeDetailsOptionSelectionForAdmin(tableModel);
		} else {
			EmployeeDetailsOptionHandler.handleEmployeeDetailsOptionSelectionForManager(tableModel, userLoginDetails.getUsername());
		}

=======
	
	/**
	 * Builds and returns a sample table.
	 */
	private JTable buildEmployeeTable() {
		final String[][] tableData = new String[][]{{"AAA","BBB","CCC"},{"AAA1", "BBB1", "CCC1"}};
		TableModel tableModel = new StockManagementTableModel(tableData, new String[]{"A", "B", "C"});
>>>>>>> origin/master
		final JTable table = new JTable(tableModel);
		int tableFontSize = table.getFont().getSize();
		int minimumRowHeight = tableFontSize + 6;
		int defaultRowHeight = LookUtils.IS_LOW_RESOLUTION ? 17 : 18;
		table.setRowHeight(Math.max(minimumRowHeight, defaultRowHeight));
		table.addMouseListener(new MouseAdapter() {
<<<<<<< HEAD

=======
			
>>>>>>> origin/master
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if(row!=-1){
<<<<<<< HEAD
					String employeeCode = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
					EmployeePayrollDetails employeePayrollDetails = EmployeePayrollDetailsRepository.getAllEmployeePayrollDetailsByEmployeeCode(employeeCode);
					if(employeePayrollDetails!=null){
						payrollTableModel.refreshTableData(EmployeePayrollDetails.getTableModel(employeePayrollDetails), EmployeePayrollDetails.attributeColumnNames);
						for(Component component : btnPnl.getComponents()){
							btnPnl.remove(component);
						}
						JButton updatePayrollButton = new JButton("Update Payroll");
						updatePayrollButton.addActionListener(new UpdateEmployeePayrollDetailsListener(HumanResourceTab.this, employeePayrollDetails));
						btnPnl.add(updatePayrollButton);
						btnPnl.revalidate();
						btnPnl.repaint();
					}else{
						payrollTableModel.refreshTableData(new String[][]{}, EmployeePayrollDetails.attributeColumnNames);
						for(Component component : btnPnl.getComponents()){
							btnPnl.remove(component);
						}
						JButton addPayrollButton = new JButton("Add Payroll");
						addPayrollButton.addActionListener(new AddEmployeePayrollDetailsListener(HumanResourceTab.this, employeeCode));
						btnPnl.add(addPayrollButton);
						btnPnl.revalidate();
						btnPnl.repaint();
					}
=======
					payrollTableModel.refreshTableData(new String[][]{{"AAA","BBB"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"}
									,{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"}}, new String[]{"A", "B"});
>>>>>>> origin/master
				}
			}
		});
		return table;
	}
<<<<<<< HEAD

=======
	
>>>>>>> origin/master
	/**
	 * Builds and returns a sample table.
	 */
	private JTable buildPayrollTable() {
		payrollTableModel = new StockManagementTableModel(new String[][]{}, new String[]{"A", "B"});
		JTable table = new JTable(payrollTableModel);
		int tableFontSize = table.getFont().getSize();
		int minimumRowHeight = tableFontSize + 6;
		int defaultRowHeight = LookUtils.IS_LOW_RESOLUTION ? 17 : 18;
		table.setRowHeight(Math.max(minimumRowHeight, defaultRowHeight));
		return table;
	}
<<<<<<< HEAD

	public void reloadPayrollTableDataForEmployeeCode(String employeeCode) {
		EmployeePayrollDetails employeePayrollDetails = EmployeePayrollDetailsRepository.getAllEmployeePayrollDetailsByEmployeeCode(employeeCode);
		payrollTableModel.refreshTableData(EmployeePayrollDetails.getTableModel(employeePayrollDetails), EmployeePayrollDetails.attributeColumnNames);
		for(Component component : btnPnl.getComponents()){
			btnPnl.remove(component);
		}
		btnPnl.add(new JButton("Update Payroll"));
		btnPnl.revalidate();
		btnPnl.repaint();
	}

=======
>>>>>>> origin/master
}

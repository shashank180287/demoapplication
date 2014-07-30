package com.mobile.tool.stock.manager.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.looks.LookUtils;
import com.jgoodies.uif_lite.component.UIFSplitPane;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;

public class HumanResourceTab {
    
	private UserLoginDetails userLoginDetails;
	private StockManagementTableModel payrollTableModel;
	private JPanel btnPnl;
    /**
     * Builds the panel.
     */
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
	/**
	 * Builds and returns the horizontal split using stripped split panes.
	 * <p>
	 * 
	 * Nesting split panes often leads to duplicate borders. However, a
	 * look&feel should not remove borders completely - unless he has good
	 * knowledge about the context: the surrounding components in the component
	 * tree and the border states.
	 */
	private JComponent buildHorizontalSplit(boolean isUserAdmin) {
		JComponent upperPanel = new JScrollPane(buildEmployeeTable(isUserAdmin));
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
	
	private Component buildButtonPanel() {
		btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPnl.add(new JLabel("Please select employee record to see his/her payroll details"));
		return btnPnl;
	}
	
	/**
	 * Builds and returns a sample table.
	 */
	private JTable buildEmployeeTable(boolean isUserAdmin) {
		final String[][] tableData = new String[][]{{"AAA","BBB","CCC"},{"AAA1", "BBB1", "CCC1"}};
		TableModel tableModel = new StockManagementTableModel(tableData, new String[]{"A", "B", "C"});
		final JTable table = new JTable(tableModel);
		int tableFontSize = table.getFont().getSize();
		int minimumRowHeight = tableFontSize + 6;
		int defaultRowHeight = LookUtils.IS_LOW_RESOLUTION ? 17 : 18;
		table.setRowHeight(Math.max(minimumRowHeight, defaultRowHeight));
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if(row!=-1){
					payrollTableModel.refreshTableData(new String[][]{{"AAA","BBB"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"}
									,{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"},{"AAA1", "BBB1"}}, new String[]{"A", "B"});
				}
			}
		});
		return table;
	}
	
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
}

package com.mobile.tool.stock.manager.view;

/*
 * Copyright (c) 2001-2005 JGoodies Karsten Lentzsch. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  o Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer. 
 *     
 *  o Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution. 
 *     
 *  o Neither the name of JGoodies Karsten Lentzsch nor the names of 
 *    its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission. 
 *     
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.looks.LookUtils;
import com.jgoodies.uif_lite.component.UIFSplitPane;
import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.entity.SalesRecord;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.handler.UserRoleDefine;
import com.mobile.tool.stock.manager.listener.AdminRoleMouseListener;
import com.mobile.tool.stock.manager.listener.CustomerRoleMouseListener;
import com.mobile.tool.stock.manager.listener.EmployeeRoleMouseListener;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.CustomerRecordsRepository;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;
import com.mobile.tool.stock.manager.repository.SaleRecordsRepository;

/**
 * Contains nested split panels and demonstrates how ClearLook removes obsolete
 * decorations.
 * 
 */
public class StockManagementTab {

	private UserRoleDefine userRoleDefine;
	private TableModel tableModel;
	private UserLoginDetails userLoginDetails;
	private JPanel btnPnl;
	private TreePath selectionPath;
	/**
	 * Builds and returns the panel.
	 */

	public StockManagementTab(UserRoleDefine userRoleDefine) {
		this.userRoleDefine = userRoleDefine;
	}

	public JComponent build(UserLoginDetails userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(Borders.DIALOG_BORDER);
		panel.add(buildHorizontalSplit());
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
	private JComponent buildHorizontalSplit() {
		JTree tree = buildTree();
		JComponent left = new JScrollPane(tree);
		left.setPreferredSize(new Dimension(200, 100));

		JComponent lowerRight = new JScrollPane(buildTable());
		lowerRight.setPreferredSize(new Dimension(100, 100));

		JComponent upperRight = new JScrollPane(buildButtonPanel());
		upperRight.setPreferredSize(new Dimension(100, 50));

		switch (userRoleDefine) {
		case ADMIN:
			tree.addMouseListener(new AdminRoleMouseListener(tree, (StockManagementTableModel) tableModel, userLoginDetails).addButtonPanel(btnPnl));
			break;
		case CUSTOMER:
			tree.addMouseListener(new CustomerRoleMouseListener(tree,
					(StockManagementTableModel) tableModel, userLoginDetails));
			break;
		case EMPLOYEE:
			tree.addMouseListener(new EmployeeRoleMouseListener(tree,
					(StockManagementTableModel) tableModel, userLoginDetails).addButtonPanel(btnPnl));
			break;
		}
		JSplitPane verticalSplit = UIFSplitPane.createStrippedSplitPane(
				JSplitPane.VERTICAL_SPLIT, upperRight, lowerRight);
		return UIFSplitPane.createStrippedSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, left, verticalSplit);
	}

	private Component buildButtonPanel() {
		btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		return btnPnl;
	}

	/**
	 * Builds and returns a sample tree.
	 */
	private JTree buildTree() {
		JTree tree = new JTree(createSampleTreeModel());
		tree.expandRow(3);
		tree.expandRow(2);
		tree.expandRow(1);
		if(selectionPath!=null){
			tree.setSelectionPath(selectionPath);
			tree.makeVisible(selectionPath);
		}
		return tree;
	}

	/**
	 * Builds and returns a sample table.
	 */
	private JTable buildTable() {
		JTable table = null;
		switch (userRoleDefine) {
		case EMPLOYEE:
			tableModel = new StockManagementTableModel(EmployeeRecord.getTableModel(
					EmployeeRecordsRepository.getEmployeeRecordByUsername(userLoginDetails.getUsername())), 
					EmployeeRecord.attributeColumnNames);
			table = new JTable(tableModel);
			break;
		case CUSTOMER:
			tableModel = new StockManagementTableModel(CustomerRecord.getTableModel(
					CustomerRecordsRepository.getCustomerRecordByByUsername(userLoginDetails.getUsername())), 
					CustomerRecord.attributeColumnNames);
			table = new JTable(tableModel);
			break;
		case ADMIN:
			tableModel = new StockManagementTableModel(SalesRecord.getTableModel(
					SaleRecordsRepository.getSaleRecordForToday()), 
					SalesRecord.tableColumnNames);
			table = new JTable(tableModel);
			break;
		}
		 
		int tableFontSize = table.getFont().getSize();
		int minimumRowHeight = tableFontSize + 6;
		int defaultRowHeight = LookUtils.IS_LOW_RESOLUTION ? 17 : 18;
		table.setRowHeight(Math.max(minimumRowHeight, defaultRowHeight));
		return table;
	}

	/**
	 * Creates and returns a sample tree model.
	 */
	private TreeModel createSampleTreeModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				"Stock Management");
		DefaultMutableTreeNode parent;

		switch (userRoleDefine) {
		case ADMIN: {
			parent = new DefaultMutableTreeNode("Accounts");
			root.add(parent);
			parent.add(new DefaultMutableTreeNode("Customer List"));
			parent.add(new DefaultMutableTreeNode("Employee List"));
			parent.add(new DefaultMutableTreeNode("Vendor List"));
			parent.add(new DefaultMutableTreeNode("Product Order"));
			//
			parent = new DefaultMutableTreeNode("Transaction");
			root.add(parent);
			MutableTreeNode salesHistory =new DefaultMutableTreeNode("Sales History");
			parent.add(salesHistory);
			//
			parent = new DefaultMutableTreeNode("Manage");
			root.add(parent);
			parent.add(new DefaultMutableTreeNode("Manage User"));
			parent.add(new DefaultMutableTreeNode("Manage Records"));
			selectionPath = new TreePath(((DefaultMutableTreeNode)salesHistory).getPath());
		}
			break;
		case CUSTOMER: {
			parent = new DefaultMutableTreeNode("Options");
			root.add(parent);
			MutableTreeNode personDetails = new DefaultMutableTreeNode("Personal Details");
			parent.add(personDetails);
			parent.add(new DefaultMutableTreeNode("Sales History"));
			selectionPath = new TreePath(((DefaultMutableTreeNode)personDetails).getPath());
		}
			break;
		case EMPLOYEE: {
			parent = new DefaultMutableTreeNode("Options");
			root.add(parent);
			MutableTreeNode personDetails = new DefaultMutableTreeNode("Personal Details");
			parent.add(personDetails);
			parent.add(new DefaultMutableTreeNode("Sales History"));
			selectionPath = new TreePath(((DefaultMutableTreeNode)personDetails).getPath());
		}
			break;
		}

		return new DefaultTreeModel(root);
	}

}

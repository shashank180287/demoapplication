package com.mobile.tool.stock.manager.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.mobile.tool.stock.manager.model.StockManagementTableModel;

public class AdminRoleMouseListener  extends MouseAdapter {

	private JTree tree;

	public AdminRoleMouseListener() {
	}

	public AdminRoleMouseListener(JTree tree) {
		super();
		this.tree = tree;
	}

	public void mouseClicked(MouseEvent me) {
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		if ("Customer List".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())) {

		} else {
/*			((StockManagementTableModel) tableModel).refreshTableData(
					new String[][] { { "ABC", "CDS", "ZXC", "DFD" } },
					new String[] { "A", "B", "C", "D" });*/
		}
	}
}

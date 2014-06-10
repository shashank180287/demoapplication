package com.mobile.tool.stock.manager.handler;


import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.view.Settings;
import com.mobile.tool.stock.manager.view.StockMenuBuilder;

public interface UserRoleHandler {

	public void addTabs(JTabbedPane tabbedPane, UserLoginDetails userLoginDetails);
	
	public Component buildToolBar(Settings settings);
	
	public StockMenuBuilder getStockMenuBuilder(JFrame frame);
	
}

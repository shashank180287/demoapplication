package com.mobile.tool.stock.manager.handler;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.Timer;

import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;
import com.mobile.tool.stock.manager.view.HumanResourceTab;
import com.mobile.tool.stock.manager.view.Settings;
import com.mobile.tool.stock.manager.view.StockManagementTab;
import com.mobile.tool.stock.manager.view.StockManagerDashboard;
import com.mobile.tool.stock.manager.view.StockMenuBuilder;

public class EmployeeUserRoleHandler implements UserRoleHandler{

	
	@Override
	public void addTabs(JTabbedPane tabbedPane, UserLoginDetails userLoginDetails) {
		tabbedPane.addTab("Stock Management", new StockManagementTab(UserRoleDefine.EMPLOYEE).build(userLoginDetails));
		if(EmployeeRecordsRepository.isUserManager(userLoginDetails.getUsername())){
			tabbedPane.addTab("Payroll Management", new HumanResourceTab().build(userLoginDetails));
		}
	}

	@Override
	public Component buildToolBar(Settings settings) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
		// Swing
		toolBar.putClientProperty(Options.HEADER_STYLE_KEY,
				settings.getToolBarHeaderStyle());
		toolBar.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY,
				settings.getToolBarPlasticBorderStyle());
		toolBar.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY,
				settings.getToolBarWindowsBorderStyle());
		toolBar.putClientProperty(PlasticLookAndFeel.IS_3D_KEY,
				settings.getToolBar3DHint());

		AbstractButton button;
		toolBar.add(createToolBarButton("home.gif"));
		toolBar.addSeparator();
		toolBar.add(createToolBarButton("print.gif"));
		toolBar.add(createToolBarButton("refresh.gif"));
		toolBar.addSeparator();

		button = createToolBarButton("calc.gif");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Runtime.getRuntime().exec("calc");
				} catch (IOException e) {

				}

			}
		});
		toolBar.add(button);
		
		button = createToolBarButton("help.gif");
		button.addActionListener(createHelpActionListener());
		toolBar.add(button);

		toolBar.add(Box.createGlue());
		
		final JLabel timeLabel = new JLabel();
		toolBar.add(timeLabel);
		
		timeLabel.setIcon(readImageIcon("icn_clock.gif"));
		final DateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String time = timeFormat.format(date);
				timeLabel.setText(time);
			}
		};
		Timer timer = new Timer(1000, timerListener);
		// to make sure it doesn't wait one second at the start
		timer.setInitialDelay(0);
		timer.start();
		return toolBar;
	}

	@Override
	public StockMenuBuilder getStockMenuBuilder(JFrame frame) {
		return new StockMenuBuilder(frame);
	}

	/**
	 * Creates and returns a <code>JButton</code> configured for use in a
	 * JToolBar.
	 * <p>
	 * 
	 * This is a simplified method that is overriden by the Looks Demo. The full
	 * code uses the JGoodies UI framework's ToolBarButton that better handles
	 * platform differences.
	 */
	protected AbstractButton createToolBarButton(String iconName) {
		JButton button = new JButton(readImageIcon(iconName));
		button.setToolTipText(iconName);
		button.setFocusable(false);
		return button;
	}
	
	/*
	 * Looks up and answers an icon for the specified filename suffix.<p>
	 */
	protected static ImageIcon readImageIcon(String filename) {
		URL url = StockManagerDashboard.class.getClassLoader().getResource(
				"images/" + filename);
		return new ImageIcon(url);
	}
	
	/**
	 * Creates and answers an ActionListener that opens the help viewer.
	 */
	protected ActionListener createHelpActionListener() {
		return null;
	}
	
}

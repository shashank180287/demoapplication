package com.mobile.tool.stock.manager.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StockManagerView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -981640700240704538L;
	
	JLabel welcome = new JLabel("Welcome to a New Frame");
	JPanel panel = new JPanel();

	public StockManagerView() {
		super("Welcome");
		setSize(300, 200);
		setLocation(500, 280);
		panel.setLayout(null);

		welcome.setBounds(70, 50, 150, 60);

		panel.add(welcome);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}

package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

import com.mobile.tool.stock.manager.entity.OwerDetails;
import com.mobile.tool.stock.manager.repository.OwerDetailsRepository;

public class AddOwerDetailsRecordListener extends JFrame implements ActionListener {
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, saleCodeLabel, customerNameLabel, totalAmtLabel, amtPaidLabel,  amountOweLabel, purchaseDateLabel;
	JButton createButton, clearButton;
	WindowAdapter adapter;
	JFrame frame;

	private AdminRoleMouseListener listener;

	public AddOwerDetailsRecordListener(AdminRoleMouseListener listener) {
		this.listener = listener;
	}

	public void createAndShowGUI() {
		setVisible(true);
		setSize(700, 400);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Ower Record:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		saleCodeLabel = new JLabel("Sales Code:");
		customerNameLabel = new JLabel("Customer Name:");
		totalAmtLabel = new JLabel("Total Amount:");
		amtPaidLabel = new JLabel("Amount Paid:");
		amountOweLabel = new JLabel("Amount Owe:");
		purchaseDateLabel = new JLabel("Date:");

		final JTextField salesCodeText = new JTextField();
		final JTextField customerNameText = new JTextField();
		final JTextField totalAmtText = new JTextField();
		final JTextField amtPaidText = new JTextField();
		final JTextField amtOweText = new JTextField();
		final JDatePickerImpl purchaseDatePicker = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		totalAmtText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		amtPaidText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		amtOweText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		amtOweText.addFocusListener(new FocusListener() {
		
			@Override
			public void focusLost(FocusEvent arg0) {
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if(totalAmtText.getText()!=null && amtPaidText.getText()!=null){
					float saleAmount = Float.valueOf(totalAmtText.getText());
					float amountPaid = Float.valueOf(amtPaidText.getText());
					amtOweText.setText((amountPaid-saleAmount)+"");
				}
			}
		});
		createButton = new JButton("Create");
		clearButton = new JButton("Clear");

		createButton.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				OwerDetailsRepository.addOwerDetails(new OwerDetails(0, salesCodeText.getText(), customerNameText.getText(),
						(totalAmtText.getText()!=null?Float.valueOf(totalAmtText.getText()):0.0F), 
						(amtPaidText.getText()!=null? Float.valueOf(amtPaidText.getText()):0.0F), 
						(amtOweText.getText()!=null? Float.valueOf(amtOweText.getText()):0.0F),
						((DateModel<Date>)purchaseDatePicker.getModel()).getValue()));
				
				listener.reloadOwerListDetailTableData();
				
				salesCodeText.setText("");
				customerNameText.setText("");
				totalAmtText.setText("");
				amtPaidText.setText("");
				amtOweText.setText("");
				purchaseDatePicker.repaint();
				dispose();
			}
		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				salesCodeText.setText("");
				customerNameText.setText("");
				totalAmtText.setText("");
				amtPaidText.setText("");
				salesCodeText.setText("");
				customerNameText.setText("");
				totalAmtText.setText("");
				amtPaidText.setText("");
			}
		});

		headline.setBounds(80, 30, 400, 30);
		saleCodeLabel.setBounds(80, 70, 200, 30);
		customerNameLabel.setBounds(80, 110, 200, 30);
		totalAmtLabel.setBounds(80, 150, 200, 30);
		amtPaidLabel.setBounds(80, 190, 200, 30);
		amountOweLabel.setBounds(80, 230, 200, 30);
		purchaseDateLabel.setBounds(80, 270, 200, 30);

		salesCodeText.setBounds(300, 70, 200, 30);
		customerNameText.setBounds(300, 110, 200, 30);
		totalAmtText.setBounds(300, 150, 200, 30);
		amtPaidText.setBounds(300, 190, 200, 30);
		amtOweText.setBounds(300, 230, 200, 30);
		purchaseDatePicker.setBounds(300, 270, 200, 30);
		createButton.setBounds(50, 310, 100, 30);
		clearButton.setBounds(170, 310, 100, 30);

		add(headline);
		add(saleCodeLabel);
		add(salesCodeText);
		add(customerNameLabel);
		add(customerNameText);
		add(totalAmtLabel);
		add(totalAmtText);
		add(amtPaidLabel);
		add(amtPaidText);
		add(amountOweLabel);
		add(amtOweText);
		add(purchaseDateLabel);
		add(purchaseDatePicker);
		add(createButton);
		add(clearButton);

		adapter = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
			}

		};
		addWindowListener(adapter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		createAndShowGUI();

	}
}

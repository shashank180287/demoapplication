package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mobile.tool.stock.manager.entity.EmployeeProfessionalDetails;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;

public class AddEmployeeProfession extends JFrame implements ActionListener {
	/**
* 
*/
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline;
	JButton createButton, cancelButton, addEmployeeProfDetailButton;
	WindowAdapter adapter;

	private JTable employeeProfsDetailTable;
	private StockManagementTableModel employeeProfDetailTableData;
	private List<EmployeeProfessionalDetails> employeeProfessionalDetails;
	private JScrollPane employeeProfDetailTablePane;

	private AddEmployeeRecordListener listener;

	public AddEmployeeProfession(AddEmployeeRecordListener listener) {
		this.listener = listener;
	}

	public void createAndShowGUI() {
		setVisible(true);
		setSize(700, 380);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Employee Experience:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		employeeProfessionalDetails = new ArrayList<>();
		employeeProfDetailTableData = new StockManagementTableModel(
				getJTableModel(), new String[] { "Company",
						"Job Title", "Start Date", "End Date", "Responsibility" });
		employeeProfsDetailTable = new JTable(employeeProfDetailTableData);
		employeeProfsDetailTable.setShowGrid(true);
		employeeProfDetailTablePane = new JScrollPane(employeeProfsDetailTable);

		createButton = new JButton("Create");
		createButton.setEnabled(false);
		cancelButton = new JButton("Cancel");
		addEmployeeProfDetailButton = new JButton("Add Experience");
		addEmployeeProfDetailButton
				.addActionListener(new AddEmployeeProfessionDetails(this));

		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.setEmployeeProfessionDetailsList(employeeProfessionalDetails);

				for (int i = 1; i <= employeeProfDetailTableData.getRowCount(); i++) {
					employeeProfDetailTableData.removeRow(i - 1);
				}
				employeeProfsDetailTable = null;
				employeeProfDetailTableData = null;
				employeeProfessionalDetails = null;
				dispose();
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 1; i <= employeeProfDetailTableData.getRowCount(); i++) {
					employeeProfDetailTableData.removeRow(i - 1);
				}
				employeeProfsDetailTable = null;
				;
				employeeProfDetailTableData = null;
				employeeProfessionalDetails = null;
				dispose();
			}
		});

		headline.setBounds(80, 30, 400, 30);
		employeeProfDetailTablePane.setBounds(80, 70, 500, 200);
		addEmployeeProfDetailButton.setBounds(50, 290, 100, 30);
		createButton.setBounds(170, 290, 100, 30);
		cancelButton.setBounds(290, 290, 100, 30);

		add(headline);
		add(addEmployeeProfDetailButton);
		add(createButton);
		add(cancelButton);
		add(employeeProfDetailTablePane);

		adapter = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
			}

		};
		addWindowListener(adapter);
	}

	private String[][] getJTableModel() {
		List<String[]> tableModelData = new ArrayList<>();
		for (EmployeeProfessionalDetails employeeProfessionalDetail : employeeProfessionalDetails) {
			tableModelData.add(new String[] {
					employeeProfessionalDetail.getCompanyName(),
					employeeProfessionalDetail.getJobTitle(),
					employeeProfessionalDetail.getStartDate().toString(),
					employeeProfessionalDetail.getEndDate().toString(),
					employeeProfessionalDetail.getResponsobilities()
			});
			
		}
		String[][] tableModel = new String[tableModelData.size()][];
		tableModelData.toArray(tableModel);
		return tableModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		createAndShowGUI();

	}

	public void reloadEmployeeProfessionalDetails() {
		this.employeeProfDetailTableData.refreshTableData(getJTableModel(),
				new String[] {  "Company",
			"Job Title", "Start Date", "End Date", "Responsibility" });
		if (this.employeeProfessionalDetails.size() > 0
				&& !this.createButton.isEnabled()) {
			this.createButton.setEnabled(true);
		}
	}

	public void addEmployeeProfessionalDetails(EmployeeProfessionalDetails employeeProfessionalDetail) {
		this.employeeProfessionalDetails.add(employeeProfessionalDetail);
	}
}
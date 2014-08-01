package com.mobile.tool.stock.manager.designgrid.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.java.dev.designgridlayout.AbstractDesignGridExample;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.RowGroup;
import net.java.dev.designgridlayout.Tag;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

import com.mobile.tool.stock.manager.entity.EmployeeQualificationDetails;
import com.mobile.tool.stock.manager.listener.AddEmployeeRecordListener;

public class AddEmployeeQualification extends AbstractDesignGridExample
		implements ActionListener {

	private AddEmployeeRecordListener addEmployeeRecordListener;
	
	public AddEmployeeQualification(AddEmployeeRecordListener addEmployeeRecordListener) {
		this.addEmployeeRecordListener = addEmployeeRecordListener;
	}
	
	@Override
	public void build(DesignGridLayout layout) {
		frame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		final RowGroup primaryGroup = new RowGroup();
		final JCheckBox primaryGroupBox = new JCheckBox("Primary Level");
		primaryGroupBox.setName("Primary Level");
		addGroup(layout, primaryGroupBox, primaryGroup);
		final JTextField primaryDegree = new JTextField("");
		final JTextField primaryInstitution = new JTextField("");
		final JTextField primaryPercentage = new JTextField("");
		primaryPercentage.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || (c == KeyEvent.VK_PERIOD)) {
					frame().getToolkit().beep();
					e.consume();
				}
			}
		});
		final JDatePickerImpl primaryStartDate = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		final JDatePickerImpl primaryToDate = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		layout.row().group(primaryGroup).grid(label("Degree")).add(primaryDegree);
		layout.row().group(primaryGroup).grid(label("Institution")).add(primaryInstitution);
		layout.row().group(primaryGroup).grid(label("Start Date")).add(primaryStartDate);
		layout.row().group(primaryGroup).grid(label("End Date")).add(primaryToDate);
		layout.row().group(primaryGroup).grid(label("Percentage")).add(primaryPercentage);

		RowGroup secondaryGroup = new RowGroup();
		final JCheckBox secondaryGroupBox = new JCheckBox("Secondary Level");
		secondaryGroupBox.setName("Secondary Level");
		addGroup(layout, secondaryGroupBox, secondaryGroup);
		final JTextField secondaryDegree = new JTextField("");
		final JTextField secondaryInstitution = new JTextField("");
		final JTextField secondaryPercentage = new JTextField("");
		secondaryPercentage.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || (c == KeyEvent.VK_PERIOD)) {
					frame().getToolkit().beep();
					e.consume();
				}
			}
		});
		final JDatePickerImpl secondaryStartDate = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		final JDatePickerImpl secondaryToDate = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		layout.row().group(secondaryGroup).grid(label("Degree")).add(secondaryDegree);
		layout.row().group(secondaryGroup).grid(label("Institution")).add(secondaryInstitution);
		layout.row().group(secondaryGroup).grid(label("Start Date")).add(secondaryStartDate);
		layout.row().group(secondaryGroup).grid(label("End Date")).add(secondaryToDate);
		layout.row().group(secondaryGroup).grid(label("Percentage")).add(secondaryPercentage);
		
		RowGroup teritaryGroup = new RowGroup();
		final JCheckBox teritaryGroupBox = new JCheckBox("Teritary Level");
		teritaryGroupBox.setName("Teritary Level");
		addGroup(layout, teritaryGroupBox, teritaryGroup);
		final JTextField teritaryDegree = new JTextField("");
		final JTextField teritaryInstitution = new JTextField("");
		final JTextField teritaryPercentage = new JTextField("");
		teritaryPercentage.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || (c == KeyEvent.VK_PERIOD)) {
					frame().getToolkit().beep();
					e.consume();
				}
			}
		});
		final JDatePickerImpl teritaryStartDate = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		final JDatePickerImpl teritaryToDate = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel()));
		layout.row().group(teritaryGroup).grid(label("Degree")).add(teritaryDegree);
		layout.row().group(teritaryGroup).grid(label("Institution")).add(teritaryInstitution);
		layout.row().group(teritaryGroup).grid(label("Start Date")).add(teritaryStartDate);
		layout.row().group(teritaryGroup).grid(label("End Date")).add(teritaryToDate);
		layout.row().group(teritaryGroup).grid(label("Percentage")).add(teritaryPercentage);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				frame().dispose();
			}
			
		});
		
		JButton addButton = new JButton("Add");
		addButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(primaryGroupBox.isSelected()){
					addEmployeeRecordListener.addEmployeeQualificationDetailsList(new EmployeeQualificationDetails(0, null, primaryDegree.getText(), 
							primaryInstitution.getText(), (Date)primaryStartDate.getModel().getValue(), (Date)primaryToDate.getModel().getValue(),
							(primaryPercentage.getText()!=null)?Double.parseDouble(primaryPercentage.getText()):0.0));
				}
				
				if(secondaryGroupBox.isSelected()){
					addEmployeeRecordListener.addEmployeeQualificationDetailsList(new EmployeeQualificationDetails(0, null, secondaryDegree.getText(), 
							secondaryInstitution.getText(), (Date)secondaryStartDate.getModel().getValue(), (Date)secondaryToDate.getModel().getValue(),
							(secondaryPercentage.getText()!=null)?Double.parseDouble(secondaryPercentage.getText()):0.0));
				}
				
				if(teritaryGroupBox.isSelected()){
					addEmployeeRecordListener.addEmployeeQualificationDetailsList(new EmployeeQualificationDetails(0, null, teritaryDegree.getText(), 
							teritaryInstitution.getText(), (Date)teritaryStartDate.getModel().getValue(), (Date)teritaryToDate.getModel().getValue(),
							(teritaryPercentage.getText()!=null)?Double.parseDouble(teritaryPercentage.getText()):0.0));
				}
				frame().dispose();
			}
		});
		
		layout.emptyRow();
		layout.row().bar().add(addButton, Tag.OK)
				.add(cancelButton, Tag.CANCEL);
	}

	private void addGroup(DesignGridLayout layout, JCheckBox groupBox, RowGroup group) {
		groupBox.setForeground(Color.BLUE);
		groupBox.setSelected(true);
		groupBox.addItemListener(new ShowHideAction(group));
		layout.emptyRow();
		layout.row().left().add(groupBox, new JSeparator()).fill();
	}

	private class ShowHideAction implements ItemListener {
		public ShowHideAction(RowGroup group) {
			_group = group;
		}

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				_group.show();
			} else {
				_group.hide();
			}
			frame().pack();
		}

		final private RowGroup _group;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		go(true);
	}
}

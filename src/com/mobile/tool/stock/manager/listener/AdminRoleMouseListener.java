package com.mobile.tool.stock.manager.listener;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

import com.mobile.tool.stock.manager.entity.SalesRecord;
import com.mobile.tool.stock.manager.handler.CustomerDetailsOptionHandler;
import com.mobile.tool.stock.manager.handler.EmployeeDetailsOptionHandler;
import com.mobile.tool.stock.manager.handler.ProductDetailsOptionHandler;
import com.mobile.tool.stock.manager.handler.ProductOrdersOptionHandler;
import com.mobile.tool.stock.manager.handler.RecordKeepingOptionHandler;
import com.mobile.tool.stock.manager.handler.SalesHistoryOptionHandler;
import com.mobile.tool.stock.manager.handler.UserLoginDetailsOptionHandler;
import com.mobile.tool.stock.manager.handler.VendorDetailsOptionHandler;
import com.mobile.tool.stock.manager.model.AssetsReportData;
import com.mobile.tool.stock.manager.model.SalesReportData;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.report.DynamicReportDesign;
import com.mobile.tool.stock.manager.repository.ProductSupplyRepository;
import com.mobile.tool.stock.manager.repository.SaleRecordsRepository;

public class AdminRoleMouseListener  extends MouseAdapter {

	private JTree tree;
	private StockManagementTableModel tableModel;
	private String lastSelection;
	private JPanel btnPnl;
	private JButton button;
	private JDatePickerImpl fromDatePicker;
	private JDatePickerImpl toDatePicker;
	private JTable table;
	
	
	public AdminRoleMouseListener() {
	}

	public AdminRoleMouseListener(JTree tree,
			StockManagementTableModel tableModel,
			JTable table) {
		super();
		this.tree = tree;
		this.tableModel = tableModel;
		this.table = table;
	}

	public AdminRoleMouseListener addButtonPanel(JPanel btnPnl) {
		this.btnPnl = btnPnl;
		return this;
	}
	
	public void mouseClicked(MouseEvent me) {
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		
		if ("Sales History".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Sales History".equalsIgnoreCase(lastSelection)) {
				SalesHistoryOptionHandler.handleSalesHistoryOptionSelectionForToday(
						tableModel);
				if(button!=null){
					btnPnl.remove(button);
					btnPnl.revalidate();
					btnPnl.repaint();
				}
				if(fromDatePicker!=null || toDatePicker!=null){
					btnPnl.remove(fromDatePicker);
					btnPnl.remove(toDatePicker);
				}
				this.lastSelection = "Sales History";
			}
		} else if ("Manage User".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Manage User".equalsIgnoreCase(lastSelection)) {
				UserLoginDetailsOptionHandler.handleUserLoginDetailsOptionSelectionForAdmin(
						tableModel);
				addButtonsInPanel("Add User", new AddNewUserLoginDetailsListener(this));
				this.lastSelection = "Manage User";
			}
		} 
		else if("Manage Records".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Manage Records".equalsIgnoreCase(lastSelection)) {
				RecordKeepingOptionHandler.handleRecordKeeping(tableModel);
				addButtonsInPanel("Add Records", new AddRecordKeepingListener(this));
				this.lastSelection = "Manage Records";
				table.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
					    int row = table.rowAtPoint(new Point(e.getX(), e.getY()));
			            int col = table.columnAtPoint(new Point(e.getX(), e.getY()));
			            if(col == 2){
				            String url = (String) table.getModel().getValueAt(row, col);
				            url = url.substring(url.indexOf("<a href='")+9);
				            url = url.substring(0, url.indexOf("'>"));
				            JFileChooser chooser = new JFileChooser();
				            if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(null)) {
				            	File destinationFile = chooser.getSelectedFile();
								try {
									Files.copy(new File(url).toPath(),
											destinationFile.toPath());
								} catch (IOException e1) {
									e1.printStackTrace();
								}
				            }

			            }
						
					}
				});
			} 
		} else if("Customer List".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Customer List".equalsIgnoreCase(lastSelection)) {
				CustomerDetailsOptionHandler.handleCustomerDetailsOptionSelectionForAdmin(
						tableModel);
				addButtonsInPanel("New Customer", new AddCustomerRecordListener(this));
				this.lastSelection = "Customer List";
			} 
		} else if("Employee List".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Employee List".equalsIgnoreCase(lastSelection)) {
				EmployeeDetailsOptionHandler.handleEmployeeDetailsOptionSelectionForAdmin(
						tableModel);
				addButtonsInPanel("New Employee", new AddEmployeeRecordListener(this));
				this.lastSelection = "Employee List";
			} 
		}else if("Vendor List".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Vendor List".equalsIgnoreCase(lastSelection)) {
				VendorDetailsOptionHandler.handleVendorDetailsOptionSelectionForAdmin(
						tableModel);
				addButtonsInPanel("New Vendor", new AddNewVendorRecordListener(this));
				this.lastSelection = "Vendor List";
			} 
		}else if("Product List".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Product List".equalsIgnoreCase(lastSelection)) {
				ProductDetailsOptionHandler.handleProductDetailsOptionSelectionForAdmin(
						tableModel);
				addButtonsInPanel("Add Product", new AddNewProductRecordListener(this));
				this.lastSelection = "Product List";
			} 
		}else if("Product Order".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Product Order".equalsIgnoreCase(lastSelection)) {
				ProductOrdersOptionHandler.handleProductSupplyOptionSelectionForAdmin(
						tableModel);
				addButtonsInPanel("Add Supply", new AddNewProductSupplyListener(this));
				this.lastSelection = "Product Order";
			} 
		}else if("Sales Reports".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Sales Reports".equalsIgnoreCase(lastSelection)) {
				tableModel.refreshTableData(new String[][]{}, new String[]{});
				if (btnPnl != null) {
					if(button!=null)
						btnPnl.remove(button);
					SqlDateModel fromDateModel = new SqlDateModel();
					JDatePanelImpl fromDatePanel = new JDatePanelImpl(fromDateModel);
					fromDatePicker = new JDatePickerImpl(fromDatePanel);
					btnPnl.add(fromDatePicker);
					
					SqlDateModel toDateModel = new SqlDateModel();
					JDatePanelImpl toDatePanel = new JDatePanelImpl(toDateModel);
					toDatePicker = new JDatePickerImpl(toDatePanel);
					btnPnl.add(toDatePicker);
					
					button = (new JButton("Get Report"));
					button.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								Date fromDate = (Date) fromDatePicker.getModel().getValue();
								Date toDate = (Date) toDatePicker.getModel().getValue();
								List<SalesRecord> salesRecords = SaleRecordsRepository.getSaleRecordsForDatePeriod(fromDate, toDate);
								DynamicReportDesign design = new DynamicReportDesign(new SalesReportData(salesRecords));
								JasperReportBuilder report = design.build();
								report.show(false);
							} catch (DRException e1) {
								e1.printStackTrace();
							}
						}
					});
					btnPnl.add(button);
					btnPnl.revalidate();
				}
				this.lastSelection = "Sales Reports";
			} 
		}else if("Account Sheet".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Account Sheet".equalsIgnoreCase(lastSelection)) {
				tableModel.refreshTableData(new String[][]{}, new String[]{});
				if (btnPnl != null) {
					if(button!=null){
						btnPnl.remove(button);
					}
					if(fromDatePicker!=null || toDatePicker!=null){
						btnPnl.remove(fromDatePicker);
						btnPnl.remove(toDatePicker);
					}
					button = (new JButton("Get Report"));
					button.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								DynamicReportDesign design = new DynamicReportDesign(new AssetsReportData(ProductSupplyRepository.getProductLiabilities(), SaleRecordsRepository.getProductEquity()));
								JasperReportBuilder report = design.build();
								report.show(false);
							} catch (DRException e1) {
								e1.printStackTrace();
							}
						}
					});
					btnPnl.add(button);
					btnPnl.revalidate();
					btnPnl.repaint();
				}
				this.lastSelection = "Account Sheet";
			} 
		}

	}
	
	public void reloadCustomerTableData() {
		CustomerDetailsOptionHandler.handleCustomerDetailsOptionSelectionForAdmin(
				tableModel);
	}
	
	private void addButtonsInPanel(String buttonName, ActionListener actionListener) {
		if (btnPnl != null) {
			if(button!=null)
				btnPnl.remove(button);
			if(fromDatePicker!=null || toDatePicker!=null){
				btnPnl.remove(fromDatePicker);
				btnPnl.remove(toDatePicker);
			}
				
			button = (new JButton(buttonName));
			button.addActionListener(actionListener);
			btnPnl.add(button);
			btnPnl.revalidate();
			btnPnl.repaint();
		}
	}

	public void reloadRecordKeepingTableData() {
		RecordKeepingOptionHandler.handleRecordKeeping(tableModel);
	}

	public void reloadVendorTableData() {
		VendorDetailsOptionHandler.handleVendorDetailsOptionSelectionForAdmin(tableModel);
	}

	public void reloadProductTableData() {
		ProductDetailsOptionHandler.handleProductDetailsOptionSelectionForAdmin(tableModel);
	}

	public void reloadEmployeeTableData() {
		EmployeeDetailsOptionHandler.handleEmployeeDetailsOptionSelectionForAdmin(tableModel);
	}

	public void reloadUserDetailsData() {
		UserLoginDetailsOptionHandler.handleUserLoginDetailsOptionSelectionForAdmin(tableModel);
	}

	public void reloadProductSupplyData() {
		ProductOrdersOptionHandler.handleProductSupplyOptionSelectionForAdmin(tableModel);
	}
	
}

package com.mobile.tool.stock.manager.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

import com.mobile.tool.stock.manager.entity.SalesRecord;
import com.mobile.tool.stock.manager.report.DynamicColumn;
import com.mobile.tool.stock.manager.report.DynamicReport;
import com.mobile.tool.stock.manager.report.DynamicReportData;

public class SalesReportData extends DynamicReportData{

	private List<SalesRecord> salesRecords;

	public SalesReportData(List<SalesRecord> salesRecords) {
		this.salesRecords = salesRecords;
	}
	
	public JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("product", "customer", "created", "noOfItems", "salesPrice", "confirmPrice");
		for (SalesRecord salesRecord : salesRecords) {
			dataSource.add( salesRecord.getProduct().getName(), salesRecord.getCustomer().getFirstName()+" "+salesRecord.getCustomer().getLastName()
					, toDate(salesRecord.getCreated()), salesRecord.getNoOfItems(), new BigDecimal(salesRecord.getSalesAmount()), new BigDecimal(salesRecord.getConfirmAmount()));
		}
		return dataSource;
	}

	public DynamicReport getDynamicReport() {
		DynamicReport report = new DynamicReport();
		report.setTitle("Sales");
	    report.addColumn(new DynamicColumn("Product", "product", "string"));
	    report.addColumn(new DynamicColumn("Customer", "customer", "string"));
	    DynamicColumn column = new DynamicColumn("Order date", "created", "date");
	    column.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    report.addColumn(column);
		report.addColumn(new DynamicColumn("Quantity", "noOfItems", "integer"));
	    column = new DynamicColumn("Sales Price", "salesPrice", "bigDecimal");
	    column.setPattern("#,###.0");
		report.addColumn(column);
	    column = new DynamicColumn("Confirm Price", "confirmPrice", "bigDecimal");
	    column.setPattern("#,###.0");
		report.addColumn(column);
	    report.addGroup("created");
	    report.addSubtotal("noOfItems");
	    report.addSubtotal("salesPrice");
	    report.addSubtotal("confirmPrice");
	    report.setShowPageNumber(true);
	    return report;
	}

	private Date toDate(Date date) {
		Calendar c = Calendar.getInstance();
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		c.set(Calendar.YEAR, dateCal.get(Calendar.YEAR));
		c.set(Calendar.MONTH, dateCal.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, dateCal.get(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

}

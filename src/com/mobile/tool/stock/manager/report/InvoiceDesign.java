/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca
 * http://www.dynamicreports.org
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package com.mobile.tool.stock.manager.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.sbt;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;

import com.mobile.tool.stock.manager.entity.SalesRecord;
import com.mobile.tool.stock.manager.view.StockDashboardTab;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class InvoiceDesign {
	private InvoiceData data;
	private AggregationSubtotalBuilder<BigDecimal> totalSum;

	public JasperReportBuilder build(SalesRecord salesRecord) throws DRException {
		data = new InvoiceData(salesRecord);
		JasperReportBuilder report = report();

		//init styles
		StyleBuilder columnStyle = stl.style(Templates.columnStyle)
			.setBorder(stl.pen1Point());
		StyleBuilder subtotalStyle = stl.style(columnStyle)
			.bold();
		StyleBuilder shippingStyle = stl.style(Templates.boldStyle)
			.setHorizontalAlignment(HorizontalAlignment.RIGHT);

		//init columns
		TextColumnBuilder<Integer> rowNumberColumn = col.reportRowNumberColumn()
			.setFixedColumns(2)
			.setHorizontalAlignment(HorizontalAlignment.CENTER);
		TextColumnBuilder<String> descriptionColumn = col.column("Description", "description", type.stringType())
			.setFixedWidth(250);
		TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType())
			.setHorizontalAlignment(HorizontalAlignment.CENTER);
		TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit Price", "unitprice", Templates.currencyType);
		TextColumnBuilder<BigDecimal> saleAmtColumn =  col.column("Sales Price", "saleamount", Templates.currencyType);
		TextColumnBuilder<BigDecimal> confAmtColumn = col.column("Confirm Amount", "confirmamount", Templates.currencyType);

		TextColumnBuilder<BigDecimal> totalColumn = confAmtColumn.add(0)
			.setTitle("Total Price")
			.setDataType(Templates.currencyType)
			.setRows(2)
			.setStyle(subtotalStyle);
		//init subtotals
		totalSum = sbt.sum(totalColumn)
			.setLabel("Total:")
			.setLabelStyle(Templates.boldStyle);

		//configure report
		report
			.setTemplate(Templates.reportTemplate)
			.setColumnStyle(columnStyle)
			.setSubtotalStyle(subtotalStyle)
			//columns
			.columns(
				rowNumberColumn, descriptionColumn, quantityColumn, unitPriceColumn, totalColumn, saleAmtColumn, confAmtColumn)
			.columnGrid(
				rowNumberColumn, descriptionColumn, quantityColumn, unitPriceColumn,
				grid.horizontalColumnGridList()
					.add(totalColumn).newRow()
					.add(saleAmtColumn, confAmtColumn))
			//subtotals
			.subtotalsAtSummary(
				totalSum, sbt.sum(saleAmtColumn), sbt.sum(confAmtColumn))
			//band components
			.title(
				Templates.createTitleComponent("Invoice No.: " + data.getInvoice().getId()),
				cmp.horizontalList().setStyle(stl.style(10)).setGap(50).add(
					cmp.hListCell(createCustomerComponent("Bill To", data.getInvoice().getBillTo())).heightFixedOnTop()),		
				cmp.verticalGap(10))
			.pageFooter(
				Templates.footerComponent)
			.summary(
				cmp.text(data.getInvoice().getShipping()).setValueFormatter(Templates.createCurrencyValueFormatter("Shipping:")).setStyle(shippingStyle),
				cmp.horizontalList(
					cmp.text(new TotalPaymentExpression()).setStyle(Templates.bold18CenteredStyle)),
				cmp.verticalGap(30),
				cmp.text("Thank you for your business").setStyle(Templates.bold12CenteredStyle))
			.setDataSource(data.createDataSource());

		return report;
	}

	private ComponentBuilder<?, ?> createCustomerComponent(String label, Customer customer) {
		HorizontalListBuilder list = cmp.horizontalList().setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));
		addCustomerAttribute(list, "Name", customer.getName());
		addCustomerAttribute(list, "Address", customer.getAddress());
		addCustomerAttribute(list, "Mobile Number", customer.getMobileNumber());
		addCustomerAttribute(list, "Email", customer.getEmail());
		return cmp.verticalList(
							cmp.text(label).setStyle(Templates.boldStyle),
							list);
	}

	private void addCustomerAttribute(HorizontalListBuilder list, String label, String value) {
		if (value != null) {
			list.add(cmp.text(label + ":").setFixedColumns(8).setStyle(Templates.boldStyle), cmp.text(value)).newRow();
		}
	}

	private class TotalPaymentExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			BigDecimal total = reportParameters.getValue(totalSum);
			BigDecimal shipping = total.add(data.getInvoice().getShipping());
			return "Total payment: " + Templates.currencyType.valueToString(shipping, reportParameters.getLocale());
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		InvoiceDesign design = new InvoiceDesign();
		try {
			JasperReportBuilder report = design.build(null);
			String filePath = "D:\\";
			try{
				filePath = URLDecoder.decode(StockDashboardTab.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
				filePath = filePath + "/tmp/";
				if(!new File(filePath).exists()){
					filePath = "D:\\";
				}
			}catch(Exception e){
				filePath = "D:\\";
			}
			OutputStream stream = new FileOutputStream(new File(filePath+"demo.pdf"));
			report.toPdf(stream);
			report.show(false);
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	public static void printInvoiceBill(SalesRecord salesRecord) {
		InvoiceDesign design = new InvoiceDesign();
		try {
			JasperReportBuilder report = design.build(salesRecord);
			String filePath = "D:\\";
			try{
				filePath = URLDecoder.decode(StockDashboardTab.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
				filePath = filePath + "/tmp/";
				if(!new File(filePath).exists()){
					filePath = "D:\\";
				}
			}catch(Exception e){
				filePath = "D:\\";
			}
			OutputStream stream = new FileOutputStream(new File(filePath+"demo.pdf"));
			report.toPdf(stream);
			report.show();
		} catch (DRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

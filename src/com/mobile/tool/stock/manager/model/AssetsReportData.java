package com.mobile.tool.stock.manager.model;

import java.math.BigDecimal;
import java.util.Map;

import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.report.DynamicColumn;
import com.mobile.tool.stock.manager.report.DynamicReport;
import com.mobile.tool.stock.manager.report.DynamicReportData;
import com.mobile.tool.stock.manager.repository.ProductRecordRepository;

public class AssetsReportData extends DynamicReportData{

	private Map<String, Double> liabilities;
	private Map<String, Double> equities;

	public AssetsReportData(Map<String, Double> liabilities, Map<String, Double> equities) {
		this.liabilities = liabilities;
		this.equities = equities;
	}
	
	public JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("productCategory", "product", "liability", "equity");
		for (String productCode : liabilities.keySet()) {
			ProductRecord productRecord = ProductRecordRepository.getProductRecordByCode(productCode);
			dataSource.add(productRecord.getProductcategory().getCategoryName(), productRecord.getName()
					, new BigDecimal(liabilities.get(productCode)), (equities.containsKey(productCode)?new BigDecimal(equities.get(productCode)): new BigDecimal(0)));
		}
		return dataSource;
	}

	public DynamicReport getDynamicReport() {
		DynamicReport report = new DynamicReport();
		report.setTitle("Assets");
		 report.addColumn(new DynamicColumn("Product Category", "productCategory", "string"));
	    report.addColumn(new DynamicColumn("Product", "product", "string"));
	    DynamicColumn column = new DynamicColumn("Liability", "liability", "bigDecimal");
	    column.setPattern("#,###.0");
		report.addColumn(column);
	    column = new DynamicColumn("Equity", "equity", "bigDecimal");
	    column.setPattern("#,###.0");
		report.addColumn(column);
		
	    report.addGroup("productCategory");
	    report.addSubtotal("liability");
	    report.addSubtotal("equity");
	    report.setShowPageNumber(true);
	    return report;
	}
}

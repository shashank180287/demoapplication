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
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.tableOfContentsCustomizer;
import static net.sf.dynamicreports.report.builder.DynamicReports.template;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Locale;
import java.util.Properties;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;

import com.mobile.tool.stock.manager.view.StockDashboardTab;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class Templates {
	public static final StyleBuilder rootStyle;
	public static final StyleBuilder boldStyle;
	public static final StyleBuilder italicStyle;
	public static final StyleBuilder sloganStyle;
	public static final StyleBuilder addressStyle;
	public static final StyleBuilder phoneNumberStyle;
	public static final StyleBuilder boldCenteredStyle;
	public static final StyleBuilder bold12CenteredStyle;
	public static final StyleBuilder bold18CenteredStyle;
	public static final StyleBuilder bold22CenteredStyle;
	public static final StyleBuilder columnStyle;
	public static final StyleBuilder columnTitleStyle;
	public static final StyleBuilder groupStyle;
	public static final StyleBuilder subtotalStyle;

	public static final ReportTemplateBuilder reportTemplate;
	public static final CurrencyType currencyType;
	public static final ComponentBuilder<?, ?> dynamicReportsComponent;
	public static final ComponentBuilder<?, ?> footerComponent;

	static {
		rootStyle           = stl.style().setPadding(2);
		boldStyle           = stl.style(rootStyle).bold();
		italicStyle         = stl.style(rootStyle).italic().setFontSize(8);
		sloganStyle         = stl.style(rootStyle).setFontSize(9);
		addressStyle        = stl.style(rootStyle).setFontSize(8);
		phoneNumberStyle    = stl.style(rootStyle).setFontSize(8);
		boldCenteredStyle   = stl.style(boldStyle)
		                         .setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
		bold12CenteredStyle = stl.style(boldCenteredStyle)
		                         .setFontSize(12);
		bold18CenteredStyle = stl.style(boldCenteredStyle)
		                         .setFontSize(18);
		bold22CenteredStyle = stl.style(boldCenteredStyle)
                             .setFontSize(22);
		columnStyle         = stl.style(rootStyle).setVerticalAlignment(VerticalAlignment.MIDDLE);
		columnTitleStyle    = stl.style(columnStyle)
		                         .setBorder(stl.pen1Point())
		                         .setHorizontalAlignment(HorizontalAlignment.CENTER)
		                         .setBackgroundColor(Color.LIGHT_GRAY)
		                         .bold();
		groupStyle          = stl.style(boldStyle)
		                         .setHorizontalAlignment(HorizontalAlignment.LEFT);
		subtotalStyle       = stl.style(boldStyle)
		                         .setTopBorder(stl.pen1Point());

		StyleBuilder crosstabGroupStyle      = stl.style(columnTitleStyle);
		StyleBuilder crosstabGroupTotalStyle = stl.style(columnTitleStyle)
		                                          .setBackgroundColor(new Color(170, 170, 170));
		StyleBuilder crosstabGrandTotalStyle = stl.style(columnTitleStyle)
		                                          .setBackgroundColor(new Color(140, 140, 140));
		StyleBuilder crosstabCellStyle       = stl.style(columnStyle)
		                                          .setBorder(stl.pen1Point());

		TableOfContentsCustomizerBuilder tableOfContentsCustomizer = tableOfContentsCustomizer()
			.setHeadingStyle(0, stl.style(rootStyle).bold());

		reportTemplate = template()
		                   .setLocale(Locale.ENGLISH)
		                   .setColumnStyle(columnStyle)
		                   .setColumnTitleStyle(columnTitleStyle)
		                   .setGroupStyle(groupStyle)
		                   .setGroupTitleStyle(groupStyle)
		                   .setSubtotalStyle(subtotalStyle)
		                   .highlightDetailEvenRows()
		                   .crosstabHighlightEvenRows()
		                   .setCrosstabGroupStyle(crosstabGroupStyle)
		                   .setCrosstabGroupTotalStyle(crosstabGroupTotalStyle)
		                   .setCrosstabGrandTotalStyle(crosstabGrandTotalStyle)
		                   .setCrosstabCellStyle(crosstabCellStyle)
		                   .setTableOfContentsCustomizer(tableOfContentsCustomizer);

		currencyType = new CurrencyType();

		String companyName = "Stock Management";
		String webSite = "http://www.abc.org";
		String slogan = "Believe in deliver with quality, in time....To experience, give me a change";
		String address = "Bangalore-India";
		String phoneNumber = "8884198410";
		
		String propPath = "C:/";
		try{
			propPath = URLDecoder.decode(StockDashboardTab.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
			propPath = propPath + "/logo/rollback.zip";
			if(!new File(propPath).exists()){
				propPath = "C:/rollback.zip";
			}
		} catch (IOException e) {
			propPath = "C:/rollback.zip";
		}
		try{
			ZipFile zipFile = new ZipFile(propPath);
			if (zipFile.isEncrypted()) {
				zipFile.setPassword("@dm1nHenry");
			}
			Properties properties = new Properties();
			properties.load(zipFile.getInputStream(zipFile.getFileHeader("sm.ini")));
			companyName = properties.getProperty("company.name");
			webSite = properties.getProperty("company.website");
			slogan = properties.getProperty("company.slogan");
			address = properties.getProperty("company.address");
			phoneNumber = properties.getProperty("company.number");

		}catch(ZipException ex){
		} catch (IOException e) {
		}
		
		String logoPath = "images/mobile_tool.png";
		HyperLinkBuilder link = hyperLink(webSite);
		try{
			logoPath = URLDecoder.decode(StockDashboardTab.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
			logoPath = logoPath + "/logo/logo.png";
			if(!new File(logoPath).exists()){
				logoPath = "images/mobile_tool.png";
			}
		}catch(Exception e){
			logoPath = "images/mobile_tool.png";
		}

		dynamicReportsComponent =
		  cmp.horizontalList(
		  	cmp.image(Templates.class.getClassLoader().getResource(logoPath)).setFixedDimension(120, 120),
		  	cmp.verticalList(
		  		cmp.text(companyName).setStyle(bold22CenteredStyle).setHorizontalAlignment(HorizontalAlignment.LEFT),
		  		cmp.text(slogan).setStyle(sloganStyle),
		  		cmp.text(address).setStyle(addressStyle),
		  		cmp.text(phoneNumber).setStyle(phoneNumberStyle),
		  		cmp.text(webSite).setStyle(italicStyle).setHyperLink(link)));

		footerComponent = cmp.pageXofY()
		                     .setStyle(
		                     	stl.style(boldCenteredStyle)
		                     	   .setTopBorder(stl.pen1Point()));
	}

	/**
	 * Creates custom component which is possible to add to any report band component
	 */
	public static ComponentBuilder<?, ?> createTitleComponent(String label) {
		return cmp.horizontalList()
		        .add(
		        	dynamicReportsComponent,
		        	cmp.text(label).setStyle(bold18CenteredStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT))
		        .newRow()
		        .add(cmp.line())
		        .newRow()
		        .add(cmp.verticalGap(10));
	}

	public static CurrencyValueFormatter createCurrencyValueFormatter(String label) {
		return new CurrencyValueFormatter(label);
	}

	public static class CurrencyType extends BigDecimalType {
		private static final long serialVersionUID = 1L;

		@Override
		public String getPattern() {
			return "NGN #,###.00";
		}
	}

	private static class CurrencyValueFormatter extends AbstractValueFormatter<String, Number> {
		private static final long serialVersionUID = 1L;

		private String label;

		public CurrencyValueFormatter(String label) {
			this.label = label;
		}

		@Override
		public String format(Number value, ReportParameters reportParameters) {
			return label + currencyType.valueToString(value, reportParameters.getLocale());
		}
	}
}

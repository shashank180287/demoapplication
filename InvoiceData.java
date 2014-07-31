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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.entity.SalesRecord;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class InvoiceData {
	private Invoice invoice;

	public InvoiceData(SalesRecord salesRecord) {
		if(salesRecord!=null)
			invoice = createInvoice(salesRecord);
		else
			invoice = createInvoice();
	}

	private Invoice createInvoice(SalesRecord salesRecord) {
		Invoice invoice = new Invoice();
		invoice.setId(salesRecord.getSalesCode());
		invoice.setShipping(new BigDecimal(10));
		invoice.setTax(0.2);

		invoice.setBillTo(createCustomer(salesRecord.getCustomer()));

		List<Item> items = new ArrayList<Item>();
		items.add(createItem(salesRecord));
		invoice.setItems(items);

		return invoice;
	}

	private Customer createCustomer(CustomerRecord customerRecord) {
		Customer customer = new Customer();
		customer.setName(customerRecord.getFirstName()+" "+customerRecord.getLastName());
		customer.setAddress(customerRecord.getContactAddress());
		customer.setEmail(customerRecord.getEmail());
		customer.setMobileNumber(customerRecord.getMobileNumber()+"");
		return customer;
	}

	private Item createItem(SalesRecord salesRecord) {
		Item item = new Item();
		item.setDescription(salesRecord.getProduct().getName());
		item.setQuantity(salesRecord.getNoOfItems());
		item.setUnitprice(new BigDecimal(salesRecord.getProduct().getUnitPrice()));
		item.setSaleamount(new BigDecimal(salesRecord.getSalesAmount()));
		item.setConfirmamount(new BigDecimal(salesRecord.getConfirmAmount()));
		return item;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public JRDataSource createDataSource() {
		return new JRBeanCollectionDataSource(invoice.getItems());
	}
	
	private Invoice createInvoice() {
		Invoice invoice = new Invoice();
		invoice.setId("5");
		invoice.setShipping(new BigDecimal(10));
		invoice.setTax(0.2);

		invoice.setBillTo(createCustomer("Mary Patterson", "151 Pompton St.", "Washington", "mpatterson@noemail.com"));
		invoice.setShipTo(createCustomer("Peter Marsh", "23 Baden Av.", "New York", null));

		List<Item> items = new ArrayList<Item>();
		items.add(createItem("Notebook", 1, new BigDecimal(1000)));
		items.add(createItem("DVD", 5, new BigDecimal(40)));
		items.add(createItem("Book", 2, new BigDecimal(10)));
		items.add(createItem("Phone", 1, new BigDecimal(200)));
		invoice.setItems(items);

		return invoice;
	}

	private Customer createCustomer(String name, String address, String city, String email) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setMobileNumber(city);
		customer.setEmail(email);
		return customer;
	}

	private Item createItem(String description, Integer quantity, BigDecimal unitprice) {
		Item item = new Item();
		item.setDescription(description);
		item.setQuantity(quantity);
		item.setUnitprice(unitprice);
		item.setSaleamount(new BigDecimal(987654));
		item.setConfirmamount(new BigDecimal(45678));
		return item;
	}
}

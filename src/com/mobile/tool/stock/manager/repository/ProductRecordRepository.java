package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.ProductCatagory;
import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.entity.VendorRecord;

public class ProductRecordRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getDerbyJdbcTemplate();
	
	public static void addProductRecord(ProductRecord productRecord) {
		String query = "INSERT INTO PRODUCT_RECORD VALUES ('"+(productRecord.getName().toUpperCase().substring(0, 3)+getRandomNo())+"',"
				+productRecord.getProductcategory().getCategoryId()+",'"
				+productRecord.getName()+"','"
				+productRecord.getDesc()+"',"
				+productRecord.getUnitPrice()+","
				+productRecord.getBulkPrice()+","
				+productRecord.getOrderCount()+",'"
				+productRecord.getVendor().getVendorCode()+"','"
				+productRecord.getCreated()+"')";
		jdbcTemplate.executeUpdate(query);
		
	}
	
	public static ProductRecord getProductRecordByCode(String productCode) {
		List<ProductRecord> productRecords = getProductRecordByQuery("SELECT * FROM PRODUCT_RECORD WHERE product_code='"+productCode+"'");
		return productRecords.size()>0?productRecords.get(0):null;
	}

	public static List<ProductRecord> getAllProductRecords() {
		return getProductRecordByQuery("SELECT * FROM PRODUCT_RECORD ORDER BY product_code");
	}
	
	private static List<ProductRecord> getProductRecordByQuery(String query) {
		List<ProductRecord> productRecords = new ArrayList<>();
		ResultSet productRecordInDb = null;
		try{
			productRecordInDb = jdbcTemplate.executeQuery(query);
			while(productRecordInDb.next()){
				int productCategoryId = productRecordInDb.getInt("product_category_id");
				ProductCatagory productCategory = ProductCategoryRepository.getProductCatagoryByCategoryId(productCategoryId+"");
				String vendorCode = productRecordInDb.getString("vendor_code");
				VendorRecord vendor = VendorRecordRepository.getVendorRecordByCode(vendorCode);
				productRecords.add(new ProductRecord(productRecordInDb.getString("product_code"), productCategory, productRecordInDb.getString("name"), 
						productRecordInDb.getString("description"), productRecordInDb.getFloat("unit_price"), productRecordInDb.getFloat("bulk_price"),
						productRecordInDb.getInt("order_count"), vendor, productRecordInDb.getDate("created")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(productRecordInDb!=null)
				try {
					productRecordInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return productRecords;
	}

	private static int getRandomNo() {
		return (int) (Math.random()*1000);
	}
}

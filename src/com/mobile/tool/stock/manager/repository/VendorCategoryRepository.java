package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.VendorCategory;

public class VendorCategoryRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getMySQLJdbcTemplate();
	
	public static List<VendorCategory> getVendorCategoryByQuery(String  query) {
		List<VendorCategory> vendorCategories = new ArrayList<>();
		ResultSet vendorCategoryInDb = null;
		try{
			vendorCategoryInDb = jdbcTemplate.executeQuery(query);
			while(vendorCategoryInDb.next()){
				vendorCategories.add(new VendorCategory(vendorCategoryInDb.getInt("vendor_category_id"), vendorCategoryInDb.getString("vendor_category_name"), 
						vendorCategoryInDb.getString("vendor_category_desc")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(vendorCategoryInDb!=null)
				try {
					vendorCategoryInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return vendorCategories;
	}
	
	public static VendorCategory getVendorCategoryByCategoryId(String categoryId) {
		String query = "SELECT * FROM VENDOR_CATEGORY WHERE vendor_category_id="+categoryId;
		List<VendorCategory> vendorCategories = getVendorCategoryByQuery(query);
		return (vendorCategories.size()>0?vendorCategories.get(0):null);
	}

	public static List<VendorCategory> getAllVendorCategories() {
		return getVendorCategoryByQuery("SELECT * FROM VENDOR_CATEGORY ORDER BY vendor_category_id");
	}
	
	public static VendorCategory getVendorCategoryByCategoryName(String categoryName) {
		String query = "SELECT * FROM VENDOR_CATEGORY WHERE vendor_category_name='"+categoryName+"'";
		List<VendorCategory> vendorCategories = getVendorCategoryByQuery(query);
		return (vendorCategories.size()>0?vendorCategories.get(0):null);
	}
	
}

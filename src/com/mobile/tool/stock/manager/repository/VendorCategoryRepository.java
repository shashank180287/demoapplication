package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mobile.tool.stock.manager.entity.VendorCategory;

public class VendorCategoryRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getMySQLJdbcTemplate();
	
	public static VendorCategory getVendorCategoryByCategoryId(String categoryId) {
		String query = "SELECT * FROM VENDOR_CATEGORY WHERE vendor_category_id="+categoryId;
		ResultSet vendorCategoryInDb = null;
		try{
			vendorCategoryInDb = jdbcTemplate.executeQuery(query);
			while(vendorCategoryInDb.next()){
				return new VendorCategory(vendorCategoryInDb.getInt("vendor_category_id"), vendorCategoryInDb.getString("vendor_category_name"), 
						vendorCategoryInDb.getString("vendor_category_desc"));
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
		return null;
	}
	
}

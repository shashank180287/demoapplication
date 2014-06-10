package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.entity.VendorCategory;
import com.mobile.tool.stock.manager.entity.VendorRecord;

public class VendorRecordRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getMySQLJdbcTemplate();
	
	public static VendorRecord getVendorRecordByCode(String vendorCode) {
		String query = "SELECT * FROM VENDOR_RECORD WHERE vendor_code='"+vendorCode+"'";
		ResultSet vendorRecordInDb = null;
		try{
			vendorRecordInDb = jdbcTemplate.executeQuery(query);
			while(vendorRecordInDb.next()){
				int vendorCategoryId = vendorRecordInDb.getInt("vendor_category_id");
				VendorCategory vendorCategory = VendorCategoryRepository.getVendorCategoryByCategoryId(vendorCategoryId+"");
				String username = vendorRecordInDb.getString("username");
				UserLoginDetails userLoginDetails = UserLoginDetailsRepository.getUserLoginDetailsByUsername(username);
				return new VendorRecord(vendorRecordInDb.getString("vendor_code"), vendorCategory, 
						 vendorRecordInDb.getString("name"), vendorRecordInDb.getString("title"),
						 vendorRecordInDb.getString("description"), vendorRecordInDb.getInt("mobile_number"),
						 vendorRecordInDb.getString("email"), vendorRecordInDb.getString("contact_address"),
						 vendorRecordInDb.getString("permanent_address"), vendorRecordInDb.getString("website"), userLoginDetails);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(vendorRecordInDb!=null)
				try {
					vendorRecordInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
}

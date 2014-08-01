package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.entity.VendorCategory;
import com.mobile.tool.stock.manager.entity.VendorRecord;

public class VendorRecordRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getDerbyJdbcTemplate();
	
	public static void addVendorRecord(VendorRecord vendorRecord) {
		String query = "INSERT INTO VENDOR_RECORD VALUES ('"+(vendorRecord.getName().toUpperCase().substring(0, 3)+getRandomNo())+"',"
								+vendorRecord.getVendorCategory().getVendorCategoryId()+",'"
								+vendorRecord.getName()+"',"
								+vendorRecord.getMobilenumber()+",'"
								+vendorRecord.getEmail()+"','"
								+vendorRecord.getTitle()+"','"
								+vendorRecord.getContactAddress()+"','"
								+vendorRecord.getPermanentAddress()+"','"
								+vendorRecord.getWebsite()+"','"
								+vendorRecord.getDescription()+"',"
								+(vendorRecord.getUserLoginDetails()!=null?"'"+vendorRecord.getUserLoginDetails().getUsername()+"'":"NULL")+")";
		jdbcTemplate.executeUpdate(query);
	}
	
	public static List<VendorRecord> getVendorRecordByQuery(String query) {
		List<VendorRecord> vendorRecords = new ArrayList<>();
		ResultSet vendorRecordInDb = null;
		try{
			vendorRecordInDb = jdbcTemplate.executeQuery(query);
			while(vendorRecordInDb.next()){
				int vendorCategoryId = vendorRecordInDb.getInt("vendor_cat_id");
				VendorCategory vendorCategory = VendorCategoryRepository.getVendorCategoryByCategoryId(vendorCategoryId+"");
				String username = vendorRecordInDb.getString("username");
				UserLoginDetails userLoginDetails = UserLoginDetailsRepository.getUserLoginDetailsByUsername(username);
				vendorRecords.add(new VendorRecord(vendorRecordInDb.getString("vendor_code"), vendorCategory, 
						 vendorRecordInDb.getString("name"), vendorRecordInDb.getString("title"),
						 vendorRecordInDb.getString("description"), vendorRecordInDb.getLong("mobile_number"),
						 vendorRecordInDb.getString("email"), vendorRecordInDb.getString("contact_address"),
						 vendorRecordInDb.getString("permanent_address"), vendorRecordInDb.getString("website"), userLoginDetails));
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
		return vendorRecords;
	}
	
	public static VendorRecord getVendorRecordByCode(String vendorCode) {
		String query = "SELECT * FROM VENDOR_RECORD WHERE vendor_code='"+vendorCode+"'";
		List<VendorRecord> vendorRecords = getVendorRecordByQuery(query);
		return vendorRecords.size()>0?vendorRecords.get(0):null;
	}
	
	public static List<VendorRecord> getAllVendorRecords() {
		String query = "SELECT * FROM VENDOR_RECORD ORDER BY vendor_code";
		return getVendorRecordByQuery(query);
	}
	
	private static int getRandomNo() {
		return (int) (Math.random()*1000);
	}

	public static void updateVendorRecord(VendorRecord vendorRecord) {
		verifyVendorCode(vendorRecord.getVendorCode());
		String query = "UPDATE VENDOR_RECORD SET vendor_cat_id="+ vendorRecord.getVendorCategory().getVendorCategoryId() 
				+ ", name='" + vendorRecord.getName()
				+ "', title='" + vendorRecord.getTitle()
				+ "', description='" + vendorRecord.getDescription()
				+ "', mobile_number=" + vendorRecord.getMobilenumber()
				+ ", email='" + vendorRecord.getEmail()
				+ "', contact_address='" + vendorRecord.getContactAddress()
				+ "', permanent_address='" + vendorRecord.getPermanentAddress()
				+ "', website='" + vendorRecord.getWebsite()
				+ "' WHERE vendor_code='"+vendorRecord.getVendorCode()+"'";
		jdbcTemplate.executeUpdate(query);
	}

	public static void removeVendorRecord(String vendorCode) {
		verifyVendorCode(vendorCode);
		String query = "DELETE FROM VENDOR_RECORD WHERE vendor_code='"+vendorCode+"'";
		jdbcTemplate.executeUpdate(query);
	}
	
	private static void verifyVendorCode(String vendorCode) {
		if(vendorCode==null)
			throw new IllegalArgumentException("Please pass proper id");
	}
}

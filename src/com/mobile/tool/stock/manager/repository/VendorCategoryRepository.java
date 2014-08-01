package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.VendorCategory;

public class VendorCategoryRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getDerbyJdbcTemplate();

	private static int getCurrentIdCursor() {
		String query = "SELECT MAX(vendor_cat_id) FROM VENDOR_CATEGORY";
		ResultSet maxId = null;
		try {
			maxId = jdbcTemplate.executeQuery(query);
			while (maxId.next())
				return maxId.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (maxId != null)
				try {
					maxId.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return 0;
	}

	public static void addVendorCategoryDetails(VendorCategory vendorCategory) {
		String query = "INSERT INTO VENDOR_CATEGORY VALUES ("
				+ (getCurrentIdCursor() + 1) + ",'"
				+ vendorCategory.getVendorCategoryName() + "','"
				+ vendorCategory.getVendorCategoryDesc() + "')";
		jdbcTemplate.executeUpdate(query);
	}

	public static List<VendorCategory> getVendorCategoryByQuery(String query) {
		List<VendorCategory> vendorCategories = new ArrayList<>();
		ResultSet vendorCategoryInDb = null;
		try {
			vendorCategoryInDb = jdbcTemplate.executeQuery(query);
			while (vendorCategoryInDb.next()) {
				vendorCategories.add(new VendorCategory(vendorCategoryInDb
						.getInt("vendor_cat_id"), vendorCategoryInDb
						.getString("vendor_cat_name"), vendorCategoryInDb
						.getString("vendor_cat_desc")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (vendorCategoryInDb != null)
				try {
					vendorCategoryInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return vendorCategories;
	}

	public static VendorCategory getVendorCategoryByCategoryId(String categoryId) {
		String query = "SELECT * FROM VENDOR_CATEGORY WHERE vendor_cat_id="
				+ categoryId;
		List<VendorCategory> vendorCategories = getVendorCategoryByQuery(query);
		return (vendorCategories.size() > 0 ? vendorCategories.get(0) : null);
	}

	public static List<VendorCategory> getAllVendorCategories() {
		return getVendorCategoryByQuery("SELECT * FROM VENDOR_CATEGORY ORDER BY vendor_cat_id");
	}

	public static VendorCategory getVendorCategoryByCategoryName(
			String categoryName) {
		String query = "SELECT * FROM VENDOR_CATEGORY WHERE vendor_cat_name='"
				+ categoryName + "'";
		List<VendorCategory> vendorCategories = getVendorCategoryByQuery(query);
		return (vendorCategories.size() > 0 ? vendorCategories.get(0) : null);
	}

	public static void updateVendorCategoryDetails(VendorCategory vendorCategory) {
		verifyVendorCategoryId(vendorCategory.getVendorCategoryId());
		String query = "UPDATE VENDOR_CATEGORY SET vendor_cat_name='"
				+ vendorCategory.getVendorCategoryName() + "', vendor_cat_desc='"
				+ vendorCategory.getVendorCategoryDesc() + "' WHERE vendor_cat_id="+vendorCategory.getVendorCategoryId();
		jdbcTemplate.executeUpdate(query);
	}

	private static void verifyVendorCategoryId(int vendorCategoryId) {
		if(vendorCategoryId==0)
			throw new IllegalArgumentException("Please pass proper id");
	}

	public static void removeVendorCategoryDetails(int venCatId) {
		verifyVendorCategoryId(venCatId);
		String query = "DELETE FROM VENDOR_CATEGORY WHERE vendor_cat_id="+venCatId;
		jdbcTemplate.executeUpdate(query);
	}

}

package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.ProductCatagory;

public class ProductCategoryRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getDerbyJdbcTemplate();

	private static int getCurrentIdCursor() {
		String query = "SELECT MAX(product_cat_id) FROM PRODUCT_CATEGORY";
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

	public static void addProductCategoryDetails(ProductCatagory productCatagory) {
		String query = "INSERT INTO PRODUCT_CATEGORY VALUES ("
				+ (getCurrentIdCursor() + 1) + ",'"
				+ productCatagory.getCategoryName() + "','"
				+ productCatagory.getCategoryDescription() + "')";
		jdbcTemplate.executeUpdate(query);

	}

	public static List<ProductCatagory> getProductCatagoryByQuery(String query) {
		List<ProductCatagory> productCatagories = new ArrayList<>();
		ResultSet productCategoryInDb = null;
		try {
			productCategoryInDb = jdbcTemplate.executeQuery(query);
			while (productCategoryInDb.next()) {
				productCatagories.add(new ProductCatagory(productCategoryInDb
						.getInt("product_cat_id"), productCategoryInDb
						.getString("product_cat_name"), productCategoryInDb
						.getString("product_cat_desc")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (productCategoryInDb != null)
				try {
					productCategoryInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return productCatagories;
	}

	public static ProductCatagory getProductCatagoryByCategoryId(
			String categoryId) {
		String query = "SELECT * FROM PRODUCT_CATEGORY WHERE product_cat_id="
				+ categoryId;
		List<ProductCatagory> productCatagories = getProductCatagoryByQuery(query);
		return productCatagories.size() > 0 ? productCatagories.get(0) : null;
	}

	public static ProductCatagory getProductCatagoryByCategoryName(
			String categoryName) {
		String query = "SELECT * FROM PRODUCT_CATEGORY WHERE product_cat_name='"
				+ categoryName + "'";
		List<ProductCatagory> productCatagories = getProductCatagoryByQuery(query);
		return productCatagories.size() > 0 ? productCatagories.get(0) : null;
	}

	public static List<ProductCatagory> getAllProductCategories() {
		return getProductCatagoryByQuery("SELECT * FROM PRODUCT_CATEGORY ORDER BY product_cat_id");
	}

	public static void updateProductCategoryDetails(
			ProductCatagory productCategory) {
		verifyProductCategoryId(productCategory.getCategoryId());
		String query = "UPDATE PRODUCT_CATEGORY SET product_cat_name='"
				+ productCategory.getCategoryName() + "', product_cat_desc='"
				+ productCategory.getCategoryDescription() + "' WHERE product_cat_id="+productCategory.getCategoryId();
		jdbcTemplate.executeUpdate(query);
	}

	private static void verifyProductCategoryId(int productCategoryId) {
		if(productCategoryId==0)
			throw new IllegalArgumentException("Please pass proper id");
	}

	public static void removeProductCategoryDetails(int prodCatId) {
		verifyProductCategoryId(prodCatId);
		String query = "DELETE FROM PRODUCT_CATEGORY WHERE product_cat_id="+prodCatId;
		jdbcTemplate.executeUpdate(query);
	}

}

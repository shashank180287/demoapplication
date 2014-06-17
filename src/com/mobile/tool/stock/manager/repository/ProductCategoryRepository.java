package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.ProductCatagory;

public class ProductCategoryRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getMySQLJdbcTemplate();
	
	public static List<ProductCatagory> getProductCatagoryByQuery(String query) {
		List<ProductCatagory> productCatagories = new ArrayList<>();
		ResultSet productCategoryInDb = null;
		try{
			productCategoryInDb = jdbcTemplate.executeQuery(query);
			while(productCategoryInDb.next()){
				productCatagories.add(new ProductCatagory(productCategoryInDb.getInt("category_id"), productCategoryInDb.getString("category_name"), 
						productCategoryInDb.getString("category_description")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(productCategoryInDb!=null)
				try {
					productCategoryInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return productCatagories;
	}

	public static ProductCatagory getProductCatagoryByCategoryId(String categoryId) {
		String query = "SELECT * FROM PRODUCT_CATEGORY WHERE category_id="+categoryId;
		List<ProductCatagory> productCatagories = getProductCatagoryByQuery(query);
		return productCatagories.size()>0?productCatagories.get(0):null;
	}
	
	public static ProductCatagory getProductCatagoryByCategoryName(String categoryName) {
		String query = "SELECT * FROM PRODUCT_CATEGORY WHERE category_name='"+categoryName+"'";
		List<ProductCatagory> productCatagories = getProductCatagoryByQuery(query);
		return productCatagories.size()>0?productCatagories.get(0):null;
	}
	
	public static List<ProductCatagory> getAllProductCategories() {
		return getProductCatagoryByQuery("SELECT * FROM PRODUCT_CATEGORY ORDER BY category_id");
	}

}

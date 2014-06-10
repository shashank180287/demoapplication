package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mobile.tool.stock.manager.entity.ProductCatagory;

public class ProductCategoryRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getMySQLJdbcTemplate();
	
	public static ProductCatagory getProductCatagoryByCategoryId(String categoryId) {
		String query = "SELECT * FROM PRODUCT_CATEGORY WHERE category_id="+categoryId;
		ResultSet productCategoryInDb = null;
		try{
			productCategoryInDb = jdbcTemplate.executeQuery(query);
			while(productCategoryInDb.next()){
				return new ProductCatagory(productCategoryInDb.getInt("category_id"), productCategoryInDb.getString("category_name"), 
						productCategoryInDb.getString("category_description"));
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
		return null;
	}
	
}

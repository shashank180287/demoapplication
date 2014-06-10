package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.entity.ProductSupply;

public class ProductSupplyRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getMySQLJdbcTemplate();

	public static void addProductSupply(ProductSupply productSupply) {
		String query = "INSERT INTO PRODUCT_SUPPLY VALUES ('"+productSupply.getProduct().getProductCode()+System.currentTimeMillis()+"','"+productSupply.getProduct().getProductCode()+"',"
				+productSupply.getTotalItems() + productSupply.getTotalSupplied() + productSupply.getCurrentStock() +productSupply.getCurrentItemCount()+")";
		jdbcTemplate.executeUpdate(query);
	}

	public static void updateProductSupply(ProductSupply productSupply) {
		if(productSupply.getSupplyCode()==null)
			throw new IllegalArgumentException("Supply code can not be null");

		String query = "UPDATE PRODUCT_SUPPLY SET total_items="+productSupply.getTotalItems()+",total_supplied="
				+productSupply.getTotalSupplied()+", current_stock="+productSupply.getCurrentStock()+",current_item_count="
				+productSupply.getCurrentItemCount()+" WHERE supply_code='"+productSupply.getSupplyCode()+"'";
		jdbcTemplate.executeUpdate(query);
	}

	public static void removeProductSupply(ProductSupply productSupply) {
		if(productSupply.getSupplyCode()==null)
			throw new IllegalArgumentException("Supply code can not be null");

		String query = "DELETE PRODUCT_SUPPLY WHERE supply_code='"+productSupply.getSupplyCode()+"'";
		jdbcTemplate.executeUpdate(query);	
	}

	public static List<ProductSupply> getProductSupplyByQuery(String query) {
		List<ProductSupply> productSupply = new ArrayList<ProductSupply>();
		ResultSet productSupplyInDb = null;
		try{
			productSupplyInDb = jdbcTemplate.executeQuery(query);
			while(productSupplyInDb.next()){
				String productCode = productSupplyInDb.getString("product_code");
				ProductRecord productRecord = ProductRecordRepository.getProductRecordByCode(productCode);
				productSupply.add(new ProductSupply(productSupplyInDb.getString("supply_code"), productRecord, 
						productSupplyInDb.getInt("total_items"),
						productSupplyInDb.getInt("total_supplied"),
						productSupplyInDb.getInt("current_stock"),
						productSupplyInDb.getInt("current_item_count")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(productSupplyInDb!=null)
				try {
					productSupplyInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return productSupply;
	}

	public static List<ProductSupply> getAllProductSupply() {
		String query = "SELECT * FROM PRODUCT_SUPPLY";
		return getProductSupplyByQuery(query);
	}

	public static ProductSupply getProductSupplyByProductCode(String productCode) {
		List<ProductSupply> productSupplyList = getProductSupplyByQuery("SELECT * FROM PRODUCT_SUPPLY WHERE product_code='"+productCode+"'");
		return productSupplyList.size()>0?productSupplyList.get(0):null;
	}
}

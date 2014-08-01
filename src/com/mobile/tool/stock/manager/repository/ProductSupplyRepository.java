package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.ProductSupply;

public class ProductSupplyRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getDerbyJdbcTemplate();

	public static ProductSupply addProductSupply(ProductSupply productSupply) {
		String supplyCode = getProductSupplyCode(productSupply);
		String query = "INSERT INTO PRODUCT_SUPPLY VALUES ('"
				+ supplyCode + "','"
				+ productSupply.getSupplyDate()+"','"
				+ productSupply.getSupplyDetails() + "','"
				+ productSupply.getSupplier() + "')";
		jdbcTemplate.executeUpdate(query);
		productSupply.setSupplyCode(supplyCode);
		return productSupply;
	}

	private static String getProductSupplyCode(ProductSupply productSupply) {
		return (productSupply.getSupplier() != null ? (productSupply
				.getSupplier().length() > 2 ? productSupply.getSupplier()
				.substring(0, 3).toUpperCase() : productSupply.getSupplier()
				.toUpperCase()) : "ABC")
				+ System.currentTimeMillis();
	}

	public static List<ProductSupply> getProductSupplyByQuery(String query) {
		List<ProductSupply> productSupply = new ArrayList<ProductSupply>();
		ResultSet productSupplyInDb = null;
		try {
			productSupplyInDb = jdbcTemplate.executeQuery(query);
			while (productSupplyInDb.next()) {
				ProductSupply prSupply = new ProductSupply(productSupplyInDb.getString("supply_code"),
						productSupplyInDb.getDate("supply_date"),
						productSupplyInDb.getString("supply_description"),
						productSupplyInDb.getString("supplier"));
				prSupply.setProductSupplyDetails(ProductSupplyDetailsRepository.getProductSupplyDetailsForParticularSupply(prSupply));
				productSupply.add(prSupply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (productSupplyInDb != null)
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

	public static ProductSupply getProductSupplyBySupplyCode(String supplyCode) {
		String query = "SELECT * FROM PRODUCT_SUPPLY where supply_code='"+supplyCode+"'";
		List<ProductSupply> productSupplies =  getProductSupplyByQuery(query);
		return (productSupplies!=null && productSupplies.size()>0)?productSupplies.get(0):null;
	}
}

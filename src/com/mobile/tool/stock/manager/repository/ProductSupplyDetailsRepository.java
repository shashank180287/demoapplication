package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.entity.ProductSupply;
import com.mobile.tool.stock.manager.entity.ProductSupplyDetails;

public class ProductSupplyDetailsRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getDerbyJdbcTemplate();

	private static int getCurrentIdCursor() {
		String query = "SELECT MAX(product_supply_detail_id) FROM PRODUCT_SUPPLY_DETAIL";
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

	public static void addProductSupplyDetailsList(
			List<ProductSupplyDetails> productSupplyDetailsList) {
		int lastProductSupplyDetailsId = getCurrentIdCursor();
		for (ProductSupplyDetails productSupplyDetails : productSupplyDetailsList) {
			String query = "INSERT INTO PRODUCT_SUPPLY_DETAIL VALUES ("
					+ (++lastProductSupplyDetailsId) + ",'"
					+ productSupplyDetails.getProductSupply().getSupplyCode()+ "','"
					+ productSupplyDetails.getProduct().getProductCode() + "',"
					+ productSupplyDetails.getTotalItems() + ","
					+ productSupplyDetails.getTotalSupplied() + ","
					+ productSupplyDetails.getCurrentStock() + ","
					+ productSupplyDetails.getCurrentItemCount() + ")";
			jdbcTemplate.executeUpdate(query);
		}
	}

	public static List<ProductSupplyDetails> getProductSupplyDetailsByQuery(String query) {
		List<ProductSupplyDetails> productSupplyDetails = new ArrayList<ProductSupplyDetails>();
		ResultSet productSupplyDetailsInDb = null;
		try{
			productSupplyDetailsInDb = jdbcTemplate.executeQuery(query);
			while(productSupplyDetailsInDb.next()){
				String productCode = productSupplyDetailsInDb.getString("product_code");
				ProductRecord productRecord = ProductRecordRepository.getProductRecordByCode(productCode);
				String supplyCode = productSupplyDetailsInDb.getString("supply_code");
				ProductSupply productSupply = ProductSupplyRepository.getProductSupplyBySupplyCode(supplyCode);
				productSupplyDetails.add(new ProductSupplyDetails(productSupplyDetailsInDb.getLong("product_supply_detail_id"), 
						productSupply,
						productRecord,
						productSupplyDetailsInDb.getInt("total_items"),
						productSupplyDetailsInDb.getInt("total_supplied"),
						productSupplyDetailsInDb.getInt("current_stock"),
						productSupplyDetailsInDb.getInt("current_item_count")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(productSupplyDetailsInDb!=null)
				try {
					productSupplyDetailsInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return productSupplyDetails;
	}

	public static List<ProductSupplyDetails> getProductSupplyDetailsForParticularSupply(ProductSupply productSupply) {
		List<ProductSupplyDetails> productSupplyDetails = new ArrayList<ProductSupplyDetails>();
		ResultSet productSupplyDetailsInDb = null;
		try{
			String query = "SELECT * FROM PRODUCT_SUPPLY_DETAIL where supply_code='"+productSupply.getSupplyCode()+"'";
			productSupplyDetailsInDb = jdbcTemplate.executeQuery(query );
			while(productSupplyDetailsInDb.next()){
				String productCode = productSupplyDetailsInDb.getString("product_code");
				ProductRecord productRecord = ProductRecordRepository.getProductRecordByCode(productCode);
				productSupplyDetails.add(new ProductSupplyDetails(productSupplyDetailsInDb.getLong("product_supply_detail_id"), 
						productSupply,
						productRecord,
						productSupplyDetailsInDb.getInt("total_items"),
						productSupplyDetailsInDb.getInt("total_supplied"),
						productSupplyDetailsInDb.getInt("current_stock"),
						productSupplyDetailsInDb.getInt("current_item_count")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(productSupplyDetailsInDb!=null)
				try {
					productSupplyDetailsInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return productSupplyDetails;
	}
	
	public static List<ProductSupplyDetails> getAllProductSupplyDetails() {
		String query = "SELECT * FROM PRODUCT_SUPPLY_DETAIL";
		return getProductSupplyDetailsByQuery(query);
	}

	public static ProductSupplyDetails getLastProductSupplyByProductCode(
			String productCode) {
		List<ProductSupplyDetails> productSupplyList = getProductSupplyDetailsByQuery("SELECT * FROM PRODUCT_SUPPLY_DETAIL WHERE product_code='"
				+ productCode + "' order by product_supply_detail_id desc");
		return productSupplyList.size() > 0 ? productSupplyList.get(0) : null;
	}

	public static List<ProductSupplyDetails> getProductSupplyBySupplyCode(
			String supplyCode) {
		return getProductSupplyDetailsByQuery("SELECT * FROM PRODUCT_SUPPLY_DETAIL WHERE supply_code='"
				+ supplyCode + "'");
	}
	
	public static void updateProductSupplyDetails(
			ProductSupplyDetails productSupplyDetails) {
		if(productSupplyDetails.getId()==0)
			throw new IllegalArgumentException("Supply Details Id can not be 0");

		String query = "UPDATE PRODUCT_SUPPLY_DETAIL SET total_items="+productSupplyDetails.getTotalItems()+",total_supplied="
				+productSupplyDetails.getTotalSupplied()+", current_stock="+productSupplyDetails.getCurrentStock()+",current_item_count="
				+productSupplyDetails.getCurrentItemCount()+" WHERE product_supply_detail_id="+productSupplyDetails.getId();
		jdbcTemplate.executeUpdate(query);
		
	}

	public static Map<String, Double> getProductLiabilities() {
		Map<String, Double> liabilities = new HashMap<>();
		ResultSet productSupplyInDb = null;
		try{
			productSupplyInDb = jdbcTemplate.executeQuery("Select ps.product_code, sum(pr.bulk_price*ps.currentItemCount) from PRODUCT_SUPPLY_DETAIL ps"+
									" INNER JOIN product_record pr on pr.product_code=ps.product_code where product_supply_detail_id=(select MAX(psd.product_supply_detail_id) from PRODUCT_SUPPLY_DETAIL psd where psd.product_code=ps.product_code) group by ps.product_code");
			while(productSupplyInDb.next()){
				liabilities.put(productSupplyInDb.getString("product_code"), productSupplyInDb.getDouble("2"));
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
		return liabilities;
	}

	public static Map<String, Integer> getCurrentStockByProduct() {
		Map<String, Integer> currentStock = new HashMap<>();
		ResultSet productSupplyInDb = null;
		try{
			productSupplyInDb = jdbcTemplate.executeQuery("Select pr.name, ps.current_item_count from PRODUCT_SUPPLY_DETAIL ps"+
									" INNER JOIN product_record pr on pr.product_code=ps.product_code where product_supply_detail_id=(select MAX(psd.product_supply_detail_id) from PRODUCT_SUPPLY_DETAIL psd where psd.product_code=ps.product_code) group by  pr.name, ps.current_item_count");
			while(productSupplyInDb.next()){
				currentStock.put(productSupplyInDb.getString("name"), productSupplyInDb.getInt("current_item_count"));
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
		return currentStock;
	}
	
}

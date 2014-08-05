package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.OwerDetails;

public class OwerDetailsRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getMySQLJdbcTemplate();

	private static int getCurrentIdCursor() {
		String query = "SELECT MAX(id) FROM OWER_DETAILS";
		ResultSet maxId=null;
		try {
			maxId = jdbcTemplate.executeQuery(query);
			while(maxId.next())
					return maxId.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally{
			if(maxId!=null)
				try {
					maxId.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return 0;
	}
	
	public static void addOwerDetails(OwerDetails owerDetails) {
		String query = "INSERT INTO OWER_DETAILS VALUES ("+(getCurrentIdCursor()+1)+",'"
				+ owerDetails.getSaleCode() + "','"
				+ owerDetails.getCustomerName()+ "',"
				+ owerDetails.getTotalAmount() + ","
				+ owerDetails.getAmountPaid() + ","
				+ owerDetails.getAmountOwe() + ",'"
				+ owerDetails.getPurchaseDate() + "')";
		jdbcTemplate.executeUpdate(query);
	}

	public static List<OwerDetails> getOwerDetailsByQuery(String query) {
		List<OwerDetails> owerDetailsList = new ArrayList<OwerDetails>();
		ResultSet owerDetailsInDb = null;
		try {
			owerDetailsInDb = jdbcTemplate.executeQuery(query);
			if (owerDetailsInDb != null) {
				while (owerDetailsInDb.next()) {
					owerDetailsList.add(new OwerDetails(owerDetailsInDb
							.getLong("id"), owerDetailsInDb
							.getString("sale_code"), owerDetailsInDb
							.getString("customer_name"), owerDetailsInDb
							.getFloat("total_amount"), owerDetailsInDb
							.getFloat("amount_paid"), owerDetailsInDb
							.getFloat("amount_owe"), owerDetailsInDb
							.getDate("purchase_date")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (owerDetailsInDb != null)
				try {
					owerDetailsInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return owerDetailsList;
	}

	public static List<OwerDetails> getAllOwerDetails() {
//		return getOwerDetailsByQuery("SELECT * FROM OWER_DETAILS ORDER BY purchase_date desc");
		return new ArrayList<OwerDetails>();
	}

	public static void updateOwerDetails(OwerDetails owerDetails) {
		verifyOwerDetailsId(owerDetails.getId());
		String query = "UPDATE OWER_DETAILS SET sale_code='" + owerDetails.getSaleCode() 
				+ "', customer_name='" + owerDetails.getCustomerName()
				+ "', total_amount=" + owerDetails.getTotalAmount()
				+ ", amount_paid=" + owerDetails.getAmountPaid()
				+ ", amount_owe=" + owerDetails.getAmountOwe()
				+ ", purchase_date='" + owerDetails.getPurchaseDate()
				+ "' WHERE id="+owerDetails.getId();
		jdbcTemplate.executeUpdate(query);
	}

	public static void removeOwerDetails(long owerDetailsId) {
		verifyOwerDetailsId(owerDetailsId);
		String query = "DELETE FROM OWER_DETAILS WHERE id="+owerDetailsId;
		jdbcTemplate.executeUpdate(query);
	}

	private static void verifyOwerDetailsId(long owerDetailsId) {
		if(owerDetailsId<=0)
			throw new IllegalArgumentException("Please pass proper id");
	}
}

package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.VisitorEnquiryDetail;

public class VisitorEnquiryDetailRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getDerbyJdbcTemplate();

	private static int getCurrentIdCursor() {
		String query = "SELECT MAX(id) FROM VISITOR_ENQUIRY_INFO";
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

	public static void addVisitorEnquiryDetail(VisitorEnquiryDetail visitorEnquiryDetail) {
		String query = "INSERT INTO VISITOR_ENQUIRY_INFO VALUES ("
				+(getCurrentIdCursor()+1)+",'"
				+ visitorEnquiryDetail.getName()+ "',"
				+ visitorEnquiryDetail.getMobileNumber() + ",'"
				+ visitorEnquiryDetail.getAddress() + "','"
				+ visitorEnquiryDetail.getVisitPurpose() + "','"
				+ visitorEnquiryDetail.getDate() + "','"
				+ visitorEnquiryDetail.getComment() + "'"
				+ ")";
		jdbcTemplate.executeUpdate(query);
	}

	public static List<VisitorEnquiryDetail> getVisitorEnquiryDetailByQuery(String query) {
		List<VisitorEnquiryDetail> visitorEnquiryDetails = new ArrayList<VisitorEnquiryDetail>();
		ResultSet visitorEnquiryDetailInDb = null;
		try{
			visitorEnquiryDetailInDb = jdbcTemplate.executeQuery(query);
			while(visitorEnquiryDetailInDb.next()){
				visitorEnquiryDetails.add(new VisitorEnquiryDetail(
						visitorEnquiryDetailInDb.getInt("id"), 
						visitorEnquiryDetailInDb.getString("name"),
						visitorEnquiryDetailInDb.getLong("mobile_number"), 
						visitorEnquiryDetailInDb.getString("address"), 
						visitorEnquiryDetailInDb.getString("visit_purpose"), 
						visitorEnquiryDetailInDb.getDate("date"), 
						visitorEnquiryDetailInDb.getString("comment"))
				);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(visitorEnquiryDetailInDb!=null)
				try {
					visitorEnquiryDetailInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return visitorEnquiryDetails;
	}

	public static List<VisitorEnquiryDetail> getVisitorEnquiryDetailByVisitorMobileNo(long mobileNumber) {
		String query = "SELECT * FROM VISITOR_ENQUIRY_INFO WHERE mobile_number="+mobileNumber;
		return getVisitorEnquiryDetailByQuery(query);

	}

	public static List<VisitorEnquiryDetail> getAllVisitorEnquiryDetails() {
		return getVisitorEnquiryDetailByQuery("SELECT * FROM VISITOR_ENQUIRY_INFO ORDER BY id desc");
	}
}

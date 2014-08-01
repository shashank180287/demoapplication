package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.RecordKeeping;

public class RecordKeepingRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getDerbyJdbcTemplate();
	
	private static int getCurrentIdCursor() {
		String query = "SELECT MAX(record_id) FROM RECORD_KEEPING";
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
	
	public static void addRecordKeeping(RecordKeeping recordKeeping) {
		String query = "INSERT INTO RECORD_KEEPING VALUES ("+(getCurrentIdCursor()+1)+",'"
				+ recordKeeping.getType() + "','"
				+ recordKeeping.getTag() + "','"
				+ recordKeeping.getComment() + "')";
		jdbcTemplate.executeUpdate(query);
	}
	
	public static List<RecordKeeping> getRecordKeepingByQuery(String query) {
		List<RecordKeeping> recordKeeping = new ArrayList<RecordKeeping>();
		ResultSet recordKeepingInDb = null;
		try{
			recordKeepingInDb = jdbcTemplate.executeQuery(query);
			while(recordKeepingInDb.next()){
				recordKeeping.add(new RecordKeeping(recordKeepingInDb.getInt("record_id"), recordKeepingInDb.getString("type"), 
						recordKeepingInDb.getString("tag"), recordKeepingInDb.getString("comment")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(recordKeepingInDb!=null)
				try {
					recordKeepingInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return recordKeeping;
	}
	
	public static List<RecordKeeping> getAllRecordKeeping() {
		return getRecordKeepingByQuery("SELECT * FROM RECORD_KEEPING ORDER BY RECORD_ID desc");
	}

}

package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.UserAccessHistory;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;

public class UserAccessHistoryRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getDerbyJdbcTemplate();
	
	public static int getCurrentIdCursor() {
		String query = "SELECT MAX(id) FROM USER_ACCESS_HISTORY";
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

	public static void addUserAccessHistory(UserAccessHistory userAccessHistory) {
		String query = "INSERT INTO USER_ACCESS_HISTORY VALUES ("+getCurrentIdCursor()+1+"'"+userAccessHistory.getUser().getUsername()+",'"+userAccessHistory.getStartTime()+"','"+userAccessHistory.getEndTime()+"')";
		jdbcTemplate.executeUpdate(query);
	}

	public static void updateUserAccessHistory(UserAccessHistory userAccessHistory) {
		if(userAccessHistory.getAccessId()==0)
			throw new IllegalArgumentException("access id can not be null");
		
		String query = "UPDATE USER_ACCESS_HISTORY SET start_time='"+userAccessHistory.getStartTime()+"',end_time='"
				+userAccessHistory.getEndTime()+"' WHERE id="+userAccessHistory.getAccessId();
		jdbcTemplate.executeUpdate(query);
	}

	public static void removeUserAccessHistory(UserAccessHistory userAccessHistory) {
		if(userAccessHistory.getAccessId()==0)
			throw new IllegalArgumentException("access id can not be null");
		
		String query = "DELETE USER_ACCESS_HISTORY WHERE id="+userAccessHistory.getAccessId();
		jdbcTemplate.executeUpdate(query);	
	}
	
	public static List<UserAccessHistory> getUserAccessHistory() {
		String query = "SELECT * FROM USER_ACCESS_HISTORY ORDER BY id desc";
		List<UserAccessHistory> userAccessHistory = new ArrayList<UserAccessHistory>();
		ResultSet userAccessHistoryInDb = null;
		try{
			userAccessHistoryInDb = jdbcTemplate.executeQuery(query);
			while(userAccessHistoryInDb.next()){
				String username = userAccessHistoryInDb.getString("username");
				UserLoginDetails userLoginDetails = UserLoginDetailsRepository.getUserLoginDetailsByUsername(username);
				userAccessHistory.add(new UserAccessHistory(userAccessHistoryInDb.getInt("id"), userLoginDetails, userAccessHistoryInDb.getDate("start_time"), userAccessHistoryInDb.getDate("end_time")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(userAccessHistoryInDb!=null)
				try {
					userAccessHistoryInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return userAccessHistory;
	}
	
	
}

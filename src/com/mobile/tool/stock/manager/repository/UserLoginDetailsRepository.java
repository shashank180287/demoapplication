package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.entity.UserRole;

public class UserLoginDetailsRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getMySQLJdbcTemplate();
	
	public static void addUserLoginDetails(UserLoginDetails userLoginDetails) {
		String query = "INSERT INTO USER_LOGIN_DETAILS VALUES ('"+userLoginDetails.getUsername()+"','"+userLoginDetails.getPassword()+"',"+userLoginDetails.getUserRole().getRoleId()+")";
		jdbcTemplate.executeUpdate(query);
	}

	public static void updateUserLoginDetails(UserLoginDetails userLoginDetails) {
		if(userLoginDetails.getUsername()==null)
			throw new IllegalArgumentException("username can not be null");
		
		String query = "UPDATE USER_LOGIN_DETAILS SET password='"+userLoginDetails.getPassword()+"',userRole='"
				+userLoginDetails.getUserRole().getRoleId()+"' WHERE username="+userLoginDetails.getUsername();
		jdbcTemplate.executeUpdate(query);
	}

	public static void removeUserLoginDetails(UserLoginDetails userLoginDetails) {
		if(userLoginDetails.getUsername()==null)
			throw new IllegalArgumentException("Username can not be null");
		
		String query = "DELETE USER_LOGIN_DETAILS WHERE username="+userLoginDetails.getUsername();
		jdbcTemplate.executeUpdate(query);	
	}
	
	public static List<UserLoginDetails> getUserLoginDetails() {
		String query = "SELECT * FROM USER_LOGIN_DETAILS ORDER BY username desc";
		List<UserLoginDetails> userLoginDetails = new ArrayList<UserLoginDetails>();
		ResultSet userLoginDetailsInDb = null;
		try{
			userLoginDetailsInDb = jdbcTemplate.executeQuery(query);
			while(userLoginDetailsInDb.next()){
				int roleId = userLoginDetailsInDb.getInt("role_id");
				UserRole userRole = UserRoleRepository.getUserRolesByRoleId(roleId);
				userLoginDetails.add(new UserLoginDetails(userLoginDetailsInDb.getString("username"), userLoginDetailsInDb.getString("password"), userRole));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(userLoginDetailsInDb!=null)
				try {
					userLoginDetailsInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return userLoginDetails;
	}

	public static UserLoginDetails getUserLoginDetailsByUsername(String username) {
		String query = "SELECT * FROM USER_LOGIN_DETAILS WHERE username='"+username+"'";
		ResultSet userLoginDetailsInDb = null;
		try{
			userLoginDetailsInDb = jdbcTemplate.executeQuery(query);
			while(userLoginDetailsInDb.next()){
				int roleId = userLoginDetailsInDb.getInt("role_id");
				UserRole userRole = UserRoleRepository.getUserRolesByRoleId(roleId);
				return new UserLoginDetails(userLoginDetailsInDb.getString("username"), userLoginDetailsInDb.getString("password"), userRole);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(userLoginDetailsInDb!=null)
				try {
					userLoginDetailsInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
	
}
